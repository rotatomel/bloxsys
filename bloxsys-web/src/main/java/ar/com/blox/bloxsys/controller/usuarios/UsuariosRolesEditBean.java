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

import ar.com.blox.bloxsys.domain.RolesEnum;
import ar.com.blox.bloxsys.domain.Usuario;
import ar.com.blox.bloxsys.domain.UsuarioRol;
import ar.com.blox.bloxsys.eao.RolesFacade;
import ar.com.blox.bloxsys.eao.UsuariosFacade;
import ar.com.blox.bloxsys.search.RolesSearchFilter;
import ar.com.blox.bloxsys.utils.JSFUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Controlador para el caso de uso de edición de usuarios
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 * @since 2.0.1
 * @version 1.0.0
 */
@ManagedBean(name = "usuariosRolesEditBean")
@ViewScoped
public class UsuariosRolesEditBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @EJB
    private UsuariosFacade usuarioFacade;

    @EJB
    private RolesFacade rolFacade;

    private Usuario usuarioActual;

    private static final Logger LOG = Logger.getLogger(UsuariosRolesEditBean.class.getName());

    private static final String RESERVED_USERNAME = "admin";

    /**
     * Creates a new instance of UsuariosEditBean
     */
    public UsuariosRolesEditBean() {
    }

    @PostConstruct
    private void init() {

        String logIn = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("login");
        if (logIn == null) {

            LOG.log(Level.INFO, "Usuario inexistente! {0}", logIn);
            usuarioActual = null;
        } else {
            usuarioActual = usuarioFacade.find(Long.parseLong(logIn));
            if (usuarioActual == null) {
                LOG.log(Level.INFO, "Usuario inexistente! {0}", logIn);
                throw new IllegalArgumentException(String.format("Usuario inexistente: %s", logIn));
            }
        }

    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

//    private void doGuardarUsuario() {
//
//        if (usuarioActual.getLogin().equals(RESERVED_USERNAME)) {
//            JSFUtil.getInstance().addErrorMessage("No se permite modificar el usuario reservado");
//            return;
//        }
//        usuarioFacade.createOrEdit(usuarioActual);
//        JSFUtil.getInstance().addInfoMessage("Usuario guardado exitosamente!");
//
//    }
    public RolesEnum[] getRolesDisponibles() {
        return RolesEnum.values();
    }

    public List<UsuarioRol> getRolesUsuario() {
        if (usuarioActual == null) {
            return new ArrayList<>();
        }
        return rolFacade.findAllBySearchFilter(new RolesSearchFilter(usuarioActual.getId()));
    }

    public void asignarRol(RolesEnum rol) {
        if (usuarioActual == null) {
            return;
        }
        UsuarioRol nuevoRol = new UsuarioRol();
        nuevoRol.setRoleName(rol.getNombre());
        nuevoRol.setIdUsuario(usuarioActual);
        rolFacade.create(nuevoRol);
        JSFUtil.getInstance().addInfoMessage("Rol asignado exitosamente!");

    }

    public void quitarRol(UsuarioRol rol) {
        if (usuarioActual == null) {
            return;
        }
        rolFacade.remove(rol);
        JSFUtil.getInstance().addInfoMessage("Rol desasignado exitosamente!");

    }
}
