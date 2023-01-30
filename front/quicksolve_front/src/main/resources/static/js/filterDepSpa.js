
$(document).ready(function () {

    var waitingAccordion = $('#waiting')
    var solvingAccordion = $('#solving')
    var solvedAccordion = $('#solved')
    var cancelledAccordion = $('#cancelled')

    var cardsWaiting = $('#waiting .card')
    var cardsSolving = $('#solving .card')
    var cardsSolved = $('#solved .card')
    var cardsCancelled = $('#cancelled .card')

    var incidences = $('.incidence')
    var newIncidence = $('#newIncidence')
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

        fillAccordions(result)
        feedBackEmptyIncidences()
    }


    function feedBackEmptyIncidences(){

        if (waitingAccordion.children().length === 0) {
            waitingAccordion.append('<h3 class="text-center m-auto">N/A</h3>')
        }

        if (solvingAccordion.children().length === 0) {
            solvingAccordion.append('<h3 class="text-center m-auto">N/A</h3>')
        }

        if (solvedAccordion.children().length === 0) {
            solvedAccordion.append('<h3 class="text-center m-auto">N/A</h3>')
        }

        if (cancelledAccordion.children().length === 0) {
            cancelledAccordion.append('<h3 class="text-center m-auto">N/A</h3>')
        }
    }

    function fillAccordions(result){
        cardsWaiting.remove()
        cardsSolving.remove()
        cardsSolved.remove()
        cardsCancelled.remove()

        waitingAccordion.empty()
        solvedAccordion.empty()
        solvingAccordion.empty()
        cancelledAccordion.empty()

        $.each(result, function (index, incidence) {

            if ($(incidence).hasClass('waiting')) {
                waitingAccordion.append($(incidence))
                waitingAccordion.append($(newIncidence))
            }
            if ($(incidence).hasClass('solving')) {
                solvingAccordion.append($(incidence))
            }

            if ($(incidence).hasClass('solved')) {
                solvedAccordion.append($(incidence))
            }

            if ($(incidence).hasClass('cancelled')) {
                cancelledAccordion.append($(incidence))
            }
        });
    }


});