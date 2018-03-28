package uno;

import java.util.Scanner;

public class WildDrawFour extends Card{

	public WildDrawFour(String c, String s, int id) {
		super(c, s, id);
	}

	public void action(){
			int colorChoice;
			String rubbish;
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Please select your desired color:");
			System.out.println("1. Red");
			System.out.println("2. Yellow");
			System.out.println("3. Green");
			System.out.println("4. Blue");
			colorChoice = sc.nextInt();
			rubbish = sc.nextLine();
			
			switch(colorChoice){
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
		Game.flagwilddrawfour = true;

	}
}
