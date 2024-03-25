package co.edu.uniquindio.reservas.proyecto_reservas.model;

import co.edu.uniquindio.reservas.proyecto_reservas.model.services.IeventosVIPService;

import java.util.ArrayList;
import java.util.List;

public class EventoVIP implements IeventosVIPService {

    private List<Usuario> listaUsuarios= new ArrayList<>();
    private  List<Usuario> listaEmpleados= new ArrayList<>();
    private List<Usuario> listaEventos= new ArrayList<>();
    private  List<Usuario> listaReservas= new ArrayList<>();


    public void EventosVIP(){}


    // metodos de acceso
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Usuario> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Usuario> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public List<Usuario> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<Usuario> listaEventos) {
        this.listaEventos = listaEventos;
    }

    public List<Usuario> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(List<Usuario> listaReservas) {
        this.listaReservas = listaReservas;
    }



    //Sobreescribir la interfaz
    @Override
    public boolean actualizarUsuario(int id, Usuario usuario) {
        return false;
    }

    @Override
    public boolean crearUsuario(Usuario usuario) {
        return false;
    }

    @Override
    public boolean eliminarUsuario(String id) {
        return false;
    }

    @Override
    public boolean consultarUsuario(String id) {
        return false;
    }
}
