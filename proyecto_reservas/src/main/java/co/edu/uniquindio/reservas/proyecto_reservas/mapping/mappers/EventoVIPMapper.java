package co.edu.uniquindio.reservas.proyecto_reservas.mapping.mappers;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Usuario;

import java.util.List;

public interface EventoVIPMapper {

    UsuarioDto usuarioToUsuarioDto (Usuario usuario);

    Usuario usuarioDtoTousario (UsuarioDto usuarioDto);

    List<UsuarioDto> ListausuarioToUsuarioDto(List<Usuario>listaUsuarios);

    List<Usuario> ListausuarioDtoTousario(List<UsuarioDto>listaUsuariosDTO);


}
