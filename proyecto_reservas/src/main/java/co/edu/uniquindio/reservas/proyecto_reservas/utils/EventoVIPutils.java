package co.edu.uniquindio.reservas.proyecto_reservas.utils;

import co.edu.uniquindio.reservas.proyecto_reservas.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventoVIPutils {

    public static EventoVIP inicializar(){

        System.out.println("Se llama el metodo EventoVIPutils");

        EventoVIP eventoVIP = new EventoVIP();

        Usuario usuario = new Usuario("1234","Alfonso", "alfonso@gmail.com","123");
        Usuario usuario2 = new Usuario("12345","Liseth", "liseth@gmail.com","123");
        Usuario usuario3 = new Usuario("12346","sara", "sara@gmail.com","123");

        Empleado empleado1 = new Empleado("23","mark","mar@23","123","mesero");
        Empleado empleado2 = new Empleado("24","elon","alo@23","123","captador");
        Empleado empleado3 = new Empleado("33","maria","maria@23","123","portero");
        List<Empleado> epleados = new ArrayList<>();
        epleados.add(empleado1);
        epleados.add(empleado2);
        epleados.add(empleado3);
//


        // creacion de eventos
        LocalDate fechaEvento = LocalDate.of(2024,05,14);
        LocalDate fechaEvento1 = LocalDate.of(2024,05,14);
        LocalDate fechaEvento2 = LocalDate.of(2024,05,20);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaEventoStr = fechaEvento.format(formatter);
        String fechaEventoStr1 = fechaEvento1.format(formatter);
        String fechaEventoStr2 = fechaEvento2.format(formatter);

        Evento evento1 = new Evento("02", "Boda", "Matrimonio por la iglesia " ,fechaEventoStr, 80, empleado2);
        Evento evento2 = new Evento("03", "Cine", "Pelicula de Accion" , fechaEventoStr1, 60, empleado3);
        Evento evento = new Evento("01", "Concierto", "concierto de Shakira" ,fechaEventoStr2, 80, empleado1);
        List <Evento> eventos = new ArrayList<>();
        eventos.add(evento);
        eventos.add(evento1);
        eventos.add(evento2);

        Reserva reserva = new Reserva("1", usuario, evento, LocalDate.of(2024,05,14), Estado.APROBADA );
        Reserva reserva2 = new Reserva("1", usuario2, evento, LocalDate.of(2024,05,15), Estado.RECHAZADA );

        List <Reserva> reservas = new ArrayList<>();
        reservas.add(reserva);
        reservas.add(reserva2);

        usuario2.setReservasDelUsuario(reservas);
        usuario3.setReservasDelUsuario(reservas);

        List <Usuario> usuarios = new ArrayList<>();
        usuario.setReservasDelUsuario(reservas);
        usuarios.add(usuario);
        usuarios.add(usuario2);
        usuarios.add(usuario3);

        List<Empleado> empleados=new ArrayList<>();
        empleados.add(empleado1);

        Administrador admin1 = new Administrador("111", "Cabbyd", "Cabbyd@gmail.com", "123");
        Administrador admin2 = new Administrador("222", "Connor", "Connor@gmail.com", "123");
        List<Administrador> listaAdmins = new ArrayList<>();
        listaAdmins.add(admin1);
        listaAdmins.add(admin2);

        eventoVIP.setListaAdministradores(listaAdmins);
        eventoVIP.setListaEventos(eventos);
        eventoVIP.setListaEmpleados(epleados);
        eventoVIP.setListaUsuarios(usuarios);
        return eventoVIP;
    }
}
