$(document).ready(function () {


    //Si el div de los mensajes ocupa mas de 500px de alto, se muestra el boton de scroll
    var comments = document.getElementById('comments');
    comments.scrollTo(0, comments.scrollHeight);

    if (comments.style.height  > 500) {
        comments.css('overflow', 'scroll');
    }

    //Sroll hacia el comentario donde quiere editar
    var update = document.getElementById('update');
    if (update) {
        $("html, body").animate({
            scrollTop: $("#update").offset().top
        }, 500);
    }


    //Prevenir que se envie el formulario al pulsar enter si esta vacio
    $('form').on('submit', function (event) {
        if ($('#messageUser').val() == '') {
            event.preventDefault();
        }
    });


    //Modal imagenes
    var modal = document.getElementById("modal-img");

    modal.onclick = function() {
        modal.style.display = "none";
    }
    var modalImg = document.getElementById("img-target");


    var img = document.getElementsByClassName("image-modal");
    for (var i = 0; i < img.length; i++) {
        img[i].onclick = function(){
            modal.style.display = "block";
            modalImg.src = this.src;
        }
    }

    var span = document.getElementsByClassName("close")[0];

    span.onclick = function() {
        modal.style.display = "none";
    }
});