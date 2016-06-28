/**
 * Created by forte on 27/06/16.
 */

$(document).ready(function () {
    //elemento input con texto del elemento que se desea crear
    $("#text-input").keyup(function () {
        //tipo de elemento que se desea crear
        var target_type = $(this).attr("name");
        //texto del nombre del elemento
        var texto_target = $(this).val();

        //solo seguir si el texto no esta vacio
        if(texto_target.length > 0) {
            var estado_wait = $("#estado-wait").clone().removeClass("hidden");

            var boton_crear = $("#btn-crear-target");
            boton_crear.html(estado_wait.html());

            //url de ruta que retornara json con respuesta de verificacion
            var target_url = "/admin/ajax/" + target_type + "/nuevo/";

            $.get({
                url: target_url,
                data: {
                    target: texto_target
                },
                success: function (data) {
                    var resultado = $.parseJSON(data);

                    //verificar si la marca actual existe
                    if (resultado.exito) {
                        //se puede crear marca
                        boton_crear.removeClass("btn-danger").addClass("btn-success");
                        boton_crear.attr('disabled', false);
                        boton_crear.html("Guardar");
                    }
                    else {
                        //ya existe marca indicada
                        boton_crear.removeClass("btn-success").addClass("btn-danger");
                        boton_crear.attr('disabled', true);
                        boton_crear.html("Ya existe");
                    }
                },
                error: function () {
                    //hacer algo con el resultado en caso de fallo
                    alert("Hubo un error");
                }
            });
        }
    });
});