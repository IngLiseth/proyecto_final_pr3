package co.edu.uniquindio.reservas.proyecto_reservas.controller;

import co.edu.uniquindio.reservas.proyecto_reservas.controller.factory.ModelFactoryController;
import co.edu.uniquindio.reservas.proyecto_reservas.controller.services.IEmpleadoControllerServices;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;

import java.util.List;

public class EmpleadoController implements IEmpleadoControllerServices {

    ModelFactoryController modelFactoryController;
    public EmpleadoController(){
        modelFactoryController= ModelFactoryController.getInstance();
    }

    public List<EmpleadoDto> obtenerEmpleados(){

        return  modelFactoryController.obtenerEmpleados();
    }

    public EmpleadoDto obtenerUnEmpleado(String id){
        return modelFactoryController.obtenerUnEmpleadoDelEvento(id);
    }


    @Override
    public boolean actualizaEmpleado(String id, EmpleadoDto empleadoDto) {
        return modelFactoryController.actualizaEmpleado(id,empleadoDto);
    }

    @Override
    public boolean crearEmpleado(EmpleadoDto empleadoDto) {
        return modelFactoryController.crearEmpleado(empleadoDto);
    }

    @Override
    public boolean eliminarEmpleado(String id) {
        return modelFactoryController.eliminarEmpleado(id);
    }

    @Override
    public boolean consultarEmpleado(String id) {
        return modelFactoryController.consultarEmpleado(id);
    }

    public void registrarAccionesSistema(String mensaje, int nivel, String accion){
        modelFactoryController.registrarAccionesSistema(mensaje,nivel,accion);

    }
}
