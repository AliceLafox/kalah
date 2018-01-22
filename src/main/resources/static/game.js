var currentGame;

function getNewGameApiUrl() {
    $.urlParam = function (name) {
        var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
        if (results != null)
            return results[1] || 0;
        else return null;
    };
    var seeds = $.urlParam("seeds");
    console.log(seeds);

    return seeds == null ? "api/v1/game/newGame" : "api/v1/game/newGame?seeds=" + seeds;
}

$(document).ready(function () {

    $.get(getNewGameApiUrl(), function (game) {
        console.log(game);
        currentGame = game;
        drawGame(currentGame);
    });

    $.postJSON = function (url, data, onSuccess, onError) {
        return jQuery.ajax({
            'type': 'POST',
            'url': url,
            'contentType': 'application/json',
            'data': JSON.stringify(data),
            'dataType': 'json',
            'success': onSuccess,
            'error': onError
        });
    };
});

function checkEndGame() {
    if (currentGame.winner) {
        var winMessage = "The Game Is Over. The Winner Is :" + currentGame.winner;
        addAlert(winMessage, "alert-success");
    }
}

function nextGame(index) {
    if ($("#house" + index).hasClass("disabled")) return;
    currentGame.selectedHouse = index;

    $.postJSON("api/v1/game/nextTurn", currentGame, function (game) {
        removeAlert();
        currentGame = game;
        drawGame(currentGame);
        checkEndGame();
    }, function (data) {
        console.log(data);
        addAlert(data.responseJSON.message, "alert-danger");
    });
}

function drawGame(game) {
    if (game.player == "GREEN") {
        $(".redhouse").addClass("disabled");
        $(".greenhouse").removeClass("disabled");
    } else {
        $(".greenhouse").addClass("disabled");
        $(".redhouse").removeClass("disabled");
    }
    $.each(game.houses, function (index, value) {
        $("#house" + index).text(value);
        if (value == 0) {
            $("#house" + index).addClass("disabled");
        }
    });

}

function addAlert(text, css) {
    $("#alertMessage").addClass("alert " + css);
    $("#alertMessage").text(text);
}

function removeAlert() {
    $("#alertMessage").removeClass("alert alert-danger");
    $("#alertMessage").text("");
}