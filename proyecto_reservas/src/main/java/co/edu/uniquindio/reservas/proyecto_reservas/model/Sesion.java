package co.edu.uniquindio.reservas.proyecto_reservas.model;

import java.io.Serializable;

public class Sesion implements Serializable {

    private static final long serialVersionUID = 1L;

    private Persona persona;
    private static Sesion INTANCIA;

    public static Sesion getINTANCIA() {
        return INTANCIA;
    }

    public static void setINTANCIA(Sesion INTANCIA) {
        Sesion.INTANCIA = INTANCIA;
    }

    private Sesion(){

    }

    public static Sesion getIntancia(){
        if(INTANCIA == null){
            INTANCIA = new Sesion();
        }

        return INTANCIA;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
