package ar.com.blox.bloxsys.controller.carga;

import ar.com.blox.bloxsys.domain.Chofer;
import ar.com.blox.bloxsys.domain.ControlCarga;
import ar.com.blox.bloxsys.domain.Usuario;
import ar.com.blox.bloxsys.eao.AbstractFacade;
import ar.com.blox.bloxsys.eao.ControlCargaFacade;
import ar.com.blox.bloxsys.eao.UsuariosFacade;
import ar.com.blox.bloxsys.search.ChoferesSearchFilter;
import ar.com.blox.bloxsys.search.ControlCargaSearchFilter;
import ar.com.blox.bloxsys.search.UsuariosSearchFilter;
import ar.com.blox.bloxsys.ui.search.AbstractSearchBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "historialControlCargaBean")
@ViewScoped
public class HistorialControlCargaBean extends AbstractSearchBean<ControlCarga, ControlCargaSearchFilter> {


    @EJB
    private ControlCargaFacade facade;


    @EJB
    private UsuariosFacade usuariosFacade;
    private List<Usuario> usuarios = null;


    private static final ControlCargaSearchFilter filter = new ControlCargaSearchFilter();

    @Override
    protected AbstractFacade<ControlCarga, ControlCargaSearchFilter> getFacade() {
        return facade;
    }

    @Override
    public ControlCargaSearchFilter getFilter() {
        return filter;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField("id", false);
        }
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
