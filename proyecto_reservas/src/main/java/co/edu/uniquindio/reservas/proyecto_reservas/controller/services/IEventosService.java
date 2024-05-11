package co.edu.uniquindio.reservas.proyecto_reservas.controller.services;

import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.EventoDto;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Evento;
import org.w3c.dom.events.Event;

public interface IEventosService {
    boolean  actualizarEvento (String id, EventoDto eventoDto);
    boolean crearEvento(EventoDto eventoDto);
    boolean eliminarEvento(String id);
    boolean consultarEvento(String id);
}
