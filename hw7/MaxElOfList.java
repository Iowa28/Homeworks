package inf6;

import java.util.ArrayList;
import java.util.Optional;

public class MaxElOfList {
	public static void main(String[] args) {
		ArrayList<Integer> coll1 = new ArrayList<>(); // first array
		coll1.add(14);
		coll1.add(9);
		coll1.add(17);
		coll1.add(4);
		coll1.add(25);
		
		ArrayList<Integer> coll2 = new ArrayList<>(); //second array
		coll2.add(26);
		coll2.add(16);
		coll2.add(33);
		coll2.add(40);
		coll2.add(21);
		
		Optional<Integer> max = coll1.stream().max((Integer s1, Integer s2) -> {return s1 - s2;}); // searching max number
		Integer maxNum = max.get();
		
		coll2.stream().filter(o -> o > maxNum).forEach(System.out::println);
	}
}
