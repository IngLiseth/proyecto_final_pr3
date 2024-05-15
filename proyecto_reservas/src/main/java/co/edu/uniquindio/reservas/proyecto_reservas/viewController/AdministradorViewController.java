package co.edu.uniquindio.reservas.proyecto_reservas.viewController;

import co.edu.uniquindio.reservas.proyecto_reservas.controller.EmpleadoController;
import co.edu.uniquindio.reservas.proyecto_reservas.controller.UsuarioController;
import co.edu.uniquindio.reservas.proyecto_reservas.controller.factory.ModelFactoryController;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class AdministradorViewController {


    ModelFactoryController modelFactoryController;
    EmpleadoController empleadoController;
    ObservableList<EmpleadoDto> listaEmpleadosDto = FXCollections.observableArrayList();
    EmpleadoDto empleadoSeleccionado;

    @FXML
    private Button btnActualizarERmpleado;

    @FXML
    private Button btnConsultarEmpleado;

    @FXML
    private Button btnEliminarEmpleado;

    @FXML
    private Button btnRegistrarEmpleado;

    @FXML
    private TableView<EmpleadoDto> tblEmpleado = new TableView<>();

    @FXML
    private TableColumn<EmpleadoDto, String> tcCedulaEmpleado = new TableColumn<>("Cédula");

    @FXML
    private TableColumn<EmpleadoDto, String> tcCorreoEmpleado = new TableColumn<>("Correo");;

    @FXML
    private TableColumn<EmpleadoDto, String> tcNombreEmpleado = new TableColumn<>("Nombre");;

    @FXML
    private TableColumn<EmpleadoDto, String> tcRolEmpleado = new TableColumn<>("Rol");;

    @FXML
    private TextField txtCedulaEmpleado;

    @FXML
    private TextField txtContrasenaEmpleado;

    @FXML
    private TextField txtCorreoEmpleado;

    @FXML
    private TextField txtNombreEmpleado;

    @FXML
    private TextField txtRolEmpleado;


    @FXML
    void initialize (){
        modelFactoryController = ModelFactoryController.getInstance();
       // sesion = modelFactoryController.obtenerSesion();
        empleadoController= new EmpleadoController();
        intiView();
    }
    private void intiView(){
        initDataBinding();
        obtenerEmpleado();
        tblEmpleado.getItems().clear();
        tblEmpleado.setItems(listaEmpleadosDto);
        listenerSelection();

//        if( sesion instanceof Usuario){
//            tblUsuario.setVisible(false);
//        }else{
//
//            initDataBinding();
//            obtenerUsuario();
//            tblUsuario.getItems().clear();
//            tblUsuario.setItems(listaUsuariosDto);
//            listenerSelection();
//        }

    }
    private void initDataBinding() {

        tcCorreoEmpleado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().correo()));
        tcNombreEmpleado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tcCedulaEmpleado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().id()));
        tcRolEmpleado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().roles()));
    }

    private void listenerSelection() {
        tblEmpleado.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            empleadoSeleccionado = newSelection;
            mostrarInformacionEmpleado(empleadoSeleccionado);
        });
    }
    private void mostrarInformacionEmpleado(EmpleadoDto empleadoSeleccionado) {
        if(empleadoSeleccionado != null){
            txtCedulaEmpleado.setText(empleadoSeleccionado.id());
            txtContrasenaEmpleado.setText(empleadoSeleccionado.contraseña());
            txtCorreoEmpleado.setText(empleadoSeleccionado.correo());
            txtNombreEmpleado.setText(empleadoSeleccionado.nombre());
            txtRolEmpleado.setText(empleadoSeleccionado.roles());
        }
    }

    private void  obtenerEmpleado(){
        listaEmpleadosDto.addAll(empleadoController.obtenerEmpleados());
    }

    @FXML
    void actualizarEmpleadoAction(ActionEvent event) {
        actualizarEmpleado();

    }

    @FXML
    void consultarEmpleadoAction(ActionEvent event) {
        consultarEmpleado();

    }

    @FXML
    void crearEmpleadoAction(ActionEvent event) {
        crearEmpleado();

    }

    @FXML
    void eliminarEmpleadoAction(ActionEvent event) {
        eliminarEmpleado();

    }

    private void crearEmpleado(){
        EmpleadoDto empleadoDto = construirEmpleadoDTO ();
        if(datosValidos(empleadoDto)){
            if (empleadoController.crearEmpleado(empleadoDto)) {
                listaEmpleadosDto.add(empleadoDto);
                mostrarMensaje("Notificación empleado", "Empleado creado","El empleado ha sido creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposEmpleados();
            }else {
                mostrarMensaje("Notificación empleado","Empleado no creado","El empleado no se ha creado con éxito", Alert.AlertType.ERROR);
            }
        }else {
            mostrarMensaje("Notificación empleado","Empleado no creado","Los datos ingresados son invalidos ", Alert.AlertType.ERROR);
        }

    }

    private void eliminarEmpleado(){
        boolean empleadoEliminado = false;
        if(empleadoSeleccionado != null){
            if(mostrarMensajeConfirmacion("¿Estas seguro de elmininar al empleado?")){
                empleadoController.registrarAccionesSistema("Se confirmo la eliminacion del empleado con id:"+empleadoSeleccionado.id()+" y de nombre:  "+ empleadoSeleccionado.nombre(),
                        1,"eliminarEmpleado, clase:AdministradorViewController");
                empleadoEliminado = empleadoController.eliminarEmpleado(empleadoSeleccionado.id());
                if(empleadoEliminado == true){
                    listaEmpleadosDto.remove(empleadoSeleccionado);
                    empleadoSeleccionado = null;
                    tblEmpleado.getSelectionModel().clearSelection();
                    limpiarCamposEmpleados();
                    mostrarMensaje("Notificación EMPLEADO", "EMPLEADO eliminado", "El EMPLEADO se ha eliminado con éxito", Alert.AlertType.INFORMATION);
                }else{
                    mostrarMensaje("Notificación EMPLEADO", "EMPLEADO no eliminado", "El EMPLEADO no se puede eliminar", Alert.AlertType.ERROR);
                }
            }
        }else{
            mostrarMensaje("Notificación EMPLEADO", "EMPLEADO no seleccionado", "Seleccionado un EMPLEADO de la lista", Alert.AlertType.WARNING);
        }
    }

    public void actualizarEmpleado( ){
        boolean empleadoActualizado = false;
        //1. Capturar los datos
        // String idActual = usuarioSeleccionado.id(); // actualiza todo
        EmpleadoDto empleadoDto = construirEmpleadoDTO();
        String idActual = empleadoDto.id(); // actualiza todo menos la cedula
        //2. verificar el empleado seleccionado
        if(empleadoSeleccionado != null){
            //3. Validar la información
            if(datosValidos(empleadoSeleccionado)){
                empleadoActualizado = empleadoController.actualizaEmpleado( idActual,empleadoDto);
                if(empleadoActualizado){
                    listaEmpleadosDto.remove(empleadoSeleccionado);
                    listaEmpleadosDto.add(empleadoDto);
                    tblEmpleado.refresh();
                    mostrarMensaje("Notificación Empleado", "Empleado actualizado", "El Empleado se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                    limpiarCamposEmpleados();
                }else{
                    mostrarMensaje("Notificación Empleado", "Empleado no actualizado", "El Empleado no se ha actualizado ", Alert.AlertType.INFORMATION);
                }
            }else{
                mostrarMensaje("Notificación Empleado", "usuario no Empleado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
            }

        }else{
            mostrarMensaje("Notificación Empleado", "Empleado no seleccionado", "Seleccionado un Empleado de la lista", Alert.AlertType.WARNING);

        }
    }

    private void consultarEmpleado(){
        if(empleadoController.consultarEmpleado(txtCedulaEmpleado.getText())){
            EmpleadoDto empleadoDto= empleadoController.obtenerUnEmpleado(txtCedulaEmpleado.getText());
            mostrarInformacionEmpleado(empleadoDto);
        }else{
            mostrarMensaje("Notificación Empleado","Empleado no encontrado","El Empleado no éxiste", Alert.AlertType.ERROR);
        }
    }
    private EmpleadoDto construirEmpleadoDTO() {
        EmpleadoDto empleadoDto = new EmpleadoDto(
                txtCedulaEmpleado.getText(),
                txtNombreEmpleado.getText(),
                txtCorreoEmpleado.getText(),
                txtContrasenaEmpleado.getText(),
                txtRolEmpleado.getText()
        );
        return empleadoDto;
    }

    private boolean datosValidos(EmpleadoDto empleadoDto) {
        String mensaje = "";
        if(empleadoDto.id() == null || empleadoDto.id().equals(""))
            mensaje += "La cédula es invalida \n" ;
        if(empleadoDto.nombre() == null || empleadoDto.nombre() .equals(""))
            mensaje += "El nombre es invalido \n" ;
        if(empleadoDto == null || empleadoDto.correo().equals(""))
            mensaje += "El correo electrónico es invalido \n" ;
        if(empleadoDto.contraseña() == null || empleadoDto.contraseña().equals(""))
            mensaje += "La contraseña es invalida \n" ;
        if(empleadoDto.roles() == null || empleadoDto.roles().equals(""))
            mensaje += "La contraseña es invalida \n" ;
        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificación empleado","Datos invalidos",mensaje, Alert.AlertType.WARNING);
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

    private void limpiarCamposEmpleados() {
        txtCedulaEmpleado.setText("");
        txtNombreEmpleado.setText("");
        txtCorreoEmpleado.setText("");
        txtContrasenaEmpleado.setText("");
        txtRolEmpleado.setText("");

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

}
