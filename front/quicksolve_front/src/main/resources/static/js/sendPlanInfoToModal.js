$("button[name='submit']").click(function (){
    let idPlan = $(this).closest("div.card-body").find("input[name='plan']").val();
    $("#paypal-button-container").html("");
    initPayPalButton(idPlan);
})

function initPayPalButton(id) {
    $.ajax('http://localhost:8080/planes/precio', {
        method: 'POST',
        contentType: 'application/json',
        data: id,
        success: function (precio) {
            paypal.Buttons({
                style: {
                    shape: 'pill',
                    color: 'gold',
                    layout: 'vertical',
                    label: 'paypal',
                },

                createOrder: function(data, actions) {
                    return actions.order.create({
                        purchase_units: [ {"amount": {"currency_code":"EUR","value": precio} }]
                    });
                },

                onApprove: function(data, actions) {
                    return actions.order.capture().then(function(orderData) {
                        $.ajax("http://localhost:8080/modificar/plan", {
                            method: 'POST',
                            contentType: 'application/json',
                            data: id,
                            success: function (){
                                window.location.href = "/planes";
                            }
                        })
                    });
                },

                onError: function(err) {
                    console.log(err);
                }
            }).render('#paypal-button-container');
        }
    })
}