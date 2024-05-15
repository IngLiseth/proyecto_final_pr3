package co.edu.uniquindio.reservas.proyecto_reservas.mapping.mappers;

import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Empleado;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Usuario;

import java.util.List;

public interface IEmpleadoMapper {
    EmpleadoDto empleadoToEmpleadoDto (Empleado empleado);

    Empleado empleadoDtoToEmpleado(EmpleadoDto empleadoDto);

    List<EmpleadoDto> ListaEmpleadosToEmpleadosDto(List<Empleado>listaEmpleados);

    List<Empleado> ListaEmpleadosDtoToEmpleado(List<EmpleadoDto>listaEmpleadosDTO);
}
