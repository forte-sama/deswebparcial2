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
                    <h3>Registro de Usuario</h3>
                </div>
                <div class="panel-body">
                    <form action="/usuario/registro/" method="POST" class="form-signin">
                        <br>
                        <label for="inputEmail">Nombre de Usuario</label>
                        <input name="username" type="text" id="username" class="form-control" placeholder="Digite su usuario..." required="" autofocus="">
                        <br>

                        <label for="password">Contraseña</label>
                        <input name="password" type="password" id="password" class="form-control" placeholder="Digite su contraseña..."  required="">
                        <br>

                        <label for="telefono">Nombre (Personal o Negocio)</label>
                        <input  name="nombre" type="text" id="nombre" class="form-control" placeholder="Digite su nombre..."  required="" autofocus="">
                        <br>

                        <label for="telefono">Télefono</label>
                        <input  name="telefono" type="text" id="telefono" class="form-control" placeholder="(___) ___-____"  required="" autofocus="">
                        <br>

                        <label for="celular">Celular</label>
                        <input  name="celular" type="text" id="celular" class="form-control" placeholder="(___) ___-____"  required="" autofocus="">
                        <br>

                        <label for="email">Correo Electrónico</label>
                        <input  name="email" type="text" id="email" class="form-control" placeholder="Digite su correo..."  required="" autofocus="">
                        <br>

                        <label for="direccion">Dirección:</label>
                        <textarea class="form-control" rows="2" id="direccion" name="direccion" required=""></textarea>
                        <br>


                        <br><br>
                        <button style="border-radius: 15px" class="btn btn-lg btn-primary btn-block" type="submit">Registrar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>