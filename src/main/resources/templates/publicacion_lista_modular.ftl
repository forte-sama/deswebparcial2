<div class="row">
    <div class="col col-lg-4 col-md-4">
        <form id="form-filtro" method="get">
            <div id="btn-show-filtros-container" class="well well-sm texto-centrado">
                <button id="btn-show-filtros" class="btn btn-warning btn-sm"
                        data-toggle="collapse"
                        data-target="#filtros-publicaciones">
                    Mostrar/Esconder Filtros
                </button>
            </div>
            <div class="list-group">
                <div id="filtros-publicaciones" class="collapse">
                    <a href="#" class="list-group-item active">
                        <p class="list-group-item-text">
                            <button type="submit" class="btn btn-default">
                                <i class="fa fa-sliders" aria-hidden="true"></i> Aplicar Filtros
                            </button>
                        </p>
                    </a>
                    <a href="#" class="list-group-item">
                        <h4 class="list-group-item-heading">
                            <i class="fa fa-tint" aria-hidden="true"></i> Combustible
                        </h4>
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
                        <h4 class="list-group-item-heading">
                            <i class="fa fa-calendar" aria-hidden="true"></i> A&ntilde;o
                        </h4>
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
                        <h4 class="list-group-item-heading">
                            <i class="fa fa-money" aria-hidden="true"></i> Precio
                        </h4>
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
                        <h4 class="list-group-item-heading">
                            <i class="fa fa-tags" aria-hidden="true"></i> Marca
                        </h4>
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
                        <h4 class="list-group-item-heading">
                            <i class="fa fa-taxi"></i> Tipo de Vehiculo
                        </h4>
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
            </div>
        </form>
    </div>
    <div class="col col-lg-8 col-md-8">
        <#if datos_publicaciones["vacio"]??>
        <div class="row">
            <div class="col col-lg-12 col-md-12">
                <div class="alert alert-danger texto-centrado">
                    <h3>No hay publicaciones</h3>
                </div>
            </div>
        </div>
        <#else>
        <div class="row">
            <div class="col col-lg-12 col-md-12">
            <#if datos_publicaciones["url_anterior"]??>
                <div class="col col-lg-3 col-md-3 pull-left">
                    <a class="btn btn-default btn-block" href="${datos_publicaciones["url_anterior"]}">
                        <i class="fa fa-arrow-circle-left" ></i> Anterior
                    </a>
                </div>
            </#if>
            <#if datos_publicaciones["url_siguiente"]??>
                <div class="col col-lg-3 col-md-3 pull-right">
                    <a class="btn btn-default btn-block" href="${datos_publicaciones["url_siguiente"]}">
                        Siguiente <i class="fa fa-arrow-circle-right" ></i>
                    </a>
                </div>
            </#if>
            </div>
        </div>
        <div class="row">
            <div class="col col-lg-12 col-md-12">
                <hr />
                <#if datos_publicaciones["publicaciones"]??>
                <#list 0..datos_publicaciones["publicaciones"]?size-1 as i>
                    <div class="col col-lg-3 col-md-4 col-sm-6 col-xs-24">
                        <div class="thumbnail">
                            <img class="img-responsive" src="${datos_publicaciones["rutas_imagenes_publicaciones"][i]}" alt="..." height="250">
                            <div class="caption row">
                                <div class="col col-lg-12 col-md-12 col-sm-12 col-xs-24">
                                    <p>
                                        ${datos_publicaciones["publicaciones"][i].getModelo()} <i>${datos_publicaciones["publicaciones"][i].getAnio()?string["0"]}</i>
                                        <b>RD$ ${datos_publicaciones["publicaciones"][i].getPrecioVehiculo()}</b>
                                    </p>
                                </div>
                                <div class="col col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <a href="/publicacion/ver/${datos_publicaciones["publicaciones"][i].getId()}/" class="btn btn-primary btn-block">
                                        <i class="fa fa-search" aria-hidden="true"></i>
                                    </a>
                                </div>
                                <#if usuario_sesion?? && datos_publicaciones["publicaciones"][i].getUsuario().getUsername() == usuario_sesion.getUsername()>
                                <div class="col col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <a href="/publicacion/vender/${datos_publicaciones["publicaciones"][i].getId()}/" class="btn btn-success btn-block">
                                        <i class="fa fa-usd" aria-hidden="true"></i>
                                    </a>
                                </div>
                                </#if>
                            </div>
                        </div>
                    </div>
                </#list>
                </#if>
            </div>
        </div>
        </#if>
    </div>
</div>