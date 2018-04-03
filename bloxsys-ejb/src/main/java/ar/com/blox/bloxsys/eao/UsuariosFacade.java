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

import ar.com.blox.bloxsys.domain.Usuario;
import ar.com.blox.bloxsys.domain.Usuario_;
import ar.com.blox.bloxsys.search.UsuariosSearchFilter;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Rodrigo M. Tato Rothamel <rotatomel@gmail.com>
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuario, UsuariosSearchFilter> {

    @PersistenceContext(unitName = "bloxsys-pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuario.class);
    }

    @Override
    public Predicate createWhereFromSearchFilter(UsuariosSearchFilter usf, CriteriaBuilder cb, Root<Usuario> root) {
        Predicate p = null;

        if (usf.hasLoginFilter()) {
            Predicate p1 = cb.equal(root.get(Usuario_.login), usf.getLogin());
            p = appendAndPredicate(cb, p, p1);
        }

        if (usf.hasTextFilter()) {
            for (String s : usf.getText().toUpperCase().split("\\W")) {

                Predicate p1 = cb.like(cb.upper(root.get(Usuario_.login)), String.format("%%%s%%", s));
                Predicate p2 = cb.like(cb.upper(root.get(Usuario_.nombre)), String.format("%%%s%%", s));

                p = appendOrPredicate(cb, p, p1);
                p = appendOrPredicate(cb, p, p2);
            }
        }

        return p;
    }

}
