
function regUser() {
    let firstName = $("#firstName").val();
    let secondName = $("#secondName").val();
    let email = $("#email").val();
    let login = $("#login").val();
    let pass = $("#pass").val();

    $.ajax({
        url: '/saveUser',
        method: 'POST',
        data: JSON.stringify({
            firstName: firstName,
            secondName: secondName,
            email: email,
            login: login,
            password: md5(pass)
        })
    }).done(function(data) {
        $("#displayed-data").text(data.message);
        if (!data.error) {
            sleep(500);
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


