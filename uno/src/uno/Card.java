package uno;

public class Card implements Comparable<Card>{
	private String color;
	private String symbol;
	private int id;

	public Card(String c, String s, int id) {
		this.color = c;
		this.symbol = s;
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public String getSymbol() {
		return symbol;
	}
	
	public int getId(){
		return id;
	}

	public void setColor(String c) {
		this.color = c;
		
	}

	public void setSymbol(String s) {
		this.symbol = s;
	}
	
	public void action() {
	}

	public String toString() {
		return "Color: '" + this.color + "', Symbol: '" + this.symbol + ", ID: " + this.id + "\n";
	}

	@Override
	public int compareTo(Card o) {
		return this.getId()-o.getId();
	}

	
}
