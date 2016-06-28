<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="/css/style.css">
    <title>Parcial 2</title>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/bootstrap.js"></script>
    <script type="text/javascript" src="/js/custom.js"></script>
</head>
<body>
<#include "nav.ftl">
<div id="contenido" class="container">
    <div class="row">
        <div class="col col-lg-8 col-lg-offset-2">
        <#-- Aqui dentro va cada contenido de cada template -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>Creacion de nueva marca</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="marca-input" class="col-md-2 control-label">Nueva Marca: </label>
                                <div class="col-md-5">
                                    <input id="marca-input" type="text" class="form-control" placeholder="Marca">
                                </div>
                                <div class="col-md-2">
                                    <button id="btn-crear-marca" type="submit" class="btn btn-default" disabled>Guardarla</button>
                                </div>
                                <div class="col-md-3 texto-izquierda">
                                    <span class="label label-default">Exito</span>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/admin_marcas.js"></script>
</body>
</html>