package co.edu.uniquindio.reservas.proyecto_reservas.model;

import co.edu.uniquindio.reservas.proyecto_reservas.Exceptions.UsuarioExceptions;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.services.IeventosVIPService;

import java.util.ArrayList;
import java.util.List;

public class EventoVIP implements IeventosVIPService {

    private List<Usuario> listaUsuarios= new ArrayList<>();
    private  List<Empleado> listaEmpleados= new ArrayList<>();
    private List<Reserva> listaEventos= new ArrayList<>();
    private  List<Evento> listaReservas= new ArrayList<>();


    public void EventosVIP(){}


    // metodos de acceso
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public List<Reserva> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<Reserva> listaEventos) {
        this.listaEventos = listaEventos;
    }

    public List<Evento> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(List<Evento> listaReservas) {
        this.listaReservas = listaReservas;
    }

    //Sobreescribir la interfaz
    @Override
    public boolean actualizarUsuario(String id, Usuario usuario) {
        return false;
    }

    @Override
    public boolean crearUsuario(Usuario usuario) {
      return true;
    }

    @Override
    public boolean eliminarUsuario(String id) throws UsuarioExceptions {
        Usuario usuario = null;
        boolean flagExiste = false;
        usuario = obtenerUsuario(id);
        if(usuario == null)
            throw new UsuarioExceptions("El usuario a eliminar no existe");
        else{
            getListaUsuarios().remove(usuario);
            flagExiste = true;
        }
        return flagExiste;
    }

    @Override
    public boolean consultarUsuario(String id) {
        return false;
    }

    public boolean verificarUsuarioExistente(String id)  throws UsuarioExceptions {
        if ( usuarioExiste(id)) {
            throw new UsuarioExceptions("El usuario con cédula" + id + "ya existe");
        }else {
            return false;
        }
    }

    private boolean usuarioExiste(String id) {
        boolean usuarioExiste = false;
        for (Usuario usuario: getListaUsuarios()) {
            if (usuario.getId().equalsIgnoreCase(id)){
                usuarioExiste = true;
                break;
            }
        }
        return  usuarioExiste;
    }

    @Override
    public Usuario obtenerUsuario(String id) {
            Usuario usuarioEncontrado = null;
            for (Usuario usuario : getListaUsuarios()) {
                if(usuario.getId().equalsIgnoreCase(id)){
                    usuarioEncontrado = usuario;
                    break;
                }
            }
            return usuarioEncontrado;

    }
}
