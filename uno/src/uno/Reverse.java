package uno;

public class Reverse extends Card {

	public Reverse(String c, String s, int id) {
		super(c, s, id);
		//test hehe 22
	}

	public void action(){
		if (Game.flagrev == true)
			Game.flagrev = false;
		else
			Game.flagrev = true;
	}
}
