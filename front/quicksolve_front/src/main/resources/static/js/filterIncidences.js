$(document).ready(function (){

    const options = {
        dateFormat: "yy-mm-dd",
        maxDate: new Date()
    }

    $( "#datepicker" ).datepicker(options);
    $( "#datepickerResponsive" ).datepicker(options);

    const btnCanvas = $('#offcanvasBtn')
    const btnCanvasContainer= $("#offCanvasBtnCont")
    const formNoResponsive = $('#filter')

    window.onload = toggleForms;
    window.onresize = toggleForms;
    window.addEventListener("fullscreenchange", toggleForms);

    function toggleForms(){
        if ($(window).width() < 720){
            $(btnCanvas).removeClass("d-none")
            $(btnCanvas).addClass("w-100")
            $(formNoResponsive).addClass("d-none")
            $(btnCanvasContainer).addClass("w-100")
        }else {
            $(btnCanvas).addClass("d-none")
            $(btnCanvas).removeClass("w-100")
            $(formNoResponsive).removeClass("d-none")
            $(btnCanvasContainer).removeClass("w-100")
        }
    }


    const accordions = $(".incidences");
    const message = $("#emptyMessage")
    const messageTrue = $(message).clone()

    $.each(accordions,function (index,accordion){
        if (accordion.children.length === 0) {
            $(message).remove()
            $(messageTrue).removeClass("d-none")
            $(accordion).append($(messageTrue))
        }
    })


    var firstAccordionShow = $('.accordion-collapse')[0]
    $(firstAccordionShow).addClass('show')
    var firstAccordion = $('.accordion-button')[0]
    $(firstAccordion).removeClass('collapsed')
})
