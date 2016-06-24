package servicios;

import main.EntityManagerCRUD;
import modelos.Publicacion;
import modelos.Usuario;

/**
 * Created by saleta on 6/24/2016.
 */
public class PublicacionServicios extends EntityManagerCRUD<Publicacion> {
    private static PublicacionServicios instancia;

    private PublicacionServicios() {
        super(Publicacion.class);
    }

    public static PublicacionServicios getInstancia() {
        if (instancia == null) {
            instancia = new PublicacionServicios();
        }
        return instancia;
    }
}