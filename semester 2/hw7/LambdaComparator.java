package inf6;

import java.util.ArrayList;

public class LambdaComparator {
	public static void main(String[] args) {
		ArrayList<Player> al = new ArrayList<>();
		int n = 5;
		al.add(new Player(0, 15, "I_am_noob"));
		al.add(new Player(100, 87, "Vitalik2008"));
		al.add(new Player(99, 75, "Admin"));
		al.add(new Player(100, 100, "Killer007"));
		
		for (int i = 0; i < al.size(); i++) { //print all players
			System.out.println(al.get(i).getNickname());
		}
		System.out.println();
		
		al.sort((Player p1, Player p2) -> {return p2.getArmorPoint() - p1.getArmorPoint();}); // sorting
		
		for (int i = 0; i < al.size(); i++) { // print all players
			System.out.println(al.get(i).getNickname());
		}
	}
}
