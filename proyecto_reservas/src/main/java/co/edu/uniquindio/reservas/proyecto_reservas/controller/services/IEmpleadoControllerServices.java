package co.edu.uniquindio.reservas.proyecto_reservas.controller.services;

import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;

public interface IEmpleadoControllerServices {
    boolean  actualizaEmpleado(String id, EmpleadoDto empleadoDto);
    boolean crearEmpleado(EmpleadoDto empleadoDto);
    boolean eliminarEmpleado(String id);
    boolean consultarEmpleado(String id);
}
