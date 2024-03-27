package co.edu.uniquindio.reservas.proyecto_reservas.viewController;

import co.edu.uniquindio.reservas.proyecto_reservas.controller.UsuarioController;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class UsuarioViewController {

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

    @FXML
    void initialize (){
        usuarioController= new UsuarioController();
        intiView();
    }
    private void intiView(){

        initDataBinding();
        obtenerUsuario();
        tblUsuario.getItems().clear();
        tblUsuario.setItems(listaUsuariosDto);
        listenerSelection();
    }
    @FXML
    public void crearUsuario (ActionEvent event ){
        agregarUsuario();

    }
    @FXML
    void eliminarUsuaroAction(ActionEvent event) {
        eliminarUsuario();
    }
    @FXML
    public void actualizarUsuarioAction (ActionEvent event){
        actualizarUsuario();
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
        String idActual = usuarioSeleccionado.id();
        UsuarioDto usuarioDto = construirUsuarioDTO();
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

        }




    }
    private boolean datosValidos(UsuarioDto usuarioDto) {
        String mensaje = "";
        if(usuarioDto.id() == null || usuarioDto.id().equals(""))
            mensaje += "La cédula es invalida \n" ;
        if(usuarioDto.nombre() == null || usuarioDto.nombre() .equals(""))
            mensaje += "El nombre es invalido \n" ;
        if(usuarioDto.correo() == null || usuarioDto.correo().equals(""))
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
            mostrarInformacionEmpleado(usuarioSeleccionado);
        });
    }

    private void mostrarInformacionEmpleado(UsuarioDto usuarioSeleccionado) {
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
