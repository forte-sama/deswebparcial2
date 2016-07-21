/**
 * Created by forte on 24/06/16.
 */
$(document).ready(function($){
    $("#celular").mask("(999) 999-9999");
    $("#telefono").mask("(999) 999-9999");

    $("#btn-show-filtros").click(function(e) {
        e.preventDefault();
    });

    var username = $("span#user_").html();

    //solo seguir si se ha iniciado sesion
    if(username !== undefined && username !== null) {
        var last_login = sessionStorage.getItem(username);
        var last_login_msj;
        //mostrar mensaje de ultimo inicio de sesion
        if (last_login !== null) {
            last_login_msj = "Ultima vez: " + last_login + ".&nbsp;&nbsp;";
        }
        else {
            last_login_msj = "Nunca has ingresado con este dispositivo/navegador.&nbsp;&nbsp;";

            sessionStorage.setItem(username,new Date().toDateString());
        }
        
        $("#last_login_place").html(last_login_msj);
    }
});

