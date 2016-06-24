package main;

import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {
    public static void main(String[] args) {

        //indicar ruta de archivos publicos
        staticFileLocation("/public");
        //agregar pantalla de debug
        enableDebugScreen();

        //configuracion de freemarker
        Configuration conf = new Configuration();
        conf.setClassForTemplateLoading(Main.class, "/templates");

        get("/", (req, res) -> {
            return new ModelAndView(null,"_basic.ftl");
        }, new FreeMarkerEngine(conf));
    }
}
