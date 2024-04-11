package co.edu.uniquindio.reservas.proyecto_reservas.controller;

import co.edu.uniquindio.reservas.proyecto_reservas.controller.services.ILoginService;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Persona;

public class InicioController implements ILoginService {

    private ModelFactoryController modelFactoryController;

    public InicioController(){
        modelFactoryController= ModelFactoryController.getInstance();
    }


    @Override
    public Persona validarPersona(String email, String password, String tipoUsuario) {
        return modelFactoryController.validarPersona(email, password, tipoUsuario);
    }
}
