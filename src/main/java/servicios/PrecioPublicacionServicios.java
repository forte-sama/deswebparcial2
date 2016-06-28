package servicios;

import main.EntityManagerCRUD;
import modelos.Marca;
import modelos.PrecioPublicacion;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Calendar;

/**
 * Created by saleta on 6/24/2016.
 */
public class PrecioPublicacionServicios extends EntityManagerCRUD<PrecioPublicacion> {
    private static PrecioPublicacionServicios instancia;

    private PrecioPublicacionServicios() {
        super(PrecioPublicacion.class);
    }

    public static PrecioPublicacionServicios getInstancia() {
        if (instancia == null) {
            instancia = new PrecioPublicacionServicios();
        }

        return instancia;
    }

    public void precioDefault() {
        PrecioPublicacion precioDefault = new PrecioPublicacion();
        precioDefault.setPrecio(500.00);
        precioDefault.setFechaModificacion(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));

        if(PrecioPublicacionServicios.getInstancia().findAll().size() == 0) {
            super.create(precioDefault);
        }
    }

    private Integer maxId() {
        EntityManager em = getEntityManager();

        //resultado
        Integer resp = null;

        //do the thing
        try {
            //do the exact thing
            String str_query = "SELECT MAX(pp.id) FROM PrecioPublicacion pp";
            TypedQuery<Integer> query = em.createQuery(str_query, Integer.class);

            resp = query.getSingleResult();

        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }

        return resp;
    }

    public PrecioPublicacion precioActual() {
        //construir resultado
        PrecioPublicacion resp = null;

        Integer id = maxId();

        EntityManager em = getEntityManager();

        //do the thing
        try {
            //do the thing
            String str_query = "SELECT pp FROM PrecioPublicacion pp WHERE pp.id = :id";
            TypedQuery<PrecioPublicacion> query = em.createQuery(str_query, PrecioPublicacion.class);

            resp = query.setParameter("id", id).getSingleResult();

        } catch (Exception ex) {
            ex.printStackTrace();
            resp = null;
        } finally {
            em.close();
        }

        return resp;
    }
}