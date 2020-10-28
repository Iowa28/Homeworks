document.onreadystatechange = function(){

	let headerColor = "bg-success";
	let newColor;
	let header = document.getElementById("header");
	let index = 1;

	function changeColor() {
		console.log("index: " + index);
		newColor = colors[index];

		//changing class to change color
		header.classList.remove(headerColor);
		header.classList.add(newColor);

		headerColor = newColor;
	}

	let buttonRed;
	let buttonGreen;
	let buttonBlue;
	let buttonNext;

	let names = ["red", "green", "blue", "next"];
	let colors = ["bg-danger", "bg-success", "bg-primary"];
	let buttons = [buttonRed, buttonGreen, buttonBlue, buttonNext];
	

	for (let i = 0; i < 4; i++) {
		buttons[i] = document.getElementById(names[i]);
		buttons[i].addEventListener("click", function() {
			if (i != 3) {
				index = i;
			} else {
				index = (index + 1) % 3;
			}
			changeColor();
		});
	}
};