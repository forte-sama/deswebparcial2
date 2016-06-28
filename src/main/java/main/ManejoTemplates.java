package main;

import freemarker.template.Configuration;
import modelos.Marca;
import modelos.PrecioPublicacion;
import modelos.Tipo;
import modelos.Usuario;
import servicios.MarcaServicios;
import servicios.PrecioPublicacionServicios;
import servicios.TipoServicios;
import servicios.UsuarioServicios;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;

/**
 * Created by forte on 24/06/16.
 */
public class ManejoTemplates {
    public static void manejarTemplates() {
        //configuracion de freemarker
        Configuration conf = new Configuration();
        conf.setClassForTemplateLoading(Main.class, "/templates");

        //manejo de llamadas GET
        get("/", (req, res) -> {
            return new ModelAndView(null,"_basic.ftl");
        }, new FreeMarkerEngine(conf));

        get("/admin/user/ver/", (req, res) -> {
            HashMap<String,Object> data = new HashMap<>();

            //obtener lista total de usuarios
            List<Usuario> usuarios_lista = UsuarioServicios.getInstancia().findAll();
            data.put("usuarios",usuarios_lista);
            //obtener lista de usuarios sin autorizar
            List<Usuario> usuarios_sin_registrar = UsuarioServicios.getInstancia().findNoAutorizado();
            data.put("usuarios_sin_registrar",usuarios_sin_registrar);

            return new ModelAndView(data,"admin_read_usuario.ftl");
        }, new FreeMarkerEngine(conf));

        get("/admin/user/autorizar/:username", (req, res) -> {
            //obtener parametros
            String username = req.params("username");
            //buscar objeto usuario deseado
            Usuario user = UsuarioServicios.getInstancia().find(username);
            //seguir solo si no es null
            if(user != null) {
                //autorizar usuario para publicar
                user.setAutorizado(true);
                //persistir cambios
                UsuarioServicios.getInstancia().edit(user);
            }

            //redireccionar a vista original
            res.redirect("/admin/user/ver/");

            return "";
        });

        get("/admin/user/borrar/:username", (req, res) -> {
            //obtener parametros
            String username = req.params("username");
            //buscar objeto usuario deseado
            Usuario user = UsuarioServicios.getInstancia().find(username);
            //seguir solo si no es null
            if(user != null) {
                //autorizar usuario para publicar
                user.setAutorizado(true);
                //persistir cambios
                UsuarioServicios.getInstancia().delete(user);
            }

            //redireccionar a vista original
            res.redirect("/admin/user/ver/");

            return "";
        });

        get("/admin/precio_publicacion/editar/", (req, res) -> {
            HashMap<String,Object> data = new HashMap<>();

            //obtener precio actual
            PrecioPublicacion ultimo = PrecioPublicacionServicios.getInstancia().precioActual();

            data.put("precio_actual",ultimo);
            return new ModelAndView(data,"admin_update_precio_publicacion.ftl");
        }, new FreeMarkerEngine(conf));

        get("/admin/:tipo_target/crear/", (req, res) -> {
            HashMap<String,Object> data = new HashMap<>();

            String tipo_target = req.params("tipo_target");

            //setear datos del template
            if(tipo_target.contentEquals("marca")) {
                data.put("title", "Creacion de Nueva Marca de Vehiculos");
                data.put("form_url", "/admin/marca/crear/");
                data.put("label", "Nueva marca");
                data.put("field_name", "marca");
            }
            else if(tipo_target.contentEquals("tipo")) {
                data.put("title","Creacion de Nuevo Tipo de Vehiculo");
                data.put("form_url","/admin/tipo/crear/");
                data.put("label","Nuevo tipo");
                data.put("field_name","tipo");
            }

            return new ModelAndView(data,"admin_create_read_delete_marca_tipo.ftl");
        }, new FreeMarkerEngine(conf));

        get("/admin/:tipo_target/editar/:target_id", (req, res) -> {
            HashMap<String,Object> data = new HashMap<>();

            String tipo_target = req.params("tipo_target");

            boolean fallo = false;
            Integer target_id = null;

            try {
                target_id = Integer.parseInt(req.params("target_id"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                fallo = true;
            }

            if(fallo) {
                res.redirect("/fail");
            }

            //setear datos del template
            if(tipo_target.contentEquals("marca")) {
                data.put("title", "Edicion de marca vehiculo");
                data.put("form_url", "/admin/marca/editar/");
                data.put("label", "Nombre marca");

                Marca marca = MarcaServicios.getInstancia().find(target_id);
                data.put("item",marca);

                fallo = marca == null;
            }
            else if(tipo_target.contentEquals("tipo")) {
                data.put("title", "Edicion de tipo de vehiculo");
                data.put("form_url", "/admin/tipo/editar/");
                data.put("label", "Nombre tipo");

                Tipo tipo = TipoServicios.getInstancia().find(target_id);
                data.put("item",tipo);

                fallo = tipo == null;
            }

            if(fallo) {
                res.redirect("/fail");
            }

            return new ModelAndView(data,"admin_update_marca_tipo.ftl");
        }, new FreeMarkerEngine(conf));

        get("/admin/:tipo_target/borrar/:target_id", (req, res) -> {

            //obtener parametros del url
            String tipo_target = req.params("tipo_target");

            boolean exito = true;
            Integer target_id = null;

            try {
                target_id = Integer.parseInt(req.params("target_id"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                exito = false;
            }

            if(!exito) {
                res.redirect("/fail");
            }

            if(tipo_target.contentEquals("marca")) {
                Marca marca = MarcaServicios.getInstancia().find(target_id);
                exito = MarcaServicios.getInstancia().delete(marca);
            }
            else if(tipo_target.contentEquals("tipo")) {
                Tipo tipo = TipoServicios.getInstancia().find(target_id);
                exito = TipoServicios.getInstancia().delete(tipo);
            }

            if(!exito) {
                res.redirect("/fail");
            }
            else {
                res.redirect("/admin/" + tipo_target + "/crear/");
            }

            return "";
        });
    }
}