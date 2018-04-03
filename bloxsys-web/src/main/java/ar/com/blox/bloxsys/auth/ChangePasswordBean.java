/*
 * Copyright 2014 Dilcar S.A..
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
package ar.com.blox.bloxsys.auth;

import ar.com.blox.bloxsys.domain.Usuario;
import ar.com.blox.bloxsys.eao.UsuariosFacade;
import ar.com.blox.bloxsys.utils.JSFUtil;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Controlador para el cambio de claves de los usuarios
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 * @since 1.0.0
 * @version 1.0.0
 */
@ManagedBean(name = "changePasswordBean")
@ViewScoped
public class ChangePasswordBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    @EJB
    private UsuariosFacade usuarioFacade;

    private String oldPassword;
    private String newPassword;
    private String newPasswordRepeat;

    /**
     * Creates a new instance of ChangePasswordBean
     */
    public ChangePasswordBean() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordRepeat() {
        return newPasswordRepeat;
    }

    public void setNewPasswordRepeat(String newPasswordRepeat) {
        this.newPasswordRepeat = newPasswordRepeat;
    }

    private boolean cambioValido() {
        if (oldPassword == null || newPassword == null || newPasswordRepeat == null) {
            JSFUtil.getInstance().addErrorMessage("Se deben completar todos los campos");

            return false;
        }
        if (newPassword.length() < 6) {
            JSFUtil.getInstance().addErrorMessage("La clave debe tener 6 o más caracteres");

            return false;
        }
        //String oldPassHashed = HashUtils.getHash(oldPassword);
        if (!oldPassword.equals(authBackingBean.getUserLoggedIn().getPassword())) {

            JSFUtil.getInstance().addErrorMessage("La contraseña anterior debe coincidir");

            return false;
        }
        if (!newPassword.equals(newPasswordRepeat)) {
            JSFUtil.getInstance().addErrorMessage("La clave de control no coincide");

            return false;
        }
        //String newPassHashed = HashUtils.getHash(newPassword);

        if (newPassword.equals(oldPassword)) {
            JSFUtil.getInstance().addErrorMessage("La nueva clave no puede ser la misma que la anterior");

            return false;
        }
        return true;
    }

    public void actualizarClave() {
        if (cambioValido()) {
            //String newPassHashed = HashUtils.getHash(newPassword);
            Usuario usuario = authBackingBean.getUserLoggedIn();
            usuario.setPassword(newPassword);
            try {
                usuarioFacade.edit(usuario);
            } catch (Exception ex) {
                Logger.getLogger(ChangePasswordBean.class.getName()).log(Level.SEVERE, null, ex);
                JSFUtil.getInstance().addErrorMessage("La clave no pudo ser actualizada");

            }
            oldPassword = null;
            newPassword = null;
            newPasswordRepeat = null;
            JSFUtil.getInstance().addInfoMessage("La clave fue actualizada exitosamente");

        }
    }

    public AuthBackingBean getAuthBackingBean() {
        return authBackingBean;
    }

    public void setAuthBackingBean(AuthBackingBean authBackingBean) {
        this.authBackingBean = authBackingBean;
    }

}
