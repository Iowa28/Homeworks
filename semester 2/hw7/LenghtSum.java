package inf6;

import java.util.ArrayList;

public class LenghtSum {
	public static void main(String[] args) {
		ArrayList<String> al = new ArrayList<>();
		// Получить сумму длин строк коллекции, которые длиннее 5-ти символов
		al.add("aaaaa");
		al.add("bbbbbbbbbb");
		al.add("cccc");
		al.add("ddddddd");
		long n = al.stream().filter((x) -> x.length() > 5).count(); // searching the number of rows that are longer than 5
		al.sort((String el1, String el2) -> {return el2.length() - el1.length();}); // descending sorting
		int sumOfElements = al.stream().limit(n).mapToInt((x) -> x.length()).sum(); // summing
		System.out.println(sumOfElements);
	}
}
