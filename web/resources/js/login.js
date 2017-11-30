
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
        let path = data.redirect
        if (path.length > 0) {
            sleep(1500);
            redirect(path);
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


