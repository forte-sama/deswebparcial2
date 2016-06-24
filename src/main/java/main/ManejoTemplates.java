package main;

import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;
import static spark.debug.DebugScreen.enableDebugScreen;

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
    }
}
