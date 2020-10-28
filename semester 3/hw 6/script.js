document.onreadystatechange = function() {

	eye1 = document.getElementById("eye1");
	eye2 = document.getElementById("eye2");

	border = 500;
	eyeRadius = 30;

	onmousemove = function(e) {

			x = e.clientX;
			y = e.clientY;
			xError = 0;
			yError = 0;
			eye1xError = 0;
			eye1yError = 0;

			if (x <= border && y <= border) {
				x = -(border - x);
				y = border - y;
				xError = 3;
				yError = -2;
				eye1xError = 2;
				eye1yError = -2;

			} else if (x > border && y < border) {
				x = x - border;
				y = border - y;
				xError = -3;
				yError = -2;
				eye1yError = -2;
				
			} else if (x < border && y > border) {
				x = -(border - x);
				y = -(y - border);
				xError = 3;
				yError = 3;
				eye1xError = 2;
				eye1yError = 2;

			} else {
				x = x - border;
				y = -(y - border);
				xError = -3;
				yError = 3;
				eye1yError = 2;

			}

			z = Math.sqrt(x*x + y*y);

			eyeX = (eyeRadius * x) / z;
			eyeY = (eyeRadius * y) / z;

			eye1.style.left = eyeX + eye1xError + "px";
			eye1.style.bottom = eyeY + eye1yError + "px";

			eye2.style.left = eyeX + xError + "px";
			eye2.style.bottom = eyeY + yError + "px";
		}
};