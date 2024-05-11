package co.edu.uniquindio.reservas.proyecto_reservas.mapping.mappers;

import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.EventoDto;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Evento;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Usuario;

import java.util.List;

public interface EventoVIPEMapper {

    EventoDto eventoToEventoDto  (Evento evento);

    Evento eventoDtoToevento (EventoDto eventoDto);

    List<EventoDto> ListaeventoToeventoDto(List<Evento>listaEventos);

    List<Evento> ListaeventoDtoToevento(List<EventoDto>listaEventosDTO);


}
