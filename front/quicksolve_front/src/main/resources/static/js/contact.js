(() => {
    'use strict'

    const forms = document.querySelectorAll('.needs-validation')

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

})()