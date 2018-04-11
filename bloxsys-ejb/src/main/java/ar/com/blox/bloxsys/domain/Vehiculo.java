/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.blox.bloxsys.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Entidad de Vehiculo
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Entity
@Table(name = "vehiculos")
@AttributeOverride(column = @Column(name = "id_vehiculo"), name = "id")
@NamedEntityGraph(name = "fullVehiculoGraph", includeAllAttributes = true)
public class Vehiculo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "dominio", length = 8, unique = true)
    @Size(max = 8)
    private String dominio;
    @NotNull
    @Column(name = "marca", length = 45, unique = true)
    @Size(max = 45)
    private String marca;
    @NotNull
    @Column(name = "modelo", length = 45, unique = true)
    @Size(max = 45)
    private String modelo;

    @Column(name = "nro_chasis")
    private String nroChasis;

    @Column(name = "nro_motor")
    private String nroMotor;

    @Min(1)
    @Column(name = "capacidad_carga")
    private Integer capacidadCarga;

    @NotNull
    @Column(name = "fecha_compra")
    @Temporal(TemporalType.DATE)
    @Past
    private Date fechaCompra;

    @Column(name = "observaciones", columnDefinition = "text")
    private String observaciones;

    @NotNull
    @Column(name = "activo")
    private Boolean activo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_tipo_vehiculo", referencedColumnName = "id_tipo_vehiculo")
    private VehiculoTipo idTipoVehiculo;

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(Integer capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public String getNroChasis() {
        return nroChasis;
    }

    public void setNroChasis(String nroChasis) {
        this.nroChasis = nroChasis;
    }

    public String getNroMotor() {
        return nroMotor;
    }

    public void setNroMotor(String nroMotor) {
        this.nroMotor = nroMotor;
    }

    public VehiculoTipo getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(VehiculoTipo idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    @Override
    public String getBusinessString() {
        return String.format("[%s] %s - %s", dominio, marca, modelo);
    }

}
