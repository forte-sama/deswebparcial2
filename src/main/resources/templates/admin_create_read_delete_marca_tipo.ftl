<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" rel="stylesheet" href="/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="/css/font-awesome.css">
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
                    <h3>${title}</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col col-md-12">
                            <form id="target-form" class="form-horizontal" action="${form_url}" method="post">
                                <div class="form-group">
                                    <label for="text-input" class="col-md-2 control-label">${label}: </label>
                                    <div class="col-md-6">
                                        <input id="text-input" type="text" name="${field_name}" class="form-control input-lg" placeholder="${label}">
                                    </div>
                                    <div class="col-md-4">
                                        <button id="btn-crear-target" type="submit" class="btn btn-lg btn-default" disabled>Ingresar Nuevo</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div id="lista-target" class="panel-footer">
                </div>
                <div id="estado-wait" class="hidden">
                    <i class="fa fa-refresh fa-spin fa-lg"></i>
                </div>
                <div id="lista-wait" class="hidden">
                    <h1 id="" class="texto-centrado"><i class="fa fa-refresh fa-spin fa-lg"></i></h1>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/admin_ajax_marca_tipo.js"></script>
</body>
</html>