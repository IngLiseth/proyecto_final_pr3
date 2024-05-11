package co.edu.uniquindio.reservas.proyecto_reservas.controller.services;

import co.edu.uniquindio.reservas.proyecto_reservas.mapping.dto.UsuarioDto;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Persona;
import co.edu.uniquindio.reservas.proyecto_reservas.model.Usuario;

public interface IModelFactoryService extends IUsuarioService, ILoginService,IEventosService {

    Persona obtenerSesion();

}
