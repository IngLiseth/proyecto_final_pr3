package co.edu.uniquindio.reservas.proyecto_reservas.controller;

import co.edu.uniquindio.reservas.proyecto_reservas.Exceptions.UsuarioExceptions;
import co.edu.uniquindio.reservas.proyecto_reservas.HelloApplication;
import co.edu.uniquindio.reservas.proyecto_reservas.controller.services.IModelFactoryService;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.mappers.MapperUsuario;
import co.edu.uniquindio.reservas.proyecto_reservas.model.EventoVIP;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Persona;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Usuario;
import co.edu.uniquindio.reservas.proyecto_reservas.utils.EventoVIPutils;
import co.edu.uniquindio.reservas.proyecto_reservas.utils.Persistencia;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static co.edu.uniquindio.reservas.proyecto_reservas.utils.Persistencia.cargarRecursoReservaVIPXML;


public class ModelFactoryController implements IModelFactoryService {

    EventoVIP eventoVIP;
    MapperUsuario mapperUsuario;

    public ModelFactoryController() {

        mapperUsuario = new MapperUsuario();
        //1. inicializar datos y luego guardarlo en archivos
        System.out.println("invocación clase singleton");
        //inicializarDatos();
        //salvarDatosPrueba();

        //2. Cargar los datos de los archivo
       // cargarDatosDesdeArchivos();

        //3. Guardar y Cargar el recurso serializable binario
      // cargarResourceBinario();
       //guardarResourceBinario();

        //4. Guardar y Cargar el recurso serializable XML
       //guardarResourceXML();
        cargarResourceXML();

        //Siempre se debe verificar si la raiz del recurso es null

        crearCopiaSeguridadUsuario();
        crearCopiaSeguridadXML();
        crearCopiaSeguridadBinario();
        crearCopiaSeguridadLOG();

       if(eventoVIP == null){
          inicializarDatos();
          guardarResourceXML();
        }
    }

    @Override
    public boolean actualizarUsuario(String id, UsuarioDto usuarioDto) {
        try{
            Usuario usuario =mapperUsuario.usuarioDtoTousario(usuarioDto);
            getEventoVIP().actualizarUsuario(id,usuario);
            salvarDatosPrueba();
            guardarResourceXML();
            guardarResourceBinario();
            registrarAccionesSistema("Actualizacion con exito del usuario "+usuario.getNombre()+" con cedula: "+id+" ", 1, "actualizarUsuario, Clase: ModelFactoryController ");
            return true;
        }catch (UsuarioExceptions e){
            registrarAccionesSistema(e.getMessage(), 2, "actualizarUsuario, Clase: ModelFactoryController ");
            return false;
        }
    }

    @Override
    public boolean crearUsuario(UsuarioDto usuarioDto) {
        try {
            if(!eventoVIP.verificarUsuarioExistente(usuarioDto.id())) {
                Usuario usuario = mapperUsuario.usuarioDtoTousario(usuarioDto);
                getEventoVIP().crearUsuario(usuario);
                salvarDatosPrueba();
                guardarResourceXML();
                guardarResourceBinario();
                registrarAccionesSistema("Creacion con exito del usuario "+usuario.getNombre()+" con cedula: "+usuario.getId()+" ", 1, "crearUsuario, Clase: ModelFactoriController");
            }
            return true;

        }catch (UsuarioExceptions exceptions){
            registrarAccionesSistema(exceptions.getMessage(), 2, "crearUsuario ,clase:ModelFactoryController");
            return false;
        }
    }

    @Override
    public boolean eliminarUsuario(String id) {
        boolean flagExiste = false;
        try {
            flagExiste = getEventoVIP().eliminarUsuario(id);
            salvarDatosPrueba();
            guardarResourceXML();
            guardarResourceBinario();
            registrarAccionesSistema("Eliminacion con exito del usuario con cedula: "+id+" ", 1, "eliminarUsuario, Clase: ModelFactoryController");
        } catch (UsuarioExceptions e) {
            // TODO Auto-generated catch block
            registrarAccionesSistema(e.getMessage(), 2, "eliminarUsuario, Clase: ModelFactoryController");
        }
        return flagExiste;
    }

    @Override
    public boolean consultarUsuario(String id) {
        boolean existe = false;
        try {
            existe = getEventoVIP().consultarUsuario(id);
            registrarAccionesSistema("El usuario a consultar existe con cedula: "+id+" ", 1, "consultarUsuario, Clase: ModelFactoryController");
        } catch (UsuarioExceptions e) {
            // TODO Auto-generated catch block
            registrarAccionesSistema(e.getMessage(), 2, "consultarUsuario, Clase: ModelFactoryController");
        }
        return existe;
    }

    @Override
    public Persona validarPersona(String email, String password, String tipoUsuario) {

        try {

            if(tipoUsuario==null || email.isBlank() || password.isBlank()){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alerta");
                alert.setContentText("Todos los datos son obligatorios");
                alert.show();

            }else {

                Persona persona = eventoVIP.validarPersona(email, password, tipoUsuario);

                if (persona != null) {
                    navegarVentana("empresaReservas.fxml");
                    registrarAccionesSistema("Inicio de sesión por "+tipoUsuario+ " con correo "+email, 1, "validarPersona, clase: ModelFactoryController");

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Alerta");
                    alert.setContentText("Sus datos de acceso son incorrectos");
                    alert.show();
                    registrarAccionesSistema("Datos invalidos al Inicio de sesión: "+tipoUsuario+ " con correo "+email, 2, "validarPersona, clase: ModelFactoryController");

                }
            }

        }catch (Exception e){
            e.printStackTrace();
            registrarAccionesSistema(e.getMessage(), 2, "validarPersona, clase: ModelFactoryController");
        }
        return null;
    }

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();

    }


    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }




    public List<UsuarioDto> obtenerUsuario() {
        return mapperUsuario.ListausuarioToUsuarioDto(eventoVIP.getListaUsuarios());
    }

    public UsuarioDto obtenerUnUsuariodelEvento(String id){
        return mapperUsuario.usuarioToUsuarioDto(eventoVIP.obtenerUnUsuario(id));
    }


    public EventoVIP getEventoVIP() {
        return eventoVIP;
    }

    public void setEventoVIP(EventoVIP eventoVIP) {
        this.eventoVIP = eventoVIP;
    }


    public void navegarVentana(String nombreVentana) throws Exception{

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(nombreVentana));
        Scene scene = new Scene(loader.load());

        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    private void inicializarDatos() {
        eventoVIP = EventoVIPutils.inicializar();
    }


    // peristencias ******************
    //XML
    private void guardarResourceXML() {
        Persistencia.guardarRecursoReservaVIPXML(eventoVIP);
    }
    private void cargarResourceXML() {
        eventoVIP = Persistencia.cargarRecursoReservaVIPXML();
    }

    //log
    public static void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }

    //txt
    private void salvarDatosPrueba() {
        try {
            Persistencia.guardarUsuario((ArrayList<Usuario>) getEventoVIP().getListaUsuarios());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void cargarDatosDesdeArchivos() {
        eventoVIP = new EventoVIP();
        try {
            Persistencia.cargarDatosArchivos(eventoVIP);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // binario
    private void guardarResourceBinario() {
        Persistencia.guardarRecursoEventoVIPBinario(getEventoVIP());
    }
    private void cargarResourceBinario() {
        eventoVIP = Persistencia.cargarRecursoEventoVIPBinario();
    }

    //copias
    private void crearCopiaSeguridadUsuario(){
        Persistencia.crearCopiaSeguridad(1);
    }
    private void crearCopiaSeguridadXML(){
        Persistencia.crearCopiaSeguridad(2);
    }
    private void crearCopiaSeguridadBinario(){
        Persistencia.crearCopiaSeguridad(3);
    }
    private void crearCopiaSeguridadLOG(){
        Persistencia.crearCopiaSeguridad(4);
    }

}
