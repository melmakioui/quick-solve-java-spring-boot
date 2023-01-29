
$(document).ready(function () {

    var waitingAccordion = $('#waiting') // Accordion for waiting list
    var solvingAccordion = $('#solving') // Accordion for solving list

    var cardsWaiting = $('#waiting .card') // get all cards inside waiting accordion
    var cardsSolving = $('#solving .card') // get all cards inside solving accordion

    var incidences = $('.incidence') // get all incidences cards
    var newIncidence = $('#newIncidence') // get new incidence button
    var selectDep = $('#dep')
    var selectSpa = $('#spa')

    selectDep.on('change', filterIncidence)
    selectSpa.on('change', filterIncidence)

    function filterIncidence(event) {
        var result = [];
        var value = event.target.value;

        result = $.grep(incidences, function (incidence) {
            return $(incidence).data('department') === value ||
                $(incidence).data('space') === value;
        });

        if(value === 'all') {
            if (selectSpa.val() === 'all' && selectDep.val() === 'all') {
                result = incidences;
            }

            /*
            * Si el departamento es all y el espacio no es all, entonces filtramos por espacio independientemente del departamento
            * */
            if (selectSpa.val() !== 'all') {
                result = $.grep(incidences, function (incidence) {
                    return $(incidence).data('space') === selectSpa.val();
                });
            }

            if (selectDep.val() !== 'all') {
                result = $.grep(incidences, function (incidence) {
                    return $(incidence).data('department') === selectDep.val();
                });
            }
        }

        fillAccordions(result);
    }

    function fillAccordions(result){
        cardsWaiting.remove()
        cardsSolving.remove()

        $.each(result, function (index, incidence) {

            if ($(incidence).hasClass('waiting')) {
                waitingAccordion.append($(incidence))
                waitingAccordion.append($(newIncidence))
            }
            if ($(incidence).hasClass('solving')) {
                solvingAccordion.append($(incidence))
            }
        });
    }


});