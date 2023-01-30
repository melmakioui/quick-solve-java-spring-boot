$(document).ready(function () {

    var main = $('main');
    var smallWith = 768;
    var sidbarToggleBtn = $('#sidebar-icon');
    var sidebar = $('#sidebar');
    var wrapper = $('#page-content-wrapper');

    if ($(window).width() > smallWith) {
        main.removeClass('sidebar-hide');
    }

    triggerButton()

    sidbarToggleBtn.on('click', function () {
        $(this).toggleClass('open');

        if ($(window).width() > smallWith) {
            main.toggleClass('sidebar-hide');
        }

        if ($(window).width() < smallWith) {
            main.toggleClass('sidebar-hide');
            sidebar.toggleClass('sidebar-dashboard');
            sidebar.toggleClass('sidebar-dashboard-responsive');
            wrapper.toggleClass('d-none');

        }
    });

    $(window).on('resize', function () {

        if ($(window).width() < smallWith) {
            main.addClass('sidebar-hide');
            //Cerrar siempre sidebar-responsive si el tamaño de la pantalla es menor a 768px
            if (sidebar.hasClass('sidebar-dashboard-responsive')) {
                sidebar.removeClass('sidebar-dashboard-responsive');
                sidebar.addClass('sidebar-dashboard');
                wrapper.removeClass('d-none');
                main.removeClass('sidebar-hide');
            }
        }

        if ($(window).width() > smallWith) {
            sidebar.removeClass('sidebar-dashboard-responsive');
            sidebar.addClass('sidebar-dashboard');
            main.removeClass('sidebar-hide');
            wrapper.removeClass('d-none');
        }
        triggerButton()
    });

    //El background del link cambia segun la pagina en la que estemos
    var as = $('.nav-link');
    $.each(as, function (index, value) {
        $(value).removeClass('sidebar-active');
    });

    var path = window.location.pathname;
    var activeLink = $('.nav-link[href="' + path + '"]');
    activeLink.addClass('sidebar-active');


    //funciones para el sidebar responsive

    function triggerButton(){
        if (main.hasClass('sidebar-hide')) {
            sidbarToggleBtn.removeClass('open')
        }else {
            sidbarToggleBtn.addClass('open')
        }
    }
});