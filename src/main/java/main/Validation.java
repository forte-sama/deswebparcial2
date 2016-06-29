package main;

import servicios.UsuarioServicios;
import spark.Request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by saleta on 6/29/2016.
 */
public class Validation {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static Validation instancia;

    private Validation() {

    }

    public static Validation getInstancia() {
        if (instancia == null) {
            instancia = new Validation();
        }
        return instancia;
    }

    public boolean validarUsuario(Request request, boolean editando){
        if(!editando){
            if(request.queryParams("username") == null || request.queryParams("password") == null ||
                    request.queryParams("telefono") == null || request.queryParams("celular") == null  ||
                    request.queryParams("email") == null || request.queryParams("direccion") == null )
                return false;
        }
        else{
                if(request.queryParams("telefono") == null || request.queryParams("celular") == null  ||
                        request.queryParams("email") == null || request.queryParams("direccion") == null ||
                        !usuarioExiste(request.queryParams("username")))
                    return false;

            }


        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(request.queryParams("email"));
        if(!matcher.find())
            return false;

        return true;
    }

    public boolean usuarioExiste(String usuario){
        return UsuarioServicios.getInstancia().find(usuario) != null;
    }

}
