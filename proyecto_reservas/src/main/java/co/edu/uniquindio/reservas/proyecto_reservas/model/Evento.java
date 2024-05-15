package co.edu.uniquindio.reservas.proyecto_reservas.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    private String descripcion;
    private String fecha;
    private int capacidadMaxima;
    private Empleado empleadoEncargado;

    private boolean disponible;


    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Evento(String  id, String nombre, String descripcion, String fecha, int capacidadMaxima, Empleado empleadoEncargado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.capacidadMaxima = capacidadMaxima;
        this.empleadoEncargado =empleadoEncargado;
        this.disponible = true;
    }
    public Evento(){

    }


    public String  getId() {return id;}
    public void setId(String id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public String getFecha() {return fecha;}
    public void setFecha(String fecha) {this.fecha = fecha;}
    public int getCapacidadMaxima() {return capacidadMaxima;}
    public void setCapacidadMaxima(int capacidadMaxima) {this.capacidadMaxima = capacidadMaxima;}

    public Empleado getEmpleadoEncargado() {
        return empleadoEncargado;
    }

    public void setEmpleadoEncargado(Empleado empleadoEncargado) {
        this.empleadoEncargado = empleadoEncargado;
    }

}
