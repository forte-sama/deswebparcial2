/**
 * Created by forte on 27/06/16.
 */

$(document).ready(function () {
    $("#marca-input").keyup(function () {
        console.warn("klk");

        $("#estado,#espera").toggleClass("hidden");

        setTimeout(function() {
            $("#espera").toggleClass("hidden");
        }, 1500);
    });
});