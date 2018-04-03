/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.blox.bloxsys.search;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class UsuariosSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private String login;

    private String text;

    @Override
    public boolean hasFilter() {
        return hasLoginFilter() || hasTextFilter();
    }

    public UsuariosSearchFilter() {
    }

    public UsuariosSearchFilter(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean hasLoginFilter() {
        return StringUtils.isNotBlank(login);
    }

    public boolean hasTextFilter() {
        return StringUtils.isNotBlank(text);
    }

}
