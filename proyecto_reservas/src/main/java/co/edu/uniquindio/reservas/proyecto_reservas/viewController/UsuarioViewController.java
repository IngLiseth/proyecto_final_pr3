package co.edu.uniquindio.reservas.proyecto_reservas.viewController;

import co.edu.uniquindio.reservas.proyecto_reservas.controller.UsuarioController;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    private TableView<UsuarioDto> tblUsuario;

    @FXML
    private TableColumn<UsuarioDto, String> tcCedulaUsuario;

    @FXML
    private TableColumn<UsuarioDto, String> tcCorreoUsuario;

    @FXML
    private TableColumn<UsuarioDto, String> tcNombreUsuario;

    @FXML
    private TableColumn<UsuarioDto, String> tcReservaUsuario;

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

    private void initDataBinding() {
        tcCedulaUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().id())));
        tcCorreoUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().correo()));
        tcNombreUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));

    }

    private void listenerSelection() {
        tblUsuario.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            usuarioSeleccionado = newSelection;
            mostrarInformacionEmpleado(usuarioSeleccionado);
        });
    }

    private void mostrarInformacionEmpleado(UsuarioDto usuarioSeleccionado) {
        if(usuarioSeleccionado != null){
            txtCedulaUsuario.setText(String.valueOf(usuarioSeleccionado.id()));
            txtContrasenaUsuario.setText(usuarioSeleccionado.contrasena());
            txtCorreoUsuario.setText(usuarioSeleccionado.correo());
            txtNombreUsuario.setText(usuarioSeleccionado.nombre());
        }
    }

    private void  obtenerUsuario(){
        listaUsuariosDto.addAll(usuarioController.obtenerUsuario());
    }

}
