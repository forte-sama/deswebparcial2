package main;

/**
 * Created by Dell_2 on 6/29/2016.
 */

import modelos.Usuario;

import static spark.Spark.*;
public class ManejoFiltros {
    public void aplicarFiltros(){
        before("/login/",(request, response) -> {
            Usuario usuario=request.session().attribute("usuario");
            if(usuario!=null){
                response.redirect("/");
            }

        });



    }




}
