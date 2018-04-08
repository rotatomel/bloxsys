/*
 * Copyright 2018 Blox.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ar.com.blox.bloxsys.domain;

/**
 * Tipo enumerado para identificar los posibles tipos de novedad que pueden ocurrir con un vehículo
 *
 * @author Rodrigo M. Tato Rothamel mailto:rotatomel@gmail.com
 */
public enum VehiculoTiposNovedadEnum {

    REPARACION("Reparación", "Se realiza una reparación al vehículo."),
    SERVICE("Service", "Se realiza un mantenimiento preventivo."),
    VIAJE("Viaje", "Se realiza un viaje con el vehículo."),
    DETECCION_FALLA("Detección de falla", "Se detecta una falla en algún componente del vehículo.");

    private final String nombre;
    private final String descripcion;

    private VehiculoTiposNovedadEnum(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
