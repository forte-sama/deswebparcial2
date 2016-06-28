<#if no_hay??>
<h3>No hay elementos</h3>
<#else>
<div class="table-scroll">
    <table class="table">
        <thead>
        <th>#</th>
        <th>Tipo</th>
        <th>Acciones</th>
        </thead>
        <tbody>
            <#list items as item>
            <tr>
                <td>${item.getId()}</td>
                <td>${item.getNombre()}</td>
                <td>
                    <a href="/admin/${tipo_target}/editar/${item.getId()}">Editar</a>
                     |
                    <a href="/admin/${tipo_target}/borrar/${item.getId()}">Borrar</a>
                </td>
            </tr>
            </#list>
        </tbody>
    </table>
</div>
</#if>

