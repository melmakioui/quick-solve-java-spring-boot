$(document).ready(function () {
    //Navbar
    var as = $('.nav-item .nav-link');
    $.each(as, function (index, value) {
        $(value).removeClass('current-page');

        var path = window.location.pathname;
        var activeLink = $('.nav-link[href="' + path + '"]');
        activeLink.addClass('current-page');
    });
});

