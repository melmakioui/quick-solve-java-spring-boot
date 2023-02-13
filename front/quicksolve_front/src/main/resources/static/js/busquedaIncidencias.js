
$(document).ready(function (){
    $("#search-input-incidences").on('input', function (evt){
        if (evt.currentTarget.value){
            $.ajax('http://localhost:8080/incidencias/buscar', {
                method: 'POST',
                contentType: 'application/json',
                data: evt.currentTarget.value,
                success: function (data) {
                    $("#resultados-incidencias").html("")
                    data.forEach(i => {
                        $("#resultados-incidencias").append(`
                            <div class="card card-animation card mb-4 mx-2 incidence" style="width: 18rem;" onclick="window.location.href='/incidencia/${i.id}'">
                                <div class="card-body">
                                    <h5 class="card-title text-truncate">${i.title}</h5>
                                    <p class="card-text text-truncate mb-1">${i.description}</p>
                                    <div class="d-flex justify-content-between">
                                        <small>${i.daysAgo}</small>
                                    </div>
                                </div>
                            </div>
                        `)
                    })
                }
            })
        } else {
            $("#resultados-incidencias").html("")
        }
    })
})