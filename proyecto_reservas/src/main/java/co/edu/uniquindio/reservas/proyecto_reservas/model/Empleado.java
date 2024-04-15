package co.edu.uniquindio.reservas.proyecto_reservas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Empleado extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Evento> listaDeEventos;

    public void setListaDeEventos(List<Evento> listaDeEventos) {
        this.listaDeEventos = listaDeEventos;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    private String roles;

    public Empleado(String id, String nombre, String correo, String contrasena, String roles) {
        super(id, nombre, correo, contrasena);
        this.listaDeEventos=new ArrayList<>();
        this.roles= roles;
    }
    public Empleado(){

    }
    public List<Evento> getListaDeEventos() {
        return listaDeEventos;
    }

    public void agregarEventos(Evento evento) {
        this.listaDeEventos.add(evento);
    }
}
