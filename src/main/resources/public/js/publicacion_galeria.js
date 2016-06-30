/**
 * Created by forte on 29/06/16.
 */

var id_pub = $("#seccion-comentarios").attr("pub");
var comentario_respuesta_actual;
var modal_form_respuesta = $("#form-respuesta-modal");

$(document).ready(function () {

    $("a.list-group-item").click(function (e) {
        e.preventDefault();
    });

    $("#imagen-modal").click(function () {
        $("#galeria-modal").modal("hide");
    });

    $(".imagen-publicacion").click(function () {
        var ruta_imagen = $(this).find("img").first().attr("src");
        $("#imagen-modal").attr("src", ruta_imagen);
    });

    $("#form-nuevo-comentario").submit(function (e) {
        e.preventDefault();

        var input_nombre_usuario = $(this).find("input#nombre");
        var input_cuerpo = $(this).find("textarea[name=cuerpo]");

        modal_form_respuesta.modal("hide");

        var datos_form = {};
        datos_form.usuario = input_nombre_usuario.val();
        datos_form.cuerpo = input_cuerpo.val();
        datos_form.publicacion_id = id_pub;

        input_nombre_usuario.val("");
        input_cuerpo.val("");

        postearComentario(datos_form);
    });

    $("#form-respuesta-comentario").submit(function (e) {
        e.preventDefault();

        var input_nombre_usuario = $(this).find("input#nombre");
        var input_cuerpo = $(this).find("textarea[name=cuerpo]");

        var datos_form = {};
        datos_form.usuario = input_nombre_usuario.val();
        datos_form.cuerpo = input_cuerpo.val();
        datos_form.publicacion_id = id_pub;
        datos_form.comentario_padre = comentario_respuesta_actual;

        input_nombre_usuario.val("");
        input_cuerpo.val("");

        postearComentario(datos_form);
    });
    
    updateBotones();
});

function updateBotones() {
    $(".btn-responder").click(function (e) {
        e.preventDefault();

        comentario_respuesta_actual = $(this).data("comentario-id");
        var comentario_usuario = $(this).data("comentario-usuario");

        modal_form_respuesta.find("#titulo-form-respuesta").html(comentario_usuario);
        modal_form_respuesta.modal("show");

        modal_form_respuesta.find("input,textarea").val("");
    });
}

function postearComentario(datos_formulario) {
    //presentar animacion de espera
    var animacion_espera = $("#animacion-espera").clone();
    animacion_espera.toggleClass("hidden");
    $("#seccion-comentarios").html(animacion_espera.html());
    //ocultar modal de formulario de respuesta
    $("#form-respuesta-modal").modal("hide");

    //llamada asincronica para postear nuevo comentario de respuesta
    $.post({
        url: '/comentario/nuevo/',
        data: datos_formulario,
        success: function(data) {

            if(data.length == 0){
                $("#seccion-comentarios").html("<h4 class='text-danger'>Hubo un Error</h4>");
            }
            else {
                //para simular que se toma un tiempo
                setTimeout(function () {
                    $("#seccion-comentarios").html(data);
                    updateBotones();
                }, 1500);
            }
        },
        error: function () {
            $("#seccion-comentarios").html("<p>Ocurrio un error grave tratando de buscar comentarios</p>");
        }
    });
}