function sendToServer() {
    let matchDateTime = $("#matchDateTime").val();
    let matchType = $("#matchType").val();
    let firstTeamId = $("#firstTeamId").val();
    let secondTeamId = $("#secondTeamId").val();
    $.ajax({
        url: '/saveMatch',
        method: 'POST',
        data: JSON.stringify({
            matchDateTime: matchDateTime,
            matchType: matchType,
            firstTeamId: firstTeamId,
            secondTeamId: secondTeamId
        })
    }).done(function(data) {
        $("#displayed-data").text(data.message);
        let path = data.redirect
        if (path.length > 0) {
            redirect(path);
        }
    });
}

function redirect(path) {
    window.location.href = path;
}