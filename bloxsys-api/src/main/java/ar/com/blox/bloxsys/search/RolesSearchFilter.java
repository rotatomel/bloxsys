/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.blox.bloxsys.search;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class RolesSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private Long idUsuario;

    @Override
    public boolean hasFilter() {
        return hasIdUsuarioFilter();
    }

    public RolesSearchFilter(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean hasIdUsuarioFilter() {
        return idUsuario != null;
    }

}
