package main;

import com.google.gson.Gson;
import modelos.Marca;
import modelos.Tipo;
import servicios.MarcaServicios;
import servicios.TipoServicios;

import java.util.List;

import static spark.Spark.after;
import static spark.Spark.get;

/**
 * Created by forte on 27/06/16.
 */
public class ManejoAjax {

    public static void manejarAjax() {
        Gson gson = new Gson();

        get("/admin/ajax/marca/nuevo/", (req, res) -> {
            String marca = req.queryParams("target");

            List<Marca> marcas = MarcaServicios.getInstancia().findByMarca(marca);

            if(marcas.size() > 0) {
                return new MessageNuevaMarca(false,"Marca ya existe");
            }
            else {
                return new MessageNuevaMarca(true,"Nueva marca");
            }

        }, gson::toJson);

        get("/admin/ajax/tipo/nuevo/", (req, res) -> {
            String tipo = req.queryParams("target");

            List<Tipo> tipos = TipoServicios.getInstancia().findByTipo(tipo);

            if(tipos.size() > 0) {
                return new MessageNuevaMarca(false,"Tipo ya existe");
            }
            else {
                return new MessageNuevaMarca(true,"Nuevo tipo");
            }

        }, gson::toJson);
    }
}

class MessageNuevaMarca {
    public Boolean exito;
    public String mensaje;

    public MessageNuevaMarca(Boolean _exito, String _mensaje) {
        this.mensaje = _mensaje;
        this.exito = _exito;
    }
}
