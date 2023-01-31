$(document).ready(function () {
    $("#password").on('input', function(){
        $("#confirmpassword").attr("pattern", "^" + $("#password").val() + "$")
    });

    'use strict'
    const forms = document.querySelectorAll('.needs-validation');
    Array.prototype.slice.call(forms)
    .forEach(function (form) {
        form.addEventListener('submit', function (event) {
            if (!form.checkValidity()) {
                event.preventDefault()
                event.stopPropagation()
            }

            form.classList.add('was-validated')
        }, false)
    });
});