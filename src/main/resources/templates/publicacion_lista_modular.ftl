<div class="row">
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
                            <label for="precio_desde_form">Hasta</label>
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
    <div class="col col-lg-8">
        <a class="btn btn-primary btn-block">
            <#list publicaciones as pub>
            <p>${pub.getModelo()}</p>
            </#list>
        </a>
    </div>
</div>