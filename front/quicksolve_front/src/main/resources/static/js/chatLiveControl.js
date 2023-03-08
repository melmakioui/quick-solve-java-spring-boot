let sessionId = '';
let stompClient = null;
let name = '';

$(document).ready(function(){
    $("#initChatForm").on('submit', function (e){
        name = $("#nameChat").val();
        e.preventDefault();
        $("#messageFooterChat").removeClass('d-none');
        $("#titleChat").removeClass('d-none');
        $("#cerrarChat").removeClass('d-none');
        $("#messagesChat").html(`
            <div class="d-flex flex-column m-1 m-lg-2 p-3 chat-bubble2 align-items-start">
                <div class="fw-bold"> Respuesta automática </div> 
                <div class="text-break">Bienvenido al chat! Porfavor, escriba su problema y en unos instantes un técnico le responderá</div>
            </div>
        `);
        connect();
    });

    $("#abrirVentanaChat").on('click', function(){
        $("#chatLive").toggleClass('d-none');
    });

    $("#sendMessage").on('submit', function(e) {
        e.preventDefault();
        let msg = $("#inputMessage");
        sendMessage(msg.val());
        msg.val('');
    });

    $("#cerrarChat").on('click', function (){
        sendMessage(sessionId + sessionId);
        $.ajax("/close/chat", {
            method: 'POST',
            contentType: "application/json",
            data: sessionId,
            success: function(){
                cerrarChat("Te has desconectado del chat.");
            },
            error: function(err){
                console.log(err);
            }
        })
    })
});

function scrollDown(){
    let div = $("#messagesChat");
    div.scrollTop(div.prop('scrollHeight'));
}

function connect() {

    for(let i = 0; i < 20; i++) {
        sessionId += String.fromCharCode(97 + Math.floor(Math.random() * 26));
    }
    $.ajax("/create/chat", {
        method: 'POST',
        contentType: "application/json",
        data: JSON.stringify({
            "user": name,
            "sessionId": sessionId
        }),
        success: function(){
            let socket = new SockJS('/chat');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function () {
                stompClient.subscribe('/topic/group/' + sessionId, function (message) {
                    let json = JSON.parse(message.body)
                    showMessage(json.sentBy, json.message);
                });
            });
        },
        error: function(err){
            console.log(err);
        }
    })
}

function sendMessage(message) {
    stompClient.send("/chat/group/" + sessionId, {}, JSON.stringify({
        'sentBy': name,
        'message': message
    }));
}

function showMessage(sentBy, message) {
    console.log(message, sessionId + sessionId)
    if (message === sessionId + sessionId) cerrarChat("Un técnico ha cerrado el chat.");
    let chatBubble = sentBy !== name ? 'chat-bubble2 align-items-start' : 'chat-bubble1 align-items-end';
    $("#messagesChat").append(`
        <div class="d-flex flex-column m-1 m-lg-2 p-3 ${chatBubble}">
            <div class="fw-bold">${sentBy}</div> 
            <div class="text-break">${message}</div>
        </div>
    `);
    scrollDown();
}

function cerrarChat(msg){
    stompClient.disconnect();
    $("#chatCardLive").html("<div class='card-body'>" + msg + "</div>");
    setTimeout(function (){
        $("#chatLive").fadeOut();
        $("#abrirVentanaChatDiv").fadeOut();
    }, 4000);
}