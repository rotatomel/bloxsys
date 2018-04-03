/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.blox.bloxsys.search;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class ChoferesSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private String text;

    private Boolean licenciaExpirada;

    @Override
    public boolean hasFilter() {
        return hasTextFilter() || hasLicenciaExpiradaFilter();
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

    public void setLicenciaExpirada(Boolean licenciaExpirada) {
        this.licenciaExpirada = licenciaExpirada;
    }

    public boolean hasTextFilter() {
        return StringUtils.isNotBlank(text);
    }

    public boolean hasLicenciaExpiradaFilter() {
        return licenciaExpirada != null;
    }

}
