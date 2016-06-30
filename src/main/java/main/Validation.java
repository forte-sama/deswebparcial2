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

    public boolean validarPublicacion(Request request, boolean editando){
        if(request.queryParams("marca") == null || request.queryParams("modelo") == null || request.queryParams("anio") == null||
                request.queryParams("uso") == null || request.queryParams("pasajeros") ==null || request.queryParams("cilindros") == null ||
                request.queryParams("combustible") == null || request.queryParams("transmision") == null || request.queryParams("observaciones") == null||
                request.queryParams("dias") == null || request.queryParams("tipo") == null){
            System.out.println("Null value");
            return false;
        }

        if(!isInteger(request.queryParams("dias")) || !isInteger(request.queryParams("uso")) ||
                !isInteger(request.queryParams("cilindros")) || !isInteger(request.queryParams("anio")) ||
                !isInteger(request.queryParams("pasajeros"))){
            System.out.println("bad integer value");
            return  false;
        }





        return true;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

}
