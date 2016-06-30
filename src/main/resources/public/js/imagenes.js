/**
 * Created by saleta on 6/30/2016.
 */
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