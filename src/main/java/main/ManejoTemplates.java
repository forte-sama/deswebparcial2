package main;

import freemarker.template.Configuration;
import servicios.MarcaServicios;
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

            //setear datos para el template
            data.put("title","Creacion de Nueva Marca");
            data.put("form_url","/admin/marca/crear/");
            data.put("label","Nueva marca");
            data.put("field_name","marca");

            return new ModelAndView(data,"admin_form_marca_tipo.ftl");
        }, new FreeMarkerEngine(conf));

        get("/admin/tipo/crear/", (req, res) -> {
            HashMap<String,Object> data = new HashMap<>();

            //setear datos para el template
            data.put("title","Creacion de Nuevo Tipo de vehiculo");
            data.put("form_url","/admin/tipo/crear/");
            data.put("label","Nuevo tipo");
            data.put("field_name","tipo");

            return new ModelAndView(data,"admin_form_marca_tipo.ftl");
        }, new FreeMarkerEngine(conf));

    }
}
