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
public class VehiculosNovedadesSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private String text;

    private Long idVehiculo;

    private Long idUsuario;

    private Long idChofer;

    private Date fechaDesde, fechaHasta;

    @Override
    public boolean hasFilter() {
        return hasTextFilter() || hasFechasFilter()
                || hasIdUsuarioFilter() || hasIdVehiculoFilter() || hasIdChoferFilter();
    }

    public VehiculosNovedadesSearchFilter() {
    }

    public VehiculosNovedadesSearchFilter(Date fechaDesde, Date fechaHasta) {
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
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

    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdChofer() {
        return idChofer;
    }

    public void setIdChofer(Long idChofer) {
        this.idChofer = idChofer;
    }

    public boolean hasTextFilter() {
        return StringUtils.isNotBlank(text);
    }

    public boolean hasFechasFilter() {
        return (fechaDesde != null && fechaHasta != null);
    }

    public boolean hasIdVehiculoFilter() {
        return idVehiculo != null;
    }

    public boolean hasIdUsuarioFilter() {
        return idUsuario != null;
    }

    public boolean hasIdChoferFilter() {
        return idChofer != null;
    }

}
