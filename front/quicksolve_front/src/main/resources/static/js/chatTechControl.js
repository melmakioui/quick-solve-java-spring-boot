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

    $("button[data-name='cerrarChat']").each(function (){
        $(this).on('click', function (){
            console.log($(this).closest("div.card-body"))
            console.log($(this).closest("div.card-body").find("button[data-name='confirmCloseDiv']"))
            $(this).closest("div.card-body").find("div.d-none").toggleClass("d-none");
        });
    });

    $("button[ name='closeChatConfirm']").each(function(){
        $(this).on('click', async function (){
            let sessId = $(this).closest("div.card-body").find("input").val();
            await connectAndEndMsg(sessId);
            $.ajax("/close/chat", {
                method: 'POST',
                contentType: "application/json",
                data: sessId,
                success: function(){
                    stompClient.disconnect();
                    $("#chatSesId" + sessId).remove();
                }
            });
        });
    });

    $("button[ name='cancelChatConfirm']").each(function(){
        $(this).on('click', function (){
            $(this).closest("div[data-name='confirmCloseDiv']").toggleClass("d-none");
        });
    });
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

function connectAndEndMsg(sessId){
    let socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        stompClient.subscribe('/topic/group/' + sessId, function (message) {
            let json = JSON.parse(message.body);
            showMessage(json.sentBy, json.message, sessId)
        });
        stompClient.send("/chat/group/" + sessId, {}, JSON.stringify({ 'sentBy': fullName, 'message': sessId + sessId }));
    });
}

function sendMessage(sessionId, message) {
    stompClient.send("/chat/group/" + sessionId, {}, JSON.stringify({
        'sentBy': fullName,
        'message': message
    }));
}

function showMessage(sentBy, message, sessionId) {
    if (message === sessionId + sessionId) {
        message = "El usuario ha cerrado el chat. Puede cerrar esta ventana.";
        $("#chatSesId" + sessionId).remove();
    }
    let chatBubble = sentBy !== fullName ? 'chat-bubble2 align-items-start' : 'chat-bubble1 align-items-end';
    $("#messagesChat" + sessionId).append(`
        <div class="d-flex flex-column m-1 m-lg-2 p-3 ${chatBubble}">
            <div class="fw-bold">${sentBy}</div> 
            <div class="text-break">${message}</div>
        </div>
    `);
    scrollDown();
}