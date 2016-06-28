/**
 * Created by forte on 27/06/16.
 */

$(document).ready(function () {

    //div donde se coloca tabla con lista de targets
    var lista_target = $("#lista-target");
    //div con animacion de espera entre llamadas ajax
    var lista_wait_animation = $("#lista-wait");
    //input field con el texto del target a crear
    var target_input = $("#text-input");
    //tipo de elemento que se desea crear
    var target_type = target_input.attr("name");
    //boton submit del formulario
    var boton_crear = $("#btn-crear-target");

    //tratar de obtener lista de targets
    obtenerTargets(target_type);

    //elemento input con texto del elemento que se desea crear
    //verificar si es un target valido cuando suelta una tecla
    target_input.focusout(function () {
        //texto del nombre del elemento
        var texto_target = $(this).val();

        //solo seguir si el texto no esta vacio
        if(texto_target.length > 0) {
            var estado_wait = $("#estado-wait").clone().removeClass("hidden");

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
                        encenderBoton("Guardar");
                    }
                    else {
                        //ya existe marca indicada
                        apagarBoton("ya existe");
                    }
                },
                error: function () {
                    //hacer algo con el resultado en caso de fallo
                    alert("Hubo un error");
                }
            });
        }
    });

    $("#btn-crear-target").click(function (e) {
        e.preventDefault();

        //url para llamada post
        var form_url = $("form#target-form").attr("action");
        //texto del nombre del elemento
        var texto_target = target_input.val();

        $.post({
            url: form_url,
            data: {
                target : texto_target
            },
            success: function(data) {
                target_input.val("");

                reiniciarBoton();

                obtenerTargets(target_type);

                // lista_target.html(data);
            },
            error: function() {
                //hacer algo con el resultado en caso de fallo
                alert("Hubo un error");
            }
        });
    });

    function obtenerTargets(tipo_target) {
        //presentar animacion en lo que se completa llamada ajax
        lista_target.html(lista_wait_animation.html());
        
        //url de ruta que retornara lista de targets deseados
        var target_url = "/admin/ajax/" + tipo_target + "/getall/";

        $.get({
            url: target_url,
            success: function (data) {
                lista_target.html(data);
            },
            error: function () {
                //hacer algo con el resultado en caso de fallo
                alert("Hubo un error");
            }
        });

    }

    function apagarBoton(mensaje) {
        boton_crear.removeClass("btn-success").addClass("btn-danger");
        boton_crear.attr('disabled', true);
        boton_crear.html(mensaje);
    }

    function encenderBoton(mensaje) {
        boton_crear.removeClass("btn-danger").addClass("btn-success");
        boton_crear.attr('disabled', false);
        boton_crear.html(mensaje);
    }

    function reiniciarBoton() {
        boton_crear.removeClass("btn-danger").removeClass("btn-success").addClass("btn-default");
        boton_crear.attr('disabled', true);
        boton_crear.html("Ingresar Nuevo");
    }
});
