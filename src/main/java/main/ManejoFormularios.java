package main;

import freemarker.template.Configuration;
import modelos.Marca;
import modelos.PrecioPublicacion;
import modelos.Tipo;
import modelos.Usuario;
import servicios.MarcaServicios;
import servicios.PrecioPublicacionServicios;
import servicios.TipoServicios;
import servicios.UsuarioServicios;
import spark.Session;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Collection;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

/**
 * Created by forte on 24/06/16.
 */
public class ManejoFormularios {
    public static void manejarFormularios() {

        //TODO borrar cuando acabe el desarrollo
        get("/fail", (request, response) -> "fail");

        //manejo de llamadas POST
        //manejo formulario edicion de precio de publicaciones
        post("/admin/precio_publicacion/editar/", (req, res) -> {
            String rawPrecio = req.queryParams("precio");

            try {
                //castear texto de nuevo precio
                Double precio = Double.parseDouble(rawPrecio);

                //solo seguir si el nuevo precio es positivo (para evitar inconvenientes)
                if(precio.compareTo(0.0) > 0) {
                    //construir nuevo precio
                    PrecioPublicacion pp = new PrecioPublicacion();
                    pp.setPrecio(precio);
                    pp.setFechaModificacion(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));

                    //persistir nuevo precio
                    PrecioPublicacionServicios.getInstancia().create(pp);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //finalizar redireccionando a la misma pagina
            res.redirect("/admin/precio_publicacion/editar/");

            return "";
        });

        //manejo formulario creacion de marca o tipo
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

        //manejo formulario edicion de marca o tipo
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

        post("/login/", (request, response) -> {

            Session session=request.session(true);
            Usuario usuario = UsuarioServicios.getInstancia().find(request.queryParams("username"));

            if (usuario==null ||!request.queryParams("password").equals(usuario.getPassword())){
                halt(401,"Credenciales no validas...");
                return "fail";
            }
            else {
                session.attribute("usuario", usuario);
                response.redirect("/");
                return "success";
            }
        });

        post("/cerrarsesion/", (request, response) -> {
            request.session().invalidate();
            response.redirect("/");
            return "Cesion cerrada";
        });

        post("/usuario/registro/", (request, response) -> {
            if(!Validation.getInstancia().validarUsuario(request,false))
                return "Formulario invalido. Por favor no invente.";

            Usuario usuario = new Usuario();
            usuario.setUsername(request.queryParams("username"));
            usuario.setNombre(request.queryParams("nombre"));
            usuario.setEmail(request.queryParams("email"));
            usuario.setDireccion(request.queryParams("direccion"));
            usuario.setTelefono(request.queryParams("telefono"));
            usuario.setCelular(request.queryParams("celular"));
            usuario.setPassword(request.queryParams("password"));
            usuario.setAdmin(false);
            usuario.setAutorizado(false);

            UsuarioServicios.getInstancia().create(usuario);

            response.redirect("/login/");
            return "OK";
        });

        post("/usuario/edicion/", (request, response) -> {
            if(!Validation.getInstancia().validarUsuario(request,true))
                return "Formulario invalido. Por favor no invente.";

            Usuario usuario = UsuarioServicios.getInstancia().find(request.queryParams("username"));
            usuario.setNombre(request.queryParams("nombre"));
            usuario.setEmail(request.queryParams("email"));
            usuario.setDireccion(request.queryParams("direccion"));
            usuario.setTelefono(request.queryParams("telefono"));
            usuario.setCelular(request.queryParams("celular"));
            UsuarioServicios.getInstancia().edit(usuario);

            response.redirect("/");
            return "OK";
        });

        post("/upload", "multipart/form-data", (request, response) -> {

            String location = "src/main/resources/public/img";
            long maxFileSize = 5000000;
            long maxRequestSize = 5000000;
            int fileSizeThreshold = 1024;
            MultipartConfigElement multipartConfigElement = new MultipartConfigElement(location, maxFileSize, maxRequestSize, fileSizeThreshold);
            request.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);


            Collection<Part> parts = request.raw().getParts();
            for(Part part : parts) {
                if(!part.getName().equals("upfile"))
                    continue;
                System.out.println("Name:");
                System.out.println(part.getName());
                System.out.println("Size: ");
                System.out.println(part.getSize());
                System.out.println("Filename:");
                System.out.println(part.getSubmittedFileName());
                String fName = part.getSubmittedFileName();
                Path out = Paths.get("src/main/resources/public/img/"+fName);
                try (final InputStream in = part.getInputStream()) {
                    Files.copy(in, out);
                    part.delete();

                }
            }

            multipartConfigElement = null;
            parts = null;

            return "OK";
        });
    }
}
