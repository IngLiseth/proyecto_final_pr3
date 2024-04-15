package co.edu.uniquindio.reservas.proyecto_reservas.model;

import co.edu.uniquindio.reservas.proyecto_reservas.Exceptions.UsuarioExceptions;
import co.edu.uniquindio.reservas.proyecto_reservas.HelloApplication;
import co.edu.uniquindio.reservas.proyecto_reservas.model.services.IeventosVIPService;

import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class EventoVIP implements IeventosVIPService, Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(EventoVIP.class.getName());

    List<Usuario> listaUsuarios= new ArrayList<>();
    List<Empleado> listaEmpleados= new ArrayList<>();
    List<Reserva> listaEventos= new ArrayList<>();
    List<Evento> listaReservas= new ArrayList<>();


//    public void EventoVIP(){
//        //UUID.randomUUID();
//    }

    public EventoVIP(){

    }

    // metodos de acceso
    public  List<Usuario> getListaUsuarios() {
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
    public boolean actualizarUsuario(String id, Usuario usuario) throws UsuarioExceptions {
        Usuario usuarioActual= obtenerUsuario(id);
        if (usuarioActual== null){
            throw new UsuarioExceptions("El usuario: "+usuario.getNombre()+" con id: "+id+ "a actualizar no existe");

        }else{
            usuarioActual.setId(usuario.getId());
            usuarioActual.setNombre(usuario.getNombre());
            usuarioActual.setCorreo(usuario.getCorreo());
            usuarioActual.setContrasena(usuario.getContrasena());
            return true;
        }
    }

    @Override
    public void crearUsuario(Usuario usuario) {
        getListaUsuarios().add(usuario);
    }

    @Override
    public boolean eliminarUsuario(String id) throws UsuarioExceptions {
        Usuario usuario = null;
        boolean flagExiste = false;
        usuario = obtenerUsuario(id);
        if(usuario == null)
            throw new UsuarioExceptions("No existe el usuario a eliminar con cedula: "+id+" ");
        else{
            getListaUsuarios().remove(usuario);
            flagExiste = true;
        }
        return flagExiste;
    }

    @Override
    public boolean consultarUsuario(String id) throws UsuarioExceptions {
        if (usuarioExiste(id)) {
            return true;
        }else {
            throw new UsuarioExceptions(" El usuario con cédula " + id + " NO  existe ");
        }
    }
    public Usuario obtenerUnUsuario(String id){
        Usuario usuarioObtenido=null;
        for(Usuario usuario:getListaUsuarios()){
            if(usuario.getId().equals(id)){
                usuarioObtenido = usuario;
                break;
            }
        }
        return usuarioObtenido;
    }

    public boolean verificarUsuarioExistente(String id)  throws UsuarioExceptions {
        if ( usuarioExiste(id)) {
            throw new UsuarioExceptions(" El usuario con cédula " + id + " ya existe ");
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
                if(usuario.getId().equals(id)){
                    usuarioEncontrado = usuario;
                    break;
                }
            }
            return usuarioEncontrado;

    }

    @Override
    public Persona validarPersona(String email, String password, String tipoPersona) {

        Persona persona = null;

        if(tipoPersona.equals("Usuario")){
            persona = buscarUsuario(email, password);
        }else if(tipoPersona.equals("Empleado")){
            persona = buscarEmpleado(email, password);
        }else if(tipoPersona.equals("Admin")){
            persona = buscarAdmin(email, password);
        }

       // String cadenas = usuario.getNombre()+"@@"+usuario.getCorreo()+"@@".... con for para hacer el segundo punto

        if(persona!=null){
            escribirLog(Level.INFO, "La persona "+persona+ " con el rol "+tipoPersona+" ha ingresado al sistema");
        }else{
            escribirLog(Level.SEVERE, "Los datos de acceso "+email+" y "+password+" son incorrectos");
        }

        return persona;
    }

    private Persona buscarAdmin(String email, String password) {
        return null;
    }

    private Persona buscarEmpleado(String email, String password) {
        for(Empleado e : listaEmpleados){
            if( e.getCorreo().equals(email) && e.getContrasena().equals(password) ){
                return e;
            }
        }
        return  null;
    }

    private Persona buscarUsuario(String email, String password) {
        for(Usuario u : listaUsuarios){
            if( u.getCorreo().equals(email) && u.getContrasena().equals(password) ){
                return u;
            }
        }
        return  null;
    }

    public void escribirLog( Level tipo, String mensaje){

        try {
            FileHandler archivo;
            URL resourceURL = HelloApplication.class.getResource("/persistencia/log/proyecto_Reservas_log.txt");
            String archivoLeido = new File(resourceURL.getFile()).getAbsolutePath();

            archivo = new FileHandler(archivoLeido, true);
            archivo.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(archivo);
            LOGGER.log(tipo, mensaje);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
