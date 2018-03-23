package uno;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
	private String name;
	private ArrayList<Card> handcard;
	
	public Player(String s, ArrayList<Card> c){
		this.name = s;
		this.setHandcard(c);
	}
	
	public Player(String s){
		this.name=s;
		this.setHandcard(new ArrayList<Card>());
	}
	
	public Card removeCard(int i){
		return this.getHandcard().remove(i);	
	}
	
	public void addCard(Card c){
		this.getHandcard().add(c);
	}
	
	public String getName(){
		return name + "\n";
	}	
	
	public String toString() {
		return "Name: '" + this.name + "', cards: '" + this.getHandcard() + "\n";
	}

	//testing
	public ArrayList<Card> getHandcard() {
		return handcard;
	}

	public void setHandcard(ArrayList<Card> handcard) {
		this.handcard = handcard;
	}
}
