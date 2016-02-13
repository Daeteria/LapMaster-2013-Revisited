package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/*
 * InputManager-luokkaa käytetään useiden samanaikaisten painallusten rekisteröimiseen.
 * Se sisältää ArrayListin joka pitää muistissa seurattavia näppäimiä.
 */
public class InputManager implements KeyListener{
	
	public ArrayList<Key> keys = new ArrayList<Key>();
	
	/*
	 * Konstruktori ottaa sisään seurattavan GameSessionin.
	 */
	public InputManager(GameSession c){
		c.addKeyListener(this);
	}
	/*
	 * addMapping-metodi lisää Key-luokan olioita, eli näppäimiä seurattavaan listaan.
	 */
	public void addMapping(String s, int keyCode){
		keys.add(new Key(s, keyCode));
	}
	/*
	 * isPressed-metodi palauttaa boolean-arvona näppäimen tilan.
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
	 * Kun näppäintä painetaan, keyPressed-metodi vaihtaa painetun näppäimen boolean-arvon trueksi.
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
	 * Kun näppäin vapautetaan, keyReleased-metodi vaihtaa vapautetun näppäimen boolean-arvon falseksi.
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
	 * KeyListener vaatii metodin toteuttamisen, mutta sitä ei käytetä.
	 */
	@Override
	public void keyTyped(KeyEvent e) {}
	
}