package co.edu.uniquindio.reservas.proyecto_reservas.mapping.mappers;

import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Empleado;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MapperEmpleado implements IEmpleadoMapper{

    @Override
    public EmpleadoDto empleadoToEmpleadoDto(Empleado empleado) {
        EmpleadoDto empleadoDto = new EmpleadoDto(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getCorreo(),
                empleado.getContrasena(),
                empleado.getRoles()
        );
        return empleadoDto;
    }

    @Override
    public Empleado empleadoDtoToEmpleado(EmpleadoDto empleadoDto) {
        Empleado empleado = new Empleado(
                empleadoDto.id(),
                empleadoDto.nombre(),
                empleadoDto.correo(),
                empleadoDto.contraseña(),
                empleadoDto.roles()
        );
        return empleado;
    }

    @Override
    public List<EmpleadoDto> ListaEmpleadosToEmpleadosDto(List<Empleado> listaEmpleados) {
        List<EmpleadoDto> listaEmpleadoDto= new ArrayList<>();
        for (Empleado empleado:listaEmpleados){
            listaEmpleadoDto.add(empleadoToEmpleadoDto(empleado));
        }
        return listaEmpleadoDto;
    }

    @Override
    public List<Empleado> ListaEmpleadosDtoToEmpleado(List<EmpleadoDto> listaEmpleadosDTO) {
        List<Empleado> listEmpleados= new ArrayList<>();
        for (EmpleadoDto empleadoDto:listaEmpleadosDTO){
            listEmpleados.add(empleadoDtoToEmpleado(empleadoDto));
        }
        return listEmpleados;
    }
}
