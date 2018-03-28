package uno;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
	private String name;
	private ArrayList<Card> handcard;
	private int handcount; // counting number of cards in hand

	public Player(String s, ArrayList<Card> c) {
		this.name = s;
		this.setHandcard(c);
	}

	public Player(String s) {
		this.name = s;
		this.setHandcard(new ArrayList<Card>());
	}

	public Card removeCard(int i) {
		this.handcount--;
		return this.getHandcard().remove(i);
	}

	public void addCard(Card c) {
		this.getHandcard().add(c);
		this.handcount++;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}

	public int getCount() {
		return this.handcount;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Name: " + this.name + "\n");
		str.append("Cards: \n");
		for(int i = 1; i <= this.getHandcard().size(); i++){
		str.append(i+". " + this.getHandcard().get(i-1));
		}
		return str.toString();
	}

	// testing
	public ArrayList<Card> getHandcard() {
		return handcard;
	}

	public void setHandcard(ArrayList<Card> handcard) {
		this.handcard = handcard;
	}

	public boolean handCheck(Card o) {
		boolean checking = false;
		for (int i = 0; i < handcard.size(); i++) {
			if (handcard.get(i).getColor().equals(o.getColor()) || handcard.get(i).getSymbol().equals(o.getSymbol()) ||
					handcard.get(i).getSymbol().equals("Wild")||handcard.get(i).getSymbol().equals("WildDrawFour")) {
				checking = true;
				break;
			}
			
			else if (handcard.get(i).getSymbol().equals("CopyCat") && !(o.getSymbol().equals("Wild")
					|| o.getSymbol().equals("WildDrawFour") || o.getSymbol().equals("Skip")
					|| o.getSymbol().equals("Reverse")
					|| o.getSymbol().equals("DrawTwo"))) { 
				checking = true;

			}
		}
		return checking;
	}
	
	public boolean challengeCheck(Card o) {
		boolean checking = false;
		for (int i = 0; i < handcard.size(); i++) {
			if (handcard.get(i).getColor().equals(o.getColor()) || handcard.get(i).getSymbol().equals(o.getSymbol())) {
				checking = true;
				break;
			}
		}
		return checking;
	}

	public boolean playCard(int i, Card o) {
		if (i >= 0 && i <= handcard.size()) { // check valid choice
			if (handcard.get(i - 1).isValidCard(o)) // match color or symbol
				return true;
			else if (handcard.get(i - 1).getSymbol().equals("CopyCat")) {
				if (o.getSymbol().equals("Wild") || o.getSymbol().equals("WildDrawFour")
						|| o.getSymbol().equals("Skip") || o.getSymbol().equals("DrawTwo")
						||o.getSymbol().equals("Reverse"))
				{

					System.out.println("You cannot play Copy Cat this round.");
					return false;
				}
				else
					return true;
			} else {
				System.out.println("Choose a valid card that matches the color or symbol");
				return false;
			}
		} else
			System.out.println("Enter valid hand card choice");
		return false;
	}
}
