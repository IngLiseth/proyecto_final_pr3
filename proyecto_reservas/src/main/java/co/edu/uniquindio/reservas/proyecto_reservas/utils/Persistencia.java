package co.edu.uniquindio.reservas.proyecto_reservas.utils;

import co.edu.uniquindio.reservas.proyecto_reservas.model.Empleado;
import co.edu.uniquindio.reservas.proyecto_reservas.model.EventoVIP;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Usuario;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Persistencia
{


    //bancoUq/src/main/resources/persistencia/archivoClientes.txt

    public static final String RUTA_ARCHIVO_LOG = "src/main/resources/persistencia/log/proyecto_Reservas_log.txt";
    public static final String RUTA_ARCHIVO_USUARIOS = "src/main/resources/persistencia/archivos/usuario.txt";
   public static final String RUTA_ARCHIVO_MODELO_RESERVAVIP_BINARIO = "src/main/resources/persistencia/model.bin";
    public static final String RUTA_ARCHIVO_MODELO_RESERVAVIP_XML = "src/main/resources/persistencia/model.xml";

    public static final String RUTA_ARCHIVO_COPIA_SEGURIDAD_USUARIOS = "proyecto_reservas/src/main/resources/persistencia/respaldo/usuarioCopia.txt";
    public static final String RUTA_ARCHIVO_COPIA_SEGURIDAD_MODEL_XML = "proyecto_reservas/src/main/resources/persistencia/respaldo/modelCopia.XML";
    public static final String RUTA_ARCHIVO_COPIA_SEGURIDAD_MODEL_BINARIO = "proyecto_reservas/src/main/resources/persistencia/respaldo/modelCopia.bin";
    public static final String RUTA_ARCHIVO_COPIA_SEGURIDAD_LOG = "proyecto_reservas/src/main/resources/persistencia/respaldo/proyecto_Reservas_Copia_log.txt";

//	C:\td\persistencia



    public static void cargarDatosArchivos(EventoVIP eventoVIP) throws FileNotFoundException, IOException {

        //cargar archivo de Usuario
        ArrayList<Usuario> usuariosCargados = cargarUsuario();
        if(usuariosCargados.size() > 0)
            eventoVIP.getListaUsuarios().addAll(usuariosCargados);

        //cargar archivos empleados
//        ArrayList<Empleado> empleadosCargados = cargarEmpleados();
//        if(empleadosCargados.size() > 0)
//            banco.getListaEmpleados().addAll(empleadosCargados);

        //cargar archivo transcciones

        //cargar archivo empleados

        //cargar archivo prestamo

    }

    /**
     * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
     * @param
     * @param
     * @throws IOException
     */
    public static void guardarUsuario(ArrayList<Usuario> listaUsuarios) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for(Usuario usuario:listaUsuarios)
        {
            contenido+= usuario.getId()+"@@"+usuario.getNombre()+"@@"+usuario.getCorreo()+"@@"+usuario.getContrasena()+"\n";

        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido, false);
    }

//
//    public static void guardarEmpleados(ArrayList<Empleado> listaEmpleados) throws IOException {
//        String contenido = "";
//        for(Empleado empleado:listaEmpleados)
//        {
//            contenido+= empleado.getNombre()+
//                    ","+empleado.getApellido()+
//                    ","+empleado.getCedula()+
//                    ","+empleado.getFechaNacimiento()+"\n";
//        }
//        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_EMPLEADOS, contenido, false);
//    }



//	----------------------LOADS------------------------

    /**
     *
     * @param
     * @param
     * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<Usuario> cargarUsuario() throws FileNotFoundException, IOException
    {
        ArrayList<Usuario> usuarios =new ArrayList<Usuario>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_USUARIOS);
        String linea="";
        for (int i = 0; i < contenido.size(); i++)
        {
            linea = contenido.get(i);//juan,arias,125454,Armenia,uni1@,12454,125444
            Usuario usuario = new Usuario();
            usuario.setId(linea.split("@@")[0]);
            usuario.setNombre(linea.split("@@")[1]);
            usuario.setCorreo(linea.split("@@")[2]);
            usuario.setContrasena(linea.split("@@")[3]);

            usuarios.add(usuario);
        }
        return usuarios;
    }


//    public static ArrayList<Empleado> cargarEmpleados() throws FileNotFoundException, IOException {
//        ArrayList<Empleado> empleados =new ArrayList<Empleado>();
//        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_EMPLEADOS);
//        String linea="";
//        for (int i = 0; i < contenido.size(); i++)
//        {
//            linea = contenido.get(i);
//            Empleado empleado = new Empleado();
//            empleado.setNombre(linea.split(",")[0]);
//            empleado.setApellido(linea.split(",")[1]);
//            empleado.setCedula(linea.split(",")[2]);
//            empleado.setFechaNacimiento(linea.split(",")[3]);
//            empleados.add(empleado);
//        }
//        return empleados;
//    }


    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
    {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }

//
//    public static boolean iniciarSesion(String usuario, String contrasenia) throws FileNotFoundException, IOException, UsuarioExcepcion {
//
//        if(validarUsuario(usuario,contrasenia)) {
//            return true;
//        }else {
//            throw new UsuarioExcepcion("Usuario no existe");
//        }
//
//    }
//
//    private static boolean validarUsuario(String usuario, String contrasenia) throws FileNotFoundException, IOException
//    {
//        ArrayList<Usuario> usuarios = Persistencia.cargarUsuarios(RUTA_ARCHIVO_USUARIOS);
//
//        for (int indiceUsuario = 0; indiceUsuario < usuarios.size(); indiceUsuario++)
//        {
//            Usuario usuarioAux = usuarios.get(indiceUsuario);
//            if(usuarioAux.getUsuario().equalsIgnoreCase(usuario) && usuarioAux.getContrasenia().equalsIgnoreCase(contrasenia)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static ArrayList<Usuario> cargarUsuarios(String ruta) throws FileNotFoundException, IOException {
//        ArrayList<Usuario> usuarios =new ArrayList<Usuario>();
//
//        ArrayList<String> contenido = ArchivoUtil.leerArchivo(ruta);
//        String linea="";
//
//        for (int i = 0; i < contenido.size(); i++) {
//            linea = contenido.get(i);
//
//            Usuario usuario = new Usuario();
//            usuario.setUsuario(linea.split(",")[0]);
//            usuario.setContrasenia(linea.split(",")[1]);
//
//            usuarios.add(usuario);
//        }
//        return usuarios;
//    }


//	----------------------SAVES------------------------

    /**
     * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
     * @param
     * @param ruta
     * @throws IOException
     */

    public static void guardarObjetos(ArrayList<Usuario> listaUsuario, String ruta) throws IOException  {
        String contenido = "";

        for(Usuario usuarioAux:listaUsuario) {
            contenido+= usuarioAux.getId()+","+usuarioAux.getNombre()+","+usuarioAux.getCorreo()+usuarioAux.getContrasena()+"\n";
        }
        ArchivoUtil.guardarArchivo(ruta, contenido, true);
    }





    //------------------------------------SERIALIZACIÓN  y XML


    public static EventoVIP cargarRecursoEventoVIPBinario() {

        EventoVIP eventoVIP = null;

        try {
            eventoVIP = (EventoVIP)ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_RESERVAVIP_BINARIO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return eventoVIP;
    }

    public static void guardarRecursoEventoVIPBinario(EventoVIP eventoVIP) {
        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_RESERVAVIP_BINARIO, eventoVIP);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    public static EventoVIP cargarRecursoReservaVIPXML() {

        EventoVIP eventoVIP = null;

        try {
            eventoVIP  = (EventoVIP)ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_RESERVAVIP_XML);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return eventoVIP;

    }



    public static void guardarRecursoReservaVIPXML(EventoVIP eventoVIP) {

        try {
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_RESERVAVIP_XML, eventoVIP);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void crearCopiaSeguridad(int num) {

            // Obtener la fecha y hora actual
            Date fechaActual = new Date();
            // Formatear la fecha y hora en el formato deseado
            SimpleDateFormat formatoFecha = new SimpleDateFormat("ddMMyy_HH_mm_ss");
            String fechaFormateada = formatoFecha.format(fechaActual);

        switch (num) {
            case 1:
                String rutaCopiaSeguridadUsuario = "src/main/resources/persistencia/respaldo/usuarioCopia_"+ fechaFormateada +".txt";
                ArchivoUtil.crearCopiaSeguridad(RUTA_ARCHIVO_USUARIOS,rutaCopiaSeguridadUsuario);
               // ArchivoUtil.crearCopiaSeguridad(RUTA_ARCHIVO_USUARIOS,RUTA_ARCHIVO_COPIA_SEGURIDAD_USUARIOS);
                break;
            case 2:
                String rutaCopiaSeguridadModelXml = "src/main/resources/persistencia/respaldo/modelCopia_"+ fechaFormateada +".xml";
                ArchivoUtil.crearCopiaSeguridad(RUTA_ARCHIVO_MODELO_RESERVAVIP_XML,rutaCopiaSeguridadModelXml);
              //  ArchivoUtil.crearCopiaSeguridad(RUTA_ARCHIVO_MODELO_RESERVAVIP_XML,RUTA_ARCHIVO_COPIA_SEGURIDAD_MODEL_XML);
                break;
            case 3:
                String rutaCopiaSeguridadModelBin = "src/main/resources/persistencia/respaldo/modelCopia_"+ fechaFormateada +".bin";
                ArchivoUtil.crearCopiaSeguridad(RUTA_ARCHIVO_MODELO_RESERVAVIP_BINARIO,rutaCopiaSeguridadModelBin);
                //ArchivoUtil.crearCopiaSeguridad(RUTA_ARCHIVO_MODELO_RESERVAVIP_BINARIO,RUTA_ARCHIVO_COPIA_SEGURIDAD_MODEL_BINARIO);
                break;
            case 4:
                String rutaCopiaSeguridadLog = "src/main/resources/persistencia/respaldo/proyectoReservasCopia_log_"+ fechaFormateada +".txt";
                ArchivoUtil.crearCopiaSeguridad(RUTA_ARCHIVO_LOG,rutaCopiaSeguridadLog);
                //ArchivoUtil.crearCopiaSeguridad(RUTA_ARCHIVO_LOG,RUTA_ARCHIVO_COPIA_SEGURIDAD_LOG);
                break;
        }
    }


}
