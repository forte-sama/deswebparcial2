package servicios;

/**
 * Created by saleta on 7/18/2016.
 */
import com.google.gson.Gson;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import main.Validation;
import modelos.*;
import servicios.*;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.imageio.ImageIO;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static main.ManejoFormularios.sumarDias;
import static main.ManejoFormularios.tipoArchivo;
import static modelos.JsonUtil.json;
import static spark.Spark.*;

public class RestServicios {

    public RestServicios() {

        get("/restServices/marcas", (req, res) -> {
            List<Marca> marcas = MarcaServicios.getInstancia().findAll();

            return marcas;
        }, json());

        get("/restServices/tipos", (req, res) -> {

                List<Tipo> tipos = TipoServicios.getInstancia().findAll();
                return tipos;

        }, json());
        get("/restServices/publicaciones", (req, res) -> {
            formatPublicaciones();
            return formatPublicaciones() ;
        }, json());

        post("restServices/crearPublicacion", "multipart/form-data", (request, response) -> {


            String location = "src/main/resources/public/img";
            long maxFileSize = 5000000;
            long maxRequestSize = 5000000;
            int fileSizeThreshold = 1024;
            MultipartConfigElement multipartConfigElement = new MultipartConfigElement(location, maxFileSize, maxRequestSize, fileSizeThreshold);
            request.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);
            Publicacion publicacion = new Publicacion();
            publicacion.setFechaInicio(new Date());
            publicacion.setFechaFin(sumarDias(publicacion.getFechaInicio(),Integer.parseInt(request.queryParams("dias"))));
            publicacion.setUsuario(UsuarioServicios.getInstancia().find(request.queryParams("usuario")));
            publicacion.setAnio(Integer.parseInt(request.queryParams("anio")));
            publicacion.setPasajeros(Integer.parseInt(request.queryParams("pasajeros")));
            publicacion.setUso(Integer.parseInt(request.queryParams("uso")));
            publicacion.setCilindros(Integer.parseInt(request.queryParams("cilindros")));
            publicacion.setCombustible(request.queryParams("combustible"));
            publicacion.setMarca(MarcaServicios.getInstancia().find(Integer.parseInt(request.queryParams("marca"))));
            publicacion.setModelo(request.queryParams("modelo"));
            publicacion.setVendido(false);
            publicacion.setObservaciones(request.queryParams("observaciones"));
            publicacion.setTransmision(request.queryParams("transmision"));
            publicacion.setPrecioVehiculo(Double.parseDouble(request.queryParams("precio")));
            publicacion.setTipo(TipoServicios.getInstancia().find(Integer.parseInt(request.queryParams("tipo"))));
            publicacion.setPrecioPublicacion(PrecioPublicacionServicios.getInstancia().precioActual().getPrecio()*Integer.parseInt(request.queryParams("dias")));
            PublicacionServicios.getInstancia().create(publicacion);
            Collection<Part> parts = request.raw().getParts();
            int i =0 ;

            for(Part part : parts) {
                if(!part.getName().equals("upfile"))
                    continue;
                System.out.print(part.getName());
                System.out.println("Name:");
                System.out.println(part.getName());
                System.out.println("Size: ");
                System.out.println(part.getSize());
                System.out.println("Filename:");
                System.out.println(part.getSubmittedFileName());
                String extension = tipoArchivo(part.getSubmittedFileName());
                String ruta = "src/main/resources/public/img/"+publicacion.getId()+"_" + i + "." + extension;


                Path out = Paths.get(ruta);
                try (final InputStream in = part.getInputStream()) {
                    Files.copy(in, out);
                    System.out.print(part.getSubmittedFileName());
                    String rutaDB = "/img/"+publicacion.getId()+"_" + i + "." + extension;
                    Imagen imagen = new Imagen();
                    imagen.setPublicacion(publicacion);
                    imagen.setRuta(rutaDB);
                    ImagenServicios.getInstancia().create(imagen);
                    i++;
                    part.delete();

                }

            }



            return "OK";
        },json());
        after("/restServices/*",(req, res) -> {
            res.type("application/json");
        });

        get("/restServices/autenticar/:usuario/:contrasena", (req, res) -> {

            Usuario usuario = UsuarioServicios.getInstancia().find(req.params("usuario"));
            Mensaje msg = new Mensaje();
            System.out.print(req.params("contrasena"));
            if (usuario==null ||!req.params("contrasena").equals(usuario.getPassword())){
                msg.setCodigo("1");
                msg.setMensaje("Credenciales no validas...");
            }
            else {
                msg.setCodigo("0");
                msg.setMensaje("Exito...");
                msg.setUsername(usuario.getUsername());
                if(usuario.getAutorizado())
                    msg.setPublica("true");
                else
                    msg.setPublica("false");
            }
            return msg;
        }, json());


        // more routes


    }

    private List<PublicacionInfo> formatPublicaciones(){
        List<Publicacion> publicaciones = PublicacionServicios.getInstancia().findAllPublicaciones();
        List<PublicacionInfo> ret = new ArrayList<>();
        for(Publicacion p : publicaciones){
            PublicacionInfo pi = new PublicacionInfo();
            Usuario u = UsuarioServicios.getInstancia().find(p.getUsuario().getUsername());
            UsuarioInfo ui = new UsuarioInfo();
            ui.setEmail(u.getEmail());
            ui.setNombre(u.getNombre());
            ui.setTelefono(u.getTelefono());
            pi.setVendedor(ui);
            pi.setAnio(p.getAnio());
            pi.setModelo(p.getModelo());
            pi.setPrecio(p.getPrecioVehiculo());
            pi.setMarca(p.getMarca().getNombre());
            List<Imagen> imagenes = ImagenServicios.getInstancia().findByPublicacionId(p.getId());
            String rutaBase = "src/main/resources/public";
            for(Imagen i :imagenes){
                 try {
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                     File file = new File(rutaBase+i.getRuta());
                        BufferedImage img = ImageIO.read(file);
                        ImageIO.write(img, "jpg", os);

                        String imagenB64 = Base64.encode(os.toByteArray());
                        System.out.println(imagenB64);
                        pi.getImagenes().add(imagenB64);



                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            ret.add(pi);
        }
        return ret;

    }

    private class UsuarioInfo{
        private String nombre;
        private String telefono;
        private String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }
    }

    private class PublicacionInfo{
        private UsuarioInfo vendedor;
        private String marca;
        private String modelo;
        private Integer anio;
        private Double precio;
        private List<String> imagenes = new ArrayList<>();

        public UsuarioInfo getVendedor() {
            return vendedor;
        }

        public void setVendedor(UsuarioInfo vendedor) {
            this.vendedor = vendedor;
        }

        public String getMarca() {
            return marca;
        }

        public void setMarca(String marca) {
            this.marca = marca;
        }

        public String getModelo() {
            return modelo;
        }

        public void setModelo(String modelo) {
            this.modelo = modelo;
        }

        public Integer getAnio() {
            return anio;
        }

        public void setAnio(Integer anio) {
            this.anio = anio;
        }

        public Double getPrecio() {
            return precio;
        }

        public void setPrecio(Double precio) {
            this.precio = precio;
        }

        public List<String> getImagenes() {
            return imagenes;
        }

        public void setImagenes(List<String> imagenes) {
            this.imagenes = imagenes;
        }
    }
    private class Mensaje{
        private String codigo;
        private String mensaje;
        private String publica;
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPublica() {
            return publica;
        }

        public void setPublica(String publica) {
            this.publica = publica;
        }

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
    }
}