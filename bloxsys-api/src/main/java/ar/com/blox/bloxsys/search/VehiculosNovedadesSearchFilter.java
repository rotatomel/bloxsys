/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.blox.bloxsys.search;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class VehiculosNovedadesSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private String text;

    private Date fechaDesde, fechaHasta;

    @Override
    public boolean hasFilter() {
        return hasTextFilter() || hasFechasFilter();
    }

    public VehiculosNovedadesSearchFilter() {
    }

    public VehiculosNovedadesSearchFilter(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public boolean hasTextFilter() {
        return StringUtils.isNotBlank(text);
    }

    public boolean hasFechasFilter() {
        return (fechaDesde != null && fechaHasta != null);
    }

}
