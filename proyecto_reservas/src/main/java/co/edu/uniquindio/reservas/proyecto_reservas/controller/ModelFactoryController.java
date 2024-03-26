package co.edu.uniquindio.reservas.proyecto_reservas.controller;

import co.edu.uniquindio.reservas.proyecto_reservas.controller.services.IModelFactoryService;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.mappers.EventoVIPMapper;
import co.edu.uniquindio.reservas.proyecto_reservas.model.EventoVIP;
import co.edu.uniquindio.reservas.proyecto_reservas.utils.EventoVIPutils;

import java.util.ArrayList;
import java.util.List;


public class ModelFactoryController implements IModelFactoryService {
    EventoVIPMapper mapper = EventoVIPMapper.INSTANCE;

    EventoVIP eventoVIP;

    @Override
    public boolean actualizarUsuario(int id, UsuarioDto usuarioDto) {
        return false;
    }

    @Override
    public boolean crearUsuario(UsuarioDto usuarioDto) {
        return false;
    }

    @Override
    public boolean eliminarUsuario(String id) {
        return false;
    }

    @Override
    public boolean consultarUsuario(String id) {
        return false;
    }

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();

    }


    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        inicializarDatos();
    }

    private void inicializarDatos() {
        eventoVIP = EventoVIPutils.inicializar();
    }

    public List<UsuarioDto> obtenerUsuario() {
        return mapper.getUsuarioDto(eventoVIP.getListaUsuarios());
    }


    public EventoVIP getEventoVIP() {
        return eventoVIP;
    }

    public void setEventoVIP(EventoVIP eventoVIP) {
        this.eventoVIP = eventoVIP;
    }

}
