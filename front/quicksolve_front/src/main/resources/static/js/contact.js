(() => {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    const forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault()
                event.stopPropagation()
            }

            form.classList.add('was-validated')
        }, false)
    })

    window.addEventListener("resize", function () {
        if (window.innerWidth < 990) {
            document.getElementById("content").classList.add("flex-column");
        } else {
            document.getElementById("content").classList.remove("flex-column");
        }
    });

    window.addEventListener("load", function () {
        if (window.innerWidth < 990) {
            document.getElementById("content").classList.add("flex-column");
        } else {
            document.getElementById("content").classList.remove("flex-column");
        }
    });

    window.addEventListener("orientationchange", function () {
        if (window.innerWidth < 990) {
            document.getElementById("content").classList.add("flex-column");
        } else {
            document.getElementById("content").classList.remove("flex-column");
        }
    });



    function initMap() {
        const quicksolve = { lat:   39.56134417687475, lng:  3.1996467790454575 };
        const map = new google.maps.Map(document.getElementById("map"), {
            zoom: 15,
            center: quicksolve,
        });
        const marker = new google.maps.Marker({
            position: quicksolve,
            map: map,
        });
    }
    window.initMap = initMap;
})()