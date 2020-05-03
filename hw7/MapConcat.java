package inf5;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MapConcat {
	public static void main(String[] args) {
		MapR<String, Integer> mapr = new MapR<>();
		mapr.put("aaa", 1);
		mapr.put("bbb", 2);
		mapr.put("ccc", 3);
		mapr.put("ddd", 4);
		
		String n = (String) mapr.keySet().stream().collect(Collectors.joining());
		System.out.println(n);
	}
}
