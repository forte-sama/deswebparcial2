package servicios;

import main.EntityManagerCRUD;
import modelos.Comentario;
import modelos.Imagen;
import modelos.Publicacion;
import modelos.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;

/**
 * Created by saleta on 6/24/2016.
 */
public class PublicacionServicios extends EntityManagerCRUD<Publicacion> {
    private static PublicacionServicios instancia;

    private static final int pageSize = 6;

    private PublicacionServicios() {
        super(Publicacion.class);
    }

    public static PublicacionServicios getInstancia() {
        if (instancia == null) {
            instancia = new PublicacionServicios();
        }
        return instancia;
    }

    public HashMap<String,Object> filtrarPublicaciones(HashMap<String, String> criterios, int pagina,String url,Usuario user) {
        HashMap<String,Object> resp = new HashMap<>();

        EntityManager em = getEntityManager();

        //do the thing
        try {
            //construir url de criterios
            String urlParams = "";

            boolean yaConcateno = false;
            //construccion de string del query
            //estado base
            String str_condiciones_query = "FROM Publicacion p WHERE p.vendido = false";
            //construccion de string de query de conteo de paginas (saber si hay pagina anterior y sigte)
            //solo agregar clausula WHERE si tiene algun criterio
            if(criterios.size() > 0  || user != null) {
                str_condiciones_query += " AND";
            }
            //concatenar criterio e indicar que ya se ha concatenado (para usar AND)
            if(criterios.containsKey("combustible")) {
                str_condiciones_query += " p.combustible = :combustible";

                urlParams += "&combustible="+criterios.get("combustible");
                yaConcateno = true;
            }
            if(user != null) {
                String and = yaConcateno? " AND" : "";

                str_condiciones_query += and + " p.usuario = :user";
                yaConcateno = true;
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("anio_desde")) {
                String and = yaConcateno? " AND" : "";

                urlParams += yaConcateno? "&" : "";
                urlParams += "anio_desde="+criterios.get("anio_desde");

                str_condiciones_query += and + " p.anio >= :anio_desde";

                yaConcateno = true;
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("anio_hasta")) {
                String and = yaConcateno? " AND" : "";

                urlParams += yaConcateno? "&" : "";
                urlParams += "anio_hasta="+criterios.get("anio_hasta");

                str_condiciones_query += and + " p.anio <= :anio_hasta";
                yaConcateno = true;
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("precio_desde")) {
                String and = yaConcateno? " AND" : "";

                urlParams += yaConcateno? "&" : "";
                urlParams += "precio_desde="+criterios.get("precio_desde");

                str_condiciones_query += and + " p.precioVehiculo >= :precio_desde";
                yaConcateno = true;
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("precio_hasta")) {
                String and = yaConcateno? " AND" : "";

                urlParams += yaConcateno? "&" : "";
                urlParams += "precio_hasta="+criterios.get("precio_hasta");

                str_condiciones_query += and + " p.precioVehiculo <= :precio_hasta";
                yaConcateno = true;
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("marca")) {
                String and = yaConcateno? " AND" : "";

                urlParams += yaConcateno? "&" : "";
                urlParams += "marca="+criterios.get("marca");

                str_condiciones_query += and + " p.marca.nombre = :marca";
                yaConcateno = true;
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("tipo")) {
                String and = yaConcateno? " AND" : "";

                urlParams += yaConcateno? "&" : "";
                urlParams += "tipo="+criterios.get("tipo");

                str_condiciones_query += and + " p.tipo.nombre = :tipo";
            }

            //finalizacion de construccion de queries a usar
            String str_query = "SELECT p " + str_condiciones_query;
            String str_conteo_paginas = "SELECT COUNT(p) " + str_condiciones_query;

            //creacion de query a partir de string
            TypedQuery<Publicacion> query = em.createQuery(str_query, Publicacion.class);
            TypedQuery<Long> query_conteo = em.createQuery(str_conteo_paginas, Long.class);

            //setear fecha
//            query.setParameter("hoy",new Date());
//            query_conteo.setParameter("hoy",new Date());


            if(user != null) {
                query.setParameter("user",user);
                query_conteo.setParameter("user",user);
            }
            //asignar valores de los criterios si estan presentes
            if(criterios.containsKey("combustible")) {
                query.setParameter("combustible",criterios.get("combustible"));
                query_conteo.setParameter("combustible",criterios.get("combustible"));
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("anio_desde")) {
                query.setParameter("anio_desde",Integer.parseInt(criterios.get("anio_desde")));
                query_conteo.setParameter("anio_desde",Integer.parseInt(criterios.get("anio_desde")));
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("anio_hasta")) {
                query.setParameter("anio_hasta",Integer.parseInt(criterios.get("anio_hasta")));
                query_conteo.setParameter("anio_hasta",Integer.parseInt(criterios.get("anio_hasta")));
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("precio_desde")) {
                query.setParameter("precio_desde",Double.parseDouble(criterios.get("precio_desde")));
                query_conteo.setParameter("precio_desde",Double.parseDouble(criterios.get("precio_desde")));
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("precio_hasta")) {
                query.setParameter("precio_hasta",Double.parseDouble(criterios.get("precio_hasta")));
                query_conteo.setParameter("precio_hasta",Double.parseDouble(criterios.get("precio_hasta")));
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("marca")) {
                query.setParameter("marca",criterios.get("marca"));
                query_conteo.setParameter("marca",criterios.get("marca"));
            }
            //concatenar criterio e indicar que ya se ha concatenado
            if(criterios.containsKey("tipo")) {
                query.setParameter("tipo",criterios.get("tipo"));
                query_conteo.setParameter("tipo",criterios.get("tipo"));
            }
            //setear paginacion
            query.setFirstResult((pagina - 1) * pageSize);
            query.setMaxResults(pageSize);

            //conteo de publicaciones segun criterios
            long count_pages = conteoSegunQuery(query_conteo);

            List<Publicacion> pubs = query.getResultList();
            List<String> imgs = new ArrayList<>();

            //cargar primera imagen de una publicacion
            for(Publicacion pub : pubs) {
                imgs.add(ImagenServicios.getInstancia().findByPublicacionId(pub.getId()).get(0).getRuta());
            }

            if(pubs.size() > 0) {
                resp.put("publicaciones", pubs);
                resp.put("rutas_imagenes_publicaciones", imgs);

                if (imgs.size() == 0) {
                    resp.put("hay_imagenes", true);
                }
            }

            //pagina anterior
            resp.put("hay_pagina_anterior", pagina > 1);
            resp.put("url_anterior", url + "?page_num=" + (pagina - 1) + urlParams);
            //pagina siguiente
            resp.put("hay_pagina_siguiente", count_pages > pagina);
            resp.put("url_siguiente", url + "?page_num=" + (pagina + 1) + urlParams);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }

        return resp;
    }

    private long conteoSegunQuery(TypedQuery<Long> query_conteo) {
        Long cantPublicacionesFiltradas = query_conteo.getSingleResult();
        Long x = (long)Math.ceil((double)cantPublicacionesFiltradas / (double)pageSize);
        return x;
    }
}