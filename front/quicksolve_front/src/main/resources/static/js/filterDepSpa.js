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
    var buttonReset = $('#reset-filter')

    var solvingMessage = $('#solving-message')
    var solvedMessage = $('#solved-message')
    var cancelledMessage = $('#cancelled-message')


    feedBackEmptyIncidences()


    buttonReset.on('click', function () {
        selectDep.val('all')
        selectSpa.val('all')
        var result = incidences
        fillAccordions(result)

    });

    selectDep.on('change', filterIncidence)
    selectSpa.on('change', filterIncidence)

    function filterIncidence(event) {
        var result = [];
        var value = event.target.value;

        result = $.grep(incidences, function (incidence) {
            return $(incidence).data('department') === value ||
                $(incidence).data('space') === value;
        });

        if (value === 'all') {
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
    }


    function feedBackEmptyIncidences() {

        if (waitingAccordion.children().length === 0) {
            waitingAccordion.append($(newIncidence))
        }

        if (solvingAccordion.children().length === 1) {
            if (solvingAccordion.children().is('h1')) {
                solvingMessage.toggleClass('d-none')
            }else solvingMessage.removeClass('d-none')
        }else if (solvingAccordion.children().length === 0) {
            solvingAccordion.append($(solvingMessage.removeClass('d-none')))
        }

        if (solvedAccordion.children().length === 1) {
            if (solvedAccordion.children().is('h1')) {
                solvedMessage.removeClass('d-none')
            }
        }else if (solvedAccordion.children().length === 0) {
            solvedAccordion.append($(solvedMessage.removeClass('d-none')))
        }

        if (cancelledAccordion.children().length === 1) {
            if (cancelledAccordion.children().is('h1')) {
                cancelledMessage.removeClass('d-none')
            }else cancelledMessage.removeClass('d-none')
        }else if (cancelledAccordion.children().length === 0) {
            cancelledAccordion.append($(cancelledMessage.removeClass('d-none')))
        }
    }

    function fillAccordions(result) {
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

        feedBackEmptyIncidences()
    }


    // Funcion para mostrar modal de incidencia a eliminar
    var delButtons = $('.btnDelModal')

    $.each(delButtons, function (index, value) {
        $(value).on('click', function (e) {
            e.stopPropagation();
            let link = $(this).attr("data-link");
            $("#delBtnModal").attr("href", link);
        });
    });


});