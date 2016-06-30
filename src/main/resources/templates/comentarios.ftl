<#if comentarios?? && comentarios?size == 0>
<p>No hay comentarios</p>
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
