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
package ar.com.blox.bloxsys.controller.choferes;

import ar.com.blox.bloxsys.domain.Chofer;
import ar.com.blox.bloxsys.domain.Usuario;
import ar.com.blox.bloxsys.eao.ChoferesFacade;
import ar.com.blox.bloxsys.eao.UsuariosFacade;
import ar.com.blox.bloxsys.search.UsuariosSearchFilter;
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
@ManagedBean(name = "choferesEditBean")
@ViewScoped
public class ChoferesEditBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @EJB
    private ChoferesFacade choferesFacade;

    private Chofer choferActual;

    @EJB
    private UsuariosFacade usuariosFacade;

    private List<Usuario> usuarios = null;

    private boolean nuevo = false;

    private static final Logger LOG = Logger.getLogger(ChoferesEditBean.class.getName());

    /**
     * Creates a new instance of UsuariosEditBean
     */
    public ChoferesEditBean() {
    }

    @PostConstruct
    private void init() {

        String idChofer = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if (idChofer == null) {
            nuevoChofer();
        } else {
            choferActual = choferesFacade.find(Long.parseLong(idChofer));
            nuevo = false;
            if (choferActual == null) {
                LOG.log(Level.SEVERE, "Chofer inexistente! {0}", idChofer);
                throw new IllegalArgumentException(String.format("Chofer inexistente: %s", idChofer));
            }
        }

    }

    private void nuevoChofer() {
        choferActual = new Chofer();
        choferActual.setActivo(true);
        nuevo = true;
    }

    public Chofer getChoferActual() {
        return choferActual;
    }

    public void setChoferActual(Chofer choferActual) {
        this.choferActual = choferActual;
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    public void doGuardarChofer() {
        if (!validarChofer()) {
            return;
        }
        try {

            choferActual = choferesFacade.createOrEdit(choferActual);
            JSFUtil.getInstance().addInfoMessage(String.format("Chofer guardado exitosamente: %s", choferActual.getId()));
            nuevo = false;

        } catch (Exception ex) {
            JSFUtil.getInstance().addErrorMessage(String.format("Error al guardar: %s", ex.getMessage()));

        }

    }

    public void doEliminarChofer() {

        try {
            choferesFacade.remove(choferActual);
            choferActual = null;
            JSFUtil.getInstance().addInfoMessage("Chofer eliminado exitosamente!");

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            JSFUtil.getInstance().addErrorMessage("Error al borrar!");

        }
    }

    private boolean validarChofer() {
        boolean validacionCorrecta = true;
        if (choferActual == null) {
            validacionCorrecta = false;
            JSFUtil.getInstance().addErrorMessage("No se permite guardar el usuario en el estado actual");
        }
//TODO: validar el CUIL por ejemplo...
        return validacionCorrecta;
    }

    /**
     * Obtiene la lista de usuarios activos
     *
     * @return
     */
    public List<Usuario> getUsuarios() {
        if (usuarios == null) {
            usuarios = new ArrayList<>();
            UsuariosSearchFilter usf = new UsuariosSearchFilter();
            usf.setActivo(true);
            usuarios.addAll(usuariosFacade.findAllBySearchFilter(usf));
        }
        return usuarios;
    }

}
