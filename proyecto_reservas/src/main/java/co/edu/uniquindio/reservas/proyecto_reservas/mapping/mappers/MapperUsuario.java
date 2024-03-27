package co.edu.uniquindio.reservas.proyecto_reservas.mapping.mappers;

import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MapperUsuario implements EventoVIPMapper {

    @Override
    public UsuarioDto usuarioToUsuarioDto(Usuario usuario) {
        UsuarioDto usuarioDto = new UsuarioDto(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getContrasena()
        );
        return usuarioDto;
    }

    @Override
    public Usuario usuarioDtoTousario(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario(
                usuarioDto.id(),
                usuarioDto.nombre(),
                usuarioDto.correo(),
                usuarioDto.contrasena()
        );
        return usuario;
    }

    @Override
    public List<UsuarioDto> ListausuarioToUsuarioDto(List<Usuario> listaUsuarios) {
        List<UsuarioDto> listaUsuariosDto= new ArrayList<>();
        for (Usuario usuario:listaUsuarios){
            listaUsuariosDto.add(usuarioToUsuarioDto(usuario));
        }
        return listaUsuariosDto;
    }

    @Override
    public List<Usuario> ListausuarioDtoTousario(List<UsuarioDto> UsuarioDto) {
        List<Usuario> listaUsuarios= new ArrayList<>();
        for (UsuarioDto usuarioDto:UsuarioDto){
            listaUsuarios.add(usuarioDtoTousario(usuarioDto));
        }
        return listaUsuarios;
    }
}
