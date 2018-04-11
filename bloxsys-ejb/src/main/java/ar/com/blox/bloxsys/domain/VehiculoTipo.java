package ar.com.blox.bloxsys.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidad de Tipos de vehiculo
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
@Entity
@Table(name = "vehiculos_tipos")
@AttributeOverride(column = @Column(name = "id_tipo_vehiculo"), name = "id")
public class VehiculoTipo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 45)
    @Column(name = "nombre_tipo")
    private String nombreTipo;

    public VehiculoTipo() {
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }


    @Override
    public String getBusinessString() {
        return String.format("[%d] %s", getId(), nombreTipo);
    }
}

