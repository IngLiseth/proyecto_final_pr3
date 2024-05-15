package co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto;

import co.edu.uniquindio.reservas.proyecto_reservas.model.Evento;

import java.util.List;

public record EmpleadoDto(

        String id,
        String nombre,
        String correo,
        String contraseña,
        String roles
) {

}


