<!DOCTYPE html>
<html>
<head>
    <#include "head.ftl">
</head>
<body>
<#include "nav.ftl">
<div id="contenido" class="container">
    <div class="col col-lg-5 col-lg-offset-3">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3>Factura</h3>
            </div>
            <div class="panel-body">
                <label >Fecha de expiración:</label>
                <label style="margin-left: 16%; font-weight: normal" >${publicacion.fechaFin}</label>
                <hr><br>
                <label >Tiempo de vigencia:</label>
                <label style="margin-left: 17%; font-weight: normal" >${dias} días</label>
                <hr><br>
                <label >Tarifa por día:</label>
                <label style="margin-left: 28%; font-weight: normal" >RD$ ${tarifa.precio}</label>
                <hr><br>
                <label >A pagar:</label>
                <label style="margin-left: 38%; font: italic bold 16px/30px Georgia, serif;" >RD$ ${publicacion.precioPublicacion}</label>
                <br>



            </div>

        </div>
        <a style="margin-left: 40%" class="btn btn-info" href="/">Entendido</a>
    </div>




</div>
</body>
</html>