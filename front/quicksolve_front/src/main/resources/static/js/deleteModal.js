$(document).ready(function() {
    var delButtons = $('.btnDelModal')

    $.each(delButtons, function (index, value) {
        $(value).on('click', function (e) {
            e.stopPropagation();
            let link = $(this).attr("data-link");
            $("#delBtnModal").attr("href", link);
        });
    });
});