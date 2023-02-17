$(document).ready(function() {
    var delButtons = $('.btnDelModal')
    $.each(delButtons, function (index, value) {
        $(value).on('click', function (e) {
            e.stopPropagation();
            let link = $(this).attr("data-link");
            $("#delBtnModal").attr("href", link);
        });
        $(value).on('keyup', function (e) {
            if (e.key === "Enter"){
                e.stopPropagation();
                let link = $(this).attr("data-link");
                $("#delBtnModal").attr("href", link);
                $("#deleteIncidence").modal("show");
            }
        });
    });
});