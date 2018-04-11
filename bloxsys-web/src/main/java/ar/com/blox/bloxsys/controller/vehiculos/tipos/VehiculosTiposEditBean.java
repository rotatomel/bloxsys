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
package ar.com.blox.bloxsys.controller.vehiculos.tipos;

import ar.com.blox.bloxsys.domain.VehiculoTipo;
import ar.com.blox.bloxsys.eao.VehiculosTiposFacade;
import ar.com.blox.bloxsys.utils.JSFUtil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador para el caso de uso de edición de tipos de vehiculos
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 * @version 1.0.0
 * @since 2.0.1
 */
@ManagedBean(name = "vehiculosTiposEditBean")
@ViewScoped
public class VehiculosTiposEditBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(VehiculosTiposEditBean.class.getName());
    @EJB
    private VehiculosTiposFacade vehiculosTiposFacade;
    private VehiculoTipo tipoActual;
    private boolean nuevo = false;

    /**
     * Creates a new instance of VehiculosTiposEditBean
     */
    public VehiculosTiposEditBean() {
    }

    @PostConstruct
    private void init() {

        String idTipo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if (idTipo == null) {
            nuevo();
        } else {
            tipoActual = vehiculosTiposFacade.find(Long.parseLong(idTipo));
            nuevo = false;
            if (tipoActual == null) {
                LOG.log(Level.SEVERE, "Tipo inexistente! {0}", idTipo);
                throw new IllegalArgumentException(String.format("Tipo inexistente: %s", idTipo));
            }
        }

    }

    private void nuevo() {
        tipoActual = new VehiculoTipo();

        nuevo = true;
    }

    public VehiculoTipo getTipoActual() {
        return tipoActual;
    }

    public void setTipoActual(VehiculoTipo tipoActual) {
        this.tipoActual = tipoActual;
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    public void doGuardar() {
        if (!validar()) {
            return;
        }
        try {

            tipoActual = vehiculosTiposFacade.createOrEdit(tipoActual);
            JSFUtil.getInstance().addInfoMessage(String.format("Tipo guardado exitosamente: %s", tipoActual.getId()));
            nuevo = false;
        } catch (Exception ex) {
            JSFUtil.getInstance().addErrorMessage(String.format("Error al guardar: %s", ex.getMessage()));

        }

    }

    private boolean validar() {
        boolean validacionCorrecta = true;
        if (tipoActual == null) {
            validacionCorrecta = false;
            JSFUtil.getInstance().addErrorMessage("No se permite guardar el usuario en el estado actual");
        }

        return validacionCorrecta;
    }

    public void doEliminar() {

        try {
            vehiculosTiposFacade.remove(tipoActual);
            tipoActual = null;
            JSFUtil.getInstance().addInfoMessage("Tipo eliminado exitosamente!");

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            JSFUtil.getInstance().addErrorMessage("Error al borrar!");

        }
    }

}
