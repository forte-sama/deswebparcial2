<!DOCTYPE html>
<html>
<head>
    <#include "head.ftl">
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