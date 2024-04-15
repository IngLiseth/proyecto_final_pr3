package co.edu.uniquindio.reservas.proyecto_reservas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Reserva> reservasDelUsuario;

    public Usuario(String id, String nombre, String correo,String contrasena) {
        super(id, nombre, correo,contrasena);
        this.reservasDelUsuario=new ArrayList<>();
    }
    public Usuario(){

    }


    public void setReservasDelUsuario(List<Reserva> reservasDelUsuario) {
        this.reservasDelUsuario = reservasDelUsuario;
    }

    public List<Reserva> getReservasDelUsuario() {
        return reservasDelUsuario;
    }

    public void agregarReservasAlUsuario(Reserva reserva) {
        this.reservasDelUsuario.add(reserva);
    }
}
