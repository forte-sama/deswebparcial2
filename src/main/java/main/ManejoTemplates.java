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


        get("/admin/marca/nueva", (req, res) -> {
            HashMap<String,Object> data = new HashMap<>();

            //manejar cosas

            return new ModelAndView(data,"admin_marcas.ftl");
        }, new FreeMarkerEngine(conf));

    }
}
