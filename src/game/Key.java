package game;

/*
 * Key-luokka sis‰lt‰‰ n‰pp‰imen tiedot: nimen, koodin ja tilan.
 */
public class Key{
	
	public String name;
	public int keyCode;
	public boolean pressed;
	
	public Key(String name, int keyCode){
		this.name = name;
		this.keyCode = keyCode;
		pressed = false;
	}
	/*
	 * toggle-metodi vaihtaa n‰pp‰imen tilaa.
	 */
	public void toggle(boolean toggle){
		if(pressed != toggle){
			pressed = toggle;
		}
	}
}
