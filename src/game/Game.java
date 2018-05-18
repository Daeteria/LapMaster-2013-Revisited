package game;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * Game-luokka on koko pelin runko. Se sis�lt�� kaiken pelin toiminnallisuuden, riitt�� ett� main-metodissa lis�t��n yksi Game-luokan olio, joka k�ynnist�� pelin.
 * Game-luokan olioon lis�t��n Menu- ja GameSession-luokan olioita riippuen pelin tilasta. Game-luokka sis�lt�� pelin aloitus-, uudelleenaloitus- ja lopetusmetodit,
 * sek� enn�tyslistojen p�ivitys-, kirjoitus- ja j�rjestysmetodit.
 * 
 * 
 */
public class Game extends JFrame {
	
	private static final long serialVersionUID = 1L;

	Menu menu;
	GameSession session;
	public double averageLap;
	public double bestLap;
	private String iconImg = "Images\\Viper smaller1.png";
	private Image image;
	

	
	
	/*
	 * Game-luokan konstruktorissa JFrameen lis�t��n Menu-luokan JPanel-olio. 
	 * Lis�ksi kutsutaan topListArranger-metodia eri listanumeroilla, jotta enn�tyslistat olisivat heti j�rjestyksess�.
	 * 
	 */
	public Game() {
		ImageIcon ii = new ImageIcon(iconImg);
	    image = ii.getImage();
		setIconImage(image);
		menu = new Menu(this);
		
		topListArranger(101);
		topListArranger(111);
		topListArranger(102);
		topListArranger(112);
		topListArranger(201);
		topListArranger(211);
		topListArranger(202);
		topListArranger(212);
        add(menu);
        
        
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 825);
        setLocationRelativeTo(null);
        setTitle("LapMaster 2013 Revisited");
        setResizable(false);
        setVisible(true);
        
        
    }
	/*
	 * startGame-metodi aloittaa ajotilan. 
	 * Se poistaa JFramesta Menu-luokan JPanelin ja lis�� siihen valitun auton ja radan mukaisen GameSession-luokan JPanel-olion ja k�ynnist�� sessionin threadin.
	 */
	public void startGame(){
		remove(menu);
		session = new GameSession(menu.carNumber, menu.trackNumber, this);
		//session = new GameSession(4, 3, this);
		add(session);
		session.startGame();
		revalidate();
		
	}
	/*
	 * restartGame-metodi k�ynnist�� ajotilan uudestaan. Se poistaa sessionin, pys�ytt�� threadin, luo uuden sessionin samoilla arvoilla, lis�� sen JFrameen ja k�ynnist�� sen.
	 */
	public void restartGame(){
		remove(session);
		session.stopGame();
		session = new GameSession(menu.carNumber, menu.trackNumber, this);
		//session = new GameSession(4, 3, this);
		add(session);
		session.startGame();
		revalidate();
	}
	/*
	 * endGame-metodi lopettaa ajotilan. Se poistaa sessionin, pys�ytt�� threadin ja tarkistaa mill� autolla ajettiin ja lis�� sen mukaisiin listoihin saadun tuloksen.
	 * T�m�n j�lkeen metodi kutsuu topListArranger-metodia kaikilla eri listanumeroilla j�rjest��kseen listat, jonka j�lkeen metodi lis�� menun, pys�ytt�� threadin ja kutsuu repaint-metodia.
	 */
	public void endGame(){
		remove(session);
		/* 
		 * Tarkistetaan, mik� rata oli kyseess� ja ett� oliko auto Smart vai joku muu. Riippuen n�ist� tekij�ist�, kirjoitetaan sen mukaisiin tiedostoihin
		 * uusille riveille pelaajan nimi, auton nimi ja joko keskiarvokierrosaika tai paras kierrosaika riippuen listasta.
		 */
		if(menu.trackNumber == 1){
			if(session.physics.getBestLapdouble() != 0){
				if(session.physics.getAverageLapTime() != 0){
				if(menu.carNumber == 11){
					try {
						BufferedWriter out = new BufferedWriter(new FileWriter("Data\\Track1 TopLaps Smart.txt", true));
						
						out.append(menu.playerName);
						out.newLine();
						out.append(session.getSelectedCar().getName());
						out.newLine();
						out.append("" + session.physics.getBestLapdouble());
						out.newLine();
			
						out.close();
						} 
					catch (IOException e) {
			
						e.printStackTrace();
						}
					
					try {
						BufferedWriter out = new BufferedWriter(new FileWriter("Data\\Track1 TopAverages Smart.txt", true));
							
						out.append(menu.playerName);
						out.newLine();
						out.append(session.getSelectedCar().getName());
						out.newLine();
						out.append("" + session.physics.getAverageLapTime());
						out.newLine();
				
						out.close();
						} 
					catch (IOException e) {
			
						e.printStackTrace();
						}
				}
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter("Data\\Track1 TopLaps.txt", true));
					
					out.append(menu.playerName);
					out.newLine();
					out.append(session.getSelectedCar().getName());
					out.newLine();
					out.append("" + session.physics.getBestLapdouble());
					out.newLine();
		
					out.close();
					} 
				catch (IOException e) {
		
					e.printStackTrace();
					}
				
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter("Data\\Track1 TopAverages.txt", true));
						
					out.append(menu.playerName);
					out.newLine();
					out.append(session.getSelectedCar().getName());
					out.newLine();
					out.append("" + session.physics.getAverageLapTime());
					out.newLine();
			
					out.close();
					} 
				catch (IOException e) {
		
					e.printStackTrace();
					}
			}
				
			}
		}
		
		if(menu.trackNumber == 2){
			if(session.physics.getBestLapdouble() != 0){
				if(session.physics.getAverageLapTime() != 0){
				if(menu.carNumber == 11){
					try {
						BufferedWriter out = new BufferedWriter(new FileWriter("Data\\Track2 TopLaps Smart.txt", true));
						
						out.append(menu.playerName);
						out.newLine();
						out.append(session.getSelectedCar().getName());
						out.newLine();
						out.append("" + session.physics.getBestLapdouble());
						out.newLine();
			
						out.close();
						} 
					catch (IOException e) {
			
						e.printStackTrace();
						}
					
					try {
						BufferedWriter out = new BufferedWriter(new FileWriter("Data\\Track2 TopAverages Smart.txt", true));
						
						out.append(menu.playerName);
						out.newLine();
						out.append(session.getSelectedCar().getName());
						out.newLine();
						out.append("" + session.physics.getAverageLapTime());
						out.newLine();
			
						out.close();
						} 
					catch (IOException e) {
			
						e.printStackTrace();
						}
				}
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter("Data\\Track2 TopLaps.txt", true));
					
					out.append(menu.playerName);
					out.newLine();
					out.append(session.getSelectedCar().getName());
					out.newLine();
					out.append("" + session.physics.getBestLapdouble());
					out.newLine();
		
					out.close();
					} 
				catch (IOException e) {
		
					e.printStackTrace();
					}
				
					try {
						BufferedWriter out = new BufferedWriter(new FileWriter("Data\\Track2 TopAverages.txt", true));
						
						out.append(menu.playerName);
						out.newLine();
						out.append(session.getSelectedCar().getName());
						out.newLine();
						out.append("" + session.physics.getAverageLapTime());
						out.newLine();
			
						out.close();
						} 
					catch (IOException e) {
			
						e.printStackTrace();
						}
				}
				
			}
		}
		
		
		
		
		topListArranger(101);
		topListArranger(111);
		topListArranger(102);
		topListArranger(112);
		topListArranger(201);
		topListArranger(211);
		topListArranger(202);
		topListArranger(212);
		
		averageLap = session.physics.getAverageLapTime();
		bestLap = session.physics.getBestLapdouble();
		/*
		 *  Riippuen ajetusta radasta ja siit�, ajettiinko suoritus loppuun vai keskeytettiink� se, metodi valitsee sen mukaisen menun numeron. 
		 *  Jos peli� ei keskeytetty, peli menee menun tilaan, jossa se kertoo monenneksi pelaaja sijoittui k�ytetyn autonsa ja ajetun ratansa mukaisessa listassa.
		 *  Jos peli keskeytettiin, palaa menu aloitusruutuun.
		 */
		add(menu);
		if(session.physics.getAverageLapTime() != 0){
			menu.menuNumber = 10;
			
		}
		if(menu.trackNumber == 3){
			menu.menuNumber = 1;
		}
		if(session.physics.getAverageLapTime() == 0){
			menu.menuNumber = 1;
		}
		menu.requestFocusInWindow();
		menu.repaint();
		revalidate();
		session.stopGame();
		

		
	}
	/*
	 * topListArranger-metodi j�rjest�� sis��ntulevan listNumber-numeron mukaisen listan. Listan riveist� luodaan Score-luokan olioita, jotka sijoitetaan ArrayList-luokan olioon, 
	 * joka j�rjestet��n kierrosaikojen mukaisesti lapTimeComparator-luokan avulla. 
	 */
	public void topListArranger(int listNumber){
		ArrayList<Score> topArrayList = new ArrayList<Score>();
		try {
			BufferedReader in = new BufferedReader(new FileReader("Data\\File.txt"));
			if(listNumber == 111){
				in.close();
				in = new BufferedReader(new FileReader("Data\\Track1 TopLaps.txt"));
			}
			else if(listNumber == 101){
				in.close();
				in = new BufferedReader(new FileReader("Data\\Track1 TopAverages.txt"));
			}
			else if(listNumber == 112){
				in.close();
				in = new BufferedReader(new FileReader("Data\\Track1 TopLaps Smart.txt"));
			}
			else if(listNumber == 102){
				in.close();
				in = new BufferedReader(new FileReader("Data\\Track1 TopAverages Smart.txt"));
			}
			
			else if(listNumber == 211){
				in.close();
				in = new BufferedReader(new FileReader("Data\\Track2 TopLaps.txt"));
			}
			else if(listNumber == 201){
				in.close();
				in = new BufferedReader(new FileReader("Data\\Track2 TopAverages.txt"));
			}
			else if(listNumber == 212){
				in.close();
				in = new BufferedReader(new FileReader("Data\\Track2 TopLaps Smart.txt"));
			}
			else if(listNumber == 202){
				in.close();
				in = new BufferedReader(new FileReader("Data\\Track2 TopAverages Smart.txt"));
			}
			
			String line;
			String playerNameLine = "";
			String nameLine = "";
			int lineNumber = 1;
			/*
			 *  while-loopin sis�ll� metodi tallettaa arvoille playerNameLine ja nameLine pelaajan nimen ja k�ytetyn auton nimen.
			 *  Listaan arvot on kirjoitettu aina kolmelle riville, ensimm�iselle pelaajan nimi, toiselle k�ytetyn auton nimi ja kolmannelle tulos.
			 *  Loopin on siis oltava kolmijakoinen, ja aina kolmannella kierroksella arvot lis�t��n Score-olioon ja Score-olio topArrayList-nimiseen ArrayList-olioon.
			 *  T�m�n j�lkeen looppi alkaa alusta.
			 */
			while((line = in.readLine()) != null){
				if(lineNumber == 1){
					playerNameLine = line;
					lineNumber = 2;
				}
				else if(lineNumber == 2){
					nameLine = line;
					lineNumber = 3;
				}
				else if(lineNumber == 3){
					topArrayList.add(new Score(playerNameLine, nameLine, Double.parseDouble(line)));
					lineNumber = 1;
				}
			    
			}
			in.close();
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		/*
		 * T�ss� kutsutaan Collections.sort-metodia topArrayListille k�ytt�en lapTimeComparator-luokkaa j�rjest�j�n�.
		 */
		Collections.sort(topArrayList, new lapTimeComparator());
		/*
		 * T�ss� valittu lista tyhjennet��n.
		 */
		try {
			
			BufferedWriter out = new BufferedWriter(new FileWriter("Data\\File.txt", false));
			if(listNumber == 111){
				out.close();
				out = new BufferedWriter(new FileWriter("Data\\Track1 TopLaps.txt", false));
			}
			else if(listNumber == 101){
				out.close();
				out = new BufferedWriter(new FileWriter("Data\\Track1 TopAverages.txt", false));
			}
			else if(listNumber == 112){
				out.close();
				out = new BufferedWriter(new FileWriter("Data\\Track1 TopLaps Smart.txt", false));
			}
			else if(listNumber == 102){
				out.close();
				out = new BufferedWriter(new FileWriter("Data\\Track1 TopAverages Smart.txt", false));
			}
			
			else if(listNumber == 211){
				out.close();
				out = new BufferedWriter(new FileWriter("Data\\Track2 TopLaps.txt", false));
			}
			else if(listNumber == 201){
				out.close();
				out = new BufferedWriter(new FileWriter("Data\\Track2 TopAverages.txt", false));
			}
			else if(listNumber == 212){
				out.close();
				out = new BufferedWriter(new FileWriter("Data\\Track2 TopLaps Smart.txt", false));
			}
			else if(listNumber == 202){
				out.close();
				out = new BufferedWriter(new FileWriter("Data\\Track2 TopAverages Smart.txt", false));
			}
			
			
			
			out.write("");

			out.close();
			} 
		catch (IOException e) {

			e.printStackTrace();
			}
		int i = 0;
		/*
		 * T�ss� lista kirjoitetaan uusiksi topArrayList-oliosta.
		 */
		while(i < topArrayList.size()){
		try {
			if(i >= 30){
				break;
			}
			BufferedWriter out = new BufferedWriter(new FileWriter("Data\\File.txt", true));
			if(listNumber == 111){
				out.close();
				out = new BufferedWriter(new FileWriter("Data\\Track1 TopLaps.txt", true));
			}
			if(listNumber == 101){
				out.close();
				out = new BufferedWriter(new FileWriter("Data\\Track1 TopAverages.txt", true));
			}
			if(listNumber == 112){
				out.close();
				out = new BufferedWriter(new FileWriter("Data\\Track1 TopLaps Smart.txt", true));
			}
			if(listNumber == 102){
				out.close();
				out = new BufferedWriter(new FileWriter("Data\\Track1 TopAverages Smart.txt", true));
			}
			
			if(listNumber == 211){
				out.close();
				out = new BufferedWriter(new FileWriter("Data\\Track2 TopLaps.txt", true));
			}
			if(listNumber == 201){
				out.close();
				out = new BufferedWriter(new FileWriter("Data\\Track2 TopAverages.txt", true));
			}
			if(listNumber == 212){
				out.close();
				out = new BufferedWriter(new FileWriter("Data\\Track2 TopLaps Smart.txt", true));
			}
			if(listNumber == 202){
				out.close();
				out = new BufferedWriter(new FileWriter("Data\\Track2 TopAverages Smart.txt", true));
			}
			
			out.append(topArrayList.get(i).playerName);
			out.newLine();
			out.append(topArrayList.get(i).name);
			out.newLine();
			out.append("" + topArrayList.get(i).lapTime);
			out.newLine();

			out.close();
			} 
		catch (IOException e) {

			e.printStackTrace();
			}
		i++;
		}
			

	}
	
	/*
	 * Score-luokka sis�lt�� pelaajan nimen, auton nimen ja kierrosajan.
	 */
	class Score{
		String playerName;
		String name;
		double lapTime;
		
		public Score(String playerName, String name, double lapTime){
			this.playerName = playerName;
			this.name = name;
			this.lapTime = lapTime;
		}
	}
	/*
	 * lapTimeComparator-luokka ottaa sis��n kaksi Score-luokan oliota ja j�rjest�� ne kierrosajan perusteella.
	 */
	class lapTimeComparator implements Comparator<Score>{
		public int compare(Score a, Score b){
			if(a.lapTime < b.lapTime){
				return -1;
			}
			if(b.lapTime < a.lapTime){
				return 1;
			}
			else{
				return -1;
			}
		}
	}
	
	/*
	 * main-metodissa k�ynnistet��n peli.
	 */
    public static void main(String[] args) {
    	Game game = new Game();
//    	game.startGame();
    }
    
    
}