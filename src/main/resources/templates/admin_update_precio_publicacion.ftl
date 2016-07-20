<!DOCTYPE html>
<html>
<head>
    <#include "head.ftl">
</head>
<body>
<#include "nav.ftl">
<div id="contenido" class="container">
    <div class="row">
        <div class="col col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1">
        <#-- Aqui dentro va cada contenido de cada template -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>Indicar nuevo precio de publicacion</h3>
                </div>
                <div class="panel-body">
                    <#if precio_actual??>
                    <div class="row">
                        <div class="col col-lg-12 col-md-12 col-sm-12">
                            <h3><span class="label label-default">Precio actual: ${precio_actual.getPrecio()}</span></h3>
                        </div>
                    </div>
                    </#if>
                    <div class="row">
                        <div class="col col-lg-12 col-md-12 col-sm-12">
                            <form id="target-form" class="form" action="/admin/precio_publicacion/editar/" method="post">
                                <div class="form-group">
                                    <label for="text-input" class="col-lg-2 col-md-2 col-sm-2 control-label">Nuevo precio: </label>
                                    <div class="col-lg-6 col-md-6 col-sm-6">
                                        <input id="text-input" type="text" name="precio" class="form-control" placeholder="Ej: 495.95">
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-4">
                                        <span class="visible-xs"><br></span>
                                        <button id="btn-crear-target" type="submit" class="btn btn-primary">Guardar</button>
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