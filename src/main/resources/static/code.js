console.log("Sudoku!");

var client;

var playerID = "";
var gameID = "";

var selected_x = -1;
var selected_y = -1;

// Register player with name via POST request
function registerPlayer() {
	const request = new XMLHttpRequest();
	const url = "/app/register"
	request.open("POST", url, true);
	request.setRequestHeader("Content-Type", "application/json");
	request.send(JSON.stringify({
		"PlayerName" : document.getElementById("registerName").value
	}));

	request.onreadystatechange = (event) => {
		if(request.readyState == 4) {
			// Get PlayerID and save it
			const responseData = JSON.parse(request.responseText);
			playerID = responseData["Data"]["PlayerID"];
			console.log(playerID);

			// client = Stomp.over(new SockJS("/websocket"));
			// client.connect({}, function (frame) {
			// 	client.subscribe("/games", function (message) {showGame(message)});
			// });

			// Hide registration and show the games
			document.getElementById("registration").style.visibility = "hidden";
			document.getElementById("games").style.visibility = "visible";
		}
	}
}

function createGame() {
	var row = document.getElementById("gamesTableBody").insertRow(-1);
	row.insertCell(0).innerHTML = "1";
	row.insertCell(1).innerHTML = "2";
	row.insertCell(2).innerHTML = "3";
	row.insertCell(3).innerHTML = "4";

	const request = new XMLHttpRequest();
	const url = "/app/createGame"
	request.open("POST", url, true);
	request.setRequestHeader("Content-Type", "application/json");
	JSONdata = JSON.stringify({
		"PlayerID" : playerID,
		"GameName" : document.getElementById("gameName").value,
		"Difficulty" : Math.max(Math.ceil(document.getElementById("gameDifficulty").value/10)*10, 0)
	});
	request.send(JSONdata);
	console.log(JSONdata);
	console.log(playerID);

	request.onreadystatechange = (event) => {
		if(request.readyState == 4) {
			// Get PlayerID and save it
			const responseData = JSON.parse(request.responseText);
			var gameID = responseData["gameID"];
			console.log(gameID);
		}
	}
}

function showGame(message) {
	const data = JSON.parse(message.body);
	console.log("SHOW GAME CALLED");
	console.log(data);
}

// $('#connect').click(function() {
// 	client = Stomp.over(new SockJS('/chat'));
// 	client.connect({}, function (frame) {
// 		$("#connect").prop("disabled", connected);
// 		client.subscribe('/topic/messages', function (message) {
// 			showMessage(JSON.parse(message.body));
// 		});
// 	});
// });

function fieldClick(id) {
	if(selected_x != -1 && selected_y != -1) {
		document.getElementById(getFieldID()).style.borderWidth = "0px"
	}
	document.getElementById(id).style.borderWidth = "5px";
	selected_x = parseInt(id[1]);
	selected_y = parseInt(id[3]);
}

function getFieldID() {
	return "x" + selected_x + "y" + selected_y;
}

document.onkeydown = function(evt) {
    evt = evt || window.event;
	if(parseInt(evt.key) > 0 && parseInt(evt.key) < 10) {
		if(selected_x != -1 && selected_y != -1) {
			console.log(selected_x + " " + selected_y + " " + evt.key);
			document.getElementById(getFieldID()).innerHTML = evt.key;
			document.getElementById(getFieldID()).style.borderWidth = "0px"
			selected_x = -1;
			selected_y = -1;
		}
	}
};
