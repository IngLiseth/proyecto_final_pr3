package co.edu.uniquindio.reservas.proyecto_reservas.model;

import java.time.LocalDate;

public class Reserva {
    private int id;
    private Usuario usuario;
    private Evento evento;
    private LocalDate fechaDeSolicitud;
    private Estado estado;

    public Reserva(int id, Usuario usuario, Evento evento, LocalDate fechaDeSolicitud, Estado estado) {
        this.id = id;
        this.usuario = usuario;
        this.evento = evento;
        this.fechaDeSolicitud = fechaDeSolicitud;
        this.estado = estado;
    }


    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}
    public Evento getEvento() {return evento;}
    public void setEvento(Evento evento) {this.evento = evento;}
    public LocalDate getFechaDeSolicitud() {return fechaDeSolicitud;}
    public void setFechaDeSolicitud(LocalDate fechaDeSolicitud) {this.fechaDeSolicitud = fechaDeSolicitud;}
    public Estado getEstado() {return estado;}
    public void setEstado(Estado estado){this.estado = estado;}

}
