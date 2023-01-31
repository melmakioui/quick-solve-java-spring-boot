$(document).ready(function () {

    'use strict'
    var forms = document.querySelectorAll('.needs-validation')
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })


    //Files Validation

    var fileInput = $('#formFileMultiple');
    var maxQuantity = 5;
    var contentTypes = ['image/jpeg', 'image/png'];
    var counterImages = 0; //contador para que no suban mas de 5 imagenes
    var filesToDisplay = []; //con este array borramos el ultimo elemento si se pasa de 5
    var imageContainer = $('#images-container');


    fileInput.on('input', function (event) {
        var files = event.target.files;
        counterImages += files.length; //se van sumando las imagenes que se van subiendo

        if (counterImages > maxQuantity) { //comprovamos antes de que se vacie el array que no se pase de 5
            displayError();
            return
        }

        imageContainer.empty(); //vaciamos el contenedor de imagenes

        $.each(files, function (index, file) {
            if (!isValidMImeType(file)) {
                displayMimeTypeError();
                return
            }
            filesToDisplay.push(file); //se van agregando los archivos al array para luego borrar el ultimo si se pasa de 5
        });

        displayImage();
        updateFilesToUpload() //lo que se va a transferir al servidor
    });


    function updateFilesToUpload() {
        var newFileList = new DataTransfer();
        $.each(filesToDisplay, function (index, file) {
            newFileList.items.add(file);
        });
        fileInput[0].files = newFileList.files;
        console.log("actualizada", fileInput[0].files);
    }

    function displayError() {
        alert('Error');
        //counterImages = 5;
        counterImages -= fileInput[0].files.length;

        updateFilesToUpload() //para que no solo esten los que han dado error
    }

    function displayMimeTypeError() {
        alert('Error');
        fileInput.val('');
    }

    function isValidMImeType(file) {
        return contentTypes.includes(file.type);
    }

    function displayImage() {
        $.each(filesToDisplay, function (index, file) {
            var reader = new FileReader();
            reader.onload = function (event) {
                var image = event.target.result;

                var card = createCard()
                var image = createImage(file)
                var button = createButton(image)
                card.append(image)
                card.append(button)

                imageContainer.append(card);
            }
            reader.readAsDataURL(file);
        });
    }

    function createButton() {
        var button = $('<button/>', {
            id: 'del-img',
            class: 'btn d-flex justify-content-center align-items-center del-btn'
        });

        var span = $('<span/>', {
            class: 'material-symbols-outlined'
        });

        span.text('close');
        button.append(span);

        button.on('click', function (image) {
            var card = $(this).parent();
            var index = card.index();
            card.remove();
            filesToDisplay.splice(index, 1);
            counterImages--;

            $.each(filesToDisplay, function (index, file) {
                if (image.name == file.name) {
                    filesToDisplay.splice(index, 1);
                }
            })

            updateFilesToUpload()
            console.log("despues de borrar", fileInput[0].files);
        });

        return button;
    }

    function createCard() {
        var card = $('<div/>', {
            id: 'card-img',
            class: 'card m-2'
        });

        return card;
    }

    function createImage(file) {
        var image = new Image();
        image.src = URL.createObjectURL(file);
        image.width = 300;
        image.height = 200;
        image.onload = function () {
            URL.revokeObjectURL(this.src);
        }
        return image;
    }


});

