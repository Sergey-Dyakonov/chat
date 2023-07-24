var stompClient = null;

var activeUser = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#chat").html("");
}

function connect() {
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            console.log(greeting.body);
            showGreeting(JSON.parse(greeting.body));
        });
        var formData = JSON.stringify($("#name").serializeArray());
        $.ajax({
            type: "POST",
            url: "/login",
            data: formData,
            success: function () {
            },
            dataType: "json",
            contentType: "application/json"
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({
        'sender': $("#name").val(),
        'content': $("#message").val()
    }));
}

function showGreeting(messageDTO) {
    $("#chat").append("<tr><td><strong>" + messageDTO.sender + ":</strong> " + messageDTO.content + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
});