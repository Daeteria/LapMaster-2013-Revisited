package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/*
 * InputManager-luokkaa k�ytet��n useiden samanaikaisten painallusten rekister�imiseen.
 * Se sis�lt�� ArrayListin joka pit�� muistissa seurattavia n�pp�imi�.
 */
public class InputManager implements KeyListener{
	
	public ArrayList<Key> keys = new ArrayList<Key>();
	
	/*
	 * Konstruktori ottaa sis��n seurattavan GameSessionin.
	 */
	public InputManager(GameSession c){
		c.addKeyListener(this);
	}
	/*
	 * addMapping-metodi lis�� Key-luokan olioita, eli n�pp�imi� seurattavaan listaan.
	 */
	public void addMapping(String s, int keyCode){
		keys.add(new Key(s, keyCode));
	}
	/*
	 * isPressed-metodi palauttaa boolean-arvona n�pp�imen tilan.
	 */
	public boolean isPressed(String s){
		for(int i = 0; i < keys.size(); i++){
			if(s.equals(keys.get(i).name)){
				return keys.get(i).pressed;
			}
		}
		return false;
	}

	/*
	 * Kun n�pp�int� painetaan, keyPressed-metodi vaihtaa painetun n�pp�imen boolean-arvon trueksi.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		for(int i = 0; i < keys.size(); i++){
			if(e.getKeyCode() == keys.get(i).keyCode){
				keys.get(i).toggle(true);
			}
		}
	}

	/*
	 * Kun n�pp�in vapautetaan, keyReleased-metodi vaihtaa vapautetun n�pp�imen boolean-arvon falseksi.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		for(int i = 0; i < keys.size(); i++){
			if(e.getKeyCode() == keys.get(i).keyCode){
				keys.get(i).toggle(false);
			}
		}
	}

	/*
	 * KeyListener vaatii metodin toteuttamisen, mutta sit� ei k�ytet�.
	 */
	@Override
	public void keyTyped(KeyEvent e) {}
	
}