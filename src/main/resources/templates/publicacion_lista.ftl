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
            <#include "publicacion_lista_modular.ftl">
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/publicacion_filtros.js"></script>
</body>
</html>