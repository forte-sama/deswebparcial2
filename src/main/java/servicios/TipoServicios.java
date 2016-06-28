package servicios;

import main.EntityManagerCRUD;
import modelos.Marca;
import modelos.Tipo;
import modelos.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

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

    public List<Tipo> findByTipo(String textoTipo) {
        List<Tipo> resp = new ArrayList<>();

        if(textoTipo != null && !textoTipo.isEmpty()) {
            EntityManager em = getEntityManager();

            //do the thing
            try {
                //do the exact thing
                String str_query = "SELECT t FROM Tipo t WHERE t.nombre = :textoTipo";
                TypedQuery<Tipo> query = em.createQuery(str_query, Tipo.class);

                resp = query.setParameter("textoTipo", textoTipo).getResultList();

            } catch (Exception ex) {
                throw ex;
            } finally {
                em.close();
            }
        }

        return resp;
    }
}