$(document).ready(function(){
    $("#abrirChat").on('click', function (){
        $("#messageFooterChat").removeClass('d-none');
        $("#titleChat").removeClass('d-none');
        $("#messagesChat").html("");
        connect();
    });

    $("#abrirVentanaChat").on('click', function(){
        $("#chatLive").toggleClass('d-none');
    });

    $("#sendMessage").on('submit', function(e) {
        e.preventDefault();
        sendData();
    });
});

function scrollDown(){
    let div = $("#messagesChat");
    div.scrollTop(div.prop('scrollHeight'));
}

let ws;

function connect() {
    ws = new WebSocket('ws://localhost:8080/chat');
    console.log(ws)
    ws.onmessage = function(data) {
        helloWorld(data.data);
    }
}

function sendData() {
    let data = JSON.stringify({
        'message' : $("#inputMessage").val()
    })
    ws.send(data);
}

function helloWorld(message) {
    $("#messagesChat").append(message + "<br>");
}