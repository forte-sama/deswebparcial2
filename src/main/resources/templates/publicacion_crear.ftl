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
                    <h3>Nueva Publicación</h3>
                </div>
                <div class="panel-body">
                    <form action="/publicar/" method="POST" enctype="multipart/form-data" class="form-signin">
                        <br>
                        <input name="usuario" type="hidden" value="${usuario.username}">
                        <div class="row">
                            <div class="col-md-6">
                                <label for="marca">Marca</label>
                                <select class="form-control"   name="marca"  id="marca">
                                    <#list marcas as m>
                                        <option value="${m.nombre}">${m.nombre}</option>
                                    </#list>
                                </select>
                                <br>
                                <label for="anio">Año</label>
                                <input  name="anio" type="text" id="anio" class="form-control" placeholder="Digite el año..."  required="" autofocus="">
                                <br>
                                <label for="pasajeros">Cantidad de pasajeros</label>
                                <input  name="pasajeros" type="text" id="pasajeros" class="form-control" placeholder="Digite los pasajeros..."  required="" autofocus="">
                                <br>
                                <label for="combustible">Tipo de Combustible</label>
                                <select class="form-control"   name="combustible"  id="combustible">
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
                            </div>
                            <div class="col-md-6">
                                <label for="modelo">Modelo</label>
                                <input  name="modelo" type="text" id="modelo" class="form-control" placeholder="Digite el modelo..."  required="" autofocus="">
                                <br>
                                <label for="uso">Uso (KM)</label>
                                <div class="input-group">
                                    <span class="input-group-addon">KM</span>
                                    <input  name="uso" type="text" id="uso" class="form-control" placeholder="Digite el kilometraje..."  required="" autofocus="">
                                </div>
                                <br>
                                <label for="cilindros">Cantidad de cilindros</label>
                                <input  name="cilindros" type="text" id="cilindros" class="form-control" placeholder="Digite los cilindros..."  required="" autofocus="">
                                <br>
                                <label for="transmision">Tipo de Transmisión</label>
                                <select class="form-control"   name="transmision"  id="transmision">
                                    <option value="Autmática">Automática</option>
                                    <option value="Manual">Manual</option>
                                    <option value="Doble embrague">Doble embrague</option>
                                    <option value="Semi-automática">Semi-automática</option>
                                </select>

                            </div>
                        </div>


                        <br>
                        <label for="observaciones">Observaciones particulares:</label>
                        <textarea class="form-control" rows="3" id="observaciones" name="observaciones" required=""></textarea>
                        <br>
                        <#--<div class="fileUpload btn btn-primary">-->
                        <#--<span>Upload</span>-->
                        <#--<input type="file" class="upload"  onchange="readURL(this,2);" />-->
                        <#--</div>-->
                        <#--<img id="blah'+cantidad_img+'" src="" alt="your image" />-->

                        <label for="observaciones">Fotos</label>
                        <div  class="panel panel-default">
                            <div class="panel-body" id="contenedor-imagenes">
                                <span class="btn btn-success" id="add-image">Añadir foto</span>
                                <br>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col col-lg-4 col-lg-offset-4">
                                <label for="observaciones">Duración de publicación</label>
                                <div class="input-group">
                                <span class="input-group-addon">Días</span>
                                <input  name="dias" type="text" id="dias" class="form-control"   required="" autofocus="">
                                </div>
                                </div>
                        </div>


                        <br><br>
                        <button style="border-radius: 15px" class="btn btn-lg btn-primary btn-block" type="submit"> Registrar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var cantidad_img = 0;
    $('#add-image').click(function () {
        html = '<div class="fileUpload btn btn-primary">'
                +'<span>Seleccionar...</span>'
                +'<input name="upfile" type="file" class="upload" value="'+cantidad_img+'" onchange="readURL(this,'+cantidad_img+');" />'
                +'</div>';
        html += '<img style="padding-top: 15px" id="blah'+cantidad_img+'" src="" alt="" />';
        $('#contenedor-imagenes').append(html);
        cantidad_img+=1;

    });
    function readURL(input,number) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                console.log('hey');
                $('#blah'+number)
                        .attr('src', e.target.result)
                        .width(180)
                        .height(115);
            };

            reader.readAsDataURL(input.files[0]);
        }
    }

</script>
</body>
</html>