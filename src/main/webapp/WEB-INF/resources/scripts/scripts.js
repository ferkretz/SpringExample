$(document).ready(function () {
    $(".collapse-trigger").click(function () {
        if ($(this).parent().parent().hasClass("collapse")) {
            $(this).parent().parent().removeClass("collapse");
        } else {
            $(this).parent().parent().addClass("collapse");
        }
    });

    $("form[class=progress]").submit(function () {
        $("button[class=progress]").prop("disabled", "true");
    });

});


