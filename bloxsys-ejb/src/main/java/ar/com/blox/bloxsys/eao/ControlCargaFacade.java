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

import ar.com.blox.bloxsys.domain.ControlCarga;
import ar.com.blox.bloxsys.domain.ControlCarga_;
import ar.com.blox.bloxsys.domain.Usuario_;
import ar.com.blox.bloxsys.search.ControlCargaSearchFilter;

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
public class ControlCargaFacade extends AbstractFacade<ControlCarga, ControlCargaSearchFilter> {

    @PersistenceContext(unitName = "bloxsys-pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ControlCargaFacade() {
        super(ControlCarga.class);
    }

    @Override
    public Predicate createWhereFromSearchFilter(ControlCargaSearchFilter vnsf, CriteriaBuilder cb, Root<ControlCarga> root) {
        Predicate p = null;

        if (vnsf.hasTextFilter()) {
            Predicate pText = null;
            for (String s : vnsf.getText().toUpperCase().split("\\W")) {

                Predicate p1 = cb.like(cb.upper(root.get(ControlCarga_.dominio)), String.format("%%%s%%", s));

                Predicate p4 = cb.like(cb.upper(root.get(ControlCarga_.observaciones)), String.format("%%%s%%", s));

                pText = appendOrPredicate(cb, pText, p1);
                pText = appendOrPredicate(cb, pText, p4);
            }
            p = appendAndPredicate(cb, p, pText);
        }


        if (vnsf.hasIdUsuarioFilter()) {
            Predicate p1 = cb.equal(root.get(ControlCarga_.idUsuario).get(Usuario_.id), vnsf.getIdUsuario());
            p = appendAndPredicate(cb, p, p1);
        }

        if (vnsf.hasFechasFilter()) {
            Predicate p1 = cb.between(root.get(ControlCarga_.fechaControl), vnsf.getFechaDesde(), vnsf.getFechaHasta());
            p = appendAndPredicate(cb, p, p1);
        }


        return p;
    }

}
