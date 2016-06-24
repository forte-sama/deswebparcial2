package servicios;

import main.EntityManagerCRUD;
import modelos.Imagen;
import modelos.Usuario;

import java.awt.*;

/**
 * Created by saleta on 6/24/2016.
 */
public class ImagenServicios extends EntityManagerCRUD<Imagen> {
    private static ImagenServicios instancia;

    private ImagenServicios() {
        super(Imagen.class);
    }

    public static ImagenServicios getInstancia() {
        if (instancia == null) {
            instancia = new ImagenServicios();
        }
        return instancia;
    }
}