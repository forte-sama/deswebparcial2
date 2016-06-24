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

        //iniciar manejador de templates (GET requests)
        ManejoTemplates.manejarTemplates();
        //iniciar manejador de formularios (POST requests)
        ManejoFormularios.manejarFormularios();
    }
}
