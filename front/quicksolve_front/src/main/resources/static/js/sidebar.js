$(document).ready(function () {

    var sidbarToggleBtn = $('#sidebarToggle');
    var main = $('main');
    var accordionBody = $('.accordion-body');
    var smallWith = 768;


    if ($(window).width() > smallWith) {
        main.removeClass('sidebar-hide');
    }
    accordionBody.addClass('justify-content-center');

    sidbarToggleBtn.on('click', function () {
        main.toggleClass('sidebar-hide');
    });

    $(window).on('resize', function () {
        if ($(window).width() < smallWith) {
            main.addClass('sidebar-hide');
        }else{
            main.removeClass('sidebar-hide');
        }
    });


     //El background del link cambia segun la pagina en la que estemos
        var as = $('.nav-link');
        $.each(as, function (index, value) {
            $(value).removeClass('sidebar-active');
        });

        var path = window.location.pathname;
        var activeLink = $('.nav-link[href="'+path+'"]');
        activeLink.addClass('sidebar-active');

});