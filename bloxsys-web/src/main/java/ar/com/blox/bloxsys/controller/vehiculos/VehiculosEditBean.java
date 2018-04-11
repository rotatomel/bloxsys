/*
 * Copyright 2014 GTSoftware
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ar.com.blox.bloxsys.controller.vehiculos;

import ar.com.blox.bloxsys.domain.Vehiculo;
import ar.com.blox.bloxsys.domain.VehiculoTipo;
import ar.com.blox.bloxsys.eao.VehiculosFacade;
import ar.com.blox.bloxsys.eao.VehiculosTiposFacade;
import ar.com.blox.bloxsys.search.BasicTextSearchFilter;
import ar.com.blox.bloxsys.utils.JSFUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador para el caso de uso de edición de usuarios
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 * @version 1.0.0
 * @since 2.0.1
 */
@ManagedBean(name = "vehiculosEditBean")
@ViewScoped
public class VehiculosEditBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @EJB
    private VehiculosFacade vehiculosFacade;


    @EJB
    private VehiculosTiposFacade tiposFacade;

    private List<VehiculoTipo> tipos = null;

    private Vehiculo vehiculoActual;

    private boolean nuevo = false;

    private static final Logger LOG = Logger.getLogger(VehiculosEditBean.class.getName());

    /**
     * Creates a new instance of UsuariosEditBean
     */
    public VehiculosEditBean() {
    }

    @PostConstruct
    private void init() {

        String idVehiculo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if (idVehiculo == null) {
            nuevoVehiculo();
        } else {
            vehiculoActual = vehiculosFacade.find(Long.parseLong(idVehiculo));
            nuevo = false;
            if (vehiculoActual == null) {
                LOG.log(Level.SEVERE, "Vehiculo inexistente! {0}", idVehiculo);
                throw new IllegalArgumentException(String.format("Vehiculo inexistente: %s", idVehiculo));
            }
        }

    }

    private void nuevoVehiculo() {
        vehiculoActual = new Vehiculo();
        vehiculoActual.setActivo(true);
        nuevo = true;
    }

    public Vehiculo getVehiculoActual() {
        return vehiculoActual;
    }

    public void setVehiculoActual(Vehiculo vehiculoActual) {
        this.vehiculoActual = vehiculoActual;
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    public void doGuardarVehiculo() {
        if (!validar()) {
            return;
        }
        try {

            vehiculoActual = vehiculosFacade.createOrEdit(vehiculoActual);
            JSFUtil.getInstance().addInfoMessage(String.format("Vehículo guardado exitosamente: %s", vehiculoActual.getId()));
            nuevo = false;
        } catch (Exception ex) {
            JSFUtil.getInstance().addErrorMessage(String.format("Error al guardar: %s", ex.getMessage()));

        }

    }

    public void doEliminarVehiculo() {

        try {
            vehiculosFacade.remove(vehiculoActual);
            vehiculoActual = null;

            JSFUtil.getInstance().addInfoMessage("Vehículo eliminado exitosamente!");

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            JSFUtil.getInstance().addErrorMessage("Error al borrar!");

        }
    }

    private boolean validar() {
        boolean validacionCorrecta = true;
        if (vehiculoActual == null) {
            validacionCorrecta = false;
            JSFUtil.getInstance().addErrorMessage("No se permite guardar el usuario en el estado actual");
        }

        return validacionCorrecta;
    }

    /**
     * La lista de posibles tipos de vehículos
     *
     * @return
     */
    public List<VehiculoTipo> getTipos() {
        if (tipos == null) {
            tipos = new ArrayList<>();
            BasicTextSearchFilter sf = new BasicTextSearchFilter();
            sf.addSortField("nombreTipo", true);
            tipos.addAll(tiposFacade.findAllBySearchFilter(sf));
        }
        return tipos;
    }
}
