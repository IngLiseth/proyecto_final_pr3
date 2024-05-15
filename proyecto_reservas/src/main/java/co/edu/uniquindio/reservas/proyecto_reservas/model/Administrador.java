package co.edu.uniquindio.reservas.proyecto_reservas.model;

import java.io.Serializable;

public class Administrador extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    public Administrador(String id, String nombre, String correo, String contrasena) {
        super(id, nombre, correo, contrasena);
    }

    public Administrador() {
    }

}
