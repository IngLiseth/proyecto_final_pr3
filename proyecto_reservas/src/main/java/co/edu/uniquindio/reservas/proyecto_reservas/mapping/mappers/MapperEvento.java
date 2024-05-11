package co.edu.uniquindio.reservas.proyecto_reservas.mapping.mappers;

import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.EventoDto;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Evento;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MapperEvento implements EventoVIPEMapper{
    EventoDto eventoDto;
    @Override
    public EventoDto eventoToEventoDto(Evento evento) {
        EventoDto eventoDto = new EventoDto(
                evento.getId(),
                evento.getNombre(),
                evento.getDescripcion(),
                evento.getFecha(),
                evento.getCapacidadMaxima(),
                evento.getEmpleadoEncargado()
        );
        return eventoDto;
    }
    @Override
    public Evento eventoDtoToevento(EventoDto eventoDto) {
        Evento evento = new Evento(
                eventoDto.id(),
                eventoDto.nombre(),
                eventoDto.descripcion(),
                eventoDto.fecha(),
                eventoDto.capacidadMaxima(),
                eventoDto.empleadoEncargado()
        );
        return evento;
    }

    @Override
    public List<EventoDto> ListaeventoToeventoDto(List<Evento> listaEventos) {
        List<EventoDto> listaEventosDto= new ArrayList<>();
        for (Evento evento: listaEventos){
            listaEventosDto.add(eventoToEventoDto(evento));
        }
        return listaEventosDto;
    }

    @Override
    public List<Evento> ListaeventoDtoToevento(List<EventoDto> listaEventosDTO) {
        List<Evento> listaEventos= new ArrayList<>();
        for (EventoDto eventoDto:listaEventosDTO){
            listaEventos.add(eventoDtoToevento(eventoDto));
        }
        return listaEventos;

    }
}
