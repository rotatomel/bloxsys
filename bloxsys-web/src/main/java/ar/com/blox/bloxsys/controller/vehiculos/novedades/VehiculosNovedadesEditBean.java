/**
 * Copyright 2018
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ar.com.blox.bloxsys.controller.vehiculos.novedades;

import ar.com.blox.bloxsys.auth.AuthBackingBean;
import ar.com.blox.bloxsys.domain.Vehiculo;
import ar.com.blox.bloxsys.domain.VehiculoNovedad;
import ar.com.blox.bloxsys.domain.VehiculoTiposNovedadEnum;
import ar.com.blox.bloxsys.eao.VehiculosFacade;
import ar.com.blox.bloxsys.eao.VehiculosNovedadesFacade;
import ar.com.blox.bloxsys.search.VehiculosSearchFilter;
import ar.com.blox.bloxsys.utils.JSFUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador para el caso de uso de edición de novedades
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 * @version 1.0.0
 */
@ManagedBean(name = "vehiculosNovedadesEditBean")
@ViewScoped
public class VehiculosNovedadesEditBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @EJB
    private VehiculosNovedadesFacade vehiculosNovedadesFacade;

    @EJB
    private VehiculosFacade vehiculosFacade;

    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    private VehiculoNovedad novedadActual;

    private List<Vehiculo> vehiculos = null;

    private boolean nuevo = false;

    private static final Logger LOG = Logger.getLogger(VehiculosNovedadesEditBean.class.getName());

    /**
     * Creates a new instance of UsuariosEditBean
     */
    public VehiculosNovedadesEditBean() {
    }

    @PostConstruct
    private void init() {

        String idNovedad = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if (idNovedad == null) {
            nuevoVehiculo();
        } else {
            novedadActual = vehiculosNovedadesFacade.find(Long.parseLong(idNovedad));
            nuevo = false;
            if (novedadActual == null) {
                LOG.log(Level.SEVERE, "Novedad inexistente! {0}", idNovedad);
                throw new IllegalArgumentException(String.format("Novedad inexistente: %s", idNovedad));
            }
        }

    }

    private void nuevoVehiculo() {
        novedadActual = new VehiculoNovedad();
        novedadActual.setIdUsuario(authBackingBean.getUserLoggedIn());
        nuevo = true;
    }

    public VehiculoNovedad getNovedadActual() {
        return novedadActual;
    }

    public void setNovedadActual(VehiculoNovedad novedadActual) {
        this.novedadActual = novedadActual;
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    public void doGuardar() {
        if (nuevo) {
            novedadActual.setFechaNovedad(new Date());
        }
        try {

            vehiculosNovedadesFacade.createOrEdit(novedadActual);
            JSFUtil.getInstance().addInfoMessage(String.format("Novedad guardada exitosamente: %s", novedadActual.getId()));

        } catch (Exception ex) {
            JSFUtil.getInstance().addErrorMessage(String.format("Error al guardar: %s", ex.getMessage()));

        }

    }

    public void doEliminar() {

        try {
            vehiculosNovedadesFacade.remove(novedadActual);
            novedadActual = null;
            JSFUtil.getInstance().addInfoMessage("Novedad eliminada exitosamente!");

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            JSFUtil.getInstance().addErrorMessage("Error al borrar!");

        }
    }

    public AuthBackingBean getAuthBackingBean() {
        return authBackingBean;
    }

    public void setAuthBackingBean(AuthBackingBean authBackingBean) {
        this.authBackingBean = authBackingBean;
    }

    /**
     * Obtiene la lista de vehículos activos
     *
     * @return
     */
    public List<Vehiculo> getVehiculos() {
        if (vehiculos == null) {
            vehiculos = new ArrayList<>();
            VehiculosSearchFilter vsf = new VehiculosSearchFilter();
            vsf.setActivo(true);
            vehiculos.addAll(vehiculosFacade.findAllBySearchFilter(vsf));
        }
        return vehiculos;
    }

    public VehiculoTiposNovedadEnum[] getTiposNovedad() {
        return VehiculoTiposNovedadEnum.values();
    }
}
