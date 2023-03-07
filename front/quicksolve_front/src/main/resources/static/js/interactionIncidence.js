$(document).ready(function () {

    var update = document.getElementById('update');
    if (update) {
        $("html, body").animate({
            scrollTop: $("#update").offset().top
        }, 500);
    }

    $('form').on('submit', function (event) {
        if ($('#messageUser').val() == '') {
            event.preventDefault();
        }
    });

});