package uno;

import java.util.Scanner;

public class Wild extends Card {

	public Wild(String c, String s, int id) {
		super(c, s, id);
	}

	public void action() {
		int colorChoice=0;
		String rubbish;
		boolean validChoice = false;
		Scanner sc = new Scanner(System.in);

		while (validChoice == false) {
			System.out.println("Please select your desired color:");
			System.out.println("1. Red");
			System.out.println("2. Yellow");
			System.out.println("3. Green");
			System.out.println("4. Blue");
			while (!sc.hasNextInt()) { //check for invalid input (not integers)
				System.out.println("That's not a number!");
				sc.nextLine();
			}
			colorChoice = sc.nextInt();
			rubbish = sc.nextLine();
			if(colorChoice>=1 && colorChoice<=4)
				validChoice=true;
			else System.out.println("Only 4 color choices available");
		}
		switch (colorChoice) {
		case 1:
			this.setColor("Red");
			break;
		case 2:
			this.setColor("Yellow");
			break;
		case 3:
			this.setColor("Green");
			break;
		case 4:
			this.setColor("Blue");
			break;
		}
	}
}
