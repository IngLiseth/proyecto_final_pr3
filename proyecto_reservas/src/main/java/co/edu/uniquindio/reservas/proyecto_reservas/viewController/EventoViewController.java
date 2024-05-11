package co.edu.uniquindio.reservas.proyecto_reservas.viewController;

import co.edu.uniquindio.reservas.proyecto_reservas.controller.EventoController;
import co.edu.uniquindio.reservas.proyecto_reservas.controller.factory.ModelFactoryController;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.EventoDto;
import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Empleado;
import co.edu.uniquindio.reservas.proyecto_reservas.model.EventoVIP;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Reserva;
import co.edu.uniquindio.reservas.proyecto_reservas.utils.EventoVIPutils;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controlsfx.control.CheckComboBox;

import java.time.LocalDate;
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
    private TableColumn<EventoDto, String> colCapacidadMax;

    @FXML
    private TableColumn<EventoDto, String> colDescripcion;

    @FXML
    private TableColumn<EventoDto, String> colEmpleadoEncargado;

    @FXML
    private TableColumn<EventoDto, String> colFecha;

    @FXML
    private TableColumn<EventoDto, String> colId;

    @FXML
    private TableColumn<EventoDto, String> colNombre;

    @FXML
    private Button consultarEvento;

    @FXML
    private Button eliminarEvento;

    @FXML
    private DatePicker ldateFechaEvento;

    @FXML
    private Button registrarEvento;

    @FXML
    private TableView<EventoDto> tblEventos;

    @FXML
    private TextField txtCapacidadMaximaEvento;

    @FXML
    private TextField txtDescripcionEvento;

    @FXML
    private TextField txtIdEvento;

    @FXML
    private TextField txtNombreEvento;

    @FXML
    void actualizarEvento(ActionEvent event) { actualizarEvento();

    }

    @FXML
    void consultarEvento(ActionEvent event) {consultarEvento();
    }

    @FXML
    void eliminarEvento(ActionEvent event) {eliminarEvento();}

    @FXML
    void registrarEvento(ActionEvent event) {agregarEvento();}
    @FXML
    void initialize() {
        eventoController = new EventoController();
        intiView();
    }

    private void intiView() {

        initDataBinding();
        obtenerEvento();
        tblEventos.getItems().clear();
        tblEventos.setItems(listaEventosDto);
        System.out.println(modelFactoryController.obtenerEmpleadoStr()  );
        listaEmpleado = FXCollections.observableArrayList( modelFactoryController.obtenerEmpleadoStr() );
        boxEmpleadoEncargadoEvento.setItems(listaEmpleado);
        listenerSelection();
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
        EventoDto eventoDto = new EventoDto(
                txtIdEvento.getText(),
                txtNombreEvento.getText(),
                txtDescripcionEvento.getText(),
                ldateFechaEvento.getValue(),
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
    private void listenerSelection() {
        tblEventos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            eventoSeleccionado = newSelection;
            mostrarInformacionEvento(eventoSeleccionado);
        });
    }
    private void mostrarInformacionEvento(EventoDto eventoSeleccionado) {
        if(eventoSeleccionado != null){
            txtIdEvento.setText(eventoSeleccionado.id());
            txtNombreEvento.setText(eventoSeleccionado.nombre());
            txtDescripcionEvento.setText(eventoSeleccionado.descripcion());
            ldateFechaEvento.setValue(eventoSeleccionado.fecha());
            txtCapacidadMaximaEvento.setText(""+eventoSeleccionado.capacidadMaxima());
            boxEmpleadoEncargadoEvento.setValue(eventoSeleccionado.empleadoEncargado());
        }
    }
    private void eliminarEvento(){
        boolean eventoEliminado = false;
        if(eventoSeleccionado != null){
            if(mostrarMensajeConfirmacion("esta seguro de eliminar el evento")){
                ModelFactoryController.registrarAccionesSistema("Se confirmo la eliminacion del evento:"+eventoSeleccionado.id()+" con nombre:"+ eventoSeleccionado.nombre(),
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
        if (eventoController.consultarEvento(txtIdEvento.getText())) {
            EventoDto eventoDto = eventoController.obtenerUnEvento(txtIdEvento.getText());
            mostrarInformacionEvento(eventoDto);
        } else {
            mostrarMensaje("Notificación Evento", "Evento no encontrado", "El Evento no éxiste", Alert.AlertType.ERROR);
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






