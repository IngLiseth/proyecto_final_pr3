package co.edu.uniquindio.reservas.proyecto_reservas.viewController;

import co.edu.uniquindio.reservas.proyecto_reservas.controller.EventoController;
import co.edu.uniquindio.reservas.proyecto_reservas.controller.factory.ModelFactoryController;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.EventoDto;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.*;
import co.edu.uniquindio.reservas.proyecto_reservas.utils.EventoVIPutils;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.CheckComboBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventoViewController {
    ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();
    EventoController eventoController;
    EventoDto eventoSeleccionado;
    ObservableList<EventoDto> listaEventosDto = FXCollections.observableArrayList();
    ObservableList<Empleado> listaEmpleado = FXCollections.observableArrayList();

    @FXML
    private Button actualizarEvento;

    @FXML
    private ComboBox<Empleado> boxEmpleadoEncargadoEvento;

    @FXML
    private TableColumn<EventoDto, String> colCapacidadMax = new TableColumn<>("Capasidad");;

    @FXML
    private TableColumn<EventoDto, String> colDescripcion = new TableColumn<>("Descripcion");;

    @FXML
    private TableColumn<EventoDto, String> colEmpleadoEncargado = new TableColumn<>("Empleado");;

    @FXML
    private TableColumn<EventoDto, String> colFecha = new TableColumn<>("Fecha");;

    @FXML
    private TableColumn<EventoDto, String> colId = new TableColumn<>("Id");;

    @FXML
    private TableColumn<EventoDto, String> colNombre = new TableColumn<>("Nombre");;

    @FXML
    private Button consultarEvento;

    @FXML
    private Button eliminarEvento;

    @FXML
    private DatePicker ldateFechaEvento;

    @FXML
    private Button registrarEvento;

    @FXML
    private TableView<EventoDto> tblEventos = new TableView<>();

    @FXML
    private TextField txtCapacidadMaximaEvento ;

    @FXML
    private TextField txtDescripcionEvento;

    @FXML
    private TextField txtIdEvento;

    @FXML
    private TextField txtNombreEvento;

    @FXML
    private GridPane gridPaneEvento;
    @FXML
    private Label labelEventosDisponibles;
    @FXML
    private Label lblEmpleadoEncargado;

    @FXML
    void actualizarEvento(ActionEvent event) { actualizarEvento();

    }

    @FXML
    void consultarEvento(ActionEvent event) {
        consultarEvento();
    }

    @FXML
    void eliminarEvento(ActionEvent event) {eliminarEvento();}

    @FXML
    void registrarEvento(ActionEvent event) {agregarEvento();}

    private Persona sesion;
    @FXML
    void initialize() {
        modelFactoryController = ModelFactoryController.getInstance();
        sesion = modelFactoryController.obtenerSesion();
        eventoController = new EventoController();
        intiView();
    }

    private void intiView() {

        if( sesion instanceof Usuario){
            gridPaneEvento.setVisible(false);
            consultarEvento.setVisible(false);
            eliminarEvento.setVisible(false);
            registrarEvento.setVisible(false);
            actualizarEvento.setVisible(false);
            obtenerEventoDisponible();

        }else if( sesion instanceof Empleado){
            obtenerEvento();
            listaEmpleado = FXCollections.observableArrayList( modelFactoryController.obtenerEmpleadoStr() );
            boxEmpleadoEncargadoEvento.setItems(listaEmpleado);

        }else{
            labelEventosDisponibles.setVisible(false);
            obtenerEvento();
            System.out.println(modelFactoryController.obtenerEmpleadoStr()  );
            listaEmpleado = FXCollections.observableArrayList( modelFactoryController.obtenerEmpleadoStr() );
            boxEmpleadoEncargadoEvento.setItems(listaEmpleado);
        }
        initDataBinding();
        tblEventos.getItems().clear();
        tblEventos.setItems(listaEventosDto);
        listenerSelection();

//        initDataBinding();
//        obtenerEvento();
//        tblEventos.getItems().clear();
//        tblEventos.setItems(listaEventosDto);
//        System.out.println(modelFactoryController.obtenerEmpleadoStr()  );
//        listaEmpleado = FXCollections.observableArrayList( modelFactoryController.obtenerEmpleadoStr() );
//        boxEmpleadoEncargadoEvento.setItems(listaEmpleado);
//        listenerSelection();
    }

    private void agregarEvento() {
        EventoDto eventoDto = construirEventoDTO();
        if (datosValidos(eventoDto)) {
            if (eventoController.crearEvento(eventoDto)) {
                listaEventosDto.add(eventoDto);
                mostrarMensaje("Notificación evento", "Evento creado", "El evento ha sido creado con éxito", Alert.AlertType.INFORMATION);
                limpiarCamposEvento();
            } else {
                mostrarMensaje("Notificación evento", "Evento no creado", "El evento no se ha creado con éxito", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Notificación Evento", "Evento no creado", "Los datos ingresados son invalidos ", Alert.AlertType.ERROR);
        }

    }

    private EventoDto construirEventoDTO() {
        // Obtener el valor de la fecha del DatePicker
        LocalDate fechaEvento = ldateFechaEvento.getValue();
        // Crear un formateador de fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Convertir la fecha a String utilizando el formateador
        String fechaEventoStr = fechaEvento.format(formatter);

        EventoDto eventoDto = new EventoDto(
                txtIdEvento.getText(),
                txtNombreEvento.getText(),
                txtDescripcionEvento.getText(),
                fechaEventoStr,
                Integer.parseInt(txtCapacidadMaximaEvento.getText()),
                boxEmpleadoEncargadoEvento.getValue()

        );
        System.out.println(ldateFechaEvento.getValue());
        return eventoDto;
    }


    private boolean datosValidos(EventoDto eventoDto) {
        String mensaje = "";
        if(eventoDto.id() == null || eventoDto.id().equals(""))
            mensaje += " el id es invalido \n" ;
        if(eventoDto.nombre() == null || eventoDto.nombre() .equals(""))
            mensaje += "El nombre es invalido \n" ;
        if(eventoDto.descripcion() == null || eventoDto.descripcion().equals(""))
            mensaje += "no se ha ingresado descripcion \n" ;
        if(eventoDto.fecha() == null || eventoDto.fecha().equals(""))
            mensaje += "no se ha ingresado fecha \n" ;
        if(eventoDto.capacidadMaxima() <= 0)
            mensaje += "la capacidad maxima no puede ser menor o igual a cero  \n" ;
        if(eventoDto.empleadoEncargado() == null || eventoDto.empleadoEncargado().equals(""))
            mensaje += "no se ha ingresado el empleado encargado del evento \n" ;
        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificación evento","Datos invalidos",mensaje, Alert.AlertType.WARNING);
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

        colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().id()));
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        colDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().descripcion()));
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fecha().toString()));
        colCapacidadMax.setCellValueFactory(cellData -> new SimpleStringProperty(""+cellData.getValue().capacidadMaxima()));
        colEmpleadoEncargado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().empleadoEncargado().getNombre()));

    }
    private void  obtenerEvento(){
        listaEventosDto.addAll(eventoController.obtenerEvento());
    }
    private void  obtenerEventoDisponible(){
        listaEventosDto.addAll(eventoController.obtenerEventosDisponibles());
    }
    private void listenerSelection() {
        tblEventos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            eventoSeleccionado = newSelection;
            mostrarInformacionEvento(eventoSeleccionado);
        });
    }
    private void mostrarInformacionEvento(EventoDto eventoSeleccionado) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaEvento = LocalDate.parse(eventoSeleccionado.fecha(), formatter);

        if(eventoSeleccionado != null){
            txtIdEvento.setText(eventoSeleccionado.id());
            txtNombreEvento.setText(eventoSeleccionado.nombre());
            txtDescripcionEvento.setText(eventoSeleccionado.descripcion());
            ldateFechaEvento.setValue(fechaEvento);
            txtCapacidadMaximaEvento.setText(""+eventoSeleccionado.capacidadMaxima());
            boxEmpleadoEncargadoEvento.setValue(eventoSeleccionado.empleadoEncargado());
        }
    }
    private void eliminarEvento(){
        boolean eventoEliminado = false;
        if(eventoSeleccionado != null){
            if(mostrarMensajeConfirmacion("esta seguro de eliminar el evento")){
                if(mostrarMensajeConfirmacion("CUIDADO: Si elimina el evento tambien sus reservas" +
                        " se recomienda eliminar solo si el evento termino o fue cancelado")){
                    eventoController.registrarAccionesSistema("Se confirmo la eliminacion del evento:"+eventoSeleccionado.id()+" con nombre:"+ eventoSeleccionado.nombre(),
                            1,"eliminarEvento, clase:EventoViewController");
                    eventoEliminado = eventoController.eliminarEvento(eventoSeleccionado.id());
                    if(eventoEliminado == true){
                        listaEventosDto.remove(eventoSeleccionado);
                        eventoSeleccionado = null;
                        tblEventos.getSelectionModel().clearSelection();
                        limpiarCamposEvento();
                        mostrarMensaje("Notificación Evento", "Evento eliminado", "El evento se ha eliminado con éxito", Alert.AlertType.INFORMATION);
                    }else{
                        mostrarMensaje("Notificación Evento", "Evento no ha sido eliminado", "El evento no se puede eliminar", Alert.AlertType.ERROR);
                    }

                }
            }
        }else{
            mostrarMensaje("Notificación Evento", "Evento no ha sido seleccionado", "seleccione un evento de la lista", Alert.AlertType.WARNING);
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
    private void limpiarCamposEvento() {
        txtIdEvento.setText("");
        txtNombreEvento.setText("");
        txtDescripcionEvento.setText("");
        ldateFechaEvento.setValue(null);
        txtCapacidadMaximaEvento.setText("");
        boxEmpleadoEncargadoEvento.setValue(null);

    }
    private void consultarEvento() {
        boolean validar=false;
        try {
            if( !(boxEmpleadoEncargadoEvento.getValue().equals("")))
                consultarPorEmpleado();
        }catch (NullPointerException e){
            validar= true;
        }
        if(txtCapacidadMaximaEvento.getText().equals("") && ldateFechaEvento.getValue()==null
                && txtIdEvento.getText().equals("")&& validar==true){
            tblEventos.getItems().clear();
            obtenerEvento();
            tblEventos.setItems(listaEventosDto);
        }

        if( !(txtCapacidadMaximaEvento.getText().equals(""))){
            consultarPorCapasidad();
        }else if(ldateFechaEvento.getValue()!=null){
            consultarPorFecha();
        } else if( !(txtIdEvento.getText().equals(""))){
            consultarConID();
        }





    }

    private void consultarPorEmpleado(){
        System.out.println(modelFactoryController.obtenerEmpleadoStr()  );
        tblEventos.getItems().clear();
        listaEventosDto.addAll(eventoController.obtenerEventoPorEmpleado(String.valueOf(boxEmpleadoEncargadoEvento.getValue())));
        if (listaEventosDto == null || listaEventosDto.isEmpty()) {
            limpiarCamposEvento();
            mostrarMensaje("Notificación Evento", "Eventos no encontrados", "No hay Eventos asignados a este empleado: "+boxEmpleadoEncargadoEvento.getValue(), Alert.AlertType.ERROR);
        } else {
            limpiarCamposEvento();
            tblEventos.setItems(listaEventosDto);
        }
    }


    private void consultarConID(){
        if (eventoController.consultarEvento(txtIdEvento.getText())) {
            EventoDto eventoDto = eventoController.obtenerUnEvento(txtIdEvento.getText());
            tblEventos.getItems().clear();
            listaEventosDto.add(eventoDto);
            tblEventos.setItems(listaEventosDto);
            limpiarCamposEvento();
        } else {
            limpiarCamposEvento();
            mostrarMensaje("Notificación Evento", "Evento no encontrado", "El Evento no éxiste", Alert.AlertType.ERROR);
        }

    }
    private void consultarPorFecha(){
        // Obtener el valor de la fecha del DatePicker
        LocalDate fechaEvento = ldateFechaEvento.getValue();
        // Crear un formateador de fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Convertir la fecha a String utilizando el formateador
        String fechaEventoStr = fechaEvento.format(formatter);
        tblEventos.getItems().clear();
        listaEventosDto.addAll(eventoController.obtenerEventoPorFecha(fechaEventoStr));
        if (listaEventosDto == null || listaEventosDto.isEmpty()) {
            limpiarCamposEvento();
            mostrarMensaje("Notificación Evento", "Eventos no encontrados", "No hay Eventos con esta fecha", Alert.AlertType.ERROR);
        } else {
            limpiarCamposEvento();
            tblEventos.setItems(listaEventosDto);
        }
    }
    private void consultarPorCapasidad(){
        tblEventos.getItems().clear();
        listaEventosDto.addAll(eventoController.obtenerEventoPorCapasidad(Integer.parseInt(txtCapacidadMaximaEvento.getText())));
        if (listaEventosDto == null || listaEventosDto.isEmpty()) {
            limpiarCamposEvento();
            mostrarMensaje("Notificación Evento", "Eventos no encontrados", "No hay Eventos con esa capasidad", Alert.AlertType.ERROR);
        } else {
            limpiarCamposEvento();
            tblEventos.setItems(listaEventosDto);
        }
    }
    public void actualizarEvento( ){
        boolean eventoActualizado = false;
        //1. Capturar los datos
        // String idActual = eventoSeleccionado.id(); // actualiza todo
        EventoDto eventoDto = construirEventoDTO();
        String idActual = eventoDto.id(); // actualiza todo menos la cedula
        //2. verificar el empleado seleccionado
        if(eventoSeleccionado != null){
            //3. Validar la información
            if(datosValidos(eventoSeleccionado)){
                eventoActualizado = eventoController.actualizarEvento( idActual,eventoDto);
                if(eventoActualizado){
                    listaEventosDto.remove(eventoSeleccionado);
                    listaEventosDto.add(eventoDto);
                    tblEventos.refresh();
                    mostrarMensaje("Notificación eventos", "evento actualizado", "El evento se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                    limpiarCamposEvento();
                }else{
                    mostrarMensaje("Notificación eventos", "evento no actualizado", "El evento no se ha actualizado ", Alert.AlertType.INFORMATION);
                }
            }else{
                mostrarMensaje("Notificación eventos", "evento no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
            }

        }else{
            mostrarMensaje("Notificación eventos", "evento no seleccionado", "seleccione un usuario de la lista", Alert.AlertType.WARNING);

        }

    }


}






