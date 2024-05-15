package co.edu.uniquindio.reservas.proyecto_reservas.controller.factory;

import co.edu.uniquindio.reservas.proyecto_reservas.Exceptions.EmpleadoExceptions;
import co.edu.uniquindio.reservas.proyecto_reservas.Exceptions.EventoExceptions;
import co.edu.uniquindio.reservas.proyecto_reservas.Exceptions.UsuarioExceptions;
import co.edu.uniquindio.reservas.proyecto_reservas.HelloApplication;
import co.edu.uniquindio.reservas.proyecto_reservas.controller.BoundedSemaphore;
import co.edu.uniquindio.reservas.proyecto_reservas.controller.services.IModelFactoryService;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.EventoDto;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.mappers.MapperEmpleado;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.mappers.MapperEvento;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.mappers.MapperUsuario;
import co.edu.uniquindio.reservas.proyecto_reservas.model.*;
import co.edu.uniquindio.reservas.proyecto_reservas.utils.EventoVIPutils;
import co.edu.uniquindio.reservas.proyecto_reservas.utils.Persistencia;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ModelFactoryController implements IModelFactoryService, Runnable {

    EventoVIP eventoVIP;
    MapperUsuario mapperUsuario;
    MapperEvento mapperEvento;
    MapperEmpleado mapperEmpleado;

    Thread hilo1GuardarXml;
    Thread hilo2SalvarLog;

    String mensaje = "";
    int nivel = 0;
    String accion = "";
    BoundedSemaphore semaphore = new BoundedSemaphore(1);

    public ModelFactoryController() {

        mapperUsuario = new MapperUsuario();
        mapperEvento = new MapperEvento();
        mapperEmpleado = new MapperEmpleado();
        //1. inicializar datos y luego guardarlo en archivos
        System.out.println("invocación clase singleton");
       // inicializarDatos();
        //salvarDatosPrueba();

        //2. Cargar los datos de los archivo
       // cargarDatosDesdeArchivos();

        //3. Guardar y Cargar el recurso serializable binario
      // cargarResourceBinario();
       //guardarResourceBinario();

        //4. Guardar y Cargar el recurso serializable XML
      // guardarResourceXML();
        cargarResourceXML();
       // cargarResourceBinario();

     //   agregarEmpleados();

        //Siempre se debe verificar si la raiz del recurso es null

//        crearCopiaSeguridadUsuario();
//        crearCopiaSeguridadXML();
//        crearCopiaSeguridadBinario();
//        crearCopiaSeguridadLOG();

//       if(eventoVIP == null){
//          inicializarDatos();
//          guardarResourceXML();
//        }
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
                    if(persona instanceof Empleado) {
                        navegarVentana("empresaReservas.fxml");
                        registrarAccionesSistema("Inicio de sesión por " + tipoUsuario + " con correo " + email, 1, "validarPersona, clase: ModelFactoryController");
                    } else if (persona instanceof Usuario) {
                        navegarVentana("empresaReservas.fxml");
                        registrarAccionesSistema("Inicio de sesión por " + tipoUsuario + " con correo " + email, 1, "validarPersona, clase: ModelFactoryController");
                    }else if (persona instanceof Administrador) {
                        navegarVentana("empresaReservas.fxml");
                        registrarAccionesSistema("Inicio de sesión por " + tipoUsuario + " con correo " + email, 1, "validarPersona, clase: ModelFactoryController");
                    } else{
                        navegarVentana("empresaReservas.fxml");
                        registrarAccionesSistema("Inicio de sesión por " + tipoUsuario + " con correo " + email, 1, "validarPersona, clase: ModelFactoryController");
                    }

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
// TODO LO RELACIONADO CON CRUD DE EVENTOS
    @Override
    public boolean actualizarEvento(String id, EventoDto eventoDto) {
        try{
            Evento evento =mapperEvento.eventoDtoToevento(eventoDto);
            getEventoVIP().actualizarEvento(id,evento);
            salvarDatosPrueba();
            guardarResourceXML();
            guardarResourceBinario();
            registrarAccionesSistema("Actualizacion con exito del evento "+evento.getNombre()+" con id: "+id+" ", 1, "actualizarEvento, Clase: ModelFactoryController ");
            return true;
        }catch (EventoExceptions e){
            registrarAccionesSistema(e.getMessage(), 2, "actualizarEvento, Clase: ModelFactoryController ");
            return false;
        }

    }

    @Override
    public boolean crearEvento(EventoDto eventoDto) {
        try {
            if(!eventoVIP.verificarEventoExistente(eventoDto.id())) {
                Evento evento = mapperEvento.eventoDtoToevento(eventoDto);
                getEventoVIP().crearEvento(evento);
                //salvarDatosPrueba();
                guardarResourceXML();
                guardarResourceBinario();
                registrarAccionesSistema("Creacion con exito del Evento "+evento.getNombre()+" con id "+evento.getId()+" ", 1, "crearEvento, Clase: ModelFactoriController");
            }
            return true;

        }catch (EventoExceptions exceptions){
            registrarAccionesSistema(exceptions.getMessage(), 2, "crearEvento ,clase:ModelFactoryController");
            return false;
        }
    }

    @Override
    public boolean eliminarEvento(String id) {
        boolean flagExiste = false;
        try {
            flagExiste = getEventoVIP().eliminarEvento(id);
            salvarDatosPrueba();
            guardarResourceXML();
            guardarResourceBinario();
            registrarAccionesSistema("Eliminacion con exito del evento con id:"+id+" ", 1, "eliminarEvento, Clase: ModelFactoryController");
        } catch (EventoExceptions e) {
            // TODO Auto-generated catch block
            registrarAccionesSistema(e.getMessage(), 2, "Eliminar Evento, Clase: ModelFactoryController");
        }
        return flagExiste;
    }

    @Override
    public boolean consultarEvento(String id) {
        boolean existe = false;
        try {
            existe = getEventoVIP().consultarEvento(id);
            registrarAccionesSistema("El evento con el id: "+id+" ", 1, "consultarEvento, Clase: ModelFactoryController");
        } catch (EventoExceptions e) {
            // TODO Auto-generated catch block
            registrarAccionesSistema(e.getMessage(), 2, "consultarEvento, Clase: ModelFactoryController");
        }
        return existe;
    }

    @Override
    public Persona obtenerSesion() {
        return Sesion.getIntancia().getPersona();
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


    public List<EventoDto> obtenerEvento() {
        return mapperEvento.ListaeventoToeventoDto(eventoVIP.getListaEventos());
    }
    public List<EventoDto> obtenerEventoPorCapasidad(int capasidad) {
        List<EventoDto> eventosCapasidad = mapperEvento.ListaeventoToeventoDto(eventoVIP.obtenerEventosPorCapasidad(capasidad));
        if (eventosCapasidad == null || eventosCapasidad.isEmpty()) {
            registrarAccionesSistema("No hay eventos con capasidades: "+capasidad+" ", 1, "obtenerEventoPorCapasidad, Clase: ModelFactoryController");
        } else {
            registrarAccionesSistema("Si hay eventos con capasidades: "+capasidad+" ", 1, "obtenerEventoPorCapasidad, Clase: ModelFactoryController");
        }
        return eventosCapasidad;
    }
    public List<EventoDto> obtenerEventoPorFecha(String  fecha) {
        List<EventoDto> eventosCapasidad = mapperEvento.ListaeventoToeventoDto(eventoVIP.obtenerEventosPorFecha(fecha));
        if (eventosCapasidad == null || eventosCapasidad.isEmpty()) {
            registrarAccionesSistema("No hay eventos con fecha: "+fecha+" ", 1, "obtenerEventoPorFecha, Clase: ModelFactoryController");
        } else {
            registrarAccionesSistema("Si hay eventos con fecha: "+fecha+" ", 1, "obtenerEventoPorFecha, Clase: ModelFactoryController");
        }
        return eventosCapasidad;
    }
    public List<EventoDto> obtenerEventoPorEmpleado(String empleado) {
        List<EventoDto> eventosCapasidad = mapperEvento.ListaeventoToeventoDto(eventoVIP.obtenerEventosPorEmpleado(empleado));
        if (eventosCapasidad == null || eventosCapasidad.isEmpty()) {
            registrarAccionesSistema("No hay eventos con el empleado: "+empleado+" ", 1, "obtenerEventoPorEmpleado, Clase: ModelFactoryController");
        } else {
            registrarAccionesSistema("Si hay eventos con el empleado: "+empleado+" ", 1, "obtenerEventoPorEmpleado, Clase: ModelFactoryController");
        }
        return eventosCapasidad;
    }
    public List<EventoDto> obtenerEventoDisponoble() {
        return mapperEvento.ListaeventoToeventoDto(eventoVIP.getListaEventosDsiponibles());
    }

    public List<Empleado> obtenerEmpleadoStr(){

        List<Empleado> empleados=new ArrayList<>();
        for(Empleado emp:eventoVIP.getListaEmpleados() ){
            empleados.add(emp);
        }
        return empleados;
    }
    public Empleado obtenerUNempleado(String nombre){
        Empleado empleado = null;
        for(Empleado emp:eventoVIP.getListaEmpleados() ){
            if (emp.getNombre().equals(nombre)){
                empleado=emp;
                break;
            }
        }
        return empleado;
    }

    public void agregarEmpleados(){

        Empleado empleado1 = new Empleado("23","alfonso","asd@23","abc","mesero");
        Empleado empleado2 = new Empleado("34","juan","aaaa@23","abc","vendedor");

        if(!existeEmpleado("23")){
            eventoVIP.getListaEmpleados().add(empleado1);
        }

        if(!existeEmpleado("34")){
            eventoVIP.getListaEmpleados().add(empleado2);
        }
    }

    public boolean existeEmpleado(String id){

        boolean existe = false;

        for( Empleado e : getEventoVIP().getListaEmpleados() ){
            if(e.getId().equals(id)){
                existe = true;
            }
        }

        return existe;
    }
    public EventoDto obtenerUnEvento(String id){
        return mapperEvento.eventoToEventoDto(eventoVIP.obtenerUnEvento(id));
    }

    // todo sobre el crud del empleado manejado por el administrador

    @Override
    public boolean actualizaEmpleado(String id, EmpleadoDto empleadoDto) {
        try{
            Empleado empleado =mapperEmpleado.empleadoDtoToEmpleado(empleadoDto);
            getEventoVIP().actualizarEmpleado(id,empleado);
            salvarDatosPrueba();
            guardarResourceXML();
            guardarResourceBinario();
            registrarAccionesSistema("Actualizacion con exito del empleado "+empleado.getNombre()+" con cedula: "+id+" ", 1, "actualizaEmpleado, Clase: ModelFactoryController ");
            return true;
        }catch (EmpleadoExceptions e){
            registrarAccionesSistema(e.getMessage(), 2, "actualizarUsuario, Clase: ModelFactoryController ");
            return false;
        }
    }

    @Override
    public boolean crearEmpleado(EmpleadoDto empleadoDto) {
        try {
            if(!eventoVIP.verificarEmpleadoExiate(empleadoDto.id())) {
                Empleado empleado = mapperEmpleado.empleadoDtoToEmpleado(empleadoDto);
                getEventoVIP().crearEmpleado(empleado);
                salvarDatosPrueba();
                guardarResourceXML();
                guardarResourceBinario();
                registrarAccionesSistema("Creacion con exito del empleado "+empleado.getNombre()+" con cedula: "+empleado.getId()+" ", 1, "crearEmpleado, Clase: ModelFactoriController");
            }
            return true;

        }catch (EmpleadoExceptions exceptions){
            registrarAccionesSistema(exceptions.getMessage(), 2, "crearEmpleado ,clase:ModelFactoryController");
            return false;
        }
    }

    @Override
    public boolean eliminarEmpleado(String id) {
        boolean flagExiste = false;
        try {
            flagExiste = getEventoVIP().eliminarEmpleado(id);
            salvarDatosPrueba();
            guardarResourceXML();
            guardarResourceBinario();
            registrarAccionesSistema("Eliminacion con exito del empleado con cedula: "+id+" ", 1, "eliminarEmpleado, Clase: ModelFactoryController");
        } catch (EmpleadoExceptions e) {
            // TODO Auto-generated catch block
            registrarAccionesSistema(e.getMessage(), 2, "eliminarEmpleado, Clase: ModelFactoryController");
        }
        return flagExiste;
    }

    @Override
    public boolean consultarEmpleado(String id) {
        boolean existe = false;
        try {
            existe = getEventoVIP().consultarEmpleado(id);
            registrarAccionesSistema("El EMPLEADO a consultar existe con cedula: "+id+" ", 1, "consultarEmpleado, Clase: ModelFactoryController");
        } catch (EmpleadoExceptions e) {
            // TODO Auto-generated catch block
            registrarAccionesSistema(e.getMessage(), 2, "consultarEmpleado, Clase: ModelFactoryController");
        }
        return existe;
    }

    public List<EmpleadoDto> obtenerEmpleados() {
        return mapperEmpleado.ListaEmpleadosToEmpleadosDto(eventoVIP.getListaEmpleados());
    }

    public EmpleadoDto obtenerUnEmpleadoDelEvento(String id){
        return mapperEmpleado.empleadoToEmpleadoDto(eventoVIP.obtenerUnEmpleado(id));
    }




    // Todo sobre peristencias ******************
    //XML

    private void cargarResourceXML() {
        eventoVIP = Persistencia.cargarRecursoReservaVIPXML();
    }

    //log


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


    //Todo sobre la implementacion de hilos para manejar archivos de persistencia, xml y logs

    private void guardarResourceXML() {
        hilo1GuardarXml = new Thread(this);
        hilo1GuardarXml.start();
    }
    public  void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        this.mensaje = mensaje;
        this.nivel = nivel;
        this.accion = accion;
        hilo2SalvarLog =  new Thread(this);
        hilo2SalvarLog.start();
    }

    @Override
    public void run() {
        Thread hiloActual = Thread.currentThread();

        ocupar();
        if(hiloActual == hilo1GuardarXml){
            Persistencia.guardarRecursoReservaVIPXML(eventoVIP);
            liberar();
        }
        if(hiloActual == hilo2SalvarLog){
            Persistencia.guardaRegistroLog(mensaje, nivel, accion);
            liberar();
        }
    }
    private void liberar() {
        try {
            semaphore.liberar();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void ocupar() {
        try {
            semaphore.ocupar();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
