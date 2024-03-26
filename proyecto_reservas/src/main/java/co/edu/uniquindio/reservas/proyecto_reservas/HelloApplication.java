package co.edu.uniquindio.reservas.proyecto_reservas;

import co.edu.uniquindio.reservas.proyecto_reservas.viewController.EventoViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;


public class HelloApplication extends Application {
    private Stage primaryStage;
    private Stage rootLayout;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("reservas");
        mostrarVentanaPrincipal();


    }

    public void mostrarVentanaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("empresaReservas.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            EventoViewController eventoViewController = loader.getController();
            Scene scene = new Scene((rootLayout));
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    public static void main (String[]args){
        launch();
    }
}