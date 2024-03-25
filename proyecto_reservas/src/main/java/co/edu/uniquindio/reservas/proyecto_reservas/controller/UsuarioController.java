package co.edu.uniquindio.reservas.proyecto_reservas.controller;

import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Usuario;
import co.edu.uniquindio.reservas.proyecto_reservas.model.services.IeventosVIPService;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController  implements IeventosVIPService {


    ModelFactoryController modelFactoryController;

    public List<UsuarioDto> obtenerUsuario(){
        return  modelFactoryController.obtenerUsuario();
    }

    public UsuarioController(){
        modelFactoryController= ModelFactoryController.getInstance();
    }

    @Override
    public boolean actualizarUsuario(int id, Usuario usuario) {
        return false;
    }

    @Override
    public boolean crearUsuario(Usuario usuario) {
        return false;
    }

    @Override
    public boolean eliminarUsuario(String id) {
        return false;
    }

    @Override
    public boolean consultarUsuario(String id) {
        return false;
    }
}
