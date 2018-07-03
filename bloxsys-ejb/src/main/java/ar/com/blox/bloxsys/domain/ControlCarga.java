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
import java.math.BigDecimal;
import java.util.Date;

/**
 * Entidad de Vehiculo
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Entity
@Table(name = "contoles_carga")
@AttributeOverride(column = @Column(name = "id_control"), name = "id")
public class ControlCarga extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "dominio", length = 8)
    @Size(max = 8)
    private String dominio;
    @NotNull
    @Column(name = "nro_remito", length = 45)
    @Size(max = 45)
    private String nroRemito;

    @Column(name = "fecha_control")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date fechaControl;

    @Column(name = "peso_declarado")
    @NotNull
    private BigDecimal pesoDeclarado;
    @Column(name = "peso_censado")
    @NotNull
    private BigDecimal pesoCensado;
    @Column(name = "diferencia")
    @NotNull
    private BigDecimal diferencia;

    @Column(name = "observaciones", columnDefinition = "text")
    private String observaciones;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario idUsuario;


    public ControlCarga() {
        fechaControl = new Date();
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getNroRemito() {
        return nroRemito;
    }

    public void setNroRemito(String nroRemito) {
        this.nroRemito = nroRemito;
    }

    public Date getFechaControl() {
        return fechaControl;
    }

    public void setFechaControl(Date fechaControl) {
        this.fechaControl = fechaControl;
    }

    public BigDecimal getPesoDeclarado() {
        return pesoDeclarado;
    }

    public void setPesoDeclarado(BigDecimal pesoDeclarado) {
        this.pesoDeclarado = pesoDeclarado;
    }

    public BigDecimal getPesoCensado() {
        return pesoCensado;
    }

    public void setPesoCensado(BigDecimal pesoCensado) {
        this.pesoCensado = pesoCensado;
    }

    public BigDecimal getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(BigDecimal diferencia) {
        this.diferencia = diferencia;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
}
