selected_x = -1;
selected_y = -1;

function fieldClick(id) {
	//console.log("huhu! " + x.toString() + " " + y.toString()) ;
	//document.getElementById(id).style.backgroundColor = "green";
	document.getElementById(id).style.borderWidth = "5px";
	selected_x = parseInt(id[1]);
	selected_y = parseInt(id[3]);
}

document.onkeydown = function(evt) {
    evt = evt || window.event;
	if(parseInt(evt.key) > 0 && parseInt(evt.key) < 10) {
		if(selected_x != -1 || selected_y != -1) {
			console.log(selected_x + " " + selected_y + " " + evt.key);
			document.getElementById("x" + selected_x + "y" + selected_y).innerHTML = evt.key;
			document.getElementById("x" + selected_x + "y" + selected_y).style.borderWidth = "0px"
			selected_x = -1;
			selected_y = -1;
		}
	}
};
