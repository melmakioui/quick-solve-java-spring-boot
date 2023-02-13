$(document).ready(function() {
    var accordions = $('.incidences')
    var pagination = $('.pagination')
    var size = 5;


        accordions.each(function (index, accordion) {
            var items = $(accordion).children('.card')

            if (items.length > size) {
                $(pagination[index]).pagination({
                    dataSource: [...items],
                    pageSize: size,
                    className: 'paginationjs-theme-blue paginationjs-big',
                    callback: function (data, pagination) {
                        var html = template(data)
                        $(accordion).html(html);
                    }
                })
            }
        })

    function template(data) {
        var html = ''
        $.each(data, function (index, item) {
            html += item.outerHTML
        })
        return html;
    }
});