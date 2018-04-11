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
package ar.com.blox.bloxsys.controller.vehiculos.tipos;

import ar.com.blox.bloxsys.domain.VehiculoTipo;
import ar.com.blox.bloxsys.eao.AbstractFacade;
import ar.com.blox.bloxsys.eao.VehiculosTiposFacade;
import ar.com.blox.bloxsys.search.BasicTextSearchFilter;
import ar.com.blox.bloxsys.ui.search.AbstractSearchBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Bean para búsqueda de tipos de vehiculos
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "vehiculosTiposSearchBean")
@ViewScoped
public class VehiculosTiposSearchBean extends AbstractSearchBean<VehiculoTipo, BasicTextSearchFilter> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final BasicTextSearchFilter filter = new BasicTextSearchFilter();

    @EJB
    private VehiculosTiposFacade tiposFacade;

    /**
     * Creates a new instance of VehiculosTiposSearchBean
     */
    public VehiculosTiposSearchBean() {
    }

    @Override
    public BasicTextSearchFilter getFilter() {
        return filter;
    }

    @Override
    protected AbstractFacade<VehiculoTipo, BasicTextSearchFilter> getFacade() {
        return tiposFacade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField("nombreTipo", true);
        }

    }

}
