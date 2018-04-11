/*
 * Copyright 2014 GT Software.
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
package ar.com.blox.bloxsys.eao;

import ar.com.blox.bloxsys.domain.VehiculoTipo;
import ar.com.blox.bloxsys.domain.VehiculoTipo_;
import ar.com.blox.bloxsys.search.BasicTextSearchFilter;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
@Stateless
public class VehiculosTiposFacade extends AbstractFacade<VehiculoTipo, BasicTextSearchFilter> {

    @PersistenceContext(unitName = "bloxsys-pu")
    private EntityManager em;

    public VehiculosTiposFacade() {
        super(VehiculoTipo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Predicate createWhereFromSearchFilter(BasicTextSearchFilter sf, CriteriaBuilder cb, Root<VehiculoTipo> root) {
        Predicate p = null;

        if (sf.hasTextFilter()) {
            Predicate pText = null;
            for (String s : sf.getText().toUpperCase().split("\\W")) {

                Predicate p1 = cb.like(cb.upper(root.get(VehiculoTipo_.nombreTipo)), String.format("%%%s%%", s));


                pText = appendOrPredicate(cb, pText, p1);

            }
            p = appendAndPredicate(cb, p, pText);
        }
        return p;
    }

}
