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
public class VehiculosSearchFilter extends AbstractSearchFilter {

    private static final long serialVersionUID = 1L;

    private String text;

    private Boolean activo;

    private Long idTipoVehiculo;

    @Override
    public boolean hasFilter() {
        return hasTextFilter() || hasActivoFilter();
    }

    public VehiculosSearchFilter() {
    }

    public VehiculosSearchFilter(String text) {
        this.text = text;
    }

    /**
     * El texto para buscar coincidencia en alguno de los atributos de la entidad
     *
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * El texto para buscar coincidencia en alguno de los atributos de la entidad
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Si se debe buscar vehiculos activos (true) o no (false)
     *
     * @return
     */
    public Boolean getActivo() {
        return activo;
    }

    /**
     * Si se debe buscar vehiculos activos (true) o no (false)
     *
     * @param activo
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    /**
     * El ID del tipo de vehiculo
     *
     * @return
     */
    public Long getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    /**
     * El ID del tipo de vehiculo
     */
    public void setIdTipoVehiculo(Long idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    /**
     * Devuelve true si hay algún texto por el cual buscar
     *
     * @return
     */
    public boolean hasTextFilter() {
        return StringUtils.isNotBlank(text);
    }

    public boolean hasActivoFilter() {
        return activo != null;
    }

    /**
     * Devuelve true si se ha de buscar por el tipo de vehiculo
     *
     * @return
     */
    public boolean hasTipoVehiculoFilter() {
        return idTipoVehiculo != null;
    }
}
