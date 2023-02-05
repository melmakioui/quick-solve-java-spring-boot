$("button[name='submit']").click(function (){
    var idPlan = $(this).closest("div.card-body").find("input[name='plan']").val();
    $("#inputPlanForm").html(`
        <input type='number' name="tipoplan" value='${ idPlan }' hidden>
    `);
})