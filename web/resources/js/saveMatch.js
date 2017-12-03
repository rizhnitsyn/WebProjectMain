function sendToServer() {
    let matchDateTime = $("#matchDateTime").val();
    let matchType = $("#matchType").val();
    let firstTeamId = $("#firstTeam").val();
    let secondTeamId = $("#secondTeam").val();
    let tournamentId = $("#tournamentId").val();
    $.ajax({
        url: '/saveMatch',
        method: 'POST',
        data: JSON.stringify({
            matchDateTime: matchDateTime,
            matchType: matchType,
            firstTeamId: firstTeamId,
            secondTeamId: secondTeamId,
            tournamentId: tournamentId
        })
    }).done(function(data) {
        $("#displayed-data").text(data.message);
        if (!data.error) {
            redirect(data.redirectPath);
        }
    });
}

function redirect(path) {
    window.location.href = path;
}