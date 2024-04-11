package co.edu.uniquindio.reservas.proyecto_reservas.viewController;

import co.edu.uniquindio.reservas.proyecto_reservas.controller.InicioController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InicioViewController implements Initializable {
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

    public void registrarPersona(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicioController = new InicioController();
        listaTipos.setItems(FXCollections.observableArrayList( List.of("Usuario", "Empleado", "Admin") ));
    }
}
