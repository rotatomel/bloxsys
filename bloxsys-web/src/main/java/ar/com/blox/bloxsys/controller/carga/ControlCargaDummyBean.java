/*
 * Copyright 2018 UTN
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

package ar.com.blox.bloxsys.controller.carga;

import ar.com.blox.bloxsys.auth.AuthBackingBean;
import ar.com.blox.bloxsys.domain.ControlCarga;
import ar.com.blox.bloxsys.eao.ControlCargaFacade;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.math.BigDecimal;
import java.util.Random;

@ManagedBean(name = "controlCargaDummyBean")
@ViewScoped
public class ControlCargaDummyBean {


    @ManagedProperty(value = "#{authBackingBean}")
    private AuthBackingBean authBackingBean;

    @EJB
    private ControlCargaFacade facade;

    private final Random r = new Random();
    private boolean balanzaHabilitada = false;
    private boolean camionEnBalanza = false;
    private boolean remitoValidado = false;
    private String estado = "Balanza deshabilitada";

    private ControlCarga controlCarga = null;

    public ControlCargaDummyBean() {
    }

    @PostConstruct
    private void init() {
    }

    public void doHabilitarBalanza() {
        balanzaHabilitada = true;
        estado = "Esperando camión...";
    }

    public void doVerificarIngresoCamion() {
        if (balanzaHabilitada) {
            if (r.nextDouble() > 0.5) {
                doIngresaCamion();
            }
        }
    }

    public void doIngresaCamion() {
        estado = "Camion Ingresado";
        camionEnBalanza = true;
        controlCarga = new ControlCarga();
        controlCarga.setIdUsuario(authBackingBean.getUserLoggedIn());

        controlCarga.setDominio(getPatenteAleatoria());
        controlCarga.setPesoCensado(
                new BigDecimal(r.nextDouble() * 10000).setScale(2, BigDecimal.ROUND_HALF_UP));

    }


    public void doValidarRemito() {
        estado = "Remito validado";
        remitoValidado = true;
        controlCarga.setPesoDeclarado(
                new BigDecimal(r.nextDouble() * 10000).setScale(2, BigDecimal.ROUND_HALF_UP));
        controlCarga.setDiferencia(controlCarga.getPesoCensado().subtract(controlCarga.getPesoDeclarado()));

    }

    public void doGuardar() {
        balanzaHabilitada = false;
        camionEnBalanza = false;
        remitoValidado = false;
        estado = "Balanza deshabilitada";
        facade.createOrEdit(controlCarga);
    }


    public boolean isBalanzaHabilitada() {
        return balanzaHabilitada;
    }

    public boolean isCamionEnBalanza() {
        return camionEnBalanza;
    }

    public String getEstado() {
        return estado;
    }

    public boolean isRemitoValidado() {
        return remitoValidado;
    }


    /**
     * Genera una patente aleatoria que debería venir del WebService de la balanza
     *
     * @return
     */
    private String getPatenteAleatoria() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        StringBuilder patente = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            patente.append(letras.charAt(r.nextInt(letras.length())));
        }
        for (int i = 0; i < 3; i++) {
            patente.append(digits.charAt(r.nextInt(digits.length())));
        }
        return patente.toString();
    }

    public ControlCarga getControlCarga() {
        return controlCarga;
    }

    public AuthBackingBean getAuthBackingBean() {
        return authBackingBean;
    }

    public void setAuthBackingBean(AuthBackingBean authBackingBean) {
        this.authBackingBean = authBackingBean;
    }
}
