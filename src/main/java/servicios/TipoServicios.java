package servicios;

import main.EntityManagerCRUD;
import modelos.Tipo;
import modelos.Usuario;

/**
 * Created by saleta on 6/24/2016.
 */
public class TipoServicios extends EntityManagerCRUD<Tipo> {
    private static TipoServicios instancia;

    private TipoServicios() {
        super(Tipo.class);
    }

    public static TipoServicios getInstancia() {
        if (instancia == null) {
            instancia = new TipoServicios();
        }
        return instancia;
    }
}