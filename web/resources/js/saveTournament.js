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
        let path = data.redirect
        if (path.length > 0) {
            redirect(path);
        }
    });
}

function redirect(path) {
    window.location.href = path;
}