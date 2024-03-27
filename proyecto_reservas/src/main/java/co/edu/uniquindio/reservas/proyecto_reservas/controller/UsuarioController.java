package co.edu.uniquindio.reservas.proyecto_reservas.controller;

import co.edu.uniquindio.reservas.proyecto_reservas.controller.services.IModelFactoryService;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Usuario;
import co.edu.uniquindio.reservas.proyecto_reservas.model.services.IeventosVIPService;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController  implements IModelFactoryService {
    ModelFactoryController modelFactoryController;
    public UsuarioController(){
        modelFactoryController= ModelFactoryController.getInstance();
    }

    public List<UsuarioDto> obtenerUsuario(){
        return  modelFactoryController.obtenerUsuario();
    }

    public UsuarioDto obtenerUnUsuario(String id){
        return modelFactoryController.obtenerUnUsuariodelEvento(id);
    }


    @Override
    public boolean actualizarUsuario(String id, UsuarioDto usuarioDto) {
        return modelFactoryController.actualizarUsuario(id,usuarioDto);
    }

    @Override
    public boolean crearUsuario(UsuarioDto usuarioDto) {
        return modelFactoryController.crearUsuario(usuarioDto);
    }

    @Override
    public boolean eliminarUsuario(String id) {
        return modelFactoryController.eliminarUsuario(id);
    }

    @Override
    public boolean consultarUsuario(String id) {
        return modelFactoryController.consultarUsuario(id);
    }

}
