console.log("Sudoku!");

var client = null;

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

			// Hide registration and show the games
			document.getElementById("registration").style.visibility = "none";
			document.getElementById("games").style.visibility = "visible";
		}
	}
}

function createGame() {
	const request = new XMLHttpRequest();
	request.open("POST", "/app/createGame", true);
	request.setRequestHeader("Content-Type", "application/json");
	JSONdata = JSON.stringify({
		"PlayerID" : playerID,
		"GameName" : document.getElementById("gameName").value,
		"Difficulty" : Math.max(Math.ceil(document.getElementById("gameDifficulty").value), 0)
	});
	request.send(JSONdata);
	request.onreadystatechange = (event) => {
		if(request.readyState == 4) { refreshGames(); }
	}
}

function refreshGames() {
	const request = new XMLHttpRequest();
	request.open("GET", "/app/getGamesList", true);
	request.send();

	request.onreadystatechange = (event) => {
		if(request.readyState == 4) {
			const responseJSON = JSON.parse(request.responseText);
			console.log(responseJSON);
			document.getElementById("gamesTableBody").innerHTML = "";
			for(let index in responseJSON["Data"]["Games"]) {
				let game = responseJSON["Data"]["Games"][index];
				console.log(game);
				var row = document.getElementById("gamesTableBody").insertRow(-1);
				row.insertCell(0).innerHTML = game["GameID"];
				row.insertCell(1).innerHTML = game["GameName"];
				row.insertCell(2).innerHTML = game["MasterID"];
				row.insertCell(3).innerHTML = "<button onClick='joinGame(" + game["GameID"] + ")'>Join</button>";
			}
		}
	}
}

function showGame(message) {
	document.getElementById("board").style.visibility = "visible";
	const json = JSON.parse(message.body);
	for(let index in json["Data"]["Fields"]) {
		let field = json["Data"]["Fields"][index];
		document.getElementById("x" + field["X"] + "y" + field["Y"]).innerHTML = field["Value"];
		document.getElementById("x" + field["X"] + "y" + field["Y"]).style.background = field["Color"];
	}
}

function joinGame(id) {
	gameID = id;
	const request = new XMLHttpRequest();
	request.open("POST", "/app/game/" + gameID + "/join", true);
	request.setRequestHeader("Content-Type", "application/json");
	JSONdata = JSON.stringify({
		"PlayerID" : playerID,
		"GameID" : gameID.toString()
	});
	request.send(JSONdata);

	request.onreadystatechange = (event) => {
		if(request.readyState == 4) {
			if(client == null) {
				client = Stomp.over(new SockJS("/websocket"));
				client.connect({}, function (frame) {
					client.subscribe("/game/" + gameID + "/update", function (message) {showGame(message)});
				});
			}

			document.getElementById("games").style.visibility = "none";
			document.getElementById("ready").style.visibility = "visible";
		}
	}
}

function readyForGame() {
	const request = new XMLHttpRequest();
	request.open("POST", "/app/game/" + gameID + "/ready", true);
	request.setRequestHeader("Content-Type", "application/json");
	JSONdata = JSON.stringify({
		"PlayerID" : playerID,
		"GameID" : gameID.toString()
	});
	request.send(JSONdata);

	request.onreadystatechange = (event) => {
		if(request.readyState == 4) {
			document.getElementById("ready").style.visibility = "none";
		}
	}
}

function fieldClick(id) {
	if(selected_x != -1 && selected_y != -1) {
		document.getElementById(getFieldID()).style.boxShadow = "none";
	}
	x_readout = parseInt(id[1]);
	y_readout = parseInt(id[3]);
	if(selected_x == x_readout && selected_y == y_readout) {
		document.getElementById(id).style.boxShadow = "none";
		selected_x = -1;
		selected_y = -1;
	}else{
		document.getElementById(id).style.boxShadow = "0 0 0.3em black";
		selected_x = x_readout;
		selected_y = y_readout;
	}
}

function getFieldID() {
	return "x" + selected_x + "y" + selected_y;
}

document.onkeydown = function(evt) {
    evt = evt || window.event;
	if(parseInt(evt.key) > 0 && parseInt(evt.key) < 10) {
		if(selected_x != -1 && selected_y != -1) {
			console.log(selected_x + " " + selected_y + " " + evt.key);
			//document.getElementById(getFieldID()).innerHTML = evt.key;
			document.getElementById(getFieldID()).style.boxShadow = "none";

			if(client != null) {
				client.send("/app/game/" + gameID + "/move", {}, JSON.stringify({
					
				}));
			}

			selected_x = -1;
			selected_y = -1;
		}
	}
};
