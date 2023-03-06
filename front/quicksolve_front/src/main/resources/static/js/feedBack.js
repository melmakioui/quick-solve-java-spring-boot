$(document).ready(function() {
    var notify = document.getElementById('feedBack')

    if (notify) {
        $.notifyBar({
            html: $('#feedBack').text(),
            cssClass: $(notify).data('class'),
            delay: 5000,
            position: "bottom",
            animationSpeed: "normal"
        });
    }
});