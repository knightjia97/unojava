package uno;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.Scanner;

public class Game {

	public static int turncount = 0;// global turn counter
	public static boolean flagrev = false;
	public static boolean flagskip = false;
	public static boolean flagdrawtwo = false;
	public static boolean flagalldrawtwo = false;
	public static boolean flagwilddrawfour = false;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int id = 1;
		int playcard;
		int playernum = 0;
		int challengeChoice = 0;
		String rubbish;
		boolean validPlayernum = false;

		System.out.println("Welcome to JAVA UNO GAME!!!");

		// check valid player number
		while (validPlayernum == false) {
			System.out.println("Enter the number of players(Recommended 2-4 players): ");
			while (!sc.hasNextInt()) {
				System.out.println("That's not a number!");
				sc.nextLine();
			}
			playernum = sc.nextInt();
			rubbish = sc.nextLine();
			if (playernum >= 2 && playernum <= 4)
				validPlayernum = true;
		}

		// prompt players' name
		ArrayList<Player> players = new ArrayList<Player>();
		for (int i = 0; i < playernum; i++) {
			System.out.println("Enter player" + (i + 1) + " name: ");
			players.add(new Player(sc.nextLine()));
		}

		ArrayList<Card> deck = new ArrayList<Card>();
		ArrayList<Card> pile = new ArrayList<Card>(); // empty pile
		String[] symbol = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		String[] color = { "red", "yellow", "green", "blue" };

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
//			deck.add(new AllDrawTwo(color[i], "AllDrawTwo", id));
//			id++;

		}

		deck.add(new Wild("", "Wild", id));
		id++;
		deck.add(new Wild("", "Wild", id));
		id++;
		deck.add(new WildDrawFour("", "WildDrawFour", id));
		id++;
		deck.add(new WildDrawFour("", "WildDrawFour", id));
		id++;
		deck.add(new CopyCat("", "CopyCat", id));//self implemented action cards
		id++;
		deck.add(new CopyCat("", "CopyCat", id));
		id++;
		deck.add(new CopyCat("", "CopyCat", id));
		id++;
		deck.add(new CopyCat("", "CopyCat", id));

		//Collections.shuffle(deck);

		pile.add(deck.remove(0));
		
		// distribute cards to players
		for (int i = 0; i < playernum; i++) {
			players.get(i).addCard(deck.remove(0));
//			players.get(i).addCard(deck.remove(0));
//			players.get(i).addCard(deck.remove(0));
//			players.get(i).addCard(deck.remove(0));
//			players.get(i).addCard(deck.remove(0));
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
				System.out.println("\n\nTOP CARD IN PILE:" + pile.get(pile.size() - 1).toString());
				if(!(pile.get(pile.size() - 1).getSymbol().equals("Wild")||pile.get(pile.size()-1).getSymbol().equals("WildDrawFour")||
						pile.get(pile.size()-1).getSymbol().equals("CopyCat")))
					firstpilecardflag = false;
				break;
			case "WildDrawFour":
				System.out.println("Replacing Wild Draw Four Card on top");
				pile.add(deck.remove(0));
				System.out.println("\n\nTOP CARD IN PILE:" + pile.get(pile.size() - 1).toString());
				if(!(pile.get(pile.size() - 1).getSymbol().equals("Wild")||pile.get(pile.size()-1).getSymbol().equals("WildDrawFour")||
						pile.get(pile.size()-1).getSymbol().equals("CopyCat")))
					firstpilecardflag = false;
				break;
			case "CopyCat":
				System.out.println("Replacing Copy Cat Card on top");
				pile.add(deck.remove(0));
				System.out.println("\n\nTOP CARD IN PILE:" + pile.get(pile.size() - 1).toString());
				if(!(pile.get(pile.size() - 1).getSymbol().equals("Wild")||pile.get(pile.size()-1).getSymbol().equals("WildDrawFour")||
						pile.get(pile.size()-1).getSymbol().equals("CopyCat")))
					firstpilecardflag = false;
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

			System.out.println("\n\nTOP CARD IN PILE:" + pile.get(pile.size() - 1).toString());
			System.out.println(players.get(i).getName() + "'s turn: ");
			Collections.sort(players.get(i).getHandcard());
			System.out.println("Your hand card:\n" + players.get(i).toString()); // display
																					// player
																					// card
			while (choice != 1 && choice != 2) {
				System.out.println("Choose your action: 1)Play a card ");
				System.out.println("                    2)Draw a card ");
				choice = sc.nextInt();
				rubbish = sc.nextLine();
			}

			if (choice == 1) {
				// ---------------Choose card from deck--------------------
				while (validcardFlag == false) { // check valid card , if true,
													// proceed || if false, loop
													// until valid
					System.out.println("Enter your card to draw");
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

								System.out.println("1)Challenge or 2)no:");
								challengeChoice = sc.nextInt();
								rubbish = sc.nextLine();

								if (challengeChoice == 1) {
									i--;
									if (i < 0)
										i = i + playernum;
									if (players.get(i).challengeCheck(pile.get(pile.size() - 2))) {
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
									if (players.get(i).challengeCheck(pile.get(pile.size() - 2))) {
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
			// ---------------------------player draws a
			// card---------------------
			else if (choice == 2) {
				players.get(i).addCard(deck.remove(0));
				System.out.println("Player" + (i + 1) + "'s content" + players.get(i).toString());
				// System.out.println("deck before is empty" +deck.toString());
				// //display
				if (deck.isEmpty()) {
					while (pile.size() > 1) {
						deck.add(pile.remove(0));
					}
					Collections.shuffle(deck);
					// System.out.println("deck after pile filling"
					// +deck.toString());

				}
				// ---------------------------player draws a
				// card---------------------
			}
			choice = 0;
		}
		System.out.println("Player" + i + " won!");

	}

}
