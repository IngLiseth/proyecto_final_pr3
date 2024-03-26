package co.edu.uniquindio.reservas.proyecto_reservas.mapping.mappers;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Usuario;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EventoVIPMapper {
    EventoVIPMapper INSTANCE = Mappers.getMapper(EventoVIPMapper.class);
    @Named("usuarioToUsuarioDto")
    UsuarioDto usuarioToUsuarioDto (Usuario usuario);

    @Named("usuarioDtoTousario")
    Usuario usuarioDtoTousario (UsuarioDto usuarioDto);

    @IterableMapping (qualifiedByName ="usuarioToUsuarioDto" )
    List<UsuarioDto> getUsuarioDto(List<Usuario>listaUsuarios);

    @IterableMapping (qualifiedByName ="usuarioDtoTousario" )
    List<Usuario> getUsuario(List<UsuarioDto>listaUsuariosDTO);


}
