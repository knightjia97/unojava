package uno;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.Scanner;

public class Game {

	public static int turncount = 0;// global turn counter

	public static void main(String[] args) {
		ArrayList<Card> deck = new ArrayList<Card>();
		ArrayList<Card> pile = new ArrayList<Card>(); // empty pile
		String[] symbol = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		String[] color = { "red", "yellow", "green", "blue" };

		int id = 1;
		// for (int i = 0; i <= 9; i++) {
		// deck.add(new Card("red", symbol[i],));
		// deck.add(new Card("blue", symbol[i]));
		// deck.add(new Card("green", symbol[i]));
		// deck.add(new Card("yellow", symbol[i]));
		// }

		for (int i = 0; i <= 3; i++) {
			deck.add(new Card(color[i], "0", id));
			id++;
			deck.add(new Card(color[i], "1", id));
			id++;
			deck.add(new Card(color[i], "2", id));
			id++;
			deck.add(new Card(color[i], "3", id));
			id++;
			deck.add(new Card(color[i], "4", id));
			id++;
			deck.add(new Card(color[i], "5", id));
			id++;
			deck.add(new Card(color[i], "6", id));
			id++;
			deck.add(new Card(color[i], "7", id));
			id++;
			deck.add(new Card(color[i], "8", id));
			id++;
			deck.add(new Card(color[i], "9", id));
			id++;
			deck.add(new Skip(color[i], "Skip", id));
			id++;
			deck.add(new Reverse(color[i], "Reverse", id));
			id++;
			deck.add(new DrawTwo(color[i], "DrawTwo", id));
			id++;

		}

		deck.add(new Wild("", "Wild", id));
		id++;
		deck.add(new Wild("", "Wild", id));
		id++;
		deck.add(new WildDrawFour("", "WildDrawFour", id));
		id++;
		deck.add(new WildDrawFour("", "WildDrawFour", id));

		// Collections.shuffle(deck);

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("KEE1"));
		players.add(new Player("LEE2"));
		players.add(new Player("PEE3"));
		players.add(new Player("ooo4"));

		// Card tmp = deck.remove(0);
		// System.out.println("tmp content:"+tmp); //testing remove ( add card
		// into player hand)
		players.get(0).addCard(deck.remove(0));
		players.get(0).addCard(deck.remove(0));
		players.get(0).addCard(deck.remove(0));
		players.get(0).addCard(deck.remove(0));
		players.get(0).addCard(deck.remove(0));
		players.get(0).addCard(deck.remove(0));
		players.get(0).addCard(deck.remove(0));
		players.get(0).addCard(deck.remove(0));
		System.out.println("before playing card into pile\n" + players.toString());

		pile.add(deck.remove(0));
		System.out.println("TOP CARD IN PILE:" + pile.toString());

		Scanner sc = new Scanner(System.in);
		int drawcard;
		String rubbish;
		boolean validcardFlag = false;

		while (validcardFlag == false) {    //check valid card , if true, proceed || if false, loop until valid
			System.out.println("Enter your card to draw");
			drawcard = sc.nextInt();

			if (players.get(0).drawCard(drawcard, pile.get(pile.size() - 1))) {
				pile.add(players.get(0).removeCard(drawcard - 1));
				validcardFlag = true;
			} else
				rubbish = sc.nextLine();
		}
		
		// //top card of the pile
		System.out.println("Pile contents:" + pile.toString());

		// players.get(0).addCard(deck.remove(34));

		// Collections.sort(players.get(0).getHandcard());

		// class CustomComparator implements Comparator<Card> {
		// @Override
		// public int compare(Card o1, Card o2) {
		// return o1.getId().compareTo(o2.getId());
		// }
		// }
		// Collections.sort(Database.arrayList, new CustomComparator());
		// Collections.sort(Database.arrayList,(o1, o2) ->
		// o1.getId().compareTo(o2.getId()));

		System.out.println("after playing card into pile\n" + players.toString());
		System.out.println("Pile contents after playing:" + pile.toString());

		// boolean flagrev = false;
		// int i = 0;
		// while (/* END GAME */i < 10) {
		// System.out.println(players.get(turncount).getName());
		// //if (i == 5)
		// // flagrev = false;
		//
		// if (flagrev == true) {
		// if(i==0)turncount++;//test skip
		// if (turncount >= 3)//player.size()-1
		// turncount-=4;
		// turncount++;
		// }
		//
		// else {
		// if(i==2)turncount--; //test reverse skip
		// if (turncount <= 0)
		// turncount+= 4;//+ player.size()
		//
		// turncount--;
		// }
		// i++;
		// }

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
