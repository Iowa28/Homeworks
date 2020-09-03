package inf4;

public class Test {
	public static void main(String[] args) {
		EndlessArray<String> arr = new EndlessArray<>(new String[0]);
		//arr.add(null);
		arr.add("hh");
		arr.add("heey");
		arr.add("heeey");
		arr.add("heeeey");
		arr.add("heeeeey");
		arr.add(null);
		arr.add(null);
		
		
		EndlessArrayIterator<String> eai = new EndlessArrayIterator<>(arr);
		while(eai.hasNext()) {
			System.out.println(eai.next());
		}
		//that works!
	}
}
