package uno;

public class Skip extends Card{

	public Skip(String c, String s, int id) {
		super(c, s, id);
		//testhehe
	}
	
	public void action(){
			Game.flagskip = true;
	}

}
