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
package ar.com.blox.bloxsys.controller.choferes;

import ar.com.blox.bloxsys.domain.Chofer;
import ar.com.blox.bloxsys.eao.AbstractFacade;
import ar.com.blox.bloxsys.eao.ChoferesFacade;
import ar.com.blox.bloxsys.search.ChoferesSearchFilter;
import ar.com.blox.bloxsys.service.PeriodicNotificationService;
import ar.com.blox.bloxsys.ui.search.AbstractSearchBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Bena para búsqueda de usuarios
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@ManagedBean(name = "choferesSearchBean")
@ViewScoped
public class ChoferesSearchBean extends AbstractSearchBean<Chofer, ChoferesSearchFilter> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final ChoferesSearchFilter filter = new ChoferesSearchFilter();

    @EJB
    private ChoferesFacade choferesFacade;

    @EJB
    private PeriodicNotificationService periodicNotificationService;

    /**
     * Creates a new instance of UsuariosSearchBean
     */
    public ChoferesSearchBean() {
    }

    @Override
    public ChoferesSearchFilter getFilter() {
        return filter;
    }

    @Override
    protected AbstractFacade<Chofer, ChoferesSearchFilter> getFacade() {
        return choferesFacade;
    }

    @Override
    protected void prepareSearchFilter() {
        if (!filter.hasOrderFields()) {
            filter.addSortField("cuil", true);
        }
    }


    public void doEnviarNotificacionesLicencia() {
        periodicNotificationService.notificarLicenciasPorExpirar();
    }
}
