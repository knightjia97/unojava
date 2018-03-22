package uno;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
	public static void main(String[] args) {
		ArrayList<Card> deck = new ArrayList<Card>();
		String[] symbol = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		String[] color = { "red", "blue", "green", "yellow" };

		for (int i = 0; i <= 9; i++) {
			deck.add(new Card("red", symbol[i]));
			deck.add(new Card("blue", symbol[i]));
			deck.add(new Card("green", symbol[i]));
			deck.add(new Card("yellow", symbol[i]));
		}

		for (int i = 0; i <= 3; i++) {
			deck.add(new Reverse(color[i], "Reverse"));
			deck.add(new Skip(color[i], "Skip"));
			deck.add(new DrawTwo(color[i], "DrawTwo"));

		}

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("KEE1"));
		players.add(new Player("LEE2"));
		players.add(new Player("PEE3"));
		players.add(new Player("ooo4"));
		
		boolean flagrev = true;
		int i = 0;
		int turncount = 0;
		while (/* END GAME */i < 10) {
			System.out.println(players.get(turncount).getName());
			if(i==5)
				flagrev=false;
			
			if (flagrev==true)
			{
				if(turncount==3)
					turncount=-1;
				turncount++;
			}
			else
			{
			if(turncount==0)
				turncount=4;
			turncount--;
			}
				i++;
		}

		// Collections.shuffle(deck);
		// System.out.println(deck.toString());
	}
	/*
	 * deck.add(new Card ("red","6")); deck.add(new Card ("red","7"));
	 * System.out.println(deck.get(0).getColor() + deck.get(0).getSymbol() +
	 * " "); System.out.println(deck.get(1).getColor() + deck.get(1).getSymbol()
	 * + " " +"\n size:" +deck.size());
	 */
}
