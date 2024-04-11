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
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.List;


public class ModelFactoryController implements IModelFactoryService {

    EventoVIP eventoVIP;
    MapperUsuario mapperUsuario;

    public ModelFactoryController() {
        inicializarDatos();
        mapperUsuario = new MapperUsuario();
    }

    @Override
    public boolean actualizarUsuario(String id, UsuarioDto usuarioDto) {
        try{
            Usuario usuario =mapperUsuario.usuarioDtoTousario(usuarioDto);
            getEventoVIP().actualizarUsuario(id,usuario);
            return true;
        }catch (UsuarioExceptions e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean crearUsuario(UsuarioDto usuarioDto) {
        try {
            if(!eventoVIP.verificarUsuarioExistente(usuarioDto.id())) {
                Usuario usuario = mapperUsuario.usuarioDtoTousario(usuarioDto);
                getEventoVIP().crearUsuario(usuario);

            }
            return true;

        }catch (UsuarioExceptions exceptions){
            exceptions.getMessage();
            return false;

        }
    }

    @Override
    public boolean eliminarUsuario(String id) {
        boolean flagExiste = false;
        try {
            flagExiste = getEventoVIP().eliminarUsuario(id);
        } catch (UsuarioExceptions e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return flagExiste;
    }

    @Override
    public boolean consultarUsuario(String id) {
        boolean existe = false;
        try {
            existe = getEventoVIP().consultarUsuario(id);
        } catch (UsuarioExceptions e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Alerta");
                    alert.setContentText("Sus datos de acceso son incorrectos");
                    alert.show();
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();

    }


    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }



    private void inicializarDatos() {
        eventoVIP = EventoVIPutils.inicializar();
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

}
