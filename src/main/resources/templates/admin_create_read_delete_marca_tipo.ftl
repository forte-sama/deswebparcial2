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
                    <h3>${title}</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col col-lg-12 col-md-12 col-sm-12">
                            <form id="target-form" class="form-horizontal" action="${form_url}" method="post">
                                <div class="form-group">
                                    <label for="text-input" class="col col-lg-2 col-md-2 col-sm-2 control-label">${label}: </label>
                                    <div class="col col-lg-6 col-md-6 col-sm-6">
                                        <input id="text-input" type="text" name="${field_name}" class="form-control" placeholder="${label}">
                                    </div>
                                    <div class="col col-lg-4 col-md-4 col-sm-4">
                                        <span class="visible-xs"><br></span>
                                        <button id="btn-crear-target" type="submit" class="btn btn-default" disabled>Ingresar Nuevo</button>
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