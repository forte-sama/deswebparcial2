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
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>Listado de usuarios</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col col-md-12">
                            <table class="table">
                                <#--TODO colorear diferente los que sean admin-->
                                <thead>
                                    <th>Username</th>
                                    <th>Nombre</th>
                                    <th>Telefono</th>
                                    <th>Email</th>
                                    <th>Acciones</th>
                                </thead>
                                <tbody>
                                    <#list usuarios as usuario>
                                    <tr>
                                        <td>${usuario.getUsername()}</td>
                                        <td>${usuario.getNombre()}</td>
                                        <td>${usuario.getTelefono()}</td>
                                        <td>${usuario.getEmail()}</td>
                                        <td>
                                            <a href="/admin/user/editar/${usuario.getUsername()}">Editar</a>
                                            |
                                            <a href="/admin/user/borrar/${usuario.getUsername()}">Borrar</a>
                                        </td>
                                    </tr>
                                    </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col col-md-12">
                            <h3>Usuarios sin autorizacion: </h3>
                            <#if usuarios_sin_registrar?size == 0>
                            <h4><span class="label label-default">No hay</span></h4>
                            <#else>
                            <table class="table">
                                <thead>
                                    <th>Username</th>
                                    <th>Nombre</th>
                                    <th>Email</th>
                                    <th>Acciones</th>
                                </thead>
                                <tbody>
                                    <#list usuarios_sin_registrar as usuario>
                                    <tr>
                                        <td>${usuario.getUsername()}</td>
                                        <td>${usuario.getNombre()}</td>
                                        <td>${usuario.getEmail()}</td>
                                        <td>
                                            <a class="btn btn-primary" href="/admin/user/autorizar/${usuario.getUsername()}">
                                                Autorizar
                                            </a>
                                        </td>
                                    </tr>
                                    </#list>
                                </tbody>
                            </table>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>