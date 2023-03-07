$(document).ready(function (){

    $( "#datepicker" ).datepicker({
        dateFormat: "yy-mm-dd",
        maxDate: new Date(),
        culture: "es",
    },
       );
    $( "#datepickerResponsive" ).datepicker({
        dateFormat: "yy-mm-dd",
        maxDate: new Date(),
    });

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


    //Accordion
    var firstAccordionShow = $('.accordion-collapse')[0]
    $(firstAccordionShow).addClass('show')
    var firstAccordion = $('.accordion-button')[0]
    $(firstAccordion).removeClass('collapsed')


    $.datepicker.regional["en-GB"] = {
        closeText: "Done",
        prevText: "Prev",
        nextText: "Next",
        currentText: "Today",
        monthNames: [ "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December" ],
        monthNamesShort: [ "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" ],
        dayNames: [ "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" ],
        dayNamesShort: [ "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" ],
        dayNamesMin: [ "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa" ],
        weekHeader: "Wk",
        dateFormat: "dd/mm/yy",
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: ""
    }

    $.datepicker.setDefaults($.datepicker.regional["es"])

    var language = document.cookie.split("=")
    language = language[language.length -1]

    if (language === "en"){
        $.datepicker.setDefaults($.datepicker.regional["en-GB"])
    }else {
        $.datepicker.setDefaults($.datepicker.regional["es"])
    }

})
