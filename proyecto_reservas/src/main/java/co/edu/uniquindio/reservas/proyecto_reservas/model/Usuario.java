package co.edu.uniquindio.reservas.proyecto_reservas.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Persona {

    private List<Reserva> reservasDelUsuario;

    public Usuario(int id, String nombre, String correo) {
        super(id, nombre, correo);
        this.reservasDelUsuario=new ArrayList<>();
    }

    public List<Reserva> getReservasDelUsuario() {
        return reservasDelUsuario;
    }

    public void agregarReservasAlUsuario(Reserva reserva) {
        this.reservasDelUsuario.add(reserva);
    }
}
