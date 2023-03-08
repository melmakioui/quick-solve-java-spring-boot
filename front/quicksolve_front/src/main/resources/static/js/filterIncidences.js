$(document).ready(function () {

    const btnCanvas = $('#offcanvasBtn')
    const btnCanvasContainer = $("#offCanvasBtnCont")
    const formNoResponsive = $('#filter')

    window.onload = toggleForms;
    window.onresize = toggleForms;
    window.addEventListener("fullscreenchange", toggleForms);

    function toggleForms() {
        if ($(window).width() < 720) {
            $(btnCanvas).removeClass("d-none")
            $(btnCanvas).addClass("w-100")
            $(formNoResponsive).addClass("d-none")
            $(btnCanvasContainer).addClass("w-100")
        } else {
            $(btnCanvas).addClass("d-none")
            $(btnCanvas).removeClass("w-100")
            $(formNoResponsive).removeClass("d-none")
            $(btnCanvasContainer).removeClass("w-100")
        }
    }

    //Accordion
    var firstAccordionShow = $('.accordion-collapse')[0]
    $(firstAccordionShow).addClass('show')
    var firstAccordion = $('.accordion-button')[0]
    $(firstAccordion).removeClass('collapsed')


    //date picker

    $("#datepicker").datepicker({
            dateFormat: "yy-mm-dd",
            maxDate: new Date(),
        },
    );

    $("#datepickerResponsive").datepicker({
        dateFormat: "yy-mm-dd",
        maxDate: new Date(),
    });

    var cookieLanguage = document.cookie.replace(/(?:(?:^|.*;\s*)language\s*\=\s*([^;]*).*$)|^.*$/, "$1");

    if (cookieLanguage) {
        $.datepicker.setDefaults($.datepicker.regional[cookieLanguage]);
    }else {
        $.datepicker.setDefaults($.datepicker.regional[navigator.language]);
    }
})
