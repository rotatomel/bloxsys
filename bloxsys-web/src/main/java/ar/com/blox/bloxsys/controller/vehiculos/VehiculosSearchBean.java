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
package ar.com.blox.bloxsys.controller.vehiculos;

import ar.com.blox.bloxsys.domain.Vehiculo;
import ar.com.blox.bloxsys.eao.AbstractFacade;
import ar.com.blox.bloxsys.eao.VehiculosFacade;
import ar.com.blox.bloxsys.search.VehiculosSearchFilter;
import ar.com.blox.bloxsys.ui.search.AbstractSearchBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Bena para búsqueda de vehiculos
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "vehiculosSearchBean")
@ViewScoped
public class VehiculosSearchBean extends AbstractSearchBean<Vehiculo, VehiculosSearchFilter> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final VehiculosSearchFilter filter = new VehiculosSearchFilter();

    @EJB
    private VehiculosFacade vehiculosFacade;

    /**
     * Creates a new instance of UsuariosSearchBean
     */
    public VehiculosSearchBean() {
    }

    @Override
    public VehiculosSearchFilter getFilter() {
        return filter;
    }

    @Override
    protected AbstractFacade<Vehiculo, VehiculosSearchFilter> getFacade() {
        return vehiculosFacade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField("dominio", true);
        }
    }

}
