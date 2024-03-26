package co.edu.uniquindio.reservas.proyecto_reservas.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Persona {

    private String contrasena;
    private List<Reserva> reservasDelUsuario;

    public Usuario(String id, String nombre, String correo,String contrasena) {
        super(id, nombre, correo);
        this.contrasena=contrasena;
        this.reservasDelUsuario=new ArrayList<>();
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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
