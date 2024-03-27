package co.edu.uniquindio.reservas.proyecto_reservas.utils;

import co.edu.uniquindio.reservas.proyecto_reservas.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventoVIPutils {

    public static EventoVIP inicializar(){
        EventoVIP eventoVIP = new EventoVIP();

        Usuario usuario = new Usuario("1234","Alfonso", "alfonso@gmail.com","123");
        Usuario usuario2 = new Usuario("12345","Liseth", "liseth@gmail.com","123");
        Usuario usuario3 = new Usuario("12346","sara", "sara@gmail.com","123");

        Empleado empleado = new Empleado("23","alfonso","asd@23","mesero");

       Evento evento = new Evento(1, "Concierto", "concierto de Shakira" , LocalDate.of(2023,03,02), 1000, null);

        Reserva reserva = new Reserva("1", usuario, evento, LocalDate.of(2023,03,12), Estado.APROBADA );
        Reserva reserva2 = new Reserva("1", usuario2, evento, LocalDate.of(2023,04,12), Estado.RECHAZADA );

        List <Reserva> reservas = new ArrayList<>();
        reservas.add(reserva);
        reservas.add(reserva2);

        usuario2.setReservasDelUsuario(reservas);
        usuario3.setReservasDelUsuario(reservas);
        usuario.setReservasDelUsuario(reservas);

        List <Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario);
        usuarios.add(usuario2);
        usuarios.add(usuario3);


        eventoVIP.setListaUsuarios(usuarios);
        return eventoVIP;
    }
}
