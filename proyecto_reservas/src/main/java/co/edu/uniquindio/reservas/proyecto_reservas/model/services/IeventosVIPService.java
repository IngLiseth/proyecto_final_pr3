package co.edu.uniquindio.reservas.proyecto_reservas.model.services;

import co.edu.uniquindio.reservas.proyecto_reservas.Exceptions.UsuarioExceptions;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Usuario;

public interface IeventosVIPService {
    boolean  actualizarUsuario(String id, Usuario usuario) throws UsuarioExceptions;
    void crearUsuario(Usuario usuario);
    boolean eliminarUsuario(String id) throws UsuarioExceptions;
    boolean consultarUsuario(String id) throws UsuarioExceptions;
    Usuario obtenerUsuario(String id);

}
