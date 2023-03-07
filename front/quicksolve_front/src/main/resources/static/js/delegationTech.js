$(document).ready(function() {

    document.querySelectorAll(".form-select").forEach(function (element) {
        element.addEventListener("click", function (event) {
            event.preventDefault();
            event.stopPropagation();
        });
    });

    document.querySelectorAll("select").forEach(function (element) {
        element.addEventListener("input", function (e) {

            e.preventDefault();
            e.stopPropagation();

            const incidence = e.currentTarget.getAttribute("data-incidence");
            const tech = e.currentTarget.value;
            const message = document.getElementById("feedBack").innerText;
            const techName = e.currentTarget.options[e.currentTarget.selectedIndex].text;

            $.ajax('http://localhost:8080/tech/assign', {
                method: 'PUT',
                headers: {
                    'Authorization': localStorage.getItem('token')
                },
                data: {
                    incidenceId: incidence,
                    techId: tech
                },
                success: function (data) {
                    $.notifyBar({
                        html: message + " " + techName,
                        cssClass: "success",
                        delay: 5000,
                        position: "bottom",
                        animationSpeed: "normal"
                    });
                },
                error: function (data) {
                    console.log(data);
                }
            });

        });
    });
})