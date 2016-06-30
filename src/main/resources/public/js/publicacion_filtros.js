/**
 * Created by forte on 30/06/16.
 */

$(document).ready(function () {
    $("#form-filtro").submit(function (e) {
        var error = false;
        //valor por defecto en cada select
        var default_val = 'default';
        //valores de los anios a comparar
        var anio_desde = $("select[name=anio_desde] option:selected").val();
        var anio_hasta = $("select[name=anio_hasta] option:selected").val();
        //valores de los precios a comparar
        var precio_desde = $("select[name=precio_desde] option:selected").val();
        var precio_hasta = $("select[name=precio_hasta] option:selected").val();

        //si estan seteados los dos anios
        if(anio_desde !== default_val && anio_hasta !== default_val) {
            //no subir formulario si los rangos son incorrectos
            if(anio_hasta - anio_desde < 0) {
                error = true;
            }
        }
        //si estan seteados los dos precios
        if(precio_desde !== default_val && precio_hasta !== default_val) {
            //no subir formulario si los rangos son incorrectos
            if(precio_hasta - precio_desde < 0) {
                error = true;
            }
        }

        //cancelar aplicacion de filtro si hay algun error
        if(error) {
            alert("Se establecieron rangos no validos");
            e.preventDefault();
        }
    });
});