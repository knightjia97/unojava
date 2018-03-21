package uno;

public class Card {
	private String color;
	private String symbol;

	public Card(String c, String s) {
		this.color = c;
		this.symbol = s;
	}

	public String getColor() {
		return color;
	}

	public String getSymbol() {
		return symbol;
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
		return "Color: '" + this.color + "', Symbol: '" + this.symbol + "\n";
	}

}
