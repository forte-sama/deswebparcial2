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
</head>
<body>
<#include "nav.ftl">
<div id="contenido" class="container">
    <div class="row">
        <div class="col col-lg-12">
        <#-- Aqui dentro va cada contenido de cada template -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>${pub.getMarca().getNombre()} ${pub.getModelo()} ${pub.getAnio()?string["0"]}</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <#-- datos vehiculo -->
                        <section class="col col-lg-8">
                            <#-- info general -->
                            <div class="row">
                                <div class="col col-lg-12">
                                    <h3><i class="fa fa-car fa-lg"></i><span class="text-primary"> Vehiculo</span></h3>
                                    <br>
                                    <div class="col-lg-3">
                                        <b>Precio: </b>
                                    </div>
                                    <div class="col-lg-9">
                                        <p>${pub.getPrecioVehiculo()}</p>
                                    </div>
                                    <div class="col-lg-3">
                                        <b>Cilindros: </b>
                                    </div>
                                    <div class="col-lg-3">
                                        <p>${pub.getCilindros()}</p>
                                    </div>
                                    <div class="col-lg-3">
                                        <b>Uso: (km)</b>
                                    </div>
                                    <div class="col-lg-3">
                                        <p>${pub.getUso()}</p>
                                    </div>
                                    <div class="col-lg-3">
                                        <b>Tipo: </b>
                                    </div>
                                    <div class="col-lg-3">
                                        <p>${pub.getTipo().getNombre()}</p>
                                    </div>
                                    <div class="col-lg-3">
                                        <b>Transmision: </b>
                                    </div>
                                    <div class="col-lg-3">
                                        <p>${pub.getTransmision()}</p>
                                    </div>
                                    <div class="col-lg-3">
                                        <b>Marca: </b>
                                    </div>
                                    <div class="col-lg-3">
                                        <p>${pub.getMarca().getNombre()}</p>
                                    </div>
                                    <div class="col-lg-3">
                                        <b>Modelo: </b>
                                    </div>
                                    <div class="col-lg-3">
                                        <p>${pub.getModelo()}</p>
                                    </div>
                                    <div class="col-lg-3">
                                        <b>A&ntilde;o: </b>
                                    </div>
                                    <div class="col-lg-3">
                                        <p>${pub.getAnio()?string["0"]}</p>
                                    </div>
                                    <div class="col-lg-3">
                                        <b>Pasajeros: </b>
                                    </div>
                                    <div class="col-lg-3">
                                        <p>${pub.getPasajeros()}</p>
                                    </div>
                                    <div class="col-lg-3">
                                        <b>Combustible: </b>
                                    </div>
                                    <div class="col-lg-9">
                                        <p>${pub.getCombustible()}</p>
                                    </div>
                                    <div class="col-lg-3">
                                        <b>Fecha inicio: </b>
                                    </div>
                                    <div class="col-lg-3">
                                        <p>${pub.formatFecha(pub.getFechaInicio())}</p>
                                    </div>
                                    <div class="col-lg-3">
                                        <b>Fecha Fin: </b>
                                    </div>
                                    <div class="col-lg-3">
                                        <p>${pub.formatFecha(pub.getFechaFin())}</p>
                                    </div>
                                    <div class="col-lg-12">
                                        <h4><b>Observaciones: </b></h4>
                                        <p>${pub.getObservaciones()}</p>
                                    </div>
                                </div>
                            </div>
                            <#-- fotos vehiculo -->
                            <div class="row">
                                <div class="col col-lg-12">
                                    <hr />
                                    <h4><b>Fotillos</b></h4>
                                    <br />
                                    <div class="row">
                                        <#list imagenes as imagen>
                                        <div class="col col-lg-3">
                                            <a href="#" class="thumbnail imagen-publicacion" data-toggle="modal" data-target="#galeria-modal">
                                                <img img_num="${imagen.getId()}" src="${imagen.getRuta()}" alt="preview imagen">
                                            </a>
                                        </div>
                                        </#list>
                                    </div>
                                </div>
                            </div>
                        </section>
                        <#-- datos vendedor y comentarios -->
                        <section class="col col-lg-4">
                            <#-- datos vendedor -->
                            <div class="row">
                                <div class="row">
                                    <div class="col col-lg-12">
                                        <h3><i class="fa fa-user fa-lg"></i> <span class="text-primary">${vendedor.getNombre()}</span></h3>
                                        <hr />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col col-lg-2 texto-centrado">
                                        <i class="fa fa-phone fa-lg"></i>
                                    </div>
                                    <div class="col col-lg-10 texto-izquierda">
                                        <p>${vendedor.getTelefono()}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col col-lg-2 texto-centrado">
                                        <i class="fa fa-mobile fa-lg"></i>
                                    </div>
                                    <div class="col col-lg-10 texto-izquierda">
                                        <p>${vendedor.getCelular()}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col col-lg-2 texto-centrado">
                                        <i class="fa fa-at fa-lg"></i>
                                    </div>
                                    <div class="col col-lg-10 texto-izquierda">
                                        <p>${vendedor.getEmail()}</p>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col col-lg-12 texto-centrado">
                                        <hr />
                                        <p>${vendedor.getDireccion()}</p>
                                        <hr />
                                    </div>
                                </div>
                            </div>
                            <#-- comentarios -->
                            <div class="row">
                                <div class="col col-lg-12">
                                    <a class="btn btn-block btn-warning">div 5</a>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="galeria-modal" class="modal fade" tabindex="-1" role="dialog"">
    <img id="imagen-modal" src="/img/img_prueba1.jpg" alt="Imagen Full Size"/>
</div>
<script type="text/javascript" src="/js/publicacion_galeria.js"></script>
</body>
</html>