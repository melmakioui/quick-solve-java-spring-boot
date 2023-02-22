
$(document).ready(function () {
    console.log("ready!");
    const message = document.getElementById("drag-drop-label");

    if (window.location.href.includes("modificar")){
        loadIncidenceImages();
    } else {
        initializeDragAndDrop();
    }

    function initializeDragAndDrop(preloadedImages) {
        $(".input-images").imageUploader({
            label: message.innerText,
            imagesInputName: "images",
            extensions: [".jpg", ".jpeg", ".png"],
            mimes: ["image/jpeg", "image/png"],
            preloaded: preloadedImages ? preloadedImages : [],
            maxFiles: 5,
        });
    }

    function loadIncidenceImages(){

        const site = window.location.href.split("/")
        const incidenceId = site[site.length -1]
        const preloaded = [];

        $.ajax({
            url: "http://localhost:8080/rest/imagenes/" + incidenceId,
            type: "POST",
            headers: {
                "Authorization": localStorage.getItem("token")
            },
            success: function (data) {

                $.each(data, function (index, value) {
                    preloaded.push({
                        id: value.id,
                        src: value.filePath
                    });
                });

                initializeDragAndDrop(preloaded);

                let deleteImageBtns = document.querySelectorAll(".delete-image");
                $.each(deleteImageBtns, function (index, button) {
                    $(button).on("click", function () {
                        const imageId = $(this).parent().find("img").attr("src");
                        deleteImage(imageId, incidenceId);
                    });
                });
            }
        });
    }


    function deleteImage(src,incidenceId){
        $.ajax({
            url: "http://localhost:8080/rest/imagenes/eliminar",
            type: "DELETE",
            data: {
                src: src,
                incidenceId: incidenceId
            },
            success: function (data) {
                console.log(data);
            }
        });
    }
});