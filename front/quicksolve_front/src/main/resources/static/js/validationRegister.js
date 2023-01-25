$(document).ready(function () {
    const regex = /^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$/;

    $("#confirmpassword").on('keyup', function(){
        const password = $("#password").val();
        const confirmPassword = $("#confirmpassword").val();
        if (password !== confirmPassword) {
            $("#passwordMatchError").html("Las contraseñas no coinciden.");
            $("#password").addClass("is-invalid");
            $("#confirmpassword").addClass("is-invalid");
        } else {
            $("#passwordMatchError").html("");
            $("#password").removeClass("is-invalid");
            $("#confirmpassword").removeClass("is-invalid");
        }
        activateButtonForm();
    });

    $("#name").on('keyup', function(){
        const name = $("#name").val();
        console.log(regex.test(name))
        if (name === "" && !regex.test(name)){
            $("#nameBlankError").html("El nombre es obligatorio.");
            $("#name").addClass("is-invalid");
        } else {
            $("#nameBlankError").html("");
            $("#name").removeClass("is-invalid");
        }
        activateButtonForm();
    });

    $("#firstsurname").on('keyup', function(){
        const firstsurname = $("#firstsurname").val();

        if(firstsurname === "" && !regex.test(firstsurname)){
            $("#firstSurnameBlankError").html("El primer apellido es obligatorio.");
            $("#firstsurname").addClass("is-invalid");
        } else {
            $("#firstSurnameBlankError").html("");
            $("#firstsurname").removeClass("is-invalid");
        }
        activateButtonForm();
    });
});

function activateButtonForm(){
    const name = $("#name").val();
    const firstSurname = $("#firstsurname").val();
    const password = $("#password").val();
    const confirmpassword = $("#confirmpassword").val();

    if (name !== "" &&
        firstSurname !== "" &&
        password !== "" &&
        password === confirmpassword){
        $("#submit").removeClass("disabled");
    } else {
        $("#submit").addClass("disabled");
    }
}