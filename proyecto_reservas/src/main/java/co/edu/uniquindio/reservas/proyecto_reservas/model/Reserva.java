package co.edu.uniquindio.reservas.proyecto_reservas.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Reserva implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private Usuario usuario;
    private Evento evento;
    private LocalDate fechaDeSolicitud;
    private Estado estado;

    public Reserva(String id, Usuario usuario, Evento evento, LocalDate fechaDeSolicitud, Estado estado) {
        this.id = id;
        this.usuario = usuario;
        this.evento = evento;
        this.fechaDeSolicitud = fechaDeSolicitud;
        this.estado = estado;
    }
    public Reserva(){

    }


    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}
    public Evento getEvento() {return evento;}
    public void setEvento(Evento evento) {this.evento = evento;}
    public LocalDate getFechaDeSolicitud() {return fechaDeSolicitud;}
    public void setFechaDeSolicitud(LocalDate fechaDeSolicitud) {this.fechaDeSolicitud = fechaDeSolicitud;}
    public Estado getEstado() {return estado;}
    public void setEstado(Estado estado){this.estado = estado;}

}
