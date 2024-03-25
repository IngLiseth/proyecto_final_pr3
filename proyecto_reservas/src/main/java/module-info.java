module co.edu.uniquindio.reservas.proyecto_reservas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens co.edu.uniquindio.reservas.proyecto_reservas to javafx.fxml;
    exports co.edu.uniquindio.reservas.proyecto_reservas;
    exports co.edu.uniquindio.reservas.proyecto_reservas.viewController;
    opens co.edu.uniquindio.reservas.proyecto_reservas.viewController to javafx.fxml;
}