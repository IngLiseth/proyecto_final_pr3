package co.edu.uniquindio.reservas.proyecto_reservas.model;

public class Usuario extends Persona {
    Reserva reserva;

    public Usuario(int id, String nombre, String correo,Reserva reserva) {
        super(id, nombre, correo);
        this.reserva=reserva;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
