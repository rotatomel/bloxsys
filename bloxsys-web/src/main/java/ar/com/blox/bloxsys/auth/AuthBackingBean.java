/*
 Copyright 2014 GT Software.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package ar.com.blox.bloxsys.auth;

import ar.com.blox.bloxsys.domain.RolesEnum;
import ar.com.blox.bloxsys.domain.Usuario;
import ar.com.blox.bloxsys.domain.UsuarioRol;
import ar.com.blox.bloxsys.eao.RolesFacade;
import ar.com.blox.bloxsys.eao.UsuariosFacade;
import ar.com.blox.bloxsys.search.RolesSearchFilter;
import ar.com.blox.bloxsys.search.UsuariosSearchFilter;
import ar.com.blox.bloxsys.utils.JSFUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSeparator;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Rodrigo Tato <rotatomel@gmail.com>
 */
@ManagedBean(name = "authBackingBean")
@SessionScoped
public class AuthBackingBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private UsuariosFacade uf;
    @EJB
    private RolesFacade rf;

    private Usuario usuarioLogueado;
    private MenuModel rolesMenuModel;

    public AuthBackingBean() {
    }

    @PostConstruct
    public void init() {

        rolesMenuModel = new DefaultMenuModel();

        DefaultMenuItem cambiarClaveMi = new DefaultMenuItem("Cambiar clave");
        cambiarClaveMi.setIcon("fa fa-fw fa-lock");
        cambiarClaveMi.setCommand(String.format("/protected/user/changePassword.xhtml%s", JSFUtil.JSF_REDIRECT_ESCAPED));
        rolesMenuModel.addElement(cambiarClaveMi);

        DefaultMenuItem salirMi = new DefaultMenuItem("Salir");
        salirMi.setIcon("fa fa-fw fa-power-off");
        salirMi.setCommand("#{authBackingBean.logout}");
        rolesMenuModel.addElement(salirMi);

        DefaultSeparator separator = new DefaultSeparator();
        rolesMenuModel.addElement(separator);

        DefaultSubMenu rolesSubMenu = new DefaultSubMenu("Roles");
        RolesSearchFilter rsf = new RolesSearchFilter(getUserLoggedIn().getId());
        for (UsuarioRol rol : rf.findAllBySearchFilter(rsf)) {
            DefaultMenuItem rolMi = new DefaultMenuItem(rol.getRoleName());
            rolesSubMenu.addElement(rolMi);
        }
        rolesSubMenu.setExpanded(false);

        rolesMenuModel.addElement(rolesSubMenu);
    }

    public void logout() {
        JSFUtil.getInstance().logOut("/index.html");
    }

    public Usuario getUserLoggedIn() {
        if (usuarioLogueado == null) {
            String usuP = JSFUtil.getInstance().getUserPrincipalName();

            if (StringUtils.isNotEmpty(usuP)) {

                UsuariosSearchFilter usf = new UsuariosSearchFilter(usuP);
                Usuario usuario = uf.findFirstBySearchFilter(usf);
                if (usuario != null) {
                    usuarioLogueado = usuario;
                    return usuarioLogueado;
                }
            }
            throw new RuntimeException("Usuario no encontrado!");

        }
        return usuarioLogueado;
    }

    public MenuModel getRolesMenuModel() {
        return rolesMenuModel;
    }

    public boolean getIsUserInRoleAdmin() {
        return JSFUtil.getInstance().isUserInRole(RolesEnum.ROLE_ADMIN.name());
    }

    public boolean getIsUserInRoleUser() {
        return JSFUtil.getInstance().isUserInRole(RolesEnum.ROLE_USER.name());
    }

}
