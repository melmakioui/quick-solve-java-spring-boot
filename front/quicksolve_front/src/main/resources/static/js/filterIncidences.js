    //Accordion
    var firstAccordionShow = $('.accordion-collapse')[0]
    $(firstAccordionShow).addClass('show')
    var firstAccordion = $('.accordion-button')[0]
    $(firstAccordion).removeClass('collapsed')

    //Datepicker
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
