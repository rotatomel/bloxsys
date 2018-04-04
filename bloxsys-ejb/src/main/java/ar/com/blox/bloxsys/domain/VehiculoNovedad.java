/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.blox.bloxsys.domain;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Entidad de Novedad de Vehiculo
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Entity
@Table(name = "vehiculos_novedades")
@AttributeOverride(column = @Column(name = "id_novedad"), name = "id")
public class VehiculoNovedad extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo")
    private Vehiculo idVehiculo;

    @NotNull
    @Column(name = "fecha_novedad")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNovedad;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario idUsuario;

    @Column(name = "kilometros_realizados")
    private Integer kilometrosRealizados;

    @Column(name = "horas_uso")
    private Integer horasUso;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "tipo_novedad")
    @Enumerated(EnumType.STRING)
    private VehiculoTiposNovedadEnum tipoNovedad;

    public Vehiculo getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Vehiculo idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Date getFechaNovedad() {
        return fechaNovedad;
    }

    public void setFechaNovedad(Date fechaNovedad) {
        this.fechaNovedad = fechaNovedad;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getKilometrosRealizados() {
        return kilometrosRealizados;
    }

    public void setKilometrosRealizados(Integer kilometrosRealizados) {
        this.kilometrosRealizados = kilometrosRealizados;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public VehiculoTiposNovedadEnum getTipoNovedad() {
        return tipoNovedad;
    }

    public void setTipoNovedad(VehiculoTiposNovedadEnum tipoNovedad) {
        this.tipoNovedad = tipoNovedad;
    }

    public Integer getHorasUso() {
        return horasUso;
    }

    public void setHorasUso(Integer horasUso) {
        this.horasUso = horasUso;
    }

}
