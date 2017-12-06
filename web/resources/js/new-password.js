
function regUser() {
    let userId = $("#userId").val();
    let newPassword = $("#newPassword").val();

    $.ajax({
        url: '/password',
        method: 'POST',
        data: JSON.stringify({
            userId: userId,
            newPassword: md5(newPassword)
        })
    }).done(function(data) {
        $("#displayed-data").text(data.message);
           sleep(500);
           redirect(data.redirect);
    });
}

function redirect(path) {
    window.location.href = path;
}

function sleep (milliSeconds) {
    let startTime = new Date().getTime();
    while (new Date().getTime() < startTime + milliSeconds);
}



