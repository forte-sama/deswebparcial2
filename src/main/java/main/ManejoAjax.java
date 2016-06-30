package main;

import com.google.gson.Gson;
import freemarker.template.Configuration;
import modelos.*;
import servicios.*;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Created by forte on 27/06/16.
 */
public class ManejoAjax {

    public static void manejarAjax() {
        //json transformer
        Gson gson = new Gson();

        //configuracion de freemarker
        Configuration conf = new Configuration();
        conf.setClassForTemplateLoading(Main.class, "/templates");

        get("/admin/ajax/marca/nuevo/", (req, res) -> {
            String marca = req.queryParams("target");

            List<Marca> marcas = MarcaServicios.getInstancia().findByMarca(marca);

            if(marcas.size() > 0) {
                return new MensajeRespuesta(false,"Marca ya existe");
            }
            else {
                return new MensajeRespuesta(true,"Nueva marca");
            }

        }, gson::toJson);

        get("/admin/ajax/tipo/nuevo/", (req, res) -> {
            String tipo = req.queryParams("target");

            List<Tipo> tipos = TipoServicios.getInstancia().findByTipo(tipo);

            if(tipos.size() > 0) {
                return new MensajeRespuesta(false,"Tipo ya existe");
            }
            else {
                return new MensajeRespuesta(true,"Nuevo tipo");
            }

        }, gson::toJson);

        get("/admin/ajax/:target/getall/", (request, response) -> {
            HashMap<String,Object> data = new HashMap<>();

            String target = request.params("target");

            if(target.contentEquals("marca")) {
                data.put("tipo_target","marca");
                data.put("items",MarcaServicios.getInstancia().findAll());
            }
            else if(target.contentEquals("tipo")) {
                data.put("tipo_target","tipo");
                data.put("items",TipoServicios.getInstancia().findAll());
            }

            if(((ArrayList<Object>)data.get("items")).size() == 0) {
                data.put("no_hay",true);
            }

            return new ModelAndView(data,"table_marca_tipo.ftl");
        }, new FreeMarkerEngine(conf));

        get("/comentarios/ver/:pub_id/", (request, response) -> {
            HashMap<String,Object> data = new HashMap<>();

            String rawPubId = request.params("pub_id");

            try {
                Integer id = Integer.parseInt(rawPubId);

                Publicacion p = PublicacionServicios.getInstancia().find(id);
                List<Comentario> comentarios = ComentarioServicios.getInstancia().findByPublicacionId(p.getId());

                data.put("comentarios",comentarios);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            return new ModelAndView(data,"comentarios.ftl");
        }, new FreeMarkerEngine(conf));

        post("/comentario/nuevo/", (req, res) -> {
            //obtener datos provenientes de llamada post
            String rawIdPublicacion = req.queryParams("publicacion_id");
            String usuario = req.queryParams("usuario");
            String cuerpo = req.queryParams("cuerpo");
            String rawIdComentarioPadre = req.queryParams("comentario_padre");

            //construir nuevo comentario
            Comentario comentario = new Comentario();
            comentario.setCuerpo(cuerpo);
            comentario.setUsuario(usuario);
            comentario.setPadre(null);

            try {
                Integer idPublicacion = Integer.parseInt(rawIdPublicacion);
                //publicacion correspondiente
                Publicacion pub = PublicacionServicios.getInstancia().find(idPublicacion);

                //pub nunca puede ser null
                comentario.setPublicacion(pub);

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            if(rawIdComentarioPadre != null) {
                try {
                    //seguir construyendo nuevo comentario
                    Integer idComentarioPadre = Integer.parseInt(rawIdComentarioPadre);
                    //comentario padre
                    Comentario padre = ComentarioServicios.getInstancia().find(idComentarioPadre);

                    if (padre != null) {
                        comentario.setPadre(padre);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            //persistir nuevo comentario
            ComentarioServicios.getInstancia().create(comentario);

            //redireccionar a ruta que genera template de comentarios
            res.redirect("/comentarios/ver/" + rawIdPublicacion + "/");
            //enviar mensaje de respuesta
            return "";
        });
    }
}

class MensajeRespuesta {
    public Boolean exito;
    public String mensaje;

    public MensajeRespuesta(Boolean _exito, String _mensaje) {
        this.mensaje = _mensaje;
        this.exito = _exito;
    }
}
