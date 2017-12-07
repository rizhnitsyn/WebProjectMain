function sendToServer() {
    let loginValue = $("#name").val();
    let passValue = $("#pass").val();
    let randomNumber = "";

    $.ajax({
        url: '/getRandom',
        method: 'POST',
        data: JSON.stringify({
            login: "",
            password: "",
            randomNumber: ""
        })
    }).done(function(data) {
        randomNumber = data.randomNumber;

        $.ajax({
            url: '/login',
            method: 'POST',
            data: JSON.stringify({
                login: loginValue,
                password: md5(md5(passValue) + randomNumber),
                randomNumber: randomNumber
            })
        }).done(function(data) {
            $("#displayed-data").text(data.message);
            if (data.authorized) {
                sleep(500);
                redirect(data.redirectPath);
            }
        });
    })

    // $.ajax({
    //     url: '/login',
    //     method: 'POST',
    //     data: JSON.stringify({
    //         login: loginValue,
    //         password: md5(md5(passValue) + randomNumber),
    //         randomNumber: ""
    //     })
    // }).done(function(data) {
    //     $("#displayed-data").text(data.message);
    //     if (data.authorized) {
    //         sleep(1000);
    //         redirect(data.redirectPath);
    //     }
    // });
}
function redirect(path) {
    window.location.href = path;
}


function sleep (milliSeconds) {
    let startTime = new Date().getTime();
    while (new Date().getTime() < startTime + milliSeconds);
}


