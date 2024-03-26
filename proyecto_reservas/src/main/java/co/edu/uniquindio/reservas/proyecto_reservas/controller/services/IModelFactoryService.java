package co.edu.uniquindio.reservas.proyecto_reservas.controller.services;

import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Usuario;

public interface IModelFactoryService {
    boolean  actualizarUsuario(int id, UsuarioDto usuarioDto);
    boolean crearUsuario(UsuarioDto usuarioDto);
    boolean eliminarUsuario(String id);
    boolean consultarUsuario(String id);

}
