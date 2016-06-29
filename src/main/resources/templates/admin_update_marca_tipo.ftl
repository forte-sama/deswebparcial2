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
                    <h3>${title}</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col col-md-12">
                            <form id="target-form" class="form-horizontal" action="${form_url}" method="post">
                                <div class="form-group">
                                    <div class="row">
                                        <label for="text-input" class="col-md-3 control-label">ID: </label>
                                        <div class="col-md-2">
                                            <input id="text-input" type="text" name="id" class="form-control input-lg" value="${item.getId()}" readonly>
                                            <hr>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <label for="text-input" class="col-md-3 control-label">${label}: </label>
                                        <div class="col-md-5">
                                            <input id="text-input" type="text" name="target" class="form-control input-lg" placeholder="${label}" value="${item.getNombre()}">
                                        </div>
                                        <div class="col-md-4">
                                            <button id="btn-crear-target" type="submit" class="btn btn-lg btn-default">Guardar cambios</button>
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