package servicios;

/**
 * Created by Dell_2 on 6/29/2016.
 */
import main.EntityManagerCRUD;
import modelos.Comentario;
import modelos.Imagen;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ComentarioServicios extends EntityManagerCRUD {

    private static ComentarioServicios instancia;

    private ComentarioServicios() {
        super(Comentario.class);
    }

    public static ComentarioServicios getInstancia() {
        if (instancia == null) {
            instancia = new ComentarioServicios();
        }
        return instancia;
    }



    public List<Comentario> findByPublicacionId(Integer pub_id) {
        List<Comentario> resp = new ArrayList<>();

        if(pub_id != null) {
            EntityManager em = getEntityManager();

            //do the thing
            try {
                //do the exact thing
                String str_query = "SELECT c FROM Comentario c WHERE c.publicacion.id = :pub_id";
                TypedQuery<Comentario> query = em.createQuery(str_query, Comentario.class);

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

