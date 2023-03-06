let stompClient = null;
let fullName = $("#fullNameTech").html();

$(document).ready(function(){
    $("button[name='openChat']").each(function () {
        $(this).click(function (){
            let sessionId = $(this).closest("div.card-body").find("input").val();
            connect(sessionId);
        });
    });

    $("form[name='sendMessage']").each(function (){
        $(this).on('submit', function(e) {
            e.preventDefault();
            let msg = $(this).find("div input[name='inputMessage']");
            sendMessage($(this).find("input[name='sessionIdChat']").val(), msg.val());
            msg.val('');
        });
    });

    $("#cerrarChat").each(function (){
        $(this).on('click', function (){
            $.ajax("http://localhost:8080/close/chat", {
                method: 'POST',
                contentType: "application/json",
                data: $(this).closest("div.card-body").find("input").val(),
                success: function(){
                    stompClient.send("/chat/group/" + $(this).closest("div.card-body").find("input").val(), {}, JSON.stringify({ 'sentBy': fullName, 'message': "Ha cerrado el chat." }));
                    stompClient.disconnect();
                }
            });
        });
    })
});

function scrollDown(){
    let div = $("#messagesChat");
    div.scrollTop(div.prop('scrollHeight'));
}

function connect(sessionId) {
    let socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        stompClient.subscribe('/topic/group/' + sessionId, function (message) {
            let json = JSON.parse(message.body);
            showMessage(json.sentBy, json.message, sessionId);
        });
    });
}

function sendMessage(sessionId, message) {
    stompClient.send("/chat/group/" + sessionId, {}, JSON.stringify({
        'sentBy': fullName,
        'message': message
    }));
}

function showMessage(sentBy, message, sessionId) {
    let chatBubble = sentBy !== fullName ? 'chat-bubble2 align-items-start' : 'chat-bubble1 align-items-end';
    $("#messagesChat" + sessionId).append(`
        <div class="d-flex flex-column m-1 m-lg-2 p-3 ${chatBubble}">
            <div class="fw-bold">${sentBy}</div> 
            <div class="text-break">${message}</div>
        </div>
    `);
    scrollDown();
}