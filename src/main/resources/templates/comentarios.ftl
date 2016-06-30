<div class="col col-lg-12">
    <div class="alert alert-warning">
        <form id="form-nuevo-comentario" class="form" method="post">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" class="form-control" id="nombre" placeholder="juan nieve">
            </div>
            <div class="form-group">
                <label for="text-input" class="control-label">Nuevo Comentario: </label>
                <textarea style="resize:none" id="text-input" class="form-control" name="cuerpo" rows="3" placeholder="Me gusta ese carrito"></textarea>
                <br />
                <button id="btn-form-nuevo-comentario" type="submit" class="btn btn-primary">Comentar</button>
            </div>
        </form>
    </div>
    <#if comentarios?? && comentarios?size == 0>
    <p>No hay comentarios</p>
    <#else>
    <#list comentarios as comentario>
    <div class="panel panel-default">
        <div class="panel-heading">
            ${comentario.getUsuario()}:
        </div>
        <div class="panel-body">
            ${comentario.getCuerpo()}
        </div>
        <div class="panel-footer">
            <div class="row">
                <div class="col col-lg-6">
                    <button data-comentario-id="${comentario.getId()}"
                            data-comentario-usuario="${comentario.getUsuario()}"
                            class="btn btn-primary btn-sm btn-responder">
                        Responder
                    </button>
                </div>
                <#if comentario.tieneRespuestas() == true>
                <div class="col col-lg-6">
                    <button class="btn btn-warning btn-sm btn-mostrar-respuestas"
                            data-toggle="collapse"
                            data-target="#respuestas-comentario${comentario.getId()}">
                        Mostrar Respuestas
                    </button>
                </div>
                <div class="col col-lg-12">
                    <div id="respuestas-comentario${comentario.getId()}" class="collapse">
                        <hr />
                        <p>Respuestas:</p>
                        <#list comentario.respuestas() as respuesta>
                            <div class="alert alert-info">
                                <b>${respuesta.getUsuario()}: </b>
                                <p>${respuesta.getCuerpo()}</p>
                            </div>
                        </#list>
                    </div>
                </div>
                </#if>
            </div>
        </div>
    </div>
    </#list>
    </#if>
</div>
<script type="text/javascript" src="/js/publicacion_galeria.js"></script>
