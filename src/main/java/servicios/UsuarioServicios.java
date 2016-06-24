package servicios;

import main.EntityManagerCRUD;
import modelos.Usuario;

/**
 * Created by saleta on 6/24/2016.
 */
public class UsuarioServicios extends EntityManagerCRUD<Usuario> {
    private static UsuarioServicios instancia;

    private UsuarioServicios() {
        super(Usuario.class);
    }

    public static UsuarioServicios getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioServicios();
        }
        return instancia;
    }
}