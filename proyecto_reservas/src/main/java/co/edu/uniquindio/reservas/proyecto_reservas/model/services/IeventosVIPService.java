package co.edu.uniquindio.reservas.proyecto_reservas.model.services;

import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Usuario;

public interface IeventosVIPService {
    boolean  actualizarUsuario(int id, Usuario usuario);
    boolean crearUsuario(Usuario usuario);
    boolean eliminarUsuario(String id);
    boolean consultarUsuario(String id);

}
