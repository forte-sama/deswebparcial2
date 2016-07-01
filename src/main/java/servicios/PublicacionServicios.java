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

    private static final int pageSize = 3;

    private PublicacionServicios() {
        super(Publicacion.class);
    }

    public static PublicacionServicios getInstancia() {
        if (instancia == null) {
            instancia = new PublicacionServicios();
        }
        return instancia;
    }

    public HashMap<String,Object> filtrarPublicaciones(HashMap<String, String> criterios, int pagina) {
        HashMap<String,Object> resp = new HashMap<>();

        EntityManager em = getEntityManager();

        //do the thing
        try {
            boolean yaConcateno = false;
            //construccion de string del query
            //estado base
            String str_condiciones_query = "FROM Publicacion p WHERE p.vendido = false";
            //construccion de string de query de conteo de paginas (saber si hay pagina anterior y sigte)
            //solo agregar clausula WHERE si tiene algun criterio
            if(criterios.size() > 0) {
                str_condiciones_query += " AND";
            }
            //concatenar criterio e indicar que ya se ha concatenado (para usar AND)
            if(criterios.containsKey("combustible")) {
                str_condiciones_query += " p.combustible = :combustible";
                yaConcateno = true;
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("anio_desde")) {
                String and = yaConcateno? " AND" : "";

                str_condiciones_query += and + " p.anio >= :anio_desde";
                yaConcateno = true;
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("anio_hasta")) {
                String and = yaConcateno? " AND" : "";

                str_condiciones_query += and + " p.anio <= :anio_hasta";
                yaConcateno = true;
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("precio_desde")) {
                String and = yaConcateno? " AND" : "";

                str_condiciones_query += and + " p.precioVehiculo >= :precio_desde";
                yaConcateno = true;
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("precio_hasta")) {
                String and = yaConcateno? " AND" : "";

                str_condiciones_query += and + " p.precioVehiculo <= :precio_hasta";
                yaConcateno = true;
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("marca")) {
                String and = yaConcateno? " AND" : "";

                str_condiciones_query += and + " p.marca.nombre = :marca";
                yaConcateno = true;
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("tipo")) {
                String and = yaConcateno? " AND" : "";

                str_condiciones_query += and + " p.tipo.nombre = :tipo";
            }

            //finalizacion de construccion de queries a usar
            String str_query = "SELECT p " + str_condiciones_query + " ORDER BY p.id ASC";
            String str_conteo_paginas = "SELECT COUNT(p) " + str_condiciones_query;

            //creacion de query a partir de string
            TypedQuery<Publicacion> query = em.createQuery(str_query, Publicacion.class);

            //asignar valores de los criterios si estan presentes
            if(criterios.containsKey("combustible")) {
                query.setParameter("combustible",criterios.get("combustible"));
            }
            if(criterios.containsKey("anio_desde")) {
                query.setParameter("anio_desde",Integer.parseInt(criterios.get("anio_desde")));
            }
            if(criterios.containsKey("anio_hasta")) {
                query.setParameter("anio_hasta",Integer.parseInt(criterios.get("anio_hasta")));
            }
            if(criterios.containsKey("precio_desde")) {
                query.setParameter("precio_desde",Double.parseDouble(criterios.get("precio_desde")));
            }
            if(criterios.containsKey("precio_hasta")) {
                query.setParameter("precio_hasta",Double.parseDouble(criterios.get("precio_hasta")));
            }
            if(criterios.containsKey("marca")) {
                query.setParameter("marca",criterios.get("marca"));
            }
            if(criterios.containsKey("tipo")) {
                query.setParameter("tipo",criterios.get("tipo"));
            }
            //setear paginacion
            query.setFirstResult((pagina - 1) * pageSize);
            query.setMaxResults(pageSize);

            //conteo de publicaciones segun criterios
            long count_pages = conteoSegunQuery(str_conteo_paginas);

            resp.put("publicaciones",query.getResultList());
            resp.put("hay_pagina_anterior",pagina > 1);
            resp.put("hay_pagina_siguiente",count_pages > pagina);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }

        return resp;
    }

    private long conteoSegunQuery(String str_query) {
        EntityManager em = getEntityManager();

        TypedQuery<Long> query_conteo = em.createQuery(str_query, Long.class);
        Long cantPublicacionesFiltradas = query_conteo.getSingleResult();
        Long x = (long)Math.ceil((double)cantPublicacionesFiltradas / (double)pageSize);
        System.out.println("" + x);
        return x;
    }
}