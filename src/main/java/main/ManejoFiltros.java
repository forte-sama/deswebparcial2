package main;

/**
 * Created by Dell_2 on 6/29/2016.
 */

import modelos.Publicacion;
import modelos.Usuario;
import servicios.PublicacionServicios;

import static spark.Spark.*;
public class ManejoFiltros {
    public void aplicarFiltros(){
        before("/login/",(request, response) -> {
            Usuario usuario=request.session().attribute("usuario");
            if(usuario!=null){
                response.redirect("/");
            }
        });

        before("/admin/*",(request, response) -> {
            Usuario usuario=request.session().attribute("usuario");
            if(usuario == null ||usuario.getAdmin()!=true){
                response.redirect("/");
            }
        });

        before("/usuario/edicion/:valor",(request, response) -> {
            Usuario usuario=request.session().attribute("usuario");

            if(usuario == null||!usuario.getUsername().equals(request.params("valor"))){
                response.redirect("/");
            }
        });

        before("/usuario/registro/",(request, response) -> {
            Usuario usuario=request.session().attribute("usuario");
            if(usuario != null){
                response.redirect("/");
            }
        });

        before("/publicacion/crear/",(request, response) -> {
            Usuario usuario=request.session().attribute("usuario");
            if(usuario == null||usuario.getAutorizado()==false){
                response.redirect("/");
            }
        });

        before("/publicacion/editar/:valor/",(request, response) -> {
            Usuario usuario=request.session().attribute("usuario");
            Publicacion publicacion= PublicacionServicios.getInstancia().find(Integer.parseInt(request.params("valor")));
            if(usuario == null||usuario.getAutorizado()==false|| !usuario.getUsername().equals(publicacion.getUsuario().getUsername())){
                response.redirect("/");
            }
        });




    }
}
