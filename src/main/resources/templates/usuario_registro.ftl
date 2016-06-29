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
    <script type="text/javascript" src="/js/jquery.maskedinput.min.js"></script>
</head>
<body>
<#include "nav.ftl">
<div id="contenido" class="container">
    <div class="row">
        <div class="col col-lg-6 col-lg-offset-3">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <#if editando>
                        <h3>Edición de Datos</h3>
                    <#else>
                        <h3>Registro de Usuario</h3>
                    </#if>


                </div>
                <div class="panel-body">
                    <form <#if editando>action="/usuario/edicion/"<#else>action="/usuario/registro/" </#if> method="POST" class="form-signin">
                        <br>
                        <#if editando> <input type="hidden" name="username" value="${usuario.username}"></#if>
                        <#if !editando>
                            <label for="inputEmail">Nombre de Usuario</label>
                            <input name="username" type="text" id="username" class="form-control" placeholder="Digite su usuario..." required="" autofocus="">
                            <br>

                            <label for="password">Contraseña</label>
                            <input  name="password" type="password" id="password" class="form-control" placeholder="Digite su contraseña..."  required="">
                            <br>
                        </#if>


                        <label for="nombre">Nombre (Personal o Negocio)</label>
                        <input <#if editando>value="${usuario.nombre}" </#if>   name="nombre" type="text" id="nombre" class="form-control" placeholder="Digite su nombre..."  required="" autofocus="">
                        <br>

                        <label for="telefono">Télefono</label>
                        <input <#if editando>value="${usuario.telefono}" </#if>   name="telefono" type="text" id="telefono" class="form-control" placeholder="(___) ___-____"  required="" autofocus="">
                        <br>

                        <label for="celular">Celular</label>
                        <input <#if editando>value="${usuario.celular}" </#if>  name="celular" type="text" id="celular" class="form-control" placeholder="(___) ___-____"  required="" autofocus="">
                        <br>

                        <label for="email">Correo Electrónico</label>
                        <input <#if editando>value="${usuario.email}" </#if> name="email" type="text" id="email" class="form-control" placeholder="Digite su correo..."  required="" autofocus="">
                        <br>

                        <label for="direccion">Dirección:</label>
                        <textarea class="form-control" rows="2" id="direccion" name="direccion" required=""><#if editando>${usuario.celular} </#if></textarea>
                        <br>


                        <br><br>
                        <button style="border-radius: 15px" class="btn btn-lg btn-primary btn-block" type="submit"><#if editando>Guardar <#else> Registrar</#if></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>