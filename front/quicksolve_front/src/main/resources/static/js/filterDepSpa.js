$(document).ready(function () {

    var displayAcc = document.getElementsByClassName("accordion-collapse");
    displayAcc[0].classList.add("show");

    var accordions = document.getElementsByClassName("incidences");
    var selectSpace = document.getElementById("spa");
    var selectDepartment = document.getElementById("dep");
    var messageEmpty = document.getElementById("messageEmpty");
    var newIncidence = document.getElementById("newIncidence");
    var reset = document.getElementById("reset-filter");

    selectSpace.oninput = filter;
    selectDepartment.oninput = filter;
    reset.onclick = resetFilter;

    //TODO feedback to user when no incidences are found

    function resetFilter() {
        for (var accordion of accordions) {
            for (var child of accordion.children) {
                if (child.classList.contains("card")) {
                    child.classList.remove("d-none");
                }
            }
        }
        selectSpace.value = "all";
        selectDepartment.value = "all";
    }

    function filter(event) {

        var value = event.target.value;

        if (selectSpace.value === "all" && selectDepartment.value === "all") {
            for (var accoridionElement of accordions) {
                for (var card of accoridionElement.children) {
                    card.classList.remove("d-none");
                }
            }
        }

        if (selectSpace.value === "all" && selectDepartment.value !== "all") {
            for (var accoridionElement of accordions) {
                for (var card of accoridionElement.children) {
                    if (card.dataset.department === value) {
                        card.classList.remove("d-none");
                    } else {
                        card.classList.add("d-none");
                    }
                }
            }
        }

        if (selectSpace.value !== "all" && selectDepartment.value === "all") {
            for (var accoridionElement of accordions) {
                for (var card of accoridionElement.children) {
                    if (card.dataset.space === value) {
                        card.classList.remove("d-none");
                    } else {
                        card.classList.add("d-none");
                    }
                }
            }
        }

        if (selectSpace.value !== "all" && selectDepartment.value !== "all") {
            for (var accoridionElement of accordions) {
                for (var card of accoridionElement.children) {
                    if (card.dataset.space === selectSpace.value && card.dataset.department === selectDepartment.value) {
                        card.classList.remove("d-none");
                    } else {
                        card.classList.add("d-none");
                    }
                }
            }
        }


        newIncidence.classList.remove("d-none");
    }
});

