package co.edu.uniquindio.reservas.proyecto_reservas.controller;

import co.edu.uniquindio.reservas.proyecto_reservas.controller.factory.ModelFactoryController;
import co.edu.uniquindio.reservas.proyecto_reservas.controller.services.IEventosService;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.EventoDto;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Empleado;

import java.util.List;

public class EventoController implements IEventosService {
    ModelFactoryController modelFactoryController;
    public EventoController()
    {
        modelFactoryController= ModelFactoryController.getInstance();
    }
    public List<EventoDto> obtenerEvento(){
        return  modelFactoryController.obtenerEvento();
    }

    @Override
    public boolean actualizarEvento(String id, EventoDto eventoDto) {
        return modelFactoryController.actualizarEvento(id,eventoDto);
    }


    @Override
    public boolean crearEvento(EventoDto eventoDto) {

        return modelFactoryController.crearEvento(eventoDto);
    }

    @Override
    public boolean eliminarEvento(String id) {
        return modelFactoryController.eliminarEvento(id);
    }

    @Override
    public boolean consultarEvento(String id) {
        return modelFactoryController.consultarEvento(id);
    }
    public EventoDto obtenerUnEvento(String id){
        return modelFactoryController.obtenerUnEvento(id);
    }


}
