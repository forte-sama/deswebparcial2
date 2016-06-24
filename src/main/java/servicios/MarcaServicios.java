package servicios;

import main.EntityManagerCRUD;
import modelos.Marca;
import modelos.Usuario;

/**
 * Created by saleta on 6/24/2016.
 */
public class MarcaServicios extends EntityManagerCRUD<Marca> {
    private static MarcaServicios instancia;

    private MarcaServicios() {
        super(Marca.class);
    }

    public static MarcaServicios getInstancia() {
        if (instancia == null) {
            instancia = new MarcaServicios();
        }
        return instancia;
    }
}