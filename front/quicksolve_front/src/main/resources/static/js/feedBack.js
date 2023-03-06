$(document).ready(function() {
    var notify = document.getElementById('feedBack')

    if (notify) {
        $.notifyBar({
            html: $('#feedBack').text(),
            cssClass: "success",
            delay: 5000,
            position: "bottom",
            animationSpeed: "normal"
        });
    }
});