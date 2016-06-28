package servicios;

import main.EntityManagerCRUD;
import modelos.Marca;
import modelos.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

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

    public List<Marca> findByMarca(String textoMarca) {
        List<Marca> resp = new ArrayList<>();

        if(textoMarca != null && !textoMarca.isEmpty()) {
            EntityManager em = getEntityManager();

            //do the thing
            try {
                //do the exact thing
                String str_query = "SELECT m FROM Marca m WHERE m.nombre = :textoMarca";
                TypedQuery<Marca> query = em.createQuery(str_query, Marca.class);

                resp = query.setParameter("textoMarca", textoMarca).getResultList();

            } catch (Exception ex) {
                throw ex;
            } finally {
                em.close();
            }
        }

        return resp;
    }
}