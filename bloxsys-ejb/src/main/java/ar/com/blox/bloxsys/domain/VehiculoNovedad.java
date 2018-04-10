/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.blox.bloxsys.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Entidad de Novedad de Vehiculo
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Entity
@Table(name = "vehiculos_novedades")
@AttributeOverride(column = @Column(name = "id_novedad"), name = "id")
@NamedEntityGraph(name = "fullGraph",includeAllAttributes=true)
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
    private BigDecimal kilometrosRealizados;

    @Column(name = "costo")
    private BigDecimal costo;

    @Column(name = "horas_uso")
    private BigDecimal horasUso;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "tipo_novedad")
    @Enumerated(EnumType.STRING)
    private VehiculoTiposNovedadEnum tipoNovedad;


    @ManyToOne
    @JoinColumn(name = "id_chofer", referencedColumnName = "id_chofer")
    private Chofer idChofer;

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

    public BigDecimal getKilometrosRealizados() {
        return kilometrosRealizados;
    }

    public void setKilometrosRealizados(BigDecimal kilometrosRealizados) {
        this.kilometrosRealizados = kilometrosRealizados;
    }

    public BigDecimal getHorasUso() {
        return horasUso;
    }

    public void setHorasUso(BigDecimal horasUso) {
        this.horasUso = horasUso;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Chofer getIdChofer() {
        return idChofer;
    }

    public void setIdChofer(Chofer idChofer) {
        this.idChofer = idChofer;
    }
}
