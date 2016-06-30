<!DOCTYPE html>
<html>
<head>
    <#include "head.ftl">
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
                                                <button id="btn-form-nuevo-comentario" type="submit" class="btn btn-primary">
                                                    <i class="fa fa-paper-plane" aria-hidden="true"></i> Comentar
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div id="seccion-comentarios" class="col col-lg-12" pub="${pub.getId()?string["0"]}">
                                    <#include "comentarios.ftl">
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="galeria-modal" class="modal fade" tabindex="-1" role="dialog">
    <img id="imagen-modal" src="" alt="Imagen Full Size"/>
</div>
<div id="form-respuesta-modal" class="modal fade" tabindex="-1" role="dialog"">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>Respuesta a comentario de <span id="titulo-form-respuesta"></span></h4>
            </div>
            <div class="modal-body">
                <form id="form-respuesta-comentario" class="form" method="post">
                    <div class="form-group">
                        <label for="nombre">Nombre:</label>
                        <input type="text" class="form-control" id="nombre" placeholder="juan nieve">
                    </div>
                    <div class="form-group">
                        <input id="input-padre-id" type="hidden" name="padre" value="">
                        <label for="text-input" class="control-label">Nuevo Comentario: </label>
                        <textarea style="resize:none" id="text-input" class="form-control" name="cuerpo" rows="3" placeholder="Me gusta ese carrito"></textarea>
                        <br />
                        <button id="btn-form-respuesta-comentario" type="submit" class="btn btn-primary">Comentar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div id="animacion-espera" class="hidden">
    <div class="row">
        <div class="col-lg-2 col-lg-offset-5">
            <i class="fa fa-refresh fa-spin fa-5x texto-centrado"></i>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/publicacion_galeria.js"></script>
</body>
</html>