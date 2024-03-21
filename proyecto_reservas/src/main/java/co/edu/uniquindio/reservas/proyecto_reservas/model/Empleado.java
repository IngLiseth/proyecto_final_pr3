package co.edu.uniquindio.reservas.proyecto_reservas.model;

public class Empleado extends Persona{
    Evento evento;

    public Empleado(int id, String nombre, String correo ,Evento evento) {
        super(id, nombre, correo);
        this.evento=evento;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
