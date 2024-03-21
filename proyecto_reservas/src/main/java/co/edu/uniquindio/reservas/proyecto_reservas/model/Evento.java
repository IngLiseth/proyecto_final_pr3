package co.edu.uniquindio.reservas.proyecto_reservas.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Evento {
    private int id;
    private String nombre;
    private String descripcion;
    private LocalDate fecha;
    private double capacidadMaxima;
    private Empleado empleadoEncargado;
    private List<Reserva> reservasDelEvento;

    public Evento(int id, String nombre, String descripcion, LocalDate fecha, double capacidadMaxima, Empleado empleadoEncargado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.capacidadMaxima = capacidadMaxima;
        this.empleadoEncargado = empleadoEncargado;
        this.reservasDelEvento = new ArrayList<>();
    }

    public List<Reserva> getReservasDelEvento() {
        return reservasDelEvento;
    }

    public void agregarReservasAlEvento(Reserva reserva) {
        this.reservasDelEvento.add(reserva);
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public LocalDate getFecha() {return fecha;}
    public void setFecha(LocalDate fecha) {this.fecha = fecha;}
    public double getCapacidadMaxima() {return capacidadMaxima;}
    public void setCapacidadMaxima(double capacidadMaxima) {this.capacidadMaxima = capacidadMaxima;}
    public Empleado getEmpleadoEncargado() {return empleadoEncargado;}
    public void setEmpleadoEncargado(Empleado empleadoEncargado) {this.empleadoEncargado = empleadoEncargado;}
}
