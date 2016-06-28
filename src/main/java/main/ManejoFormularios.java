package main;

import freemarker.template.Configuration;
import modelos.Marca;
import modelos.Tipo;
import servicios.MarcaServicios;
import servicios.TipoServicios;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;
import static spark.debug.DebugScreen.enableDebugScreen;

/**
 * Created by forte on 24/06/16.
 */
public class ManejoFormularios {
    public static void manejarFormularios() {

        //TODO borrar cuando acabe el desarrollo
        get("/fail", (request, response) -> "fail");

        //manejo de llamadas POST
        post("/admin/marca/crear/", (req, res) -> {
            String textoMarca = req.queryParams("marca");

            Marca marca = new Marca();
            marca.setNombre(textoMarca);

            if(MarcaServicios.getInstancia().create(marca)) {
                res.redirect("/");
            }
            else {
                res.redirect("/fail");
            }

            return "";
        });

        post("/admin/tipo/crear/", (req, res) -> {
            String textoTipo = req.queryParams("tipo");

            Tipo tipo = new Tipo();
            tipo.setNombre(textoTipo);

            if(TipoServicios.getInstancia().create(tipo)) {
                res.redirect("/");
            }
            else {
                res.redirect("/fail");
            }

            return "";
        });
    }
}
