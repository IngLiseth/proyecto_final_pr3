package co.edu.uniquindio.reservas.proyecto_reservas.viewController;

import co.edu.uniquindio.reservas.proyecto_reservas.controller.UsuarioController;
import co.edu.uniquindio.reservas.proyecto_reservas.controller.factory.ModelFactoryController;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Administrador;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Empleado;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Persona;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class EmpresaViewController {

    @FXML
    private TabPane tabs;
    private ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();

    @FXML
    void initialize (){

        Persona logueada = modelFactoryController.obtenerSesion();

        if( logueada instanceof Usuario ){
            tabs.getTabs().remove(3);
        } else if (logueada instanceof Empleado) {
            tabs.getTabs().remove(0);
            tabs.getTabs().remove(2);
        }else if (logueada instanceof Administrador) {

        }

    }

}
