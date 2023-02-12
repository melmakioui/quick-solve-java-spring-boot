$(document).ready(function () {

    var selectDepartment = $('#dep')
    var selectSpace = $('#spa')
    var accordions = $('.incidences')
    var reset = $('#reset-filter')
    var newIncidence = $('#new-incidence')
    console.log(newIncidence)

    var firstAccordionShow = $('.accordion-collapse')[0]
    $(firstAccordionShow).addClass('show')

    selectDepartment.on('input',filter);
    selectSpace.on('input',filter);
    reset.on('click',resetFilter);


    function resetFilter() {
        selectDepartment.val(0)
        selectSpace.val(0)
        filter()
    }

    function filter() {
        $.each(accordions,function(index,accordion){

            var incidenceCards = $(accordion).children('.card')

            $.each(incidenceCards, function(index,card) {

                var depData = $(card).data("department")
                var spaData = $(card).data("space")

                if (selectDepartment.val() === depData && selectSpace.val() === spaData) {
                    $(card).show()
                }else if (selectDepartment.val() === depData && selectSpace.val() == 0) {
                    $(card).show()
                }else if (selectDepartment.val() == 0 && selectSpace.val() === spaData) {
                    $(card).show()
                }else if (selectDepartment.val() == 0 && selectSpace.val() == 0) {
                    $(card).show()
                }else{
                    $(card).hide()
                }

            })


            var visibleCards = $(accordion).children('.card:visible')

            if (visibleCards.length === 0) {
            }
        })

        newIncidence.show()

    }


});

