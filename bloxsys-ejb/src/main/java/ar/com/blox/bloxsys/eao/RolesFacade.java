/*
 * Copyright 2018 Blox.
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

import ar.com.blox.bloxsys.domain.UsuarioRol;
import ar.com.blox.bloxsys.domain.UsuarioRol_;
import ar.com.blox.bloxsys.domain.Usuario_;
import ar.com.blox.bloxsys.search.RolesSearchFilter;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Stateless
public class RolesFacade extends AbstractFacade<UsuarioRol, RolesSearchFilter> {

    @PersistenceContext(unitName = "bloxsys-pu")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolesFacade() {
        super(UsuarioRol.class);
    }

    @Override
    protected Predicate createWhereFromSearchFilter(RolesSearchFilter sf, CriteriaBuilder cb, Root<UsuarioRol> root) {
        Predicate p = null;

        if (sf.hasIdUsuarioFilter()) {
            Predicate p1 = cb.equal(root.get(UsuarioRol_.idUsuario).get(Usuario_.id), sf.getIdUsuario());
            p = appendAndPredicate(cb, p, p1);
        }
        return p;
    }

}
