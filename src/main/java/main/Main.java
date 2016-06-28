package main;

import servicios.PrecioPublicacionServicios;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {
    public static void main(String[] args) {
        //indicar ruta de archivos publicos
        staticFileLocation("/public");
        //agregar pantalla de debug
        enableDebugScreen();
        //inicializar servicio H2
        DBService.inicializar();
        //inicializar precio base si es primera vez
        PrecioPublicacionServicios.getInstancia().precioDefault();

        //iniciar manejador de templates (GET requests)
        ManejoTemplates.manejarTemplates();
        //iniciar manejador de formularios (POST requests)
        ManejoFormularios.manejarFormularios();
        //iniciar manejador de llamadas AJAX (GET o POST)
        ManejoAjax.manejarAjax();
    }
}
