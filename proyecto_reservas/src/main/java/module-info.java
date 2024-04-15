module co.edu.uniquindio.reservas.proyecto_reservas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.mapstruct;
    requires java.logging;
    requires java.desktop;

    opens co.edu.uniquindio.reservas.proyecto_reservas to javafx.fxml;
    exports co.edu.uniquindio.reservas.proyecto_reservas;
    exports co.edu.uniquindio.reservas.proyecto_reservas.viewController to javafx.fxml;
    exports co.edu.uniquindio.reservas.proyecto_reservas.controller;
    exports co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto;
    exports co.edu.uniquindio.reservas.proyecto_reservas.mapping.mappers;
    exports co.edu.uniquindio.reservas.proyecto_reservas.model;
    opens co.edu.uniquindio.reservas.proyecto_reservas.controller to javafx.fxml;
    opens co.edu.uniquindio.reservas.proyecto_reservas.viewController to javafx.fxml;
    exports co.edu.uniquindio.reservas.proyecto_reservas.controller.factory;
    opens co.edu.uniquindio.reservas.proyecto_reservas.controller.factory to javafx.fxml;

}