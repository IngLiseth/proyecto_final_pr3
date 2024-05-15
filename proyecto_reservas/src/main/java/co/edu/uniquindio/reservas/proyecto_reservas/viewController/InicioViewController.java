package co.edu.uniquindio.reservas.proyecto_reservas.viewController;

import co.edu.uniquindio.reservas.proyecto_reservas.HelloApplication;
import co.edu.uniquindio.reservas.proyecto_reservas.controller.InicioController;
import co.edu.uniquindio.reservas.proyecto_reservas.controller.factory.ModelFactoryController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InicioViewController implements Initializable {
    @FXML
    private Button buttonRegistroInicio;
    @FXML
    private ComboBox<String> listaTipos;
    @FXML
    private TextField txtCorreo;
    @FXML
    private PasswordField txtContrasena;

    private InicioController inicioController;

    public void autenticarPersona(ActionEvent actionEvent) {
        inicioController.validarPersona( txtCorreo.getText(), txtContrasena.getText(), listaTipos.getValue() );
    }

    public void registrarPersona(ActionEvent actionEvent) throws Exception {
        validacionMostrar();
    }

    private void validacionMostrar() throws Exception{
        if (listaTipos.getValue() == null || listaTipos.getValue().equals("")) {
            mostrarMensaje("Notificación ","Seleccion de tipo","Deves de seleccionar el tipo que deseas registrar", Alert.AlertType.ERROR);
        }else if(listaTipos.getValue().equals("Usuario")){
            inicioController.navegarVentanas("usuarioView.fxml");
        }else if(listaTipos.getValue().equals("Empleado")){
            mostrarMensaje("Notificación ","Selecciono un empleado","A un empleado solo lo puede registrar un administrador", Alert.AlertType.ERROR);
        }else if(listaTipos.getValue().equals("Admin")){
            mostrarMensaje("Notificación ","Selecciono un Admin","A un admin solo lo puede registrar alguien de sistemas,  preguntar a Liseth", Alert.AlertType.ERROR);
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtCorreo.setText("alfonso@gmail.com");
        txtContrasena.setText("123");

        inicioController = new InicioController();
        listaTipos.setItems(FXCollections.observableArrayList( List.of("Usuario", "Empleado", "Admin") ));
    }
}
