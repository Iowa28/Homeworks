package inf6;

public class Player {
	private int armorPoint;
	private int healPoint;
	private String nickname;
	
	public Player(int ap, int hp, String n) {
		this.armorPoint = ap;
		this.healPoint = hp;
		this.nickname = n;
	}
	
	public void play() {
		System.out.println("Auuuuur, where is my silver 5?");
	}

	public int getHealPoint() {
		return healPoint;
	}

	public void setHealPoint(int healPoint) {
		this.healPoint = healPoint;
	}

	public int getArmorPoint() {
		return armorPoint;
	}

	public void setArmorPoint(int armorPoint) {
		this.armorPoint = armorPoint;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
