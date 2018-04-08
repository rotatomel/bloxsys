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

import ar.com.blox.bloxsys.domain.Chofer;
import ar.com.blox.bloxsys.domain.Chofer_;
import ar.com.blox.bloxsys.search.ChoferesSearchFilter;

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
public class ChoferesFacade extends AbstractFacade<Chofer, ChoferesSearchFilter> {

    @PersistenceContext(unitName = "bloxsys-pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChoferesFacade() {
        super(Chofer.class);
    }

    @Override
    public Predicate createWhereFromSearchFilter(ChoferesSearchFilter csf, CriteriaBuilder cb, Root<Chofer> root) {
        Predicate p = null;

        if (csf.hasTextFilter()) {
            Predicate pText = null;
            for (String s : csf.getText().toUpperCase().split("\\W")) {

                Predicate p1 = cb.like(cb.upper(root.get(Chofer_.nombres)), String.format("%%%s%%", s));
                Predicate p2 = cb.like(cb.upper(root.get(Chofer_.apellidos)), String.format("%%%s%%", s));
                Predicate p3 = cb.like(root.get(Chofer_.cuil).as(String.class), String.format("%%%s%%", s));

                pText = appendOrPredicate(cb, pText, p1);
                pText = appendOrPredicate(cb, pText, p2);
                pText = appendOrPredicate(cb, pText, p3);
            }
            p = appendAndPredicate(cb, p, pText);
        }

        if (csf.hasLicenciaExpiradaFilter()) {
            Predicate p1 = cb.greaterThanOrEqualTo(cb.currentDate(), root.get(Chofer_.fechaVencimientoLicencia));
            p = appendAndPredicate(cb, p, p1);
        }

        if (csf.hasActivoFilter()) {
            Predicate p1 = cb.equal(root.get(Chofer_.activo), csf.getActivo());
            p = appendAndPredicate(cb, p, p1);
        }

        return p;
    }

}
