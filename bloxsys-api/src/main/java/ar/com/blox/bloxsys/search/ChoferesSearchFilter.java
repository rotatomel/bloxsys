/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.blox.bloxsys.search;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class ChoferesSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private String text;

    private Boolean licenciaExpirada;

    private Boolean activo;

    private Integer diasLicenciaProximaVencer;

    @Override
    public boolean hasFilter() {
        return hasTextFilter() || hasLicenciaExpiradaFilter() || hasActivoFilter()
                || hasDiasLicenciaProximaVencer();
    }

    public ChoferesSearchFilter() {
    }

    public ChoferesSearchFilter(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getLicenciaExpirada() {
        return licenciaExpirada;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public void setLicenciaExpirada(Boolean licenciaExpirada) {
        this.licenciaExpirada = licenciaExpirada;
    }

    public boolean hasTextFilter() {
        return StringUtils.isNotBlank(text);
    }

    public boolean hasLicenciaExpiradaFilter() {
        return licenciaExpirada != null;
    }

    /**
     * La cantidad de días (entero positivo) dentro del cual se considera que la licencia está próxima a expirar.
     *
     * @return
     */
    public Integer getDiasLicenciaProximaVencer() {
        return diasLicenciaProximaVencer;
    }

    /**
     * La cantidad de días (entero positivo) dentro del cual se considera que la licencia está próxima a expirar.
     *
     * @param diasLicenciaProximaVencer
     */
    public void setDiasLicenciaProximaVencer(Integer diasLicenciaProximaVencer) {
        if (diasLicenciaProximaVencer != null && diasLicenciaProximaVencer < 0) {
            throw new IllegalArgumentException("No puede ser negativo la cantidad de días de proximo vencimiento.");
        }
        this.diasLicenciaProximaVencer = diasLicenciaProximaVencer;
    }

    public boolean hasDiasLicenciaProximaVencer() {
        return diasLicenciaProximaVencer != null;
    }

    public boolean hasActivoFilter() {
        return activo != null;
    }

}
