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
package ar.com.blox.bloxsys.controller.usuarios;

import ar.com.blox.bloxsys.domain.Usuario;
import ar.com.blox.bloxsys.eao.UsuariosFacade;
import ar.com.blox.bloxsys.utils.JSFUtil;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador para el caso de uso de edición de usuarios
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 * @version 1.0.0
 * @since 2.0.1
 */
@ManagedBean(name = "usuariosEditBean")
@ViewScoped
public class UsuariosEditBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @EJB
    private UsuariosFacade usuarioFacade;

    private Usuario usuarioActual;

    private boolean nuevo = false;

    private String passwordRepeat;

    private String newPassword;

    private static final Logger LOG = Logger.getLogger(UsuariosEditBean.class.getName());

    private static final String RESERVED_USERNAME = "admin";

    /**
     * Creates a new instance of UsuariosEditBean
     */
    public UsuariosEditBean() {
    }

    @PostConstruct
    private void init() {

        String logIn = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("login");
        if (logIn == null) {
            nuevoUsuario();
        } else {
            usuarioActual = usuarioFacade.find(Long.parseLong(logIn));
            nuevo = false;
            if (usuarioActual == null) {
                LOG.log(Level.SEVERE, "Usuario inexistente! {0}", logIn);
                throw new IllegalArgumentException(String.format("Usuario inexistente: %s", logIn));
            }
        }

    }

    private void nuevoUsuario() {
        usuarioActual = new Usuario();
        usuarioActual.setActivo(true);
        nuevo = true;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    public void doGuardarUsuario() {
        if (!validarUsuario()) {
            return;
        }
        try {

            usuarioActual.setNombre(StringUtils.trim(usuarioActual.getNombre()).toUpperCase());
            usuarioActual.setLogin(StringUtils.trim(usuarioActual.getLogin()).toLowerCase());
            usuarioActual = usuarioFacade.createOrEdit(usuarioActual);
            JSFUtil.getInstance().addInfoMessage(String.format("Usuario guardado exitosamente: %s", usuarioActual.getLogin()));
            nuevo = false;
        } catch (Exception ex) {
            JSFUtil.getInstance().addErrorMessage(String.format("Error al guardar: %s", ex.getMessage()));

        }

    }

    public void doEliminarUsuario() {
        if (usuarioActual.getLogin().equals(RESERVED_USERNAME)) {
            JSFUtil.getInstance().addErrorMessage("El usuario reservado no puede borrarse!");

            return;
        }
        try {
            usuarioFacade.remove(usuarioActual);
            usuarioActual = null;
            JSFUtil.getInstance().addInfoMessage("Usuario eliminado exitosamente!");

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
            JSFUtil.getInstance().addErrorMessage("Error al borrar!");

        }
    }

    private boolean validarUsuario() {
        boolean validacionCorrecta = true;
        if (usuarioActual == null) {
            validacionCorrecta = false;
            JSFUtil.getInstance().addErrorMessage("No se permite guardar el usuario en el estado actual");
        }
        if (newPassword != null && !newPassword.isEmpty()) {
            validacionCorrecta = validarPassword();
            if (validacionCorrecta) {
                usuarioActual.setPassword(newPassword);
            }
        }
        if (usuarioActual.getLogin().equals(RESERVED_USERNAME)) {
            validacionCorrecta = false;

            JSFUtil.getInstance().addErrorMessage("No se permite modificar el usuario reservado");
        }
        return validacionCorrecta;
    }

    private boolean validarPassword() {

        //TODO:Lo ideal sería validar contra un REGEX
        boolean validacionCorrecta = true;

        if (newPassword == null || passwordRepeat == null) {
            validacionCorrecta = false;

            JSFUtil.getInstance().addErrorMessage("Todos los campos de clave deben completarse");

        }
        if (newPassword.length() < 6) {
            validacionCorrecta = false;
            JSFUtil.getInstance().addErrorMessage("La clave debe tener 6 o más caracteres");
        }

        if (!newPassword.equals(passwordRepeat)) {
            validacionCorrecta = false;
            JSFUtil.getInstance().addErrorMessage("La clave de control no coincide");

        }
        return validacionCorrecta;

        //usuarioActual.setPassword(newPassword);
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
