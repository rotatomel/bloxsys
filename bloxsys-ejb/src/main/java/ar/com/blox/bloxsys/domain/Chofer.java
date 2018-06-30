package ar.com.blox.bloxsys.domain;

import ar.com.blox.bloxsys.validators.CUIT;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @CUIT
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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_supervisor", referencedColumnName = "id_usuario")
    private Usuario supervisor;

    @Column(name = "email")
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "El email debe tener el formato correcto.")
    private String email;


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
     * El usuario que es responsable de supervisar al chofer y recibir las notificaciones.
     *
     * @return
     */
    public Usuario getSupervisor() {
        return supervisor;
    }

    /**
     * El usuario que es responsable de supervisar al chofer y recibir las notificaciones.
     *
     * @param supervisor
     */
    public void setSupervisor(Usuario supervisor) {
        this.supervisor = supervisor;
    }

    /**
     * El email en el que el chofer puede recibir las notificaciones
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * El email en el que el chofer puede recibir las notificaciones
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
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
