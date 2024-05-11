package co.edu.uniquindio.reservas.proyecto_reservas.model;

public class Sesion {

    private Persona persona;
    private static Sesion INTANCIA;

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
