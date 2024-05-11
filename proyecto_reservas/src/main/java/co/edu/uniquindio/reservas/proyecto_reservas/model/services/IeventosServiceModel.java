package co.edu.uniquindio.reservas.proyecto_reservas.model.services;

import co.edu.uniquindio.reservas.proyecto_reservas.Exceptions.EventoExceptions;
import co.edu.uniquindio.reservas.proyecto_reservas.Exceptions.UsuarioExceptions;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.EventoDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Evento;
import org.w3c.dom.events.Event;

public interface IeventosServiceModel {
    boolean  actualizarEvento (String id, Evento evento)throws EventoExceptions;
    boolean crearEvento(Evento evento);
    boolean eliminarEvento(String id) throws EventoExceptions;
    boolean consultarEvento(String id) throws EventoExceptions;
    Evento obtenerEvento(String id);
}
