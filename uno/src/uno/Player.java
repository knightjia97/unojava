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
		return name + "\n";
	}
	
	public int getCount(){
		return this.handcount;
	}

	public String toString() {
		return "Name: '" + this.name + "', cards: '" + this.getHandcard() + "\n";
	}

	// testing
	public ArrayList<Card> getHandcard() {
		return handcard;
	}

	public void setHandcard(ArrayList<Card> handcard) {
		this.handcard = handcard;
	}
	
	public boolean challengeCheck(Card o){	
		boolean checking=false;
		for (int i=0;i<handcard.size();i++){
			if(handcard.get(i).equals(o.getColor()) || handcard.get(i).equals(o.getSymbol())){
				checking=true;
				break;
			}
		}
		return checking;
		
	}

	public boolean playCard(int i, Card o) {
		if (i >= 0 && i <= handcard.size()) { //check valid choice
			if (handcard.get(i- 1).isValidCard(o)) //match color or symbol
				return true;
			else {
				System.out.println("Choose a valid card that matches the color or symbol");
				return false;
			}
		} else
			System.out.println("Enter valid hand card choice");
		return false;
	}
}
