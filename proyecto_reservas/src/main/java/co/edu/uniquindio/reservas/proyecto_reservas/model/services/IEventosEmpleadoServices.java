package co.edu.uniquindio.reservas.proyecto_reservas.model.services;

import co.edu.uniquindio.reservas.proyecto_reservas.Exceptions.EmpleadoExceptions;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Empleado;

public interface IEventosEmpleadoServices {
    boolean  actualizarEmpleado(String id, Empleado empleado) throws EmpleadoExceptions;
    void crearEmpleado(Empleado empleado);
    boolean eliminarEmpleado(String id) throws EmpleadoExceptions;
    boolean consultarEmpleado(String id) throws EmpleadoExceptions;
}
