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
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>Editar Publicación</h3>
                </div>
                <div class="panel-body">
                    <form action="/publicacion/editar/" method="POST" id="formulario-publicacion"  class="form-signin">
                        <br>

                        <input name="publicacion" type="hidden" value="${publicacion.id}">
                        <div class="row">
                            <div class="col-md-6">
                                <label for="marca">Marca</label>
                                <select class="form-control"   name="marca"  id="marca">
                                    <#list marcas as m>
                                        <option value="${m.id}" <#if publicacion.marca.id == m.id>selected="selected"</#if>>${m.nombre}</option>
                                    </#list>
                                </select>
                                <br>
                                <label for="anio">Año</label>
                                <input  name="anio" type="number" id="anio" class="form-control" placeholder="Digite el año..."  required="" value="${publicacion.anio?c}" autofocus="">
                                <br>
                                <label for="pasajeros">Cantidad de pasajeros</label>
                                <input  name="pasajeros" type="text" id="pasajeros" class="form-control" value="${publicacion.pasajeros}" placeholder="Digite los pasajeros..."  required="" autofocus="">
                                <br>
                                <label for="combustible">Tipo de Combustible</label>
                                <select class="form-control"   name="combustible"  id="combustible">
                                    <#if publicacion.combustible == 'Gasolina'>
                                        <option value="Gasolina" selected="selected">Gasolina</option>
                                    <#else >
                                            <option value="Gasolina">Gasolina</option>
                                    </#if>
                                    <#if publicacion.combustible == 'Gas Natural'>
                                        <option value="Gas Natural" selected="selected">Gas Natural</option>
                                    <#else >
                                        <option value="Gas Natural">Gas Natural</option>
                                    </#if>
                                    <#if publicacion.combustible == 'Biodisel'>
                                        <option value="Biodisel" selected="selected">Biodisel</option>
                                    <#else >
                                        <option value="Biodisel">Biodisel</option>
                                    </#if>
                                    <#if publicacion.combustible == 'Diesel'>
                                        <option value="Diesel" selected="SELECTED">Diesel</option>
                                    <#else >
                                        <option value="Diesel">Diesel</option>
                                    </#if>
                                    <#if publicacion.combustible == 'Electricidad'>
                                        <option value="Electricidad" selected="SELECTED">Electricidad</option>
                                    <#else >
                                        <option value="Electricidad">Electricidad</option>
                                    </#if>
                                    <#if publicacion.combustible == 'Etanol'>
                                        <option value="Etanol" selected="selected">Etanol</option>
                                    <#else >
                                        <option value="Etanol">Etanol</option>
                                    </#if>
                                    <#if publicacion.combustible == 'Hidrógeno'>
                                        <option value="Hidrógeno" selected="selected">Hidrógeno</option>
                                    <#else >
                                        <option value="Hidrógeno">Hidrógeno</option>
                                    </#if>
                                    <#if publicacion.combustible == 'GLP'>
                                        <option value="GLP" selected="selected">GLP</option>
                                    <#else >
                                        <option value="GLP">GLP</option>
                                    </#if>
                                    <#if publicacion.combustible == 'Metanol'>
                                        <option value="Metanol" selected="selected">Metanol</option>
                                    <#else >
                                        <option value="Metanol">Metanol</option>
                                    </#if>



                                </select>
                                <br>
                                <label for="precio">Precio</label>
                                <div class="input-group">
                                    <span class="input-group-addon">RD$</span>
                                    <input  name="precio" type="number" id="precio" class="form-control"   required="" value="${publicacion.precioVehiculo?c}" autofocus="">
                                </div>
                                <br>

                            </div>
                            <div class="col-md-6">
                                <label for="modelo">Modelo</label>
                                <input  name="modelo" type="text" id="modelo" class="form-control" placeholder="Digite el modelo..." value="${publicacion.modelo}" required="" autofocus="">
                                <br>
                                <label for="uso">Uso (KM)</label>
                                <div class="input-group">
                                    <span class="input-group-addon">KM</span>
                                    <input  name="uso" type="number" id="uso" class="form-control" placeholder="Digite el kilometraje..." value="${publicacion.uso?c}"  required="" autofocus="">
                                </div>
                                <br>
                                <label for="cilindros">Cantidad de cilindros</label>
                                <input  name="cilindros" type="number" id="cilindros" class="form-control" placeholder="Digite los cilindros..." value="${publicacion.cilindros?c}"  required="" autofocus="">
                                <br>
                                <label for="transmision">Tipo de Transmisión</label>
                                <select class="form-control"   name="transmision"  id="transmision">
                                    <#if publicacion.transmision == 'Automática'>
                                        <option value="Automática" selected="selected">Automática</option>
                                    <#else >
                                        <option value="Automática">Automática</option>
                                    </#if>
                                    <#if publicacion.transmision == 'Manual'>
                                        <option value="Manual" selected="selected">Manual</option>
                                    <#else >
                                        <option value="Manual">Manual</option>
                                    </#if>
                                    <#if publicacion.transmision == 'Doble embrague'>
                                        <option value="Doble embrague" selected="selected">Doble embrague</option>
                                    <#else >
                                        <option value="Doble embrague">Doble embrague</option>
                                    </#if>
                                    <#if publicacion.transmision == 'Semi-automática'>
                                        <option value="Semi-automática" selected="selected">Semi-automática</option>
                                    <#else >
                                        <option value="Semi-automática">Semi-automática</option>
                                    </#if>
                                </select>
                                <br>
                                <label for="tipo">Tipo</label>
                                <select class="form-control"   name="tipo"  id="tipo">
                                <#list tipos as t>
                                    <option value="${t.id}" <#if publicacion.tipo.id == t.id>selected="selected"</#if>>${t.nombre}</option>
                                </#list>
                                </select>
                                <br>
                            </div>
                        </div>


                        <br>
                        <label for="observaciones">Observaciones particulares:</label>
                        <textarea class="form-control" rows="3" id="observaciones" name="observaciones" required="">${publicacion.observaciones}</textarea>
                        <br>
                        <br><br>
                        <button style="border-radius: 15px" class="btn btn-lg btn-primary btn-block" type="submit">Editar Publicacion</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/js/imagenes.js" type="text/javascript">


</script>
</body>
</html>