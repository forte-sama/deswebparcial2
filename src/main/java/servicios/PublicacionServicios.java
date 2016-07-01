package servicios;

import main.EntityManagerCRUD;
import modelos.Comentario;
import modelos.Publicacion;
import modelos.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public List<Publicacion> findBy(HashMap<String, String> criterios) {
        List<Publicacion> resp = new ArrayList<>();

        EntityManager em = getEntityManager();

        //do the thing
        try {
            boolean yaConcateno = false;
            //construccion de string del query
            String str_query = "SELECT p FROM Publicacion p";
            //solo agregar clausula WHERE si tiene algun criterio
            if(criterios.size() > 0) {
                str_query += " WHERE";
            }
            //concatenar criterio e indicar que ya se ha concatenado (para usar AND)
            if(criterios.containsKey("combustible")) {
                str_query += " p.combustible = :combustible";
                yaConcateno = true;
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("anio_desde")) {
                String and = yaConcateno? " AND" : "";

                str_query += and + " p.anio >= :anio_desde";
                yaConcateno = true;
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("anio_hasta")) {
                String and = yaConcateno? " AND" : "";

                str_query += and + " p.anio <= :anio_hasta";
                yaConcateno = true;
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("precio_desde")) {
                String and = yaConcateno? " AND" : "";

                str_query += and + " p.precioVehiculo >= :precio_desde";
                yaConcateno = true;
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("precio_hasta")) {
                String and = yaConcateno? " AND" : "";

                str_query += and + " p.precioVehiculo <= :precio_hasta";
                yaConcateno = true;
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("marca")) {
                String and = yaConcateno? " AND" : "";

                str_query += and + " p.marca.nombre = :marca";
                yaConcateno = true;
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("tipo")) {
                String and = yaConcateno? " AND" : "";

                str_query += and + " p.tipo.nombre = :tipo";
            }

            //creacion de query a partir de string
            TypedQuery<Publicacion> query = em.createQuery(str_query, Publicacion.class);

            //concatenar criterio e indicar que ya se ha concatenado (para usar AND)
            if(criterios.containsKey("combustible")) {
                query.setParameter("combustible",criterios.get("combustible"));
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("anio_desde")) {
                query.setParameter("anio_desde",Integer.parseInt(criterios.get("anio_desde")));
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("anio_hasta")) {
                query.setParameter("anio_hasta",Integer.parseInt(criterios.get("anio_hasta")));
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("precio_desde")) {
                query.setParameter("precio_desde",Double.parseDouble(criterios.get("precio_desde")));
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("precio_hasta")) {
                query.setParameter("precio_hasta",Double.parseDouble(criterios.get("precio_hasta")));
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("marca")) {
                query.setParameter("marca",criterios.get("marca"));
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("tipo")) {
                query.setParameter("tipo",criterios.get("tipo"));
            }

            return query.getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }

        return resp;
    }
}