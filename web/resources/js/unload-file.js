function sendToServer() {
    let idTournament = $("#idTournament").val();
    $.ajax({
        url: '/resultTable',
        method: 'POST',
        data: JSON.stringify({
            idTournament: idTournament,
            message: ""
        })
    }).done(function(data) {
        $("#displayed-data").text(data.message);
    });
}

