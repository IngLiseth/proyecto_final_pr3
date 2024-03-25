package co.edu.uniquindio.reservas.proyecto_reservas.controller;

import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.EventoVIP;
import co.edu.uniquindio.reservas.proyecto_reservas.utils.EventoVIPutils;

import java.util.ArrayList;
import java.util.List;

public class ModelFactoryController {


    EventoVIP eventoVIP;

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
        List<UsuarioDto> obteneuario = new ArrayList<>();
        return obteneuario;
    }

}
