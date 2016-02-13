package game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Menu-luokka sis‰lt‰‰ pelin menu-toiminnallisuudet: Pelaajan asetus, auton valinta, radan valinta, pelin k‰ynnistys ja eri huipputuloslistojen tarkastelu.
 */
public class Menu extends JPanel implements MouseListener, MouseMotionListener{
	
	private static final long serialVersionUID = 1L;
	public int carNumber;
	public int trackNumber;
	public int menuNumber = 1;
	private int carHoverNumber;
	// Kaikkien eri objektien kuvien haku ja asetus.
	private String backgroundImagePath = "Images\\Menu background.png";
    private ImageIcon backgroundImageIcon = new ImageIcon(backgroundImagePath);
    private Image backgroundImage = backgroundImageIcon.getImage();
   
    private String leftArrowImagePath = "Images\\Menu left arrow.png";
    private ImageIcon leftArrowImageIcon = new ImageIcon(leftArrowImagePath);
    private Image leftArrowImage = leftArrowImageIcon.getImage();
   
    private String rightArrowImagePath = "Images\\Menu right arrow.png";
    private ImageIcon rightArrowImageIcon = new ImageIcon(rightArrowImagePath);
    private Image rightArrowImage = rightArrowImageIcon.getImage();

    private String arrowBackgroundImagePath = "Images\\Arrow background1.png";
    private ImageIcon arrowBackgroundImageIcon = new ImageIcon(arrowBackgroundImagePath);
    private Image arrowBackgroundImage = arrowBackgroundImageIcon.getImage();
   
    private String playButtonImagePath = "Images\\Playbutton.png";
    private ImageIcon playButtonImageIcon = new ImageIcon(playButtonImagePath);
    private Image playButtonImage = playButtonImageIcon.getImage();
   
    private String scoresButtonImagePath = "Images\\Scoresbutton.png";
    private ImageIcon scoresButtonImageIcon = new ImageIcon(scoresButtonImagePath);
    private Image scoresButtonImage = scoresButtonImageIcon.getImage();
    
    private String playerButtonImagePath = "Images\\Playerbutton.png";
    private ImageIcon playerButtonImageIcon = new ImageIcon(playerButtonImagePath);
    private Image playerButtonImage = playerButtonImageIcon.getImage();
    
    private String okButtonImagePath = "Images\\Okbutton.png";
    private ImageIcon okButtonImageIcon = new ImageIcon(okButtonImagePath);
    private Image okButtonImage = okButtonImageIcon.getImage();
   
   
    private String Buttonbackground1ImagePath = "Images\\Button background 1.png";
    private ImageIcon Buttonbackground1ImageIcon = new ImageIcon(Buttonbackground1ImagePath);
    private Image Buttonbackground1Image = Buttonbackground1ImageIcon.getImage();
   
    private String Buttonbackground2ImagePath = "Images\\Button background 2.png";
    private ImageIcon Buttonbackground2ImageIcon = new ImageIcon(Buttonbackground2ImagePath);
    private Image Buttonbackground2Image = Buttonbackground2ImageIcon.getImage();
    
    private String menuAvePath = "Images\\Aventador large1.png";
    private ImageIcon menuAveImageIcon = new ImageIcon(menuAvePath);
    private Image menuAveImage = menuAveImageIcon.getImage();
    private String menuAveShPath = "Images\\Aventador largeshadow1.png";
    private ImageIcon menuAveShImageIcon = new ImageIcon(menuAveShPath);
    private Image menuAveShImage = menuAveShImageIcon.getImage();
   
    private String menuMustangPath = "Images\\Mustang large1.png";
    private ImageIcon menuMustangImageIcon = new ImageIcon(menuMustangPath);
    private Image menuMustangImage = menuMustangImageIcon.getImage();
    private String menuMustangShPath = "Images\\Mustang largeshadow1.png";
    private ImageIcon menuMustangShImageIcon = new ImageIcon(menuMustangShPath);
    private Image menuMustangShImage = menuMustangShImageIcon.getImage();
   
    private String menuMurciePath = "Images\\Murcielago large1.png";
    private ImageIcon menuMurcieImageIcon = new ImageIcon(menuMurciePath);
    private Image menuMurcieImage = menuMurcieImageIcon.getImage();
    private String menuMurcieShPath = "Images\\Murcielago largeshadow1.png";
    private ImageIcon menuMurcieShImageIcon = new ImageIcon(menuMurcieShPath);
    private Image menuMurcieShImage = menuMurcieShImageIcon.getImage();
   
    private String menuDb9Path = "Images\\Aston large1.png";
    private ImageIcon menuDb9ImageIcon = new ImageIcon(menuDb9Path);
    private Image menuDb9Image = menuDb9ImageIcon.getImage();
    private String menuDb9ShPath = "Images\\Aston largeshadow1.png";
    private ImageIcon menuDb9ShImageIcon = new ImageIcon(menuDb9ShPath);
    private Image menuDb9ShImage = menuDb9ShImageIcon.getImage();
   
    private String menuZondaPath = "Images\\Pagani large1.png";
    private ImageIcon menuZondaImageIcon = new ImageIcon(menuZondaPath);
    private Image menuZondaImage = menuZondaImageIcon.getImage();
    private String menuZondaShPath = "Images\\Pagani largeshadow1.png";
    private ImageIcon menuZondaShImageIcon = new ImageIcon(menuZondaShPath);
    private Image menuZondaShImage = menuZondaShImageIcon.getImage();
   
    private String menuF40Path = "Images\\F40 large1.png";
    private ImageIcon menuF40ImageIcon = new ImageIcon(menuF40Path);
    private Image menuF40Image = menuF40ImageIcon.getImage();
    private String menuF40ShPath = "Images\\F40 largeshadow1.png";
    private ImageIcon menuF40ShImageIcon = new ImageIcon(menuF40ShPath);
    private Image menuF40ShImage = menuF40ShImageIcon.getImage();
   
    private String menuVeyronPath = "Images\\veyron large1.png";
    private ImageIcon menuVeyronImageIcon = new ImageIcon(menuVeyronPath);
    private Image menuVeyronImage = menuVeyronImageIcon.getImage();
    private String menuVeyronShPath = "Images\\veyron largeshadow1.png";
    private ImageIcon menuVeyronShImageIcon = new ImageIcon(menuVeyronShPath);
    private Image menuVeyronShImage = menuVeyronShImageIcon.getImage();
   
    private String menuF1Path = "Images\\F1 large1.png";
    private ImageIcon menuF1ImageIcon = new ImageIcon(menuF1Path);
    private Image menuF1Image = menuF1ImageIcon.getImage();
    private String menuF1ShPath = "Images\\F1 largeshadow1.png";
    private ImageIcon menuF1ShImageIcon = new ImageIcon(menuF1ShPath);
    private Image menuF1ShImage = menuF1ShImageIcon.getImage();
   
    private String menuSmartPath = "Images\\smart large1.png";
    private ImageIcon menuSmartImageIcon = new ImageIcon(menuSmartPath);
    private Image menuSmartImage = menuSmartImageIcon.getImage();
    private String menuSmartShPath = "Images\\smart largeshadow1.png";
    private ImageIcon menuSmartShImageIcon = new ImageIcon(menuSmartShPath);
    private Image menuSmartShImage = menuSmartShImageIcon.getImage();
   
    private String menuViperPath = "Images\\Viper large1.png";
    private ImageIcon menuViperImageIcon = new ImageIcon(menuViperPath);
    private Image menuViperImage = menuViperImageIcon.getImage();
    private String menuViperShPath = "Images\\Viper largeshadow1.png";
    private ImageIcon menuViperShImageIcon = new ImageIcon(menuViperShPath);
    private Image menuViperShImage = menuViperShImageIcon.getImage();
   
    private String menuP1Path = "Images\\P1 large1.png";
    private ImageIcon menuP1ImageIcon = new ImageIcon(menuP1Path);
    private Image menuP1Image = menuP1ImageIcon.getImage();
    private String menuP1ShPath = "Images\\P1 largeshadow1.png";
    private ImageIcon menuP1ShImageIcon = new ImageIcon(menuP1ShPath);
    private Image menuP1ShImage = menuP1ShImageIcon.getImage();
   
    private String carBackgroundPath = "Images\\Car background2.png";
    private ImageIcon carBackgroundImageIcon = new ImageIcon(carBackgroundPath);
    private Image carBackgroundImage = carBackgroundImageIcon.getImage();
   
    private String trackBackgroundPath = "Images\\Track background.png";
    private ImageIcon trackBackgroundImageIcon = new ImageIcon(trackBackgroundPath);
    private Image trackBackgroundImage = trackBackgroundImageIcon.getImage();
   
    private String track1Path = "Images\\Menu track1.png";
    private ImageIcon track1ImageIcon = new ImageIcon(track1Path);
    private Image track1Image = track1ImageIcon.getImage();
   
    private String track2Path = "Images\\Menu track2.png";
    private ImageIcon track2ImageIcon = new ImageIcon(track2Path);
    private Image track2Image = track2ImageIcon.getImage();
    
    private String track3Path = "Images\\Menu track3.png";
    private ImageIcon track3ImageIcon = new ImageIcon(track3Path);
    private Image track3Image = track3ImageIcon.getImage();
	
    private String aveStatsPath = "Images\\Car stats\\Aventador 2015.png";
    private ImageIcon aveStatsImageIcon = new ImageIcon(aveStatsPath);
    private Image aveStatsImage = aveStatsImageIcon.getImage();
    
    private String mustangStatsPath = "Images\\Car stats\\Mustang 2015.png";
    private ImageIcon mustangStatsImageIcon = new ImageIcon(mustangStatsPath);
    private Image mustangStatsImage = mustangStatsImageIcon.getImage();
    
    private String murcieStatsPath = "Images\\Murcielago stats.png";
    private ImageIcon murcieStatsImageIcon = new ImageIcon(murcieStatsPath);
    private Image murcieStatsImage = murcieStatsImageIcon.getImage();
    
    private String db9StatsPath = "Images\\Car stats\\Db9 2015.png";
    private ImageIcon db9StatsImageIcon = new ImageIcon(db9StatsPath);
    private Image db9StatsImage = db9StatsImageIcon.getImage();
    
    private String viperStatsPath = "Images\\Car stats\\Viper 2015.png";
    private ImageIcon viperStatsImageIcon = new ImageIcon(viperStatsPath);
    private Image viperStatsImage = viperStatsImageIcon.getImage();
    
    private String f40StatsPath = "Images\\Car stats\\F40 2015.png";
    private ImageIcon f40StatsImageIcon = new ImageIcon(f40StatsPath);
    private Image f40StatsImage = f40StatsImageIcon.getImage();
    
    private String veyronStatsPath = "Images\\Car stats\\Veyron 2015.png";
    private ImageIcon veyronStatsImageIcon = new ImageIcon(veyronStatsPath);
    private Image veyronStatsImage = veyronStatsImageIcon.getImage();
    
    private String p1StatsPath = "Images\\Car stats\\P1 2015.png";
    private ImageIcon p1StatsImageIcon = new ImageIcon(p1StatsPath);
    private Image p1StatsImage = p1StatsImageIcon.getImage();
    
    private String zondaStatsPath = "Images\\Car stats\\Zonda 2015.png";
    private ImageIcon zondaStatsImageIcon = new ImageIcon(zondaStatsPath);
    private Image zondaStatsImage = zondaStatsImageIcon.getImage();
    
    private String f1StatsPath = "Images\\Car stats\\F1 2015.png";
    private ImageIcon f1StatsImageIcon = new ImageIcon(f1StatsPath);
    private Image f1StatsImage = f1StatsImageIcon.getImage();
    
    private String smartStatsPath = "Images\\Car stats\\Smart 2015.png";
    private ImageIcon smartStatsImageIcon = new ImageIcon(smartStatsPath);
    private Image smartStatsImage = smartStatsImageIcon.getImage();
    
    private String carSelectionPath = "Images\\Car selection.png";
    private ImageIcon carSelectionImageIcon = new ImageIcon(carSelectionPath);
    private Image carSelectionImage = carSelectionImageIcon.getImage();
    
    private String trackSelectionPath = "Images\\Track selection.png";
    private ImageIcon trackSelectionImageIcon = new ImageIcon(trackSelectionPath);
    private Image trackSelectionImage = trackSelectionImageIcon.getImage();
    
    private String scoreBackground101Path = "Images\\Scoreboard background101.png";
    private ImageIcon scoreBackground101ImageIcon = new ImageIcon(scoreBackground101Path);
    private Image scoreBackground101Image = scoreBackground101ImageIcon.getImage();
    
    private String scoreBackground102Path = "Images\\Scoreboard background102.png";
    private ImageIcon scoreBackground102ImageIcon = new ImageIcon(scoreBackground102Path);
    private Image scoreBackground102Image = scoreBackground102ImageIcon.getImage();

    private String scoreBackground111Path = "Images\\Scoreboard background111.png";
    private ImageIcon scoreBackground111ImageIcon = new ImageIcon(scoreBackground111Path);
    private Image scoreBackground111Image = scoreBackground111ImageIcon.getImage();
    
    private String scoreBackground112Path = "Images\\Scoreboard background112.png";
    private ImageIcon scoreBackground112ImageIcon = new ImageIcon(scoreBackground112Path);
    private Image scoreBackground112Image = scoreBackground112ImageIcon.getImage();
    
    private String scoreBackground201Path = "Images\\Scoreboard background201.png";
    private ImageIcon scoreBackground201ImageIcon = new ImageIcon(scoreBackground201Path);
    private Image scoreBackground201Image = scoreBackground201ImageIcon.getImage();
    
    private String scoreBackground202Path = "Images\\Scoreboard background202.png";
    private ImageIcon scoreBackground202ImageIcon = new ImageIcon(scoreBackground202Path);
    private Image scoreBackground202Image = scoreBackground202ImageIcon.getImage();

    private String scoreBackground211Path = "Images\\Scoreboard background211.png";
    private ImageIcon scoreBackground211ImageIcon = new ImageIcon(scoreBackground211Path);
    private Image scoreBackground211Image = scoreBackground211ImageIcon.getImage();
    
    private String scoreBackground212Path = "Images\\Scoreboard background212.png";
    private ImageIcon scoreBackground212ImageIcon = new ImageIcon(scoreBackground212Path);
    private Image scoreBackground212Image = scoreBackground212ImageIcon.getImage();
    
    private String resultBackgroundPath = "Images\\Result background.png";
    private ImageIcon resultBackgroundImageIcon = new ImageIcon(resultBackgroundPath);
    private Image resultBackgroundImage = resultBackgroundImageIcon.getImage();
    
    private String track1buttonPath = "Images\\Track1 button.png";
    private ImageIcon track1buttonImageIcon = new ImageIcon(track1buttonPath);
    private Image track1buttonImage = track1buttonImageIcon.getImage();
    
    private String track2buttonPath = "Images\\Track2 button.png";
    private ImageIcon track2buttonImageIcon = new ImageIcon(track2buttonPath);
    private Image track2buttonImage = track2buttonImageIcon.getImage();
    
    private String trackButtonBGPath = "Images\\Trackbutton background1.png";
    private ImageIcon trackButtonBGImageIcon = new ImageIcon(trackButtonBGPath);
    private Image trackButtonBGImage = trackButtonBGImageIcon.getImage();
    
    private String upArrowButtonPath = "Images\\UpArrow.png";
    private ImageIcon upArrowButtonImageIcon = new ImageIcon(upArrowButtonPath);
    private Image upArrowButtonImage = upArrowButtonImageIcon.getImage();
    
    private String upArrowBGPath = "Images\\UpArrow background1.png";
    private ImageIcon upArrowBGImageIcon = new ImageIcon(upArrowBGPath);
    private Image upArrowBGImage = upArrowBGImageIcon.getImage();
    
    private String track1InfoBGPath = "Images\\Track1 infobackground.png";
    private ImageIcon track1InfoBGImageIcon = new ImageIcon(track1InfoBGPath);
    private Image track1InfoBGImage = track1InfoBGImageIcon.getImage();
    
    private String track2InfoBGPath = "Images\\Track2 infobackground.png";
    private ImageIcon track2InfoBGImageIcon = new ImageIcon(track2InfoBGPath);
    private Image track2InfoBGImage = track2InfoBGImageIcon.getImage();
    
    private String track3InfoBGPath = "Images\\Track3 infobackground.png";
    private ImageIcon track3InfoBGImageIcon = new ImageIcon(track3InfoBGPath);
    private Image track3InfoBGImage = track3InfoBGImageIcon.getImage();
    
    private String controlsPath = "Images\\Controls2.png";
    private ImageIcon controlsImageIcon = new ImageIcon(controlsPath);
    private Image controlsImage = controlsImageIcon.getImage();
    
    //--------------------------------------------------------------------------------------------------------------------
    
	private Game game;
	
	// K‰ytetyille totuusarvoille alustukset.
	private boolean backIsHovered;
	private boolean backIsClicked;
	private boolean nextIsHovered;
	private boolean nextIsClicked;
	private boolean playIsPressed;
	private boolean playIsHovered;
	private boolean playerIsPressed;
	private boolean playerIsHovered;
	private boolean scoresIsPressed;
	private boolean scoresIsHovered;
	private boolean carIsHovered;
	private boolean track1IsHovered;
	private boolean track2IsHovered;
	private boolean track3IsHovered;
	private boolean hasBeenOverButton;
	private boolean okIsHovered;
	private boolean ok2IsHovered;
	private boolean okIsPressed;
	private boolean ok2IsPressed;
	private boolean displayWarningMessage;
	private boolean trackButtonBox1IsHovered;
	private boolean trackButtonBox2IsHovered;
	private boolean trackButtonBox1IsPressed;
	private boolean trackButtonBox2IsPressed;
	private boolean upIsHovered;
	private boolean upIsPressed;
	
	// Radanvalintaruudun enn‰tysn‰kym‰n tiedot.
	private String t1lapRecAveNorm;
	private String t1lapRecAveNormCar;
	private String t1lapRecAveSmart;
	private String t1lapRecNorm;
	private String t1lapRecNormCar;
	private String t1lapRecSmart;
	private String t2lapRecAveNorm;
	private String t2lapRecAveNormCar;
	private String t2lapRecAveSmart;
	private String t2lapRecNorm;
	private String t2lapRecNormCar;
	private String t2lapRecSmart;
	
	private boolean carIsSelected;
	
	Rectangle play = new Rectangle(530, 200, 240, 70);
	Rectangle scores = new Rectangle(530, 280, 240, 70);
	Rectangle player = new Rectangle(530, 360, 240, 70);
	
	ArrayList<Rectangle> carBoxList = new ArrayList<Rectangle>();
	
	Rectangle back = new Rectangle(20, 5, 100, 50);
	Rectangle next = new Rectangle(1172, 5, 100, 50);
	Rectangle up = new Rectangle(625, 5, 50, 50);
	Rectangle setName = new Rectangle(770, 280, 70, 70);
	Rectangle okBox = new Rectangle(615, 650, 70, 70);
	
	Rectangle trackBox1 = new Rectangle(190, 140, 300, 185);
	Rectangle trackBox2 = new Rectangle(500, 140, 300, 185);
	Rectangle trackBox3 = new Rectangle(810, 140, 300, 185);
	
	Rectangle trackButtonBox1 = new Rectangle(45, 240, 600, 160);
	Rectangle trackButtonBox2 = new Rectangle(655, 240, 600, 160);
	
	
	ArrayList<Image> carStatsImages = new ArrayList<Image>();

	JTextField playerNameGetter = new JTextField(10);
	public String playerName;
	private String playerNameTry;
	Font font1 = new Font("Andalus", Font.PLAIN, 16);
	Font font2 = new Font("Andalus", Font.PLAIN, 30);
	Font font3 = new Font("Andalus", Font.PLAIN, 24);
	Font font4 = new Font("Andalus", Font.PLAIN, 38);
	Font font5 = new Font("Andalus", Font.PLAIN, 66);
	Font font6 = new Font("Andalus", Font.PLAIN, 18);
	Color textColor = new java.awt.Color(210, 143, 5);
	
	int stringLen;
	int startText;


	
	//--------------------------------------------------------------------------------------------------------------------
	
	
	
	/*
	 * Konstruktori ottaa sis‰‰ns‰ Game-luokan olion. Kutsuu trackRecordGetter-metodia radanvalintaruudun enn‰tysten asettamiseksi. Lis‰‰ MouseListenerin ja MouseMotionListenerin.
	 */
	public Menu(Game game){
		
		this.game = game;
		trackRecordGetter();
		setLayout(null);
		setSize(1300, 825);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		
		carBoxList.add(new Rectangle(280, 140, 140, 140));
		carBoxList.add(new Rectangle(430, 140, 140, 140));
		carBoxList.add(new Rectangle(580, 140, 140, 140));
		carBoxList.add(new Rectangle(730, 140, 140, 140));
		carBoxList.add(new Rectangle(880, 140, 140, 140));
		carBoxList.add(new Rectangle(280, 290, 140, 140));
		carBoxList.add(new Rectangle(430, 290, 140, 140));
		carBoxList.add(new Rectangle(580, 290, 140, 140));
		carBoxList.add(new Rectangle(730, 290, 140, 140));
		carBoxList.add(new Rectangle(880, 290, 140, 140));
		carBoxList.add(new Rectangle(580, 440, 140, 140));
		
		carStatsImages.add(mustangStatsImage);
		carStatsImages.add(murcieStatsImage);
		carStatsImages.add(db9StatsImage);
		carStatsImages.add(viperStatsImage);
		carStatsImages.add(f40StatsImage);
		carStatsImages.add(veyronStatsImage);
		carStatsImages.add(aveStatsImage);
		carStatsImages.add(p1StatsImage);
		carStatsImages.add(zondaStatsImage);
		carStatsImages.add(f1StatsImage);
		carStatsImages.add(smartStatsImage);
		playerNameGetter.setFont(font2);

		
		playerNameGetter.setLocation(450, 285);
		playerNameGetter.setSize(300, 60);
		
		
        
		
	}
	

	/*
	 * paint-metodi piirt‰‰ kaiken.
	 */
	public void paint(Graphics g) {
    	super.paint(g);
    	Graphics2D g2d = (Graphics2D)g;
    	g2d.setFont(font3);
    	
    	
    	// Hienonnussetit.
    	setDoubleBuffered(true);
    	RenderingHints hints = new RenderingHints(null);
    	hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    	hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    	hints.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
    	hints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
    	hints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
    	g2d.setRenderingHints(hints);
    	
    	g2d.setColor(Color.WHITE);
    	// Piirt‰‰ taustan menulle.
    	g2d.drawImage(backgroundImage, 0, 0, null);
    	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
    	if(playerName != null){
    		if(playerName.length() != 0){
    			g2d.drawString("Player: " + playerName, 20, 780);
    		}
    	}
    	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    	g2d.setFont(font1);
    	
    	// Valitsee menun numeron mukaan mit‰ piirret‰‰n.
    	if(menuNumber == 1){
    		
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
    		
    		if(playIsHovered){
    			g2d.drawImage(Buttonbackground1Image, 530, 200, null);
    		}
    		if(scoresIsHovered){
    			g2d.drawImage(Buttonbackground1Image, 530, 280, null);
    		}
    		if(playerIsHovered){
    			g2d.drawImage(Buttonbackground1Image, 530, 360, null);
    		}
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
    		if(playIsPressed){
    			g2d.drawImage(Buttonbackground1Image, 530, 200, null);
    		}
    		if(scoresIsPressed){
    			g2d.drawImage(Buttonbackground1Image, 530, 280, null);
    		}
    		if(playerIsPressed){
    			g2d.drawImage(Buttonbackground1Image, 530, 360, null);
    		}
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    		g2d.drawImage(playButtonImage, 530, 200, null);
	    	g2d.drawImage(scoresButtonImage, 530, 280, null);
	    	g2d.drawImage(playerButtonImage, 530, 360, null);
	    	g2d.setFont(font2);
	    	if(displayWarningMessage){
	    		g2d.drawString("You must select 'PLAYER' and type", 440, 500);
	    		g2d.drawString("yourself a name before you can play the game!", 360, 530);
	    	}
        	
	    	g2d.setFont(font1);
    	}
    	
    	//-------------------------------------------------------------------------------------------------------------------------
    	
    	
    	else if(menuNumber == 2){
    		g2d.drawImage(carSelectionImage, 450, 40, null);
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
    		if(backIsHovered){
    			g2d.drawImage(arrowBackgroundImage, (int)back.getX(), (int)back.getY(), null);
    		}
    		if(nextIsHovered){
    			g2d.drawImage(arrowBackgroundImage, (int)next.getX(), (int)next.getY(), null);
    		}
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
    		if(backIsClicked){
    			g2d.drawImage(arrowBackgroundImage, (int)back.getX(), (int)back.getY(), null);
    		}
    		if(nextIsClicked){
    			g2d.drawImage(arrowBackgroundImage, (int)next.getX(), (int)next.getY(), null);
    			
    		}
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
    		g2d.drawImage(leftArrowImage, (int)back.getX(), (int)back.getY(), null);
    		g2d.drawImage(rightArrowImage, (int)next.getX(), (int)next.getY(), null);
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
    		
    	
    		g2d.drawImage(carBackgroundImage, (int)carBoxList.get(0).getX(), (int)carBoxList.get(0).getY(), null);
    		g2d.drawImage(carBackgroundImage, (int)carBoxList.get(1).getX(), (int)carBoxList.get(1).getY(), null);
    		g2d.drawImage(carBackgroundImage, (int)carBoxList.get(2).getX(), (int)carBoxList.get(2).getY(), null);
    		g2d.drawImage(carBackgroundImage, (int)carBoxList.get(3).getX(), (int)carBoxList.get(3).getY(), null);
    		g2d.drawImage(carBackgroundImage, (int)carBoxList.get(4).getX(), (int)carBoxList.get(4).getY(), null);
    		g2d.drawImage(carBackgroundImage, (int)carBoxList.get(5).getX(), (int)carBoxList.get(5).getY(), null);
    		g2d.drawImage(carBackgroundImage, (int)carBoxList.get(6).getX(), (int)carBoxList.get(6).getY(), null);
    		g2d.drawImage(carBackgroundImage, (int)carBoxList.get(7).getX(), (int)carBoxList.get(7).getY(), null);
    		g2d.drawImage(carBackgroundImage, (int)carBoxList.get(8).getX(), (int)carBoxList.get(8).getY(), null);
    		g2d.drawImage(carBackgroundImage, (int)carBoxList.get(9).getX(), (int)carBoxList.get(9).getY(), null);
    		g2d.drawImage(carBackgroundImage, (int)carBoxList.get(10).getX(), (int)carBoxList.get(10).getY(), null);
    		
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.55f));
    		if(carIsHovered){
    			g2d.drawImage(carBackgroundImage, (int)carBoxList.get(carHoverNumber-1).getX(), (int)carBoxList.get(carHoverNumber-1).getY(), null);
    			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.70f));
    			g2d.drawImage(carStatsImages.get(carHoverNumber-1), 130, 480, null);
    		}
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.77f));
    		if(carIsSelected){
    			
    			g2d.drawImage(carBackgroundImage, (int)carBoxList.get(carNumber-1).getX(), (int)carBoxList.get(carNumber-1).getY(), null);
    			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.80f));
    			g2d.drawImage(carStatsImages.get(carNumber-1), 805, 480, null);
    			g2d.drawImage(controlsImage, 1055, 310, null);
    			
    		}
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.60f));
    		g2d.drawString("All car models used are registered trademarks of their respective manufacturers.", 740, 768);
    		g2d.drawString("The cars' performance figures are fictional and only represent the gamemakers' personal preferences.", 599, 785);
    		
            
            
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.85f));
    		
    		g2d.drawImage(menuMustangShImage, 298, 178, null);
            g2d.drawImage(menuMurcieShImage, 448, 173, null);
            g2d.drawImage(menuDb9ShImage, 598, 178, null);
            g2d.drawImage(menuViperShImage, 748, 176, null);
            g2d.drawImage(menuF40ShImage, 898, 173, null);
            g2d.drawImage(menuVeyronShImage, 298, 325, null);
            g2d.drawImage(menuAveShImage, 448, 326, null);
            g2d.drawImage(menuP1ShImage, 598, 325, null);
            g2d.drawImage(menuZondaShImage, 748, 325, null);
            g2d.drawImage(menuF1ShImage, 898, 328, null);
            g2d.drawImage(menuSmartShImage, 625, 481, null);
    		
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    		g2d.drawImage(menuMustangImage, 295, 185, null);
    		g2d.drawImage(menuMurcieImage, 445, 180, null);
    		g2d.drawImage(menuDb9Image, 595, 185, null);
    		g2d.drawImage(menuViperImage, 745, 183, null);
    		g2d.drawImage(menuF40Image, 895, 180, null);
    		g2d.drawImage(menuVeyronImage, 295, 332, null);
    		g2d.drawImage(menuAveImage, 445, 333, null);
    		g2d.drawImage(menuP1Image, 595, 332, null);
    		g2d.drawImage(menuZondaImage, 745, 332, null);
    		g2d.drawImage(menuF1Image, 895, 335, null);
    		g2d.drawImage(menuSmartImage, 622, 488, null);
    		
    	}
    	
    	
    	//----------------------------------------------------------------------------------------------------------
    	
    	
    	else if(menuNumber == 3){
    		
    		
    		
    		g2d.drawImage(trackSelectionImage, 430, 40, null);
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
    		if(backIsHovered){
    			g2d.drawImage(arrowBackgroundImage, (int)back.getX(), (int)back.getY(), null);
    		}
    		if(nextIsHovered){
    			g2d.drawImage(arrowBackgroundImage, (int)next.getX(), (int)next.getY(), null);
    		}
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
    		if(backIsClicked){
    			g2d.drawImage(arrowBackgroundImage, (int)back.getX(), (int)back.getY(), null);
    		}
    		if(nextIsClicked){
    			g2d.drawImage(arrowBackgroundImage, (int)next.getX(), (int)next.getY(), null);
    			
    		}
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
    		g2d.drawImage(leftArrowImage, (int)back.getX(), (int)back.getY(), null);
    		g2d.drawImage(rightArrowImage, (int)next.getX(), (int)next.getY(), null);
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            g2d.drawImage(trackBackgroundImage, (int)trackBox1.getX(), (int)trackBox1.getY(), null);
            g2d.drawImage(trackBackgroundImage, (int)trackBox2.getX(), (int)trackBox2.getY(), null);
            g2d.drawImage(trackBackgroundImage, (int)trackBox3.getX(), (int)trackBox3.getY(), null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.55f));
            if(track1IsHovered){
            	g2d.drawImage(trackBackgroundImage, (int)trackBox1.getX(), (int)trackBox1.getY(), null);
            }
            if(track2IsHovered){
            	g2d.drawImage(trackBackgroundImage, (int)trackBox2.getX(), (int)trackBox2.getY(), null);
            }
            if(track3IsHovered){
            	g2d.drawImage(trackBackgroundImage, (int)trackBox3.getX(), (int)trackBox3.getY(), null);
            }
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
            
            if(trackNumber == 1){
            	g2d.drawImage(trackBackgroundImage, (int)trackBox1.getX(), (int)trackBox1.getY(), null);
            	g2d.drawImage(track1InfoBGImage, 374, 370, null);
            	g2d.setFont(font3);
            	g2d.drawString("Top 10 Lap Averages:", 415, 550);
            	g2d.drawString("Fastest Laps:", 670, 550);
            	g2d.setFont(font6);
            	if(t1lapRecAveNorm != null){
            		g2d.drawString("All Cars: " + t1lapRecAveNorm + "s (" + t1lapRecAveNormCar + ")", 430, 580);
            	}
            	if(t1lapRecAveNorm == null){
            		g2d.drawString("All Cars: " + "No records yet", 430, 580);
            	}
            	if(t1lapRecNorm != null){
            		g2d.drawString("All Cars: " + t1lapRecNorm + "s (" + t1lapRecNormCar + ")", 685, 580);
            	}
            	if(t1lapRecNorm == null){
            		g2d.drawString("All Cars: " + "No records yet", 685, 580);
            	}
            	if(t1lapRecAveSmart != null){
            		g2d.drawString("Smart: " + t1lapRecAveSmart + "s", 430, 602);
            	}
            	if(t1lapRecAveSmart == null){
            		g2d.drawString("Smart: " + "No records yet", 430, 602);
            	}
            	if(t1lapRecSmart != null){
            		g2d.drawString("Smart: " + t1lapRecSmart + "s", 685, 602);
            	}
            	if(t1lapRecSmart == null){
            		g2d.drawString("Smart: " + "No records yet", 685, 602);
            	}
            	
            }
            if(trackNumber == 2){
            	g2d.drawImage(trackBackgroundImage, (int)trackBox2.getX(), (int)trackBox2.getY(), null);
            	g2d.drawImage(track2InfoBGImage, 374, 370, null);
            	g2d.setFont(font3);
            	g2d.drawString("Top 10 Lap Averages:", 415, 550);
            	g2d.drawString("Fastest Laps:", 670, 550);
            	g2d.setFont(font6);
            	if(t2lapRecAveNorm != null){
            		g2d.drawString("All Cars: " + t2lapRecAveNorm + "s (" + t2lapRecAveNormCar + ")", 430, 580);
            	}
            	if(t2lapRecAveNorm == null){
            		g2d.drawString("All Cars: " + "No records yet", 430, 580);
            	}
            	if(t2lapRecNorm != null){
            		g2d.drawString("All Cars: " + t2lapRecNorm + "s (" + t2lapRecNormCar + ")", 685, 580);
            	}
            	if(t2lapRecNorm == null){
            		g2d.drawString("All Cars: " + "No records yet", 685, 580);
            	}
            	if(t2lapRecAveSmart != null){
            		g2d.drawString("Smart: " + t2lapRecAveSmart + "s", 430, 602);
            	}
            	if(t2lapRecAveSmart == null){
            		g2d.drawString("Smart: " + "No records yet", 430, 602);
            	}
            	if(t2lapRecSmart != null){
            		g2d.drawString("Smart: " + t2lapRecSmart + "s", 685, 602);
            	}
            	if(t2lapRecSmart == null){
            		g2d.drawString("Smart: " + "No records yet", 685, 602);
            	}
            }
            if(trackNumber == 3){
            	g2d.drawImage(trackBackgroundImage, (int)trackBox3.getX(), (int)trackBox3.getY(), null);
            	g2d.drawImage(track3InfoBGImage, 374, 370, null);
            	g2d.setFont(font3);
            	g2d.drawString("The Test Track does not hold records", 415, 550);
            }
            
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.95f));
           
           
            g2d.drawImage(track1Image, (int)trackBox1.getX() + 50, (int)trackBox1.getY() + 31, null);
            g2d.drawImage(track2Image, (int)trackBox2.getX() + 50, (int)trackBox2.getY() + 31, null);
            g2d.drawImage(track3Image, (int)trackBox3.getX() + 50, (int)trackBox3.getY() + 31, null);
    		
    		
    	}
    	
    	else if(menuNumber == 4){
    		
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
    		if(backIsHovered){
    			g2d.drawImage(arrowBackgroundImage, (int)back.getX(), (int)back.getY(), null);
    		}
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
    		if(backIsClicked){
    			g2d.drawImage(arrowBackgroundImage, (int)back.getX(), (int)back.getY(), null);
    		}
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
    		g2d.drawImage(leftArrowImage, (int)back.getX(), (int)back.getY(), null);
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
    		
    		if(trackButtonBox1IsHovered){
    			g2d.drawImage(trackButtonBGImage, (int)trackButtonBox1.getX(), (int)trackButtonBox1.getY(), null);
    		}
    		if(trackButtonBox2IsHovered){
    			g2d.drawImage(trackButtonBGImage, (int)trackButtonBox2.getX(), (int)trackButtonBox2.getY(), null);
    		}
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
    		if(trackButtonBox1IsPressed){
    			g2d.drawImage(trackButtonBGImage, (int)trackButtonBox1.getX(), (int)trackButtonBox1.getY(), null);
    		}
    		if(trackButtonBox2IsPressed){
    			g2d.drawImage(trackButtonBGImage, (int)trackButtonBox2.getX(), (int)trackButtonBox2.getY(), null);
    		}
    		
    		
    		
    		g2d.drawImage(track1buttonImage, (int)trackButtonBox1.getX(), (int)trackButtonBox1.getY(), null);
    		g2d.drawImage(track2buttonImage, (int)trackButtonBox2.getX(), (int)trackButtonBox2.getY(), null);
    		
    		
    		
    	}
    	
    	
    	// Huipputuloslistojen piirto. Kaikissa listoissa on tietyt samat elementit.
    	else if(menuNumber == 101 || menuNumber == 111 || menuNumber == 102 || menuNumber == 112 || menuNumber == 201 || menuNumber == 211 || menuNumber == 202 || menuNumber == 212){
    		g2d.setFont(font2);
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
    		if(menuNumber != 101 && menuNumber != 201){
	    		if(backIsHovered){
	    			g2d.drawImage(arrowBackgroundImage, (int)back.getX(), (int)back.getY(), null);
	    		}
    		}
    		if(menuNumber != 112 && menuNumber != 212){
	    		if(nextIsHovered){
	    			g2d.drawImage(arrowBackgroundImage, (int)next.getX(), (int)next.getY(), null);
	    		}
    		}
    		if(upIsHovered){
    			g2d.drawImage(upArrowBGImage, (int)up.getX(), (int)up.getY(), null);
    		}
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
    		if(menuNumber != 101 && menuNumber != 201){
	    		if(backIsClicked){
	    			g2d.drawImage(arrowBackgroundImage, (int)back.getX(), (int)back.getY(), null);
	    		}
    		}
    		if(menuNumber != 112 && menuNumber != 212){
	    		if(nextIsClicked){
	    			g2d.drawImage(arrowBackgroundImage, (int)next.getX(), (int)next.getY(), null);
	    			
	    		}
    		}
    		if(upIsPressed){
    			g2d.drawImage(upArrowBGImage, (int)up.getX(), (int)up.getY(), null);
    		}
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
    		if(menuNumber != 101 && menuNumber != 201){
    			g2d.drawImage(leftArrowImage, (int)back.getX(), (int)back.getY(), null);
    		}
    		if(menuNumber != 112 && menuNumber != 212){
    			g2d.drawImage(rightArrowImage, (int)next.getX(), (int)next.getY(), null);
    		}
    		g2d.drawImage(upArrowButtonImage, (int)up.getX(), (int)up.getY(), null);
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
    		
    		
    		
    		
    		
    		int i = 0;
    		// Tulosten haku tiedostoista.
			try {
				BufferedReader in = new BufferedReader(new FileReader("Data\\File.txt"));
				if(menuNumber == 101){
	    			g2d.drawImage(scoreBackground101Image, 270, 65, null);
	    			in.close();
	    			in = new BufferedReader(new FileReader("Data\\Track1 TopAverages.txt"));
				}
				if(menuNumber == 111){
	    			g2d.drawImage(scoreBackground111Image, 270, 65, null);
	    			in.close();
	    			in = new BufferedReader(new FileReader("Data\\Track1 TopLaps.txt"));
				}
				if(menuNumber == 102){
	    			g2d.drawImage(scoreBackground102Image, 270, 65, null);
	    			in.close();
	    			in = new BufferedReader(new FileReader("Data\\Track1 TopAverages Smart.txt"));
				}
				if(menuNumber == 112){
	    			g2d.drawImage(scoreBackground112Image, 270, 65, null);
	    			in.close();
	    			in = new BufferedReader(new FileReader("Data\\Track1 TopLaps Smart.txt"));
				}
				if(menuNumber == 201){
	    			g2d.drawImage(scoreBackground201Image, 270, 65, null);
	    			in.close();
	    			in = new BufferedReader(new FileReader("Data\\Track2 TopAverages.txt"));
				}
				if(menuNumber == 211){
	    			g2d.drawImage(scoreBackground211Image, 270, 65, null);
	    			in.close();
	    			in = new BufferedReader(new FileReader("Data\\Track2 TopLaps.txt"));
				}
				if(menuNumber == 202){
	    			g2d.drawImage(scoreBackground202Image, 270, 65, null);
	    			in.close();
	    			in = new BufferedReader(new FileReader("Data\\Track2 TopAverages Smart.txt"));
				}
				if(menuNumber == 212){
	    			g2d.drawImage(scoreBackground212Image, 270, 65, null);
	    			in.close();
	    			in = new BufferedReader(new FileReader("Data\\Track2 TopLaps Smart.txt"));
				}
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
				String line;
				int scoreHeight = 222;
				int lineNumber = 1;
				int posNumber = 1;
				while((line = in.readLine()) != null){
					if(i >= 45){
						break;
					}
					if(lineNumber == 1){
						g2d.drawString(posNumber + "." + " " + line, 320, scoreHeight);
						lineNumber = 2;
						posNumber++;
					}
					else if(lineNumber == 2){
						g2d.drawString(line, 690, scoreHeight);
						lineNumber = 3;
					}
					else if(lineNumber == 3){
						g2d.drawString(line, 890, scoreHeight);
						scoreHeight += 35;
						lineNumber = 1;
					}
					i++;
				    
				}
				in.close();
			} catch (FileNotFoundException e1) {
				
				e1.printStackTrace();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
    		
			
    		g2d.drawString("Player", 320, 180);
    		g2d.drawString("Car", 690, 180);
    		g2d.drawString("Time", 890, 180);
    	}
    	
    	else if(menuNumber == 9){
    		// Pelaajan nimen talletusikkuna
    		add(playerNameGetter);
    		revalidate();
            playerNameGetter.repaint();
            
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
    		if(backIsHovered){
    			g2d.drawImage(arrowBackgroundImage, (int)back.getX(), (int)back.getY(), null);
    		}
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
    		if(backIsClicked){
    			g2d.drawImage(arrowBackgroundImage, (int)back.getX(), (int)back.getY(), null);
    		}
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
    		g2d.drawImage(leftArrowImage, (int)back.getX(), (int)back.getY(), null);
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    		
            
            g2d.setFont(font3);
            g2d.drawString("Enter your name in the field below", 450, 260);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
    		
    		if(okIsHovered){
    			g2d.drawImage(Buttonbackground2Image, (int)setName.getX(), (int)setName.getY(), null);
    		}
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
    		if(okIsPressed){
    			g2d.drawImage(Buttonbackground2Image, (int)setName.getX(), (int)setName.getY(), null);
    		}
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            g2d.drawImage(okButtonImage, (int)setName.getX(), (int)setName.getY(), null);
    		
    	}
    	
    	// Ajon p‰‰tteeksi tuleva menuruutu, joka kertoo monenneksi pelaaja sijoittui k‰ytetyn autonsa ja ajetun ratansa mukaisessa listassa.
    	else if(menuNumber == 10){
    		int i = 0;
    		String line;
			int lineNumber = 1;
			int posNumber = 1;
			int resultPos = 0;
			boolean nameEquals = false;
			// Tulosten haku tiedostosta ja vertailu pelaajan nimen ja ajan mukaan.
			try{
				BufferedReader in = new BufferedReader(new FileReader("Data\\File.txt"));
				if(trackNumber == 1){
					in.close();
					if(carNumber == 11){
						in = new BufferedReader(new FileReader("Data\\Track1 TopAverages Smart.txt"));
					}
					else{
						in = new BufferedReader(new FileReader("Data\\Track1 TopAverages.txt"));
					}
					
				}
				if(trackNumber == 2){
					in.close();
					if(carNumber == 11){
						in = new BufferedReader(new FileReader("Data\\Track2 TopAverages Smart.txt"));
					}
					else{
						in = new BufferedReader(new FileReader("Data\\Track2 TopAverages.txt"));
					}
				}
				while((line = in.readLine()) != null){
					if(i >= 45){
						break;
					}
					if(lineNumber == 1){
						if(line.equals(playerName)){
							nameEquals = true;
						}
						lineNumber = 2;
						posNumber++;
					}
					else if(lineNumber == 2){
						lineNumber = 3;
					}
					else if(lineNumber == 3){
						if(Double.parseDouble(line) == game.averageLap){
							if(nameEquals){
								resultPos = posNumber-1;
								break;
							}
							else{
								nameEquals = false;
							}
						}
						lineNumber = 1;
					}
					i++;
				    
				}
				in.close();
			} 
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} 
			catch (IOException e1) {
				e1.printStackTrace();
			}
    		
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.85f));
			
    		g2d.drawImage(resultBackgroundImage, 370, 80, null);
    		
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
    		
    		if(ok2IsHovered){
    			g2d.drawImage(Buttonbackground2Image, (int)okBox.getX(), (int)okBox.getY(), null);
    		}
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
    		if(ok2IsPressed){
    			g2d.drawImage(Buttonbackground2Image, (int)okBox.getX(), (int)okBox.getY(), null);
    		}
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    		// Piirtohifistely‰.
    		String posEnding = "";
    		if(resultPos == 1){
    			posEnding = "st";
    		}
    		if(resultPos == 2){
    			posEnding = "nd";
    		}
    		if(resultPos == 3){
    			posEnding = "rd";
    		}
    		if(resultPos >= 4){
    			posEnding = "th";
    		}
    		
    		g2d.setFont(font4);
    		stringLen = (int)g2d.getFontMetrics().getStringBounds("Your time was: " + game.averageLap + " seconds", g2d).getWidth(); 
			startText = 1300/2 - stringLen/2; 
    		g2d.drawString("Your time was: " + game.averageLap + " seconds", startText, 200);
    		if(resultPos != 0){
    			stringLen = (int)g2d.getFontMetrics().getStringBounds("You placed", g2d).getWidth(); 
    			startText = 1300/2 - stringLen/2;  
    			g2d.drawString("You placed", startText, 270);
    			g2d.setColor(textColor);
    			g2d.setFont(font5);
    			stringLen = (int)g2d.getFontMetrics().getStringBounds(resultPos + posEnding, g2d).getWidth();  
                startText = 1300/2 - stringLen/2;  
    			g2d.drawString(resultPos + posEnding, startText, 340);
    			String endPhrase = "";
    			// Kommentti sijoituksen mukaan.
    			if(resultPos == 1){
    				endPhrase = "You nailed it!";
    			}
    			if(resultPos > 1 && resultPos <= 3){
    				endPhrase = "Excellent!";
    			}
    			if(resultPos > 3 && resultPos <= 9){
    				endPhrase = "Well done!";
    			}
    			if(resultPos > 9){
    				endPhrase = "Not bad!";
    			}
    			stringLen = (int)g2d.getFontMetrics().getStringBounds(endPhrase, g2d).getWidth();  
                startText = 1300/2 - stringLen/2;  
                g2d.drawString(endPhrase, startText, 520);
    			
    		}
    		g2d.setFont(font4);
    		g2d.setColor(Color.WHITE);
    		// Ilmoitus jos ei ylt‰nyt listalle.
    		if(resultPos == 0){
    			stringLen = (int)g2d.getFontMetrics().getStringBounds("Out of ranking", g2d).getWidth(); 
    			startText = 1300/2 - stringLen/2;  
    			g2d.drawString("Out of ranking", startText, 300);
    		}
    		g2d.setFont(font4);
    		stringLen = (int)g2d.getFontMetrics().getStringBounds("Your best lap was: " + game.bestLap + " seconds", g2d).getWidth(); 
			startText = 1300/2 - stringLen/2; 
    		
    		g2d.drawString("Your best lap was: " + game.bestLap + " seconds", startText, 410);
    		
    		
    		
    		g2d.drawImage(okButtonImage, (int)okBox.getX(), (int)okBox.getY(), null);
    		
    		
		
    		
    	}
    	
        
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}
	/*
	 * Kaikki painallukseen liittyv‰t toiminnot suoritetaan painikkeen vapautuksen yhteydess‰. Toiminnot on toteutettu eri menun numeroiden mukaan ja yleens‰ vaihtahat menun numeroa.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		ok2IsPressed = false;
		ok2IsHovered = false;
		if(menuNumber == 1){
			if(e.getX() >= play.getX() && e.getX() <= play.getX()+play.getWidth()){
				if(e.getY() >= play.getY() && e.getY() <= play.getY()+play.getHeight()){
					if(playerName != null){
						if(playerName.length() != 0){
							menuNumber = 2;
						}
					}
					else{
						displayWarningMessage = true;
					}
					playIsPressed = false;
					repaint();
				}
			}
			
			if(e.getX() >= scores.getX() && e.getX() <= scores.getX()+scores.getWidth()){
				if(e.getY() >= scores.getY() && e.getY() <= scores.getY()+scores.getHeight()){
					menuNumber = 4;
					repaint();
				}
			}
			if(e.getX() >= player.getX() && e.getX() <= player.getX()+player.getWidth()){
				if(e.getY() >= player.getY() && e.getY() <= player.getY()+player.getHeight()){
					menuNumber = 9;
					
					repaint();
				}
			}
			backIsClicked = false;
			backIsHovered = false;
			
		}
		
		else if(menuNumber == 2){
			playIsHovered = false;
			nextIsClicked = false;
			backIsClicked = false;
			for(int i = 0; i < 11; i++){
				if(e.getX() >= carBoxList.get(i).getX() && e.getX() <= carBoxList.get(i).getX()+carBoxList.get(i).getWidth()){
					if(e.getY() >= carBoxList.get(i).getY() && e.getY() <= carBoxList.get(i).getY()+carBoxList.get(i).getHeight()){
						carNumber = i+1;
						carIsSelected = true;
						repaint();
					}
				}
			}
			if(e.getX() >= back.getX() && e.getX() <= back.getX()+back.getWidth()){
				if(e.getY() >= back.getY() && e.getY() <= back.getY()+back.getHeight()){
					menuNumber = 1;
					carIsSelected = false;
					carNumber = 0;
					repaint();
				}
			}
			
			
			else if(e.getX() >= next.getX() && e.getX() <= next.getX()+next.getWidth()){
				if(e.getY() >= next.getY() && e.getY() <= next.getY()+next.getHeight()){
					if(carNumber != 0){
						menuNumber = 3;
						trackRecordGetter();
						repaint();
					}
					repaint();
				}
			}
		}
		
		else if(menuNumber == 3){
			nextIsClicked = false;
			nextIsHovered = false;
			backIsClicked = false;
			if(e.getX() >= trackBox1.getX() && e.getX() <= trackBox1.getX()+trackBox1.getWidth()){
				if(e.getY() >= trackBox1.getY() && e.getY() <= trackBox1.getY()+trackBox1.getHeight()){
					trackNumber = 1;
					repaint();
				}
			}
			else if(e.getX() >= trackBox2.getX() && e.getX() <= trackBox2.getX()+trackBox2.getWidth()){
				if(e.getY() >= trackBox2.getY() && e.getY() <= trackBox2.getY()+trackBox2.getHeight()){
					trackNumber = 2;
					repaint();
				}
			}
			else if(e.getX() >= trackBox3.getX() && e.getX() <= trackBox3.getX()+trackBox3.getWidth()){
				if(e.getY() >= trackBox3.getY() && e.getY() <= trackBox3.getY()+trackBox3.getHeight()){
					trackNumber = 3;
					repaint();
				}
			}
			else if(e.getX() >= back.getX() && e.getX() <= back.getX()+back.getWidth()){
				if(e.getY() >= back.getY() && e.getY() <= back.getY()+back.getHeight()){
					menuNumber = 2;
					repaint();
				}
			}
			else if(e.getX() >= next.getX() && e.getX() <= next.getX()+next.getWidth()){
				if(e.getY() >= next.getY() && e.getY() <= next.getY()+next.getHeight()){
					if(trackNumber != 0){
						game.startGame();
					}
				}
			}
		}
		
		else if(menuNumber == 4){
			nextIsClicked = false;
			nextIsHovered = false;
			backIsClicked = false;
			if(e.getX() >= back.getX() && e.getX() <= back.getX()+back.getWidth()){
				if(e.getY() >= back.getY() && e.getY() <= back.getY()+back.getHeight()){
					menuNumber = 1;
					
					
					repaint();
				}
			}
			
			if(e.getX() >= trackButtonBox1.getX() && e.getX() <= trackButtonBox1.getX()+trackButtonBox1.getWidth()){
				if(e.getY() >= trackButtonBox1.getY() && e.getY() <= trackButtonBox1.getY()+trackButtonBox1.getHeight()){
					menuNumber = 101;
					
					
					repaint();
				}
			}
			
			if(e.getX() >= trackButtonBox2.getX() && e.getX() <= trackButtonBox2.getX()+trackButtonBox2.getWidth()){
				if(e.getY() >= trackButtonBox2.getY() && e.getY() <= trackButtonBox2.getY()+trackButtonBox2.getHeight()){
					menuNumber = 201;
					
					
					repaint();
				}
			}
			}
		
		else if(menuNumber == 101 || menuNumber == 201){
			nextIsClicked = false;
			backIsClicked = false;
			trackButtonBox1IsPressed = false;
			trackButtonBox2IsPressed = false;
			trackButtonBox1IsHovered = false;
			trackButtonBox1IsHovered = false;
			
			
			if(e.getX() >= next.getX() && e.getX() <= next.getX()+next.getWidth()){
				if(e.getY() >= next.getY() && e.getY() <= next.getY()+next.getHeight()){
					if(menuNumber == 101){
						menuNumber = 111;
					}
					if(menuNumber == 201){
						menuNumber = 211;
					}
					repaint();
				}
			}
			
			if(e.getX() >= up.getX() && e.getX() <= up.getX()+up.getWidth()){
				if(e.getY() >= up.getY() && e.getY() <= up.getY()+up.getHeight()){
					menuNumber = 4;
					repaint();
				}
			}
			
		}
		else if(menuNumber == 111 || menuNumber == 211){
			nextIsClicked = false;
			backIsClicked = false;
			if(e.getX() >= back.getX() && e.getX() <= back.getX()+back.getWidth()){
				if(e.getY() >= back.getY() && e.getY() <= back.getY()+back.getHeight()){
					if(menuNumber == 111){
						menuNumber = 101;
					}
					if(menuNumber == 211){
						menuNumber = 201;
					}
					
					repaint();
				}
			}
			else if(e.getX() >= next.getX() && e.getX() <= next.getX()+next.getWidth()){
				if(e.getY() >= next.getY() && e.getY() <= next.getY()+next.getHeight()){
					if(menuNumber == 111){
						menuNumber = 102;
					}
					if(menuNumber == 211){
						menuNumber = 202;
					}
					repaint();
				}
			}
			
			if(e.getX() >= up.getX() && e.getX() <= up.getX()+up.getWidth()){
				if(e.getY() >= up.getY() && e.getY() <= up.getY()+up.getHeight()){
					menuNumber = 4;
					repaint();
				}
			}
		}
		else if(menuNumber == 102 || menuNumber == 202){
			nextIsClicked = false;
			backIsClicked = false;
			if(e.getX() >= back.getX() && e.getX() <= back.getX()+back.getWidth()){
				if(e.getY() >= back.getY() && e.getY() <= back.getY()+back.getHeight()){
					if(menuNumber == 102){
						menuNumber = 111;
					}
					if(menuNumber == 202){
						menuNumber = 211;
					}
					
					
					repaint();
				}
			}
			else if(e.getX() >= next.getX() && e.getX() <= next.getX()+next.getWidth()){
				if(e.getY() >= next.getY() && e.getY() <= next.getY()+next.getHeight()){
					if(menuNumber == 102){
						menuNumber = 112;
					}
					if(menuNumber == 202){
						menuNumber = 212;
					}
					repaint();
				}
			}
			
			if(e.getX() >= up.getX() && e.getX() <= up.getX()+up.getWidth()){
				if(e.getY() >= up.getY() && e.getY() <= up.getY()+up.getHeight()){
					menuNumber = 4;
					repaint();
				}
			}
		}
		else if(menuNumber == 112 || menuNumber == 212){
			nextIsClicked = false;
			backIsClicked = false;
			if(e.getX() >= back.getX() && e.getX() <= back.getX()+back.getWidth()){
				if(e.getY() >= back.getY() && e.getY() <= back.getY()+back.getHeight()){
					if(menuNumber == 112){
						menuNumber = 102;
					}
					if(menuNumber == 212){
						menuNumber = 202;
					}
					repaint();
				}
			}
			
			if(e.getX() >= up.getX() && e.getX() <= up.getX()+up.getWidth()){
				if(e.getY() >= up.getY() && e.getY() <= up.getY()+up.getHeight()){
					menuNumber = 4;
					repaint();
				}
			}
		}
		
		
		else if(menuNumber == 9){
			playerIsHovered = false;
			if(e.getX() >= setName.getX() && e.getX() <= setName.getX()+setName.getWidth()){
				if(e.getY() >= setName.getY() && e.getY() <= setName.getY()+setName.getHeight()){
					playerNameTry = playerNameGetter.getText();
					if(playerNameTry.length() != 0){
						playerName = playerNameTry;
					}
					if(playerNameTry.length() >= 18){
						playerName = playerNameTry.substring(0, 18);
					}
					okIsPressed = false;
					okIsHovered = true;
					if(playerNameTry != null){
						if(playerNameTry.length() != 0){
							
							remove(playerNameGetter);
							displayWarningMessage = false;
							menuNumber = 1;
							
						}
					}
					repaint();
				}
			}
			if(e.getX() >= back.getX() && e.getX() <= back.getX()+back.getWidth()){
				if(e.getY() >= back.getY() && e.getY() <= back.getY()+back.getHeight()){
					menuNumber = 1;
					remove(playerNameGetter);
					
					repaint();
				}
			}
		}
		
		else if(menuNumber == 10){
			if(e.getX() >= okBox.getX() && e.getX() <= okBox.getX()+okBox.getWidth()){
				if(e.getY() >= okBox.getY() && e.getY() <= okBox.getY()+okBox.getHeight()){
					ok2IsPressed = false;
					if(trackNumber == 1){
						if(carNumber == 11){
							menuNumber = 102;
						}
						else{
							menuNumber = 101;
						}
					}
					if(trackNumber == 2){
						if(carNumber == 11){
							menuNumber = 202;
						}
						else{
							menuNumber = 201;
						}
					}
					repaint();
				}
			}
			
		}
		
	}
	/*
	 * Painallus muuttaa painikkeiden v‰ri‰/tummuutta.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if(menuNumber == 1){
			okIsPressed = false;
			if(e.getX() >= play.getX() && e.getX() <= play.getX()+play.getWidth()){
				if(e.getY() >= play.getY() && e.getY() <= play.getY()+play.getHeight()){
					playIsPressed = true;
					repaint();
					
					
				}
			}
			if(e.getX() >= scores.getX() && e.getX() <= scores.getX()+scores.getWidth()){
				if(e.getY() >= scores.getY() && e.getY() <= scores.getY()+scores.getHeight()){
					scoresIsPressed = true;
					repaint();
				}
			}
			if(e.getX() >= player.getX() && e.getX() <= player.getX()+player.getWidth()){
				if(e.getY() >= player.getY() && e.getY() <= player.getY()+player.getHeight()){
					playerIsPressed = true;
					repaint();
				}
			}
		}
		else if(menuNumber == 2){
			if(e.getX() >= back.getX() && e.getX() <= back.getX()+back.getWidth()){
				if(e.getY() >= back.getY() && e.getY() <= back.getY()+back.getHeight()){
					backIsClicked = true;
					repaint();
				}
			}
			else if(e.getX() >= next.getX() && e.getX() <= next.getX()+next.getWidth()){
				if(e.getY() >= next.getY() && e.getY() <= next.getY()+next.getHeight()){
					nextIsClicked = true;
					repaint();
				}
			}
			playIsPressed = false;
		}
		else if(menuNumber == 3){
			if(e.getX() >= back.getX() && e.getX() <= back.getX()+back.getWidth()){
				if(e.getY() >= back.getY() && e.getY() <= back.getY()+back.getHeight()){
					backIsClicked = true;
					repaint();
				}
			}
			else if(e.getX() >= next.getX() && e.getX() <= next.getX()+next.getWidth()){
				if(e.getY() >= next.getY() && e.getY() <= next.getY()+next.getHeight()){
					nextIsClicked = true;
					repaint();
				}
			}
		}
		else if(menuNumber == 4){
			scoresIsHovered = false;
			scoresIsPressed = false;
			upIsPressed = false;
			upIsHovered = false;
			if(e.getX() >= back.getX() && e.getX() <= back.getX()+back.getWidth()){
				if(e.getY() >= back.getY() && e.getY() <= back.getY()+back.getHeight()){
					backIsClicked = true;
					repaint();
				}
			}
			
			if(e.getX() >= trackButtonBox1.getX() && e.getX() <= trackButtonBox1.getX()+trackButtonBox1.getWidth()){
				if(e.getY() >= trackButtonBox1.getY() && e.getY() <= trackButtonBox1.getY()+trackButtonBox1.getHeight()){
					trackButtonBox1IsPressed = true;
					
					
					repaint();
				}
			}
			
			if(e.getX() >= trackButtonBox2.getX() && e.getX() <= trackButtonBox2.getX()+trackButtonBox2.getWidth()){
				if(e.getY() >= trackButtonBox2.getY() && e.getY() <= trackButtonBox2.getY()+trackButtonBox2.getHeight()){
					trackButtonBox2IsPressed = true;
					
					
					repaint();
				}
			}
			
		}
		
		else if(menuNumber == 101 || menuNumber == 111 || menuNumber == 102 || menuNumber == 112 || menuNumber == 201 || menuNumber == 211 || menuNumber ==  202 || menuNumber ==  212){
			scoresIsHovered = false;
			scoresIsPressed = false;
			trackButtonBox1IsHovered = false;
    		trackButtonBox2IsHovered = false;
			if(e.getX() >= back.getX() && e.getX() <= back.getX()+back.getWidth()){
				if(e.getY() >= back.getY() && e.getY() <= back.getY()+back.getHeight()){
					backIsClicked = true;
					repaint();
				}
			}
			else if(e.getX() >= next.getX() && e.getX() <= next.getX()+next.getWidth()){
				if(e.getY() >= next.getY() && e.getY() <= next.getY()+next.getHeight()){
					nextIsClicked = true;
					repaint();
				}
			}
			else if(e.getX() >= up.getX() && e.getX() <= up.getX()+up.getWidth()){
				if(e.getY() >= up.getY() && e.getY() <= up.getY()+up.getHeight()){
					upIsPressed = true;
					repaint();
				}
			}
			
		}
		
		else if(menuNumber == 9){
			playerIsPressed = false;
			if(e.getX() >= setName.getX() && e.getX() <= setName.getX()+setName.getWidth()){
				if(e.getY() >= setName.getY() && e.getY() <= setName.getY()+setName.getHeight()){
					okIsPressed = true;
					repaint();
				}
			}
			else if(e.getX() >= back.getX() && e.getX() <= back.getX()+back.getWidth()){
				if(e.getY() >= back.getY() && e.getY() <= back.getY()+back.getHeight()){
					backIsClicked = true;
					repaint();
				}
			}
		}
		
		else if(menuNumber == 10){
			if(e.getX() >= okBox.getX() && e.getX() <= okBox.getX()+okBox.getWidth()){
				if(e.getY() >= okBox.getY() && e.getY() <= okBox.getY()+okBox.getHeight()){
					ok2IsPressed = true;
					repaint();
				}
			}
			
		}

		
		
	}
	

	@Override
	public void mouseDragged(MouseEvent arg0) {
	// TODO Auto-generated method stub

	}


	/*
	 * Hiiren asettaminen painikkeiden p‰‰lle muuttaa niiden ulkoasua ja joissain tilanteissa n‰ytt‰‰ lis‰informaatiota.
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
        if(menuNumber == 1){
        	okIsHovered = false;
                if(e.getX() >= play.getX() && e.getX() <= play.getX()+play.getWidth()
                                && e.getY() >= play.getY() && e.getY() <= play.getY()+play.getHeight()){
                       
                               
                        playIsHovered = true;
                        scoresIsHovered = false;
                        playerIsHovered = false;
                        hasBeenOverButton = true;
                        repaint();
                        
                        
                       
                }
               
                else if(e.getX() >= scores.getX() && e.getX() <= scores.getX()+scores.getWidth()
                                && e.getY() >= scores.getY() && e.getY() <= scores.getY()+scores.getHeight()){
                       
                        playIsHovered = false;
                        playerIsHovered = false;
                        scoresIsHovered = true;
                        hasBeenOverButton = true;
                        repaint();
                       
                }
                else if(e.getX() >= player.getX() && e.getX() <= player.getX()+player.getWidth()
                        && e.getY() >= player.getY() && e.getY() <= player.getY()+player.getHeight()){
               
                       
                playerIsHovered = true;
                playIsHovered = false;
                scoresIsHovered = false;
                hasBeenOverButton = true;
                repaint();
                
                
               
        }
                else{
                        playIsHovered = false;
                        scoresIsHovered = false;
                        playerIsHovered = false;
                        if(hasBeenOverButton){
	                		repaint();
	                		hasBeenOverButton = false;
	                	}
                }
        }
       
        else if(menuNumber == 2){
                for(int i = 0; i < 11; i++){
                        if(e.getX() >= carBoxList.get(i).getX() && e.getX() <= carBoxList.get(i).getX()+carBoxList.get(i).getWidth()
                                        && e.getY() >= carBoxList.get(i).getY() && e.getY() <= carBoxList.get(i).getY()+carBoxList.get(i).getHeight()){
                        	carIsHovered = true;
                        	backIsHovered = false;
                        	nextIsHovered = false;
                        	carHoverNumber = i + 1;
                            repaint();
                            hasBeenOverButton = true;
                            
                        	break;
                        }
                        
                        else if(e.getX() >= back.getX() && e.getX() <= back.getX()+back.getWidth()
                                && e.getY() >= back.getY() && e.getY() <= back.getY()+back.getHeight()){
                	
		                	backIsHovered = true;
		                	nextIsHovered = false;
		                	carIsHovered = false;
	                        repaint();
	                        hasBeenOverButton = true;
	                        
                       
                        }
               
               
                        else if(e.getX() >= next.getX() && e.getX() <= next.getX()+next.getWidth()
                                && e.getY() >= next.getY() && e.getY() <= next.getY()+next.getHeight()){
		                	backIsHovered = false;
		                	nextIsHovered = true;
		                	carIsHovered = false;
	                        repaint();
	                        hasBeenOverButton = true;
	                        
		                }
		                else{
		                	backIsHovered = false;
		                	nextIsHovered = false;
		                	carIsHovered = false;
		                	if(hasBeenOverButton){
		                		repaint();
		                		hasBeenOverButton = false;
		                	}
		                	
		                	
		                }
                        
                }
                
                
        }
       
        else if(menuNumber == 3){
        	nextIsClicked = false;
			nextIsHovered = false;
                if(e.getX() >= trackBox1.getX() && e.getX() <= trackBox1.getX()+trackBox1.getWidth()
                                && e.getY() >= trackBox1.getY() && e.getY() <= trackBox1.getY()+trackBox1.getHeight()){
                	backIsHovered = false;
                	nextIsHovered = false;
                	track1IsHovered = true;
                	track2IsHovered = false;
                	track3IsHovered = false;
                	repaint();
                    hasBeenOverButton = true;
                    
                       
                }
                else if(e.getX() >= trackBox2.getX() && e.getX() <= trackBox2.getX()+trackBox2.getWidth()
                                && e.getY() >= trackBox2.getY() && e.getY() <= trackBox2.getY()+trackBox2.getHeight()){
                	backIsHovered = false;
                	nextIsHovered = false;
                	track2IsHovered = true;
                	track1IsHovered = false;
                	track3IsHovered = false;
                	repaint();
                    hasBeenOverButton = true;
                    
                       
                }
                else if(e.getX() >= trackBox3.getX() && e.getX() <= trackBox3.getX()+trackBox3.getWidth()
                                && e.getY() >= trackBox3.getY() && e.getY() <= trackBox3.getY()+trackBox3.getHeight()){
                	backIsHovered = false;
                	nextIsHovered = false;
                	track3IsHovered = true;
                	track1IsHovered = false;
                	track2IsHovered = false;
                	repaint();
                    hasBeenOverButton = true;
                    
                       
                }
                else if(e.getX() >= back.getX() && e.getX() <= back.getX()+back.getWidth()
                                && e.getY() >= back.getY() && e.getY() <= back.getY()+back.getHeight()){
                	backIsHovered = true;
                	nextIsHovered = false;
                	track1IsHovered = false;
                	track2IsHovered = false;
                	track3IsHovered = false;
                	repaint();
                    hasBeenOverButton = true;
                    
                       
                }
                else if(e.getX() >= next.getX() && e.getX() <= next.getX()+next.getWidth()
                                && e.getY() >= next.getY() && e.getY() <= next.getY()+next.getHeight()){
                	backIsHovered = false;
                	nextIsHovered = true;
                	track1IsHovered = false;
                	track2IsHovered = false;
                	track3IsHovered = false;
                	repaint();
                    hasBeenOverButton = true;
                    
                }
                else{
                	backIsHovered = false;
                	nextIsHovered = false;
                	track1IsHovered = false;
                	track2IsHovered = false;
                	track3IsHovered = false;
                	if(hasBeenOverButton){
                		repaint();
                		hasBeenOverButton = false;
                	}
                }
        }
        
        else if(menuNumber == 4){
        	
        	if(e.getX() >= back.getX() && e.getX() <= back.getX()+back.getWidth()
                    && e.getY() >= back.getY() && e.getY() <= back.getY()+back.getHeight()){
    	
        		backIsHovered = true;
        		nextIsHovered = false;
        		trackButtonBox1IsHovered = false;
        		trackButtonBox2IsHovered = false;
        		repaint();
                hasBeenOverButton = true;
                
           
        	}
        	
        	
        	else if(e.getX() >= trackButtonBox1.getX() && e.getX() <= trackButtonBox1.getX()+trackButtonBox1.getWidth() &&
				e.getY() >= trackButtonBox1.getY() && e.getY() <= trackButtonBox1.getY()+trackButtonBox1.getHeight()){
					trackButtonBox1IsHovered = true;
					trackButtonBox2IsHovered = false;
					
					repaint();
					hasBeenOverButton = true;
				
			}
			
				else if(e.getX() >= trackButtonBox2.getX() && e.getX() <= trackButtonBox2.getX()+trackButtonBox2.getWidth() &&
						e.getY() >= trackButtonBox2.getY() && e.getY() <= trackButtonBox2.getY()+trackButtonBox2.getHeight()){
							trackButtonBox2IsHovered = true;
							trackButtonBox1IsHovered = false;
							
							repaint();
							hasBeenOverButton = true;
						
					}
        	
        	
        	else{
        		backIsHovered = false;
        		nextIsHovered = false;
        		trackButtonBox1IsHovered = false;
        		trackButtonBox2IsHovered = false;
        		if(hasBeenOverButton){
            		repaint();
            		hasBeenOverButton = false;
            	}
        	}
        }
        
        else if(menuNumber == 101 || menuNumber == 111 || menuNumber == 102 || menuNumber == 112 || menuNumber == 201 || menuNumber == 211 || menuNumber ==  202 || menuNumber ==  212){
        	if(e.getX() >= back.getX() && e.getX() <= back.getX()+back.getWidth()
                    && e.getY() >= back.getY() && e.getY() <= back.getY()+back.getHeight()){
    	
        		backIsHovered = true;
        		nextIsHovered = false;
        		upIsHovered = false;
        		repaint();
                hasBeenOverButton = true;
                
           
        	}
   
   
        	else if(e.getX() >= next.getX() && e.getX() <= next.getX()+next.getWidth()
                    && e.getY() >= next.getY() && e.getY() <= next.getY()+next.getHeight()){
        		backIsHovered = false;
        		nextIsHovered = true;
        		upIsHovered = false;
        		repaint();
                hasBeenOverButton = true;
                
        	}
        	
        	else if(e.getX() >= up.getX() && e.getX() <= up.getX()+up.getWidth()
                    && e.getY() >= up.getY() && e.getY() <= up.getY()+up.getHeight()){
        		upIsHovered = true;
        		backIsHovered = false;
        		nextIsHovered = false;
        		repaint();
                hasBeenOverButton = true;
                
        	}
        	
        	else{
        		backIsHovered = false;
        		nextIsHovered = false;
        		upIsHovered = false;
        		if(hasBeenOverButton){
            		repaint();
            		hasBeenOverButton = false;
            	}
        	}
        }
        
        else if(menuNumber == 6){
        	if(e.getX() >= back.getX() && e.getX() <= back.getX()+back.getWidth()
                    && e.getY() >= back.getY() && e.getY() <= back.getY()+back.getHeight()){
    	
        		backIsHovered = true;
        		nextIsHovered = false;
        		repaint();
                hasBeenOverButton = true;
                
           
        	}
   
   
        	else if(e.getX() >= next.getX() && e.getX() <= next.getX()+next.getWidth()
                    && e.getY() >= next.getY() && e.getY() <= next.getY()+next.getHeight()){
        		backIsHovered = false;
        		nextIsHovered = true;
        		repaint();
                hasBeenOverButton = true;
                
        	}
        	else{
        		backIsHovered = false;
        		nextIsHovered = false;
        		if(hasBeenOverButton){
            		repaint();
            		hasBeenOverButton = false;
            	}
        	}
        }
        
        else if(menuNumber == 9){
        	if(e.getX() >= setName.getX() && e.getX() <= setName.getX()+setName.getWidth()
                    && e.getY() >= setName.getY() && e.getY() <= setName.getY()+setName.getHeight()){
        		okIsHovered = true;
        		backIsHovered = false;
        		if(!hasBeenOverButton){
        			repaint();
        			hasBeenOverButton = true;
        		}
        	}
        	
        	else if(e.getX() >= back.getX() && e.getX() <= back.getX()+back.getWidth()
                    && e.getY() >= back.getY() && e.getY() <= back.getY()+back.getHeight()){
            	backIsHovered = true;
            	okIsHovered = false;
            	if(!hasBeenOverButton){
                	repaint();
                	hasBeenOverButton = true;
                }
           
            }
            
            else{
            	okIsHovered = false;
            	backIsHovered = false;
            	if(hasBeenOverButton){
            		repaint();
            		hasBeenOverButton = false;
            	}
            }
            
            
           
    
        }
        else if(menuNumber == 10){
        	if(e.getX() >= okBox.getX() && e.getX() <= okBox.getX()+okBox.getWidth()
                    && e.getY() >= okBox.getY() && e.getY() <= okBox.getY()+okBox.getHeight()){
        		ok2IsHovered = true;
        		if(!hasBeenOverButton){
        			repaint();
        			hasBeenOverButton = true;
        		}
        	}
        	
            else{
            	ok2IsHovered = false;
            	if(hasBeenOverButton){
            		repaint();
            		hasBeenOverButton = false;
            	}
            }
            
            
           
    
        }
       
	}
	
	/*
	 * trackRecordGetter-metodi hakee kaikkien ratojen enn‰tykset tiedostoista k‰ytett‰v‰ksi radanvalintaikkunassa.
	 */
	public void trackRecordGetter(){
		String line;
		int fileNum = 1;
		
		try{
			
			BufferedReader in = new BufferedReader(new FileReader("Data\\File.txt"));
			while(true){
			if(fileNum == 1){
				in.close();
				in = new BufferedReader(new FileReader("Data\\Track1 TopAverages.txt"));
				}
			if(fileNum == 2){
				in.close();
				in = new BufferedReader(new FileReader("Data\\Track1 TopLaps.txt"));
			}
			if(fileNum == 3){
				in.close();
				in = new BufferedReader(new FileReader("Data\\Track1 TopAverages Smart.txt"));
			}
			if(fileNum == 4){
				in.close();
				in = new BufferedReader(new FileReader("Data\\Track1 TopLaps Smart.txt"));
			}
			if(fileNum == 5){
				in.close();
				in = new BufferedReader(new FileReader("Data\\Track2 TopAverages.txt"));
				}
			if(fileNum == 6){
				in.close();
				in = new BufferedReader(new FileReader("Data\\Track2 TopLaps.txt"));
			}
			if(fileNum == 7){
				in.close();
				in = new BufferedReader(new FileReader("Data\\Track2 TopAverages Smart.txt"));
			}
			if(fileNum == 8){
				in.close();
				in = new BufferedReader(new FileReader("Data\\Track2 TopLaps Smart.txt"));
			}
			if(fileNum == 9){
				break;
			}
			int i = 1;
			String dummy = "";
			while((line = in.readLine()) != null){
				
				if(i == 1){
					if(fileNum == 1){
						
						t1lapRecAveNormCar = in.readLine();
						t1lapRecAveNorm = in.readLine();
					}
					if(fileNum == 2){
						t1lapRecNormCar = in.readLine();
						t1lapRecNorm = in.readLine();
					}
					if(fileNum == 3){
						dummy = in.readLine();
						t1lapRecAveSmart = in.readLine();
					}
					if(fileNum == 4){
						dummy = in.readLine();
						t1lapRecSmart = in.readLine();
					}
					if(fileNum == 5){
						t2lapRecAveNormCar = in.readLine();
						t2lapRecAveNorm = in.readLine();
					}
					if(fileNum == 6){
						t2lapRecNormCar = in.readLine();
						t2lapRecNorm = in.readLine();
					}
					if(fileNum == 7){
						dummy = in.readLine();
						t2lapRecAveSmart = in.readLine();
					}
					if(fileNum == 8){
						dummy = in.readLine();
						t2lapRecSmart = in.readLine();
					}
					i = 1;
					fileNum++;
					
					break;
				}
				i++;
			    
			}
			if(line == null){
				fileNum++;
			}
			}
			in.close();
			
		} 
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	

	
}