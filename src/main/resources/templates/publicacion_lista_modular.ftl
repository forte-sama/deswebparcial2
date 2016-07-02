<div class="row">
    <#if nunca_filtro??>
    <div class="col col-lg-4">
        <form id="form-filtro" method="get">
            <div class="list-group">
                <a href="#" class="list-group-item active">
                    <p class="list-group-item-text">
                        <button type="submit" class="btn btn-default">Aplicar Filtros</button>
                    </p>
                </a>
                <a href="#" class="list-group-item">
                    <h4 class="list-group-item-heading">Combustible</h4>
                    <p class="list-group-item-text">
                        <select class="form-control" name="combustible">
                            <option value="default" selected>---</option>
                            <option value="Gasolina">Gasolina</option>
                            <option value="Gas Natural">Gas Natural</option>
                            <option value="Biodisel">Biodisel</option>
                            <option value="Diesel">Diesel</option>
                            <option value="Electricidad">Electricidad</option>
                            <option value="Etanol">Etanol</option>
                            <option value="Hidrógeno">Hidrógeno</option>
                            <option value="GLP">GLP</option>
                            <option value="Metanol">Metanol</option>
                        </select>
                    </p>
                </a>
                <a href="#" class="list-group-item">
                    <h4 class="list-group-item-heading">A&ntilde;o</h4>
                    <p class="list-group-item-text">
                    <div class="row">
                        <div class="col col-lg-6">
                            <label for="anio_desde_form">Desde</label>
                            <select class="form-control" name="anio_desde" id="anio_desde_form">
                                <option value="default" selected>---</option>
                            <#list opciones["anios"] as anio>
                                <option value="${anio}">${anio}</option>
                            </#list>
                            </select>
                        </div>
                        <div class="col col-lg-6">
                            <label for="anio_hasta_form">Hasta</label>
                            <select class="form-control" name="anio_hasta" id="anio_hasta_form">
                                <option value="default" selected>---</option>
                            <#list opciones["anios"] as anio>
                                <option value="${anio}">${anio}</option>
                            </#list>
                            </select>
                        </div>
                    </div>
                    </p>
                </a>
                <a href="#" class="list-group-item">
                    <h4 class="list-group-item-heading">Precio</h4>
                    <p class="list-group-item-text">
                    <div class="row">
                        <div class="col col-lg-6">
                            <label for="precio_desde_form">Desde</label>
                            <select class="form-control" name="precio_desde" id="precio_desde_form">
                                <option value="default" selected>---</option>
                            <#list opciones["precios"] as precios>
                                <option value="${precios}">${precios}</option>
                            </#list>
                            </select>
                        </div>
                        <div class="col col-lg-6">
                            <label for="precio_hasta_form">Hasta</label>
                            <select class="form-control" name="precio_hasta" id="precio_hasta_form">
                                <option value="default" selected>---</option>
                            <#list opciones["precios"] as precio>
                                <option value="${precio}">${precio}</option>
                            </#list>
                            </select>
                        </div>
                    </div>
                    </p>
                </a>
                <a href="#" class="list-group-item">
                    <h4 class="list-group-item-heading">Marca</h4>
                    <p class="list-group-item-text">
                        <select class="form-control" name="marca">
                            <option value="default" selected>---</option>
                        <#list opciones["marcas"] as marca>
                            <option value="${marca}">${marca}</option>
                        </#list>
                        </select>
                    </p>
                </a>
                <a href="#" class="list-group-item">
                    <h4 class="list-group-item-heading">Tipo de Vehiculo</h4>
                    <p class="list-group-item-text">
                        <select class="form-control" name="tipo">
                            <option value="default" selected>---</option>
                            <#list opciones["tipos"] as tipo>
                            <option value="${tipo}">${tipo}</option>
                            </#list>
                        </select>
                    </p>
                </a>
            </div>
        </form>
    </div>
    </#if>
    <div class="col col-lg-8">
        <div class="col col-lg-12">
            <#if datos_publicaciones["hay_pagina_anterior"] == true>
            <div class="col col-lg-3 pull-left">
                <a class="btn btn-default btn-block" href="${datos_publicaciones["url_anterior"]}">Anterior</a>
            </div>
            </#if>
            <#if datos_publicaciones["hay_pagina_siguiente"] == true>
            <div class="col col-lg-3 pull-right">
                <a class="btn btn-default btn-block" href="${datos_publicaciones["url_siguiente"]}">Siguiente</a>
            </div>
            </#if>
        </div>
        <div class="row">
            <hr />
            <#if datos_publicaciones["publicaciones"]??>
            <#list 0..datos_publicaciones["publicaciones"]?size-1 as i>
            <div class="col col-lg-4">
                <div class="thumbnail">
                    <#-- obtener primera imagen -->
                    <img class="imagen-item-publicacion" src="${datos_publicaciones["rutas_imagenes_publicaciones"][i]}" alt="..." height="250">
                    <div class="caption row">
                        <div class="col col-lg-12">
                            <p>${datos_publicaciones["publicaciones"][i].getModelo()} <i>${datos_publicaciones["publicaciones"][i].getAnio()?string["0"]}</i></p>
                            <p><b>RD$ ${datos_publicaciones["publicaciones"][i].getPrecioVehiculo()}</b></p>
                        </div>
                        <div class="col col-lg-6">
                            <a href="/publicacion/ver/${datos_publicaciones["publicaciones"][i].getId()}/" class="btn btn-primary btn-block">Ver</a>
                        </div>
                        <#if usuario_sesion?? && datos_publicaciones["publicaciones"][i].getUsuario().getUsername() == usuario_sesion.getUsername()>
                        <div class="col col-lg-6">
                            <a href="/publicacion/vender/${datos_publicaciones["publicaciones"][i].getId()}/" class="btn btn-success btn-block">Vender</a>
                        </div>
                        <div class="col col-lg-12">
                            <hr />
                            <a href="/publicacion/editar/${datos_publicaciones["publicaciones"][i].getId()}/" class="btn btn-success btn-block">Editar</a>
                        </div>
                        </#if>
                    </div>
                </div>
            </div>
            </#list>
            </#if>
        </div>
    </div>
</div>