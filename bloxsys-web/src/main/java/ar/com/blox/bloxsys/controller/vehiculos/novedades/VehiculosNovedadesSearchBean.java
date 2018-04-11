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
package ar.com.blox.bloxsys.controller.vehiculos.novedades;

import ar.com.blox.bloxsys.domain.Chofer;
import ar.com.blox.bloxsys.domain.Usuario;
import ar.com.blox.bloxsys.domain.Vehiculo;
import ar.com.blox.bloxsys.domain.VehiculoNovedad;
import ar.com.blox.bloxsys.eao.*;
import ar.com.blox.bloxsys.search.ChoferesSearchFilter;
import ar.com.blox.bloxsys.search.UsuariosSearchFilter;
import ar.com.blox.bloxsys.search.VehiculosNovedadesSearchFilter;
import ar.com.blox.bloxsys.search.VehiculosSearchFilter;
import ar.com.blox.bloxsys.ui.search.AbstractSearchBean;
import org.apache.commons.lang3.time.DateUtils;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Bena para búsqueda de vehiculos
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "vehiculosNovedadesSearchBean")
@ViewScoped
public class VehiculosNovedadesSearchBean extends AbstractSearchBean<VehiculoNovedad, VehiculosNovedadesSearchFilter> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final VehiculosNovedadesSearchFilter filter = new VehiculosNovedadesSearchFilter(
            DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH),
            DateUtils.truncate(DateUtils.addDays(new Date(), 1), Calendar.DAY_OF_MONTH));

    @EJB
    private VehiculosNovedadesFacade vehiculosNovedadesFacade;

    @EJB
    private VehiculosFacade vehiculosFacade;

    @EJB
    private UsuariosFacade usuariosFacade;

    @EJB
    private ChoferesFacade choferesFacade;

    private List<Usuario> usuarios = null;

    private List<Vehiculo> vehiculos = null;

    private List<Chofer> choferes = null;

    /**
     * Creates a new instance of VehiculosNovedadesSearchBean
     */
    public VehiculosNovedadesSearchBean() {
    }

    @Override
    public VehiculosNovedadesSearchFilter getFilter() {
        return filter;
    }

    @Override
    protected AbstractFacade<VehiculoNovedad, VehiculosNovedadesSearchFilter> getFacade() {
        return vehiculosNovedadesFacade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasNamedEntityGraph()) {
            filter.setNamedEntityGraph("fullNovedadGraph");
        }

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

    /**
     * Obtiene la lista de vehículos activos
     *
     * @return
     */
    public List<Vehiculo> getVehiculos() {
        if (vehiculos == null) {
            vehiculos = new ArrayList<>();
            VehiculosSearchFilter vsf = new VehiculosSearchFilter();
            vsf.setActivo(true);
            vehiculos.addAll(vehiculosFacade.findAllBySearchFilter(vsf));
        }
        return vehiculos;
    }

    /**
     * Devuelve la lista de choferes activos
     *
     * @return
     */
    public List<Chofer> getChoferes() {
        if (choferes == null) {
            choferes = new ArrayList<>();
            ChoferesSearchFilter csf = new ChoferesSearchFilter();
            csf.setActivo(true);
            csf.addSortField("apellidos", true);
            csf.addSortField("nombres", true);
            choferes.addAll(choferesFacade.findAllBySearchFilter(csf));

        }
        return choferes;
    }
}
