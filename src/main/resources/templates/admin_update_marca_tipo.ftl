<!DOCTYPE html>
<html>
<head>
    <#include "head.ftl">
</head>
<body>
<#include "nav.ftl">
<div id="contenido" class="container">
    <div class="row">
        <div class="col col-lg-8 col-lg-offset-2 col-md-8 col-md-offset-2 col-sm-8 col-sm-offset-2">
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
                                    <div class="row">
                                        <label for="text-input" class="col-lg-3 col-md-3 col-sm-3 control-label">ID: </label>
                                        <div class="col-lg-2 col-md-2 col-sm-2">
                                            <input id="text-input" type="text" name="id" class="form-control" value="${item.getId()}" readonly>
                                            <hr>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <label for="text-input" class="col-lg-3 col-md-3 col-sm-3  control-label">${label}: </label>
                                        <div class="col-lg-5 col-md-5 col-sm-5">
                                            <input id="text-input" type="text" name="target" class="form-control" placeholder="${label}" value="${item.getNombre()}">
                                        </div>
                                        <div class="col-lg-4 col-md-4 col-sm-4">
                                            <button id="btn-crear-target" type="submit" class="btn btn-default">Guardar cambios</button>
                                        </div>
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