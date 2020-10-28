document.onreadystatechange = function() {
	borderRight = 1175;
	borderUp = 615;

	gravity = 0;
	a = .4;
	impulse = -15;
	dx = 1;
	nav = -1;

	ball = document.getElementById("ball");
	ballX = 75;
	ballY = 0;

	button = document.getElementById("button");
	button.addEventListener("click", function() {
		gravity = 6;
		gravity *= -1;
		a = -.2;
		impulse = -15;
		dx += 8;
	});

	let timer = setInterval(function() {
		CalculatePhysics();
	}, 30);

	function CalculatePhysics() {
		if (ballX < borderRight && dx > 0) {

			dx /= 2;
			dx *= -1;
		}
		if (ballX < 0 && dx < 0) {
			dx /= 2;
			dx *= -1;
			
		}
		if (ballX >= borderRight) {
			dx *= -1;
			dx /= 2;
			nav = 1;
		}
		if (ballX <= 0) {
			nav = -1;
			dx *= -1;
		}
		ballX += nav*dx;

		if (ballY < borderUp || a < 0) {
			if (a != .4) {
				a += .075;
			}

			ballY += gravity;
			gravity += a;
			
		} else {
			if (ballY >= borderUp) {
				if (Math.abs(impulse) > .5) {

					impulse /= 2;
					
					AddForce(a/2);
				}
			}
		}

		ball.style.top = ballY + "px";
		ball.style.left = ballX + "px";
	}

	function AddForce(force) {
		gravity = -gravity;
		gravity /= 2;
		a = -force;
	}
}