<form id="my_form" method="post" action="/cerrarsesion/">
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navegacion">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/"><i class="fa fa-taxi" aria-hidden="true"> Parcial 2</i></a>
        </div>

        <div class="collapse navbar-collapse" id="navegacion">
            <ul class="nav navbar-nav right">
                <#if usuario_sesion??>
                <#if usuario_sesion.autorizado >
                <li class="active">
                    <a href="/usuario/publicaciones/">
                        <span class="glyphicon glyphicon-th-list"></span> Mis publicaciones
                    </a>
                </li>
                <li class="active">
                    <a href="/publicacion/crear/">
                        <span class="glyphicon glyphicon-pencil"> Publicar</span>
                    </a>
                </li>
                </#if>
                <#if usuario_sesion.admin >
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Administración</a>
                    <ul class="dropdown-menu">
                        <li><a href="/admin/tipo/crear/">Gestión de Tipos de Autos</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/admin/marca/crear/">Gestión de Marcas de Autos</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/admin/precio_publicacion/editar/">Editar Tarifa de Publicación</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/admin/user/ver/">Gestión de Usuarios</a></li>

                    </ul>
                </li>
                </#if>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">Cuenta</a>
                    <ul class="dropdown-menu">
                     <li><a href="/usuario/edicion/${usuario_sesion.username}">Editar información</a></li>
                     <li role="separator" class="divider"></li>
                       <li>
                           <a href="javascript:{}" onclick="document.getElementById('my_form').submit();">
                               <i class="fa fa-sign-in" aria-hidden="true"></i> Cerrar Sesión
                           </a>
                       </li>
                    </ul>
                </li>
                <#else>
                <li class="active"><a href="/usuario/registro/">Registrarse</a></li>
                <li><a href="/login/"><i class="fa fa-sign-in" aria-hidden="true"></i> Iniciar Sesión</a></li>
                </#if>
            </ul>
        </div>
    </div>
</nav>
</form>