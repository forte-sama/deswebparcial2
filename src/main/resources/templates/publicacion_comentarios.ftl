<#if comentarios?? && comentarios?size == 0>
<div class="row">
    <div class="col col-lg-12">
        <h4 class="text-primary">No hay comentarios</h4>
    </div>
</div>
<#else>
<#list comentarios as comentario>
<div class="panel panel-default">
    <div class="panel-heading">
        <i class="fa fa-commenting fa-lg" aria-hidden="true"></i> ${comentario.getUsuario()}:
    </div>
    <div class="panel-body">
        ${comentario.getCuerpo()}
    </div>
    <div class="panel-footer">
        <div class="row">
            <div class="col col-lg-6">
                <button data-comentario-id="${comentario.getId()}"
                        data-comentario-usuario="${comentario.getUsuario()}"
                        class="btn btn-success btn-sm btn-responder">
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
                    <p><i class="fa fa-comments fa-lg" aria-hidden="true"></i> Respuestas:</p>
                    <div class="list-group">
                        <#list comentario.respuestas() as respuesta>
                        <a href="#" class="list-group-item">
                            <h4 class="list-group-item-heading">${respuesta.getUsuario()}:</h4>
                            <p class="list-group-item-text">${respuesta.getCuerpo()}</p>
                        </a>
                        </#list>
                    </div>
                </div>
            </div>
            </#if>
        </div>
    </div>
</div>
</#list>
</#if>
