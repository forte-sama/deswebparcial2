package main;

import freemarker.template.Configuration;
import modelos.*;
import servicios.*;
import spark.ModelAndView;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.*;

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
            HashMap<String,Object> data = new HashMap<>();
            Usuario usuario_loguiado = req.session().attribute("usuario");
            if(usuario_loguiado != null)
                data.put("usuario",usuario_loguiado);

            //obtener las publicaciones que cumplan con los filtros
            data.put("publicaciones",filtrarPublicaciones(req));
            data.put("opciones",obtenerOpcionesFiltros());

            return new ModelAndView(data,"publicacion_lista.ftl");
        }, new FreeMarkerEngine(conf));

        get("/admin/user/ver/", (req, res) -> {
            HashMap<String,Object> data = new HashMap<>();

            //obtener lista total de usuarios
            List<Usuario> usuarios_lista = UsuarioServicios.getInstancia().findAll();
            data.put("usuarios",usuarios_lista);
            //obtener lista de usuarios sin autorizar
            List<Usuario> usuarios_sin_registrar = UsuarioServicios.getInstancia().findNoAutorizado();
            data.put("usuarios_sin_registrar",usuarios_sin_registrar);

            return new ModelAndView(data,"admin_read_usuario.ftl");
        }, new FreeMarkerEngine(conf));

        get("/admin/user/autorizar/:username", (req, res) -> {
            //obtener parametros
            String username = req.params("username");
            //buscar objeto usuario deseado
            Usuario user = UsuarioServicios.getInstancia().find(username);
            //seguir solo si no es null
            if(user != null) {
                //autorizar usuario para publicar
                user.setAutorizado(true);
                //persistir cambios
                UsuarioServicios.getInstancia().edit(user);
            }

            //redireccionar a vista original
            res.redirect("/admin/user/ver/");

            return "";
        });

        get("/admin/user/borrar/:username", (req, res) -> {
            //obtener parametros
            String username = req.params("username");
            //buscar objeto usuario deseado
            Usuario user = UsuarioServicios.getInstancia().find(username);
            //seguir solo si no es null
            if(user != null) {
                //autorizar usuario para publicar
                user.setAutorizado(true);
                //persistir cambios
                UsuarioServicios.getInstancia().delete(user);
            }

            //redireccionar a vista original
            res.redirect("/admin/user/ver/");

            return "";
        });

        get("/admin/precio_publicacion/editar/", (req, res) -> {
            HashMap<String,Object> data = new HashMap<>();

            //obtener precio actual
            PrecioPublicacion ultimo = PrecioPublicacionServicios.getInstancia().precioActual();

            data.put("precio_actual",ultimo);
            return new ModelAndView(data,"admin_update_precio_publicacion.ftl");
        }, new FreeMarkerEngine(conf));

        get("/admin/:tipo_target/crear/", (req, res) -> {
            HashMap<String,Object> data = new HashMap<>();

            String tipo_target = req.params("tipo_target");

            //setear datos del template
            if(tipo_target.contentEquals("marca")) {
                data.put("title", "Creacion de Nueva Marca de Vehiculos");
                data.put("form_url", "/admin/marca/crear/");
                data.put("label", "Nueva marca");
                data.put("field_name", "marca");
            }
            else if(tipo_target.contentEquals("tipo")) {
                data.put("title","Creacion de Nuevo Tipo de Vehiculo");
                data.put("form_url","/admin/tipo/crear/");
                data.put("label","Nuevo tipo");
                data.put("field_name","tipo");
            }

            return new ModelAndView(data,"admin_create_read_delete_marca_tipo.ftl");
        }, new FreeMarkerEngine(conf));

        get("/admin/:tipo_target/editar/:target_id", (req, res) -> {
            HashMap<String,Object> data = new HashMap<>();

            String tipo_target = req.params("tipo_target");

            boolean fallo = false;
            Integer target_id = null;

            try {
                target_id = Integer.parseInt(req.params("target_id"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                fallo = true;
            }

            if(fallo) {
                res.redirect("/fail");
            }

            //setear datos del template
            if(tipo_target.contentEquals("marca")) {
                data.put("title", "Edicion de marca vehiculo");
                data.put("form_url", "/admin/marca/editar/");
                data.put("label", "Nombre marca");

                Marca marca = MarcaServicios.getInstancia().find(target_id);
                data.put("item",marca);

                fallo = marca == null;
            }
            else if(tipo_target.contentEquals("tipo")) {
                data.put("title", "Edicion de tipo de vehiculo");
                data.put("form_url", "/admin/tipo/editar/");
                data.put("label", "Nombre tipo");

                Tipo tipo = TipoServicios.getInstancia().find(target_id);
                data.put("item",tipo);

                fallo = tipo == null;
            }

            if(fallo) {
                res.redirect("/fail");
            }

            return new ModelAndView(data,"admin_update_marca_tipo.ftl");
        }, new FreeMarkerEngine(conf));

        get("/admin/:tipo_target/borrar/:target_id", (req, res) -> {

            //obtener parametros del url
            String tipo_target = req.params("tipo_target");

            boolean exito = true;
            Integer target_id = null;

            try {
                target_id = Integer.parseInt(req.params("target_id"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                exito = false;
            }

            if(!exito) {
                res.redirect("/fail");
            }

            if(tipo_target.contentEquals("marca")) {
                Marca marca = MarcaServicios.getInstancia().find(target_id);
                exito = MarcaServicios.getInstancia().delete(marca);
            }
            else if(tipo_target.contentEquals("tipo")) {
                Tipo tipo = TipoServicios.getInstancia().find(target_id);
                exito = TipoServicios.getInstancia().delete(tipo);
            }

            if(!exito) {
                res.redirect("/fail");
            }
            else {
                res.redirect("/admin/" + tipo_target + "/crear/");
            }

            return "";
        });

        get("/login/", (req, res) -> {
            HashMap<String,Object> data = new HashMap<>();
            return new ModelAndView(data, "login.ftl");

        },new FreeMarkerEngine(conf));

        get("/usuario/registro/", (req, res) -> {
            HashMap<String,Object> data = new HashMap<>();
            data.put("editando",false);
            return new ModelAndView(data, "usuario_registro.ftl");

        },new FreeMarkerEngine(conf));

        get("/usuario/edicion/:username", (req, res) -> {
            String username = req.params("username");
            if(!Validation.getInstancia().usuarioExiste(username)){
                res.redirect("/");
                return null;
            }

            Usuario usuario = UsuarioServicios.getInstancia().find(username);
            HashMap<String,Object> data = new HashMap<>();
            data.put("editando",true);
            data.put("usuario",usuario);
            return new ModelAndView(data, "usuario_registro.ftl");

        },new FreeMarkerEngine(conf));

        get("/publicacion/ver/:pub_id/", (req, res) -> {
            HashMap<String,Object> data = new HashMap<>();

            String rawId = req.params("pub_id");

            try {
                Integer id = Integer.parseInt(rawId);

                Publicacion p = PublicacionServicios.getInstancia().find(id);
                List<Imagen> imagenes = ImagenServicios.getInstancia().findByPublicacionId(p.getId());
                List<Comentario> comentarios = ComentarioServicios.getInstancia().findByPublicacionId(p.getId());

                data.put("pub",p);
                data.put("vendedor",p.getUsuario());
                data.put("imagenes",imagenes);
                data.put("comentarios",comentarios);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            return new ModelAndView(data,"publicacion_detalle.ftl");
        }, new FreeMarkerEngine(conf));
    }

    private static HashMap<String,List<String>> obtenerOpcionesFiltros() {
        HashMap<String,List<String>> opciones = new HashMap<>();

        //anios
        opciones.put("anios",new ArrayList<>());
        for(int i = Calendar.getInstance().get(Calendar.YEAR) + 1; i >= 1890; i--) {
            opciones.get("anios").add(i + "");
        }
        //precios
        opciones.put("precios",new ArrayList<>());
        for(int i = 5000000; i >= 0;) {
            opciones.get("precios").add(i + "");

            i -= 50000;
        }
        //marcas
        opciones.put("marcas",new ArrayList<>());
        for(Marca marca : MarcaServicios.getInstancia().findAll()) {
            opciones.get("marcas").add(marca.getNombre());
        }
        //tipos
        opciones.put("tipos",new ArrayList<>());
        for(Tipo tipo : TipoServicios.getInstancia().findAll()) {
            opciones.get("tipos").add(tipo.getNombre());
        }

        return opciones;
    }

    private static List<Publicacion> filtrarPublicaciones(Request req) {
        //obtener criterios aunque tengan valor default (default no filtra nada)
        Set<String> rawCriterios = req.queryParams();
        //lista de criterios curados (solo los que no estan en default)
        HashMap<String,String> criteriosUsados = new HashMap<>();

        for(String criterio : rawCriterios) {
            String valorCriterio = req.queryParams(criterio);
            //agregar si el valor del criterio actual no esta por default
            if(!valorCriterio.toLowerCase().contentEquals("default")) {
                criteriosUsados.put(criterio,valorCriterio);
            }
        }

        for(String llave : criteriosUsados.keySet()) {
            System.out.println(llave + " : " + criteriosUsados.get(llave));
        }

        return new ArrayList<>();
    }
}
