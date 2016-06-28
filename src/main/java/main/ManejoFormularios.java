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
        post("/admin/:tipo_target/crear/", (req, res) -> {
            String tipo_target = req.params("tipo_target");
            String texto = req.queryParams("target");

            boolean exito = true;

            if(tipo_target.contentEquals("marca")) {
                Marca marca = new Marca();
                marca.setNombre(texto);

                exito = MarcaServicios.getInstancia().create(marca);
            }
            else if(tipo_target.contentEquals("tipo")) {
                Tipo tipo = new Tipo();
                tipo.setNombre(texto);

                exito = TipoServicios.getInstancia().create(tipo);
            }

            if(exito) {
                res.redirect("/admin/" + tipo_target + "/crear/");
            }
            else {
                res.redirect("/fail");
            }

            return "";
        });

        post("/admin/:tipo_target/editar/", (req, res) -> {
            String tipo_target = req.params("tipo_target");
            String texto = req.queryParams("target");
            Integer id = Integer.parseInt(req.queryParams("id"));

            boolean exito = true;

            if(tipo_target.contentEquals("marca")) {
                Marca marca = new Marca();
                marca.setNombre(texto);
                marca.setId(id);

                exito = MarcaServicios.getInstancia().edit(marca);
            }
            else if(tipo_target.contentEquals("tipo")) {
                Tipo tipo = new Tipo();
                tipo.setNombre(texto);
                tipo.setId(id);

                exito = TipoServicios.getInstancia().edit(tipo);
            }

            if(exito) {
                res.redirect("/admin/" + tipo_target + "/crear/");
            }
            else {
                res.redirect("/fail");
            }

            return "";
        });
    }
}
