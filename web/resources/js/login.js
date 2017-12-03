function sendToServer() {
    let loginValue = $("#name").val();
    let passValue = $("#pass").val();
    $.ajax({
        url: '/login',
        method: 'POST',
        data: JSON.stringify({
            login: loginValue,
            password: md5(passValue)
        })
    }).done(function(data) {
        $("#displayed-data").text(data.message);
        if (data.authorized) {
            sleep(1000);
            redirect(data.redirectPath);
        }
    });
}
function redirect(path) {
    window.location.href = path;
}

function sleep (milliSeconds) {
    let startTime = new Date().getTime();
    while (new Date().getTime() < startTime + milliSeconds);
}


