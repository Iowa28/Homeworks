package inf5;

import java.util.ArrayList;

public class LambdaStringSet {
	public static void main(String[] args) {
		//MapSet<String> ms = new MapSet<>(new String[] {"asas", ", ", "x"});

		ArrayList<String> list = new ArrayList<String>();

		long m = list.stream().filter((x) -> {
			long d = x.chars().filter((y) -> y == 'о' | y == 'ы' | y == 'а' | y == 'ю' | y == 'э' | y == 'я' | y == 'и' | y == 'у' | y == 'е' | y == 'ё').count();
			if (d > 3) {return true;}
			return false;
		}).count();
		System.out.println(m);
	}
}
