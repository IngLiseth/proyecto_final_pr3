package co.edu.uniquindio.reservas.proyecto_reservas.model;

import java.util.ArrayList;
import java.util.List;

public class Empleado extends Persona{
    private List<Evento> listaDeEventos;
    private String roles;

    public Empleado(int id, String nombre, String correo,String roles) {
        super(id, nombre, correo);
        this.listaDeEventos=new ArrayList<>();
        this.roles= roles;
    }
    public List<Evento> getListaDeEventos() {
        return listaDeEventos;
    }

    public void agregarEventos(Evento evento) {
        this.listaDeEventos.add(evento);
    }
}
