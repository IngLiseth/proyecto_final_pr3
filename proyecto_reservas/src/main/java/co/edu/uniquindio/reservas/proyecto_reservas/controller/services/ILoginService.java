package co.edu.uniquindio.reservas.proyecto_reservas.controller.services;

import co.edu.uniquindio.reservas.proyecto_reservas.model.Persona;

public interface ILoginService {
    Persona validarPersona(String email, String password, String tipoUsuario);


}
