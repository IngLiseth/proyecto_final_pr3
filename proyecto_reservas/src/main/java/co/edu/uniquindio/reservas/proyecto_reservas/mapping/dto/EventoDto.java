package co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto;

import co.edu.uniquindio.reservas.proyecto_reservas.model.Empleado;

import java.time.LocalDate;

public record EventoDto(
        String id,
        String nombre,
        String descripcion,
        LocalDate fecha,
        int capacidadMaxima,
        Empleado empleadoEncargado

) {

}
