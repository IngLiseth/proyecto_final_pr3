package co.edu.uniquindio.reservas.proyecto_reservas.viewController;

import co.edu.uniquindio.reservas.proyecto_reservas.controller.UsuarioController;
import co.edu.uniquindio.reservas.proyecto_reservas.controller.factory.ModelFactoryController;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Persona;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class UsuarioViewController {

    ModelFactoryController modelFactoryController;
UsuarioController usuarioController;
ObservableList<UsuarioDto> listaUsuariosDto = FXCollections.observableArrayList();
 UsuarioDto usuarioSeleccionado;

    @FXML
    private Button btnActualizarUsuario;

    @FXML
    private Button btnConsultarUsuario;

    @FXML
    private Button btnEliminarUsuario;

    @FXML
    private Button btnRegistrarUsuario;

    @FXML
    private TableView<UsuarioDto> tblUsuario=new TableView<>();

    @FXML
    private TableColumn<UsuarioDto, String> tcCedulaUsuario = new TableColumn<>("Cédula");

    @FXML
    private TableColumn<UsuarioDto, String> tcCorreoUsuario = new TableColumn<>("Correo");

    @FXML
    private TableColumn<UsuarioDto, String> tcNombreUsuario = new TableColumn<>("Nombre");

//    @FXML
//    private TableColumn<UsuarioDto, String> tcReservaUsuario;

    @FXML
    private TextField txtCedulaUsuario;

    @FXML
    private TextField txtContrasenaUsuario;

    @FXML
    private TextField txtCorreoUsuario;

    @FXML
    private TextField txtNombreUsuario;

    private Persona sesion;

    @FXML
    void initialize (){
        modelFactoryController = ModelFactoryController.getInstance();
        sesion = modelFactoryController.obtenerSesion();
        usuarioController= new UsuarioController();
        intiView();
    }
    private void intiView(){

        if( sesion instanceof Usuario){
            tblUsuario.setVisible(false);
        }else{

            initDataBinding();
            obtenerUsuario();
            tblUsuario.getItems().clear();
            tblUsuario.setItems(listaUsuariosDto);
            listenerSelection();
        }

    }
    @FXML
    public void crearUsuario (ActionEvent event ){agregarUsuario();}
    @FXML
    void eliminarUsuaroAction(ActionEvent event) {
        eliminarUsuario();
    }
    @FXML
    public void actualizarUsuarioAction (ActionEvent event){
        actualizarUsuario();
    }
    @FXML
    public void consultarUsuarioAction(ActionEvent event){
        consultarUsuario();
    }

    private void consultarUsuario(){
        if(usuarioController.consultarUsuario(txtCedulaUsuario.getText())){
            UsuarioDto usuarioDto= usuarioController.obtenerUnUsuario(txtCedulaUsuario.getText());
            mostrarInformacionUsuario(usuarioDto);
        }else{
            mostrarMensaje("Notificación usuario","Usuario no encontrado","El usuario no éxiste", Alert.AlertType.ERROR);
        }
    }

    private void agregarUsuario(){
       UsuarioDto usuarioDto = construirUsuarioDTO ();
       if(datosValidos(usuarioDto)){
           if (usuarioController.crearUsuario(usuarioDto)) {
             listaUsuariosDto.add(usuarioDto);
             mostrarMensaje("Notificación usuario", "Usuario creado","El usuario ha sido creado con éxito", Alert.AlertType.INFORMATION);
             limpiarCamposUsuario();
           }else {
               mostrarMensaje("Notificación usuario","Usuario no creado","El usuario no se ha creado con éxito", Alert.AlertType.ERROR);
               }
       }else {
        mostrarMensaje("Notificación usuario","Usuario no creado","Los datos ingresados son invalidos ", Alert.AlertType.ERROR);
       }

    }

    private void eliminarUsuario(){
        boolean usuarioEliminado = false;
        if(usuarioSeleccionado != null){
            if(mostrarMensajeConfirmacion("¿Estas seguro de elmininar al usuario?")){
                ModelFactoryController.registrarAccionesSistema("Se confirmo la eliminacion del usuario con id:"+usuarioSeleccionado.id()+" y de nombre:  "+ usuarioSeleccionado.nombre(),
                        1,"eliminarUsuario, clase:UsuarioVewController");
                usuarioEliminado = usuarioController.eliminarUsuario(usuarioSeleccionado.id());
                if(usuarioEliminado == true){
                    listaUsuariosDto.remove(usuarioSeleccionado);
                    usuarioSeleccionado = null;
                    tblUsuario.getSelectionModel().clearSelection();
                    limpiarCamposUsuario();
                    mostrarMensaje("Notificación usuario", "Usuario eliminado", "El usuario se ha eliminado con éxito", Alert.AlertType.INFORMATION);
                }else{
                    mostrarMensaje("Notificación usuario", "usuario no eliminado", "El usuario no se puede eliminar", Alert.AlertType.ERROR);
                }
            }
        }else{
            mostrarMensaje("Notificación usuario", "usuario no seleccionado", "Seleccionado un usuario de la lista", Alert.AlertType.WARNING);
        }
    }

    private void limpiarCamposUsuario() {
        txtNombreUsuario.setText("");
        txtCedulaUsuario.setText("");
        txtCorreoUsuario.setText("");
        txtContrasenaUsuario.setText("");

    }

    private UsuarioDto construirUsuarioDTO() {
        UsuarioDto usuarioDto = new UsuarioDto(
                txtCedulaUsuario.getText(),
                txtNombreUsuario.getText(),
                txtCorreoUsuario.getText(),
                txtContrasenaUsuario.getText()
        );
        return usuarioDto;
    }

    public void actualizarUsuario( ){
        boolean UsuarioActualizado = false;
        //1. Capturar los datos
       // String idActual = usuarioSeleccionado.id(); // actualiza todo
        UsuarioDto usuarioDto = construirUsuarioDTO();
        String idActual = usuarioDto.id(); // actualiza todo menos la cedula
        //2. verificar el empleado seleccionado
        if(usuarioSeleccionado != null){
            //3. Validar la información
            if(datosValidos(usuarioSeleccionado)){
                UsuarioActualizado = usuarioController.actualizarUsuario( idActual,usuarioDto);
                if(UsuarioActualizado){
                    listaUsuariosDto.remove(usuarioSeleccionado);
                    listaUsuariosDto.add(usuarioDto);
                    tblUsuario.refresh();
                    mostrarMensaje("Notificación usuario", "usuario actualizado", "El usuario se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                    limpiarCamposUsuario();
                }else{
                    mostrarMensaje("Notificación usuario", "usuario no actualizado", "El usuario no se ha actualizado ", Alert.AlertType.INFORMATION);
                }
            }else{
                mostrarMensaje("Notificación usuario", "usuario no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
            }

        }else{
            mostrarMensaje("Notificación usuario", "usuario no seleccionado", "Seleccionado un usuario de la lista", Alert.AlertType.WARNING);

        }

    }
    private boolean datosValidos(UsuarioDto usuarioDto) {
        String mensaje = "";
        if(usuarioDto.id() == null || usuarioDto.id().equals(""))
            mensaje += "La cédula es invalida \n" ;
        if(usuarioDto.nombre() == null || usuarioDto.nombre() .equals(""))
            mensaje += "El nombre es invalido \n" ;
        if(usuarioDto == null || usuarioDto.correo().equals(""))
            mensaje += "El correo electrónico es invalido \n" ;
        if(usuarioDto.contrasena() == null || usuarioDto.contrasena().equals(""))
            mensaje += "La contraseña es invalida \n" ;
        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificación usuario","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }




    private void initDataBinding() {

        tcCorreoUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().correo()));
        tcNombreUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tcCedulaUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().id()));
    }

    private void listenerSelection() {
        tblUsuario.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            usuarioSeleccionado = newSelection;
            mostrarInformacionUsuario(usuarioSeleccionado);
        });
    }

    private void mostrarInformacionUsuario(UsuarioDto usuarioSeleccionado) {
        if(usuarioSeleccionado != null){
            txtCedulaUsuario.setText(usuarioSeleccionado.id());
            txtContrasenaUsuario.setText(usuarioSeleccionado.contrasena());
            txtCorreoUsuario.setText(usuarioSeleccionado.correo());
            txtNombreUsuario.setText(usuarioSeleccionado.nombre());
        }
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    private void  obtenerUsuario(){
        listaUsuariosDto.addAll(usuarioController.obtenerUsuario());
    }


}
