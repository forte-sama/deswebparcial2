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
        <div class="col col-lg-4 col-lg-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>Iniciar Sesión</h3>
                </div>
                <div class="panel-body">
                    <form action="/login/" method="POST" class="form-signin">
                        <br>
                        <label for="inputEmail">Nombre de Usuario</label>
                        <input name="username" type="text" id="username" class="form-control"  required="" autofocus="">
                        <br>
                        <label for="inputEmail">Contraseña</label>
                        <input name="password" type="password" id="password" class="form-control"  required="">
                        <br><br>
                        <button style="border-radius: 30px" class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>