<!DOCTYPE html>
<html>
<head>
    <#include "head.ftl">
</head>
<body>
<#include "nav.ftl">
<div id="contenido" class="container">
    <div class="row">
        <div class="col col-lg-8 col-lg-offset-2">
        <#-- Aqui dentro va cada contenido de cada template -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>Indicar nuevo precio de publicacion</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <#if precio_actual??>
                        <div class="col col-md-12">
                            <h3><span class="label label-default">Precio actual: ${precio_actual.getPrecio()}</span></h3>
                        </div>
                        </#if>
                        <div class="col col-md-12">
                            <form id="target-form" class="form-horizontal" action="/admin/precio_publicacion/editar/" method="post">
                                <div class="form-group">
                                    <label for="text-input" class="col-md-2 control-label">Nuevo precio: </label>
                                    <div class="col-md-6">
                                        <input id="text-input" type="text" name="precio" class="form-control input-lg" placeholder="Ej: 495.95">
                                    </div>
                                    <div class="col-md-4">
                                        <button id="btn-crear-target" type="submit" class="btn btn-lg btn-primary">Guardar</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>