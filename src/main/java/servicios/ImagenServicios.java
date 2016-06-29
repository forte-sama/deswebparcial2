package servicios;

import main.EntityManagerCRUD;
import modelos.Imagen;
import modelos.Tipo;
import modelos.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.awt.*;
import java.util.*;
import java.util.List;

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

    public List<Imagen> findByPublicacionId(Integer pub_id) {
        List<Imagen> resp = new ArrayList<>();

        if(pub_id != null) {
            EntityManager em = getEntityManager();

            //do the thing
            try {
                //do the exact thing
                String str_query = "SELECT i FROM Imagen i WHERE i.publicacion.id = :pub_id";
                TypedQuery<Imagen> query = em.createQuery(str_query, Imagen.class);

                resp = query.setParameter("pub_id", pub_id).getResultList();

            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                em.close();
            }
        }

        return resp;
    }
}