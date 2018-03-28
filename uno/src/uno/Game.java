package uno;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.Scanner;

public class Game {

	public static boolean flagrev = false;
	public static boolean flagskip = false;
	public static boolean flagdrawtwo = false;
	public static boolean flagwilddrawfour = false;

	public static void main(String[] args) {
		//----------------------------ascii art drawing-----------------------------------------------
		int width=220;
		int height=25;
		
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setFont(new Font("SansSerif",Font.PLAIN,14));
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.drawString("WELCOME TO JAVA UNO GAME!", 3, 18);
		//----------------------------ascii art drawing-----------------------------------------------
		
		
		Scanner sc = new Scanner(System.in);
		int id = 1;                               //to declare id for each card
		int playcard;                             //card choice to play
		int playernum = 0;                        //number of players
		int challengeChoice = 0;
		String rubbish;
		boolean validPlayernum = false;
		boolean validPlayeraction = false;        //to validate the action for each player

		//----------------------------ascii art drawing-----------------------------------------------	
		for(int y=0;y<height;y++)
		{
			StringBuilder builder = new StringBuilder();
			
			for(int x = 0; x<width;x++){
				builder.append(image.getRGB(x, y)==-16777216 ? "@" : " ");
			}
			System.out.println(builder);
		}
		//----------------------------ascii art drawing-----------------------------------------------
		
		System.out.println("\n");
		
		// check valid player numbers
		while (validPlayernum == false) {
			System.out.println("Enter the number of players(Recommended 2-4 players): ");
			while (!sc.hasNextInt()) { //check for invalid input (not integers)
				System.out.println("That's not a number!");
				sc.nextLine();
			}
			playernum = sc.nextInt();
			rubbish = sc.nextLine();
			if (playernum >= 2 && playernum <= 4)
				validPlayernum = true;
		}

		// prompt players' names
		ArrayList<Player> players = new ArrayList<Player>();
		for (int i = 0; i < playernum; i++) {
			System.out.println("Enter player" + (i + 1) + " name: ");
			players.add(new Player(sc.nextLine()));
		}

		//intializing card deck, pile deck, and player array
		ArrayList<Card> deck = new ArrayList<Card>();
		ArrayList<Card> pile = new ArrayList<Card>(); // empty pile
		String[] symbol = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		String[] color = { "Red", "Yellow", "Green", "Blue" };

		// initializing card deck with id
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

		deck.add(new Wild("No Color", "Wild", id));
		id++;
		deck.add(new Wild("No Color", "Wild", id));
		id++;
		deck.add(new WildDrawFour("No Color", "WildDrawFour", id));
		id++;
		deck.add(new WildDrawFour("No Color", "WildDrawFour", id));
		id++;
		deck.add(new CopyCat("No Color", "CopyCat", id));//self implemented action cards, copies last played card
		id++;
		deck.add(new CopyCat("No Color", "CopyCat", id));//can only be played on normal cards to copy their property("color" and "symbol")
		id++;									//wild,wild draw four,reverse,skip are special cards(not normal cards)
		deck.add(new CopyCat("No Color", "CopyCat", id));
		id++;
		deck.add(new CopyCat("No Color", "CopyCat", id));
		
		Collections.shuffle(deck);

		pile.add(deck.remove(0));
		
		// distribute cards to players
		for (int i = 0; i < playernum; i++) {
			players.get(i).addCard(deck.remove(0));
			players.get(i).addCard(deck.remove(0));
			players.get(i).addCard(deck.remove(0));
			players.get(i).addCard(deck.remove(0));
			players.get(i).addCard(deck.remove(0));
		}

		// ------------------AFTER GAME INITIALIZATION------------------------

		boolean checkWinningCondition = false;
		boolean validcardFlag = false;

		int i = 0;
		int choice = 0;

		// ------switch case for action cards for first card drawn from
		// deck------
		
		boolean firstpilecardflag = true;
		
		while (firstpilecardflag) {

			switch (pile.get(pile.size() - 1).getSymbol()) {
			case "Reverse":
				pile.get(pile.size() - 1).action();
				firstpilecardflag = false;
				break;
			case "Skip":
				pile.get(pile.size() - 1).action();
				firstpilecardflag = false;
				break;
			case "DrawTwo":
				pile.get(pile.size() - 1).action();
				firstpilecardflag = false;
				break;
			case "Wild":
				System.out.println("Replacing Wild Card on top");
				pile.add(deck.remove(0));
				System.out.println("\n\nTOP CARD IN PILE : " + pile.get(pile.size() - 1).toString());
				break;
			case "WildDrawFour":
				System.out.println("Replacing Wild Draw Four Card on top");
				pile.add(deck.remove(0));
				System.out.println("\n\nTOP CARD IN PILE : " + pile.get(pile.size() - 1).toString());
				break;
			case "CopyCat":
				System.out.println("Replacing Copy Cat Card on top");
				pile.add(deck.remove(0));
				System.out.println("\n\nTOP CARD IN PILE : " + pile.get(pile.size() - 1).toString());
				break;
				
			default:
					firstpilecardflag = false;
			}
		}
		// ------switch case for action cards for first card on pile------

		// normal game direction
		if (flagrev == false) {
			// skip function
			if (flagskip == true) {
				i++;
				System.out.print(i);
				flagskip = false;
			}
			if (i >= playernum)
				i = i - playernum;

			if (flagdrawtwo == true) {
				players.get(i).addCard(deck.remove(0));
				players.get(i).addCard(deck.remove(0));
				System.out.println("(Draw Two action)" + players.get(i).getName() + " is skipped ");
				i++;
				flagdrawtwo = false;
			}

			// reverse game direction
		} else if (flagrev == true) {
			// skip function
			if (flagskip == true) {
				i--;
				flagskip = false;
			}
			if (i < 0)
				i = i + playernum;

		}

		while (checkWinningCondition == false) {
			validcardFlag = false;
			System.out.println(new String(new char[50]).replace("\0", "\r\n"));

			// game loop

			// looping back to player1
			if (i >= playernum)
				i = i - playernum;

			// looping back to player1 in reverse direction
			if (i < 0)
				i = i + playernum;

			System.out.println("\n\nTOP CARD IN PILE : " + pile.get(pile.size() - 1).toString());
			System.out.println(players.get(i).getName() + "'s turn: ");
			Collections.sort(players.get(i).getHandcard());
			System.out.println("Your hand card:\n" + players.get(i).toString()); // display
																					// player
																					// card
			
			while (validPlayeraction == false) {
				System.out.println("Choose your action: 1)Play a card ");
				System.out.println("                    2)Draw a card ");
				while (!sc.hasNextInt()) { //check for invalid inputs(not integers)
					System.out.println("That's not a number!");
					sc.nextLine();
					System.out.println("Choose your action: 1)Play a card ");
					System.out.println("                    2)Draw a card ");
				}
				choice = sc.nextInt();
				rubbish = sc.nextLine();
				
				if(choice==1){
				if(players.get(i).handCheck(pile.get(pile.size()-1))) //checking player's hand to check for any valid cards matching top pile
					validPlayeraction = true ;
				else{
						System.out.println("Unplayable handcards, you must draw a card");
					}
				}
				else
					validPlayeraction = true;
			}

			if (choice == 1) {
				
				// ---------------Choose card from deck--------------------
				while (validcardFlag == false) { // check valid card , if true,proceed || if false, loop
													// until valid
					System.out.println("Enter your card to play");
					while (!sc.hasNextInt()) { //check for invalid input (not integers)
						System.out.println("That's not a number!");
						sc.nextLine();
						System.out.println("Enter your card to play");
					}
					playcard = sc.nextInt();
					rubbish = sc.nextLine();

					// playing card out of hand
					if (players.get(i).playCard(playcard, pile.get(pile.size() - 1))) { // check (color or symbol and valid card choice
						pile.add(players.get(i).removeCard(playcard - 1));
						validcardFlag = true;

						// ------switch case for action cards------
						switch (pile.get(pile.size() - 1).getSymbol()) {
						case "Reverse":
							pile.get(pile.size() - 1).action();
							break;
						case "Skip":
							pile.get(pile.size() - 1).action();
							break;
						case "DrawTwo":
							pile.get(pile.size() - 1).action();
							break;
						case "Wild":
							pile.get(pile.size() - 1).action();
							break;
						case "WildDrawFour":
							pile.get(pile.size() - 1).action();
							break;
						case "CopyCat":
							pile.get(pile.size()-1).setColor(pile.get(pile.size() - 2).getColor());
							pile.get(pile.size()-1).setSymbol(pile.get(pile.size() - 2).getSymbol());
							break;

						}
						// ------switch case for action cards------

						if (players.get(i).getCount() == 0)
							checkWinningCondition = true;

						// normal game direction
						if (flagrev == false) {
							// skip function
							if (flagskip == true) {
								i++;
								flagskip = false;
							}
							// draw two function
							if (flagdrawtwo == true) {
								i++;
								if (i >= playernum)
									i = i - playernum;
								players.get(i).addCard(deck.remove(0));
								players.get(i).addCard(deck.remove(0));
								System.out.println(players.get(i).toString());
								flagdrawtwo = false;
							}
							// wild draw four
							if (flagwilddrawfour == true) {
								i++;
								if (i >= playernum)
									i = i - playernum;
								System.out.println(players.get(i).getName()+"'s turn");
								System.out.println("1)Challenge or 2)no:");
								challengeChoice = sc.nextInt();
								rubbish = sc.nextLine();

								if (challengeChoice == 1) {
									i--;
									if (i < 0)
										i = i + playernum;
									if (players.get(i).challengeCheck(pile.get(pile.size() - 2))) {//challenge checking
										players.get(i).addCard(deck.remove(0));
										players.get(i).addCard(deck.remove(0));
										players.get(i).addCard(deck.remove(0));
										players.get(i).addCard(deck.remove(0));
									}

									else {
										i++;
										if (i >= playernum)
											i = i - playernum;
										players.get(i).addCard(deck.remove(0));
										players.get(i).addCard(deck.remove(0));
										players.get(i).addCard(deck.remove(0));
										players.get(i).addCard(deck.remove(0));
										players.get(i).addCard(deck.remove(0));
										players.get(i).addCard(deck.remove(0));
									}
								} else if (challengeChoice == 2) {
									players.get(i).addCard(deck.remove(0));
									players.get(i).addCard(deck.remove(0));
									players.get(i).addCard(deck.remove(0));
									players.get(i).addCard(deck.remove(0));
								}
								Collections.sort(players.get(i).getHandcard());
								System.out.println(players.get(i).toString());
								flagwilddrawfour = false;
							}
							i++; // player turn counter
						}
						// reverse game direction
						else if (flagrev == true) {
							// skip function
							if (flagskip == true) {
								i--;
								flagskip = false;
							}
							// draw two function
							if (flagdrawtwo == true) {
								i--;
								if (i < 0)
									i = i + playernum;
								players.get(i).addCard(deck.remove(0));
								players.get(i).addCard(deck.remove(0));
								System.out.println(players.get(i).toString());
								flagdrawtwo = false;
							}
							// draw four function
							if (flagwilddrawfour == true) {
								i--;
								if (i < 0)
									i = i + playernum;

								System.out.println("1)Challenge or 2)no:");
								challengeChoice = sc.nextInt();
								rubbish = sc.nextLine();

								if (challengeChoice == 1) {
									i++;
									if (i >= playernum)
										i = i - playernum;
									if (players.get(i).challengeCheck(pile.get(pile.size() - 2))) { //challenge checking
										players.get(i).addCard(deck.remove(0));
										players.get(i).addCard(deck.remove(0));
										players.get(i).addCard(deck.remove(0));
										players.get(i).addCard(deck.remove(0));
									}

									else {
										i--;
										if (i < 0)
											i = i + playernum;
										players.get(i).addCard(deck.remove(0));
										players.get(i).addCard(deck.remove(0));
										players.get(i).addCard(deck.remove(0));
										players.get(i).addCard(deck.remove(0));
										players.get(i).addCard(deck.remove(0));
										players.get(i).addCard(deck.remove(0));
									}
								} else if (challengeChoice == 2) {
									players.get(i).addCard(deck.remove(0));
									players.get(i).addCard(deck.remove(0));
									players.get(i).addCard(deck.remove(0));
									players.get(i).addCard(deck.remove(0));
								}
								Collections.sort(players.get(i).getHandcard());
								System.out.println(players.get(i).toString());
								flagwilddrawfour = false;
							}
							i--;
						}

					}
					// ---------------CHoose card from deck--------------------
				}
			}
			
			// ---------------------------player draws a card---------------------
			else if (choice == 2) {

				if (deck.isEmpty()) { //check if deck is empty , refill from pile
					System.out.println("The deck is empty, refilling deck with cards from pile");
					while (pile.size() > 1) {
						if(pile.get(0).getId()>=57 && pile.get(0).getId()<=60){
							pile.get(0).setColor("");
							pile.get(0).setSymbol("CopyCat");
						}
						deck.add(pile.remove(0));
						
					}
					Collections.shuffle(deck);
				}
				players.get(i).addCard(deck.remove(0));
			// ---------------------------player draws a card---------------------
			}
			choice = 0;
			validPlayeraction = false;
			
		}
		width=180;
		height=25;
		BufferedImage im = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics p = im.getGraphics();
		p.setFont(new Font("SansSerif",Font.BOLD,24));
		
		Graphics2D p2 = (Graphics2D)p;
		p2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		p2.drawString("UNO GAME!", 25, 21);
		//----------------------------ascii art drawing-----------------------------------------------
		for(int y=0;y<height;y++)
		{
			StringBuilder builder = new StringBuilder();
			
			for(int x = 0; x<width;x++){
				builder.append(im.getRGB(x, y)==-16777216 ? "@" : " ");
			}
			System.out.println(builder);
		}
		//----------------------------ascii art drawing-----------------------------------------------
		System.out.println("\n\n");
		System.out.println("\n\n"+players.get(i).getName() + " won!");
		
	}

}
