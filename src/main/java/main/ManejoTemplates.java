package main;

import freemarker.template.Configuration;
import modelos.Marca;
import modelos.Tipo;
import servicios.MarcaServicios;
import servicios.TipoServicios;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;

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

        get("/admin/marca/crear/", (req, res) -> {
            HashMap<String,Object> data = new HashMap<>();

            //setear datos del template
            data.put("title","Creacion de Nueva Marca de Vehiculos");
            data.put("form_url","/admin/marca/crear/");
            data.put("label","Nueva marca");
            data.put("field_name","marca");

            return new ModelAndView(data,"admin_create_read_delete_marca_tipo.ftl");
        }, new FreeMarkerEngine(conf));

        get("/admin/tipo/crear/", (req, res) -> {
            HashMap<String,Object> data = new HashMap<>();

            //setear datos del template
            data.put("title","Creacion de Nuevo Tipo de Vehiculo");
            data.put("form_url","/admin/tipo/crear/");
            data.put("label","Nuevo tipo");
            data.put("field_name","tipo");

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
            HashMap<String,Object> data = new HashMap<>();

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

            //setear datos del template
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
