package co.edu.uniquindio.reservas.proyecto_reservas.controller.services;

import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;

public interface IUsuarioService {

    boolean  actualizarUsuario(String id, UsuarioDto usuarioDto);
    boolean crearUsuario(UsuarioDto usuarioDto);
    boolean eliminarUsuario(String id);
    boolean consultarUsuario(String id);
}
