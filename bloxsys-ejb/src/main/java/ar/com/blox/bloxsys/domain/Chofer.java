package ar.com.blox.bloxsys.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Entidad de Chofer
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Entity
@Table(name = "choferes")
@AttributeOverride(column = @Column(name = "id_chofer"), name = "id")
public class Chofer extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "apellidos")
    private String apellidos;
    @NotNull
    @Column(name = "nombres")
    private String nombres;
    @NotNull
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "celular_personal")
    private String celularPersonal;
    @Column(name = "celular_corporativo")
    private String celularCorporativo;

    @NotNull
    @Column(name = "cuil")
    private Long cuil;

    @NotNull
    @Column(name = "activo")
    private Boolean activo;
    @NotNull
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    @NotNull
    @Column(name = "fecha_vencimiento_licencia")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimientoLicencia;

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelularPersonal() {
        return celularPersonal;
    }

    public void setCelularPersonal(String celularPersonal) {
        this.celularPersonal = celularPersonal;
    }

    public String getCelularCorporativo() {
        return celularCorporativo;
    }

    public void setCelularCorporativo(String celularCorporativo) {
        this.celularCorporativo = celularCorporativo;
    }

    public Long getCuil() {
        return cuil;
    }

    public void setCuil(Long cuil) {
        this.cuil = cuil;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaVencimientoLicencia() {
        return fechaVencimientoLicencia;
    }

    public void setFechaVencimientoLicencia(Date fechaVencimientoLicencia) {
        this.fechaVencimientoLicencia = fechaVencimientoLicencia;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    /**
     * Retorna los apellidos y nombres del chofer concatenados
     *
     * @return un string que representa al nombre completo del chofer
     */
    public String getNombreCompleto() {
        return String.format("%s, %s", apellidos, nombres);
    }

    /**
     * Retorna true si la fecha de la licencia es anterior o igual a la fecha actual (Está vencida).
     *
     * @return
     */
    public boolean getIsLicenciaVencida() {
        if (fechaVencimientoLicencia == null) {
            return false;
        }
        return this.fechaVencimientoLicencia.compareTo(new Date()) <= 0;
    }

    public String getBusinessString() {
        return String.format("[%d] %s, %s", cuil, apellidos, nombres);
    }
}
