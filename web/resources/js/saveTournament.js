function sendToServer() {
    let name = $("#name").val();
    let organizerId = $("#organizerId").val();
    let startDate = $("#startDate").val();
    $.ajax({
        url: '/saveTournament',
        method: 'POST',
        data: JSON.stringify({
            name: name,
            organizerId: organizerId,
            startDate: startDate
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