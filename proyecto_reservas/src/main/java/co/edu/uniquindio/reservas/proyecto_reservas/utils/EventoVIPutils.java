package co.edu.uniquindio.reservas.proyecto_reservas.utils;

import co.edu.uniquindio.reservas.proyecto_reservas.model.EventoVIP;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Usuario;

public class EventoVIPutils {

    public static EventoVIP inicializar(){
        EventoVIP eventoVIP = new EventoVIP();

        Usuario usuario = new Usuario(1234,"Alfonso", "alfonso@gmail.com");
        Usuario usuario2 = new Usuario(12345,"Liseth", "liseth@gmail.com");
        Usuario usuario3 = new Usuario(12346,"sara", "sara@gmail.com");

        return eventoVIP;
    }
}
