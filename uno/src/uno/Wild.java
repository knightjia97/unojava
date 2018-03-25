package uno;

import java.util.Scanner;

public class Wild extends Card{

	public Wild(String c, String s,int id) {
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
			this.setColor("red");
			break;
		case 2:
			this.setColor("yellow");
			break;
		case 3:
			this.setColor("green");
			break;
		case 4:
			this.setColor("blue");
			break;
		}
	}
}
