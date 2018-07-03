/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.blox.bloxsys.search;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public class ControlCargaSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private String text;

    private Long idUsuario;

    private Date fechaDesde, fechaHasta;

    @Override
    public boolean hasFilter() {
        return hasTextFilter() || hasFechasFilter()
                || hasIdUsuarioFilter();
    }

    public ControlCargaSearchFilter() {
    }

    public ControlCargaSearchFilter(Date fechaDesde, Date fechaHasta) {
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    public ControlCargaSearchFilter(String text) {
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

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean hasTextFilter() {
        return StringUtils.isNotBlank(text);
    }

    public boolean hasFechasFilter() {
        return (fechaDesde != null && fechaHasta != null);
    }

    public boolean hasIdUsuarioFilter() {
        return idUsuario != null;
    }


}
