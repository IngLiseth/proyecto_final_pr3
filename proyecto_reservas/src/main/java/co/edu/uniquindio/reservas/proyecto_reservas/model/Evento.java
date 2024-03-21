package co.edu.uniquindio.reservas.proyecto_reservas.model;

import java.time.LocalDate;

public class Evento {
    private int id;
    private String nombre;
    private String descripcion;

    private LocalDate fecha;
    private double capacidadMaxima;
    private Empleado empleadoEncargado;
    private Reserva reserva;
    //año
    private int cedul;
}
