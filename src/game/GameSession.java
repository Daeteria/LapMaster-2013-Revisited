package game;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*
 * GameSession-luokka sis‰lt‰‰ ajotilan toiminnallisuudet. Se sis‰lt‰‰ kaikkien ajotilassa k‰ytett‰vien autojen kuvat, arvot ja autojen toiminnallisuudet. 
 */
public class GameSession extends JPanel{
	public boolean physicsCalculated;
	private static final long serialVersionUID = 1L;
	// Haetaan autojen kuvat ja luodaan Car2-oliot ja lukuisat attribuutit.
	private String aveImage = "Images\\Aventador smaller1.png";
	private String mustangImage = "Images\\Mustang smaller1.png";
	private String murcieImage = "Images\\Murcielago smaller2.png";
	private String db9Image = "Images\\Aston smaller1.png";
	private String zondaImage = "Images\\Pagani smaller1.png";
	private String f40Image = "Images\\F40 smaller1.png";
	private String veyronImage = "Images\\veyron smaller1.png";
	private String f1Image = "Images\\F1 smaller1.png";
	private String smartImage = "Images\\smart smaller1.png";
	private String viperImage = "Images\\Viper smaller1.png";
	private String p1Image = "Images\\P1 smaller1.png";
	private String aveShadow = "Images\\Aventador shadow2.png";
	private String mustangShadow = "Images\\Mustang shadow2.png";
	private String murcieShadow = "Images\\Murcielago shadow2.png";
	private String db9Shadow = "Images\\Aston shadow2.png";
	private String zondaShadow = "Images\\Pagani shadow2.png";
	private String f40Shadow = "Images\\F40 shadow2.png";
	private String veyronShadow = "Images\\veyron shadow2.png";
	private String f1Shadow = "Images\\F1 shadow2.png";
	private String smartShadow = "Images\\smart shadow2.png";
	private String viperShadow = "Images\\Viper shadow2.png";
	private String p1Shadow = "Images\\P1 shadow2.png";
	private String lapCanvas = "Images\\Canvas1.png";
	private ImageIcon lapcanvasIcon = new ImageIcon(lapCanvas);
	private Image lapcanvasImage = lapcanvasIcon.getImage();
	private String speedoCanvas = "Images\\speedocanvas small1.png";
	private ImageIcon speedocanvasIcon = new ImageIcon(speedoCanvas);
	private Image speedocanvasImage = speedocanvasIcon.getImage();
	private String pauseCanvas = "Images\\Pausebackground.png";
	private ImageIcon pausecanvasIcon = new ImageIcon(pauseCanvas);
	private Image pausecanvasImage = pausecanvasIcon.getImage();
	private Car2 aventador;
    private Car2 mustang;
    private Car2 murcielago;
    private Car2 db9;
    private Car2 zonda;
    private Car2 f40;
    private Car2 veyron;
    private Car2 f1;
    private Car2 smart;
    private Car2 viper;
    private Car2 p1;
    public Car2 selectedCar;
    public Track selectedTrack;
    int trackNumber;
    public Game game;
    private int displaySpeed;
    private double displaySpeedAcc;
    private double personalTopSpeed;
    private int personalTopSpeedDec;
    public Physics physics;
    public Drawhandler drawHandler;
    private Thread physicsThread;
    private Thread drawHandlerThread;
    
    private boolean hasPainted;
    private int fps;
    private int fpsMax;
    private int fpsMin = 100;
    
    
    public InputManager inputManager;
    
    private float previousSpeed = 0f;
    private float speedMultiplier;
    
    private float endGameFader = 0.0f;
    
    
    private Font font1 = new Font("Andalus", Font.PLAIN, 28);
    private Font font2 = new Font("Andalus", Font.PLAIN, 21);
    private Font font3 = new Font("Andalus", Font.PLAIN, 36);
    private Font font4 = new Font("Andalus", Font.PLAIN, 52);
    private Font fontSpeedo = new Font("Andalus", Font.HANGING_BASELINE, 17);
    private Font fontSpeedo2 = new Font("Andalus", Font.HANGING_BASELINE, 15);
    private Font fontSpeedo3 = new Font("Andalus", Font.PLAIN, 13);
    private Font fontSpeedo4 = new Font("Andalus", Font.PLAIN, 11);
    
    private Color textColor = new java.awt.Color(210, 143, 5);
    private Color speedoColor = new java.awt.Color(14, 134, 148);
    private Color speedoColor2 = new java.awt.Color(140, 134, 148);
    private int maxX500; 
    private int maxY500;
    private int stopperX500; 
    private int stopperY500; 
    
    private int maxX450; 
    private int maxY450;
    private int stopperX450; 
    private int stopperY450; 
    
    private int maxX400; 
    private int maxY400;
    private int stopperX400; 
    private int stopperY400; 
    
    private int maxX350; 
    private int maxY350;
    private int stopperX350; 
    private int stopperY350; 
    
    private int maxX300; 
    private int maxY300;
    private int stopperX300;
    private int stopperY300;
    
    private int maxX250;
    private int maxY250;
    private int stopperX250;
    private int stopperY250;
    
    private int maxX200;
    private int maxY200;
    private int stopperX200;
    private int stopperY200;
    
    private int maxX150;
    private int maxY150;
    private int stopperX150;
    private int stopperY150;
    private int maxX100;
    private int maxY100;
    private int stopperX100;
    private int stopperY100;
    
    private int maxX050;
    private int maxY050;
    private int stopperX050;
    private int stopperY050;
    
    private int maxX000;
    private int maxY000;
    private int stopperX000;
    private int stopperY000;
    
    
    
    private boolean bgDrawn;
    final int targetFPS = 60;
    final long optimalTime = 1000 / targetFPS; 
    
    //-----------------------------------------------------------------------------------------------
    
    
    
    
    //-----------------------------------------------------------------------------------------------
    
    

    /*
     * pause-metodi pys‰ytt‰‰ pelin.
     */
    
    /*
     * resume-metodi palauttaa, lopettaa tai uudelleenk‰ynnist‰‰ pelin. Pelin t‰ytyy olla pys‰ytettyn‰.
     */
    
    /*
     * startGame-metodi k‰ynnist‰‰ pelin threadin.
     */
    
    
    /*
     * Konstruktori ottaa sis‰‰ns‰ valitun auton numeron, valitun radan numeron ja Game-luokan olion.
     * Konstruktorissa inputManageriin lis‰t‰‰n k‰ytett‰v‰t n‰pp‰imet.
     */
    public GameSession(int carNumber, int trackNumber, Game game) {
    	
    	this.trackNumber = trackNumber;
    	this.game = game;
    	selectedTrack = new Track(trackNumber);
    	
    	inputManager = new InputManager(this);
    	
    	inputManager.addMapping("Up", KeyEvent.VK_UP);
    	inputManager.addMapping("Down", KeyEvent.VK_DOWN);
    	inputManager.addMapping("Left", KeyEvent.VK_LEFT);
    	inputManager.addMapping("Right", KeyEvent.VK_RIGHT);
    	inputManager.addMapping("Control", KeyEvent.VK_CONTROL);
    	inputManager.addMapping("S", KeyEvent.VK_S);
    	inputManager.addMapping("R", KeyEvent.VK_R);
    	inputManager.addMapping("ESC", KeyEvent.VK_ESCAPE);
    	inputManager.addMapping("C", KeyEvent.VK_C);
 // Luodaan valitun numeron mukainen auto ominaisuuksiensa mukaisesti.
        
        if(carNumber == 1){
//        	mustang = new Car2("Mustang", mustangImage, mustangShadow,  	0.160f, 0.179f, 4.2f, 40f, 45f, 0.490f, 36.5f, 0.01f);
        	mustang = new Car2("Mustang", mustangImage, mustangShadow, 			0.18f, 0.08f, 0.08f, 0.08f, 4f, 14.0f, 28.0f, 40f, 0.390f, 36.0f,  0.0525f, 1.3f);
        	selectedCar = mustang;
        }
        if(carNumber == 2){
//        	murcielago = new Car2("Murcielago", murcieImage, murcieShadow, 	0.181f, 0.115f, 4.5f, 30f, 24.25f, 0.514f, 34.0f,  0.10f);
        	murcielago = new Car2("Murcielago", murcieImage, murcieShadow, 	0.18f, 0.03f, 0.03f, 0.03f, 3f, 9f, 16f, 32f, 0.410f, 34.0f,  0.21f, 1.4f);
        	selectedCar = murcielago;
        }
        else if(carNumber == 3){	
//        	db9 = new Car2("DB9", db9Image, db9Shadow, 				0.177f, 0.117f, 4.4f, 30f, 30f, 0.515f, 34.5f,  0.08f);
        	db9 = new Car2("DB9", db9Image, db9Shadow, 			0.15f, 0.03f, 0.03f, 0.03f, 2f, 6.0f, 15.0f, 35f, 0.41f, 34.0f,  0.1575f, 1.3f);
        	selectedCar = db9;
        }
        else if(carNumber == 4){
//        	viper = new Car2("Viper", viperImage, viperShadow, 			0.168f, 0.145f, 4.6f, 35f, 35f, 0.505f, 36.0f,  0.05f);
        	viper = new Car2("Viper", viperImage, viperShadow, 			0.16f, 0.036f, 0.035f, 0.03f, 4f, 12.0f, 22.0f, 35f, 0.405f, 36.0f,  0.21f, 1.28f);
        	selectedCar = viper;
        }
        else if(carNumber == 5){
//        	f40 = new Car2("F40", f40Image, f40Shadow, 				0.180f, 0.120f, 3.0f, 30f, 30f, 0.517f, 33.0f,  0.12f);
        	f40 = new Car2("F40", f40Image, f40Shadow, 				0.180f, 0.031f, 0.02f, 0.02f, 2f, 8f, 18f, 33f, 0.440f, 33.0f,  0.2625f, 1.62f);
        	selectedCar = f40;
        }
        else if(carNumber == 6){
//        	veyron = new Car2("Veyron", veyronImage, veyronShadow, 		0.190f, 0.170f, 4.0f, 35f, 35f, 0.502f, 31.0f,  0.35f);
        	veyron = new Car2("Veyron", veyronImage, veyronShadow, 				0.220f, 0.038f, 0.05f, 0.04f, 1.2f, 10f, 24f, 35f, 0.39f, 31.0f,  0.3675f, 1.35f);

        	selectedCar = veyron;
        }
        else if(carNumber == 7){
//        	aventador = new Car2("Aventador", aveImage, aveShadow, 			0.192f, 0.120f, 4.4f, 30f, 30f, 0.522f, 34.0f,  0.23f);
        	aventador = new Car2("Aventador", aveImage, aveShadow, 				0.192f, 0.036f, 0.02f, 0.02f, 2.4f, 10f, 24f, 35f, 0.41f, 33.0f,  0.315f, 1.5f);
        	selectedCar = aventador;
        }
        else if(carNumber == 8){
//        	p1 = new Car2("P1", p1Image, p1Shadow, 					0.200f, 0.131f, 4.2f, 35f, 35f, 0.535f, 30.0f,  0.40f);
        	p1 = new Car2("P1", p1Image, p1Shadow, 			0.200f, 0.0405f, 0.04f, 0.005f, 2f, 9f, 17f, 35f, 0.44f, 30.0f,  0.42f, 1.68f);
        	
        	selectedCar = p1;
        }
        else if(carNumber == 9){
//        	zonda = new Car2("Zonda R", zondaImage, zondaShadow, 			0.209f, 0.130f, 3.8f, 35f, 35f, 0.538f, 30.0f,  0.42f);
        	zonda = new Car2("Zonda R", zondaImage, zondaShadow, 			0.209f, 0.04f, 0.02f, 0.01f, 2f, 7f, 15f, 35f, 0.46f, 30.0f,  0.4725f, 1.7f);
        	selectedCar = zonda;
        }
        else if(carNumber == 10){
//        	f1 = new Car2("F1", f1Image, f1Shadow, 					0.250f, 0.170f, 3.2f, 35f, 35f, 0.545f, 20.0f,  0.70f);
        	f1 = new Car2("F1", f1Image, f1Shadow, 					0.250f, 0.05f, 0.01f, 0.001f, 2f, 4f, 12f, 35f, 0.465f, 20.0f,  0.525f, 1.85f);
        	selectedCar = f1;
        }
        else if(carNumber == 11){
//        	smart = new Car2("Smart", smartImage, smartShadow, 			0.0835f, 0.015f, 3.2f, 15f, 15f, 0.480f, 40.0f, -1.30f);
        	smart = new Car2("Smart", smartImage, smartShadow, 					0.088f, 0.006f, 0.004f, 0.004f, 3f, 6f, 8f, 15f, 0.40f, 84.0f,  0.0525f, 1.0f);
        	selectedCar = smart;
        }
        physics = new Physics(selectedCar, selectedTrack, this);
        drawHandler = new Drawhandler(this);
        physicsThread = new Thread(physics);
        drawHandlerThread = new Thread(drawHandler);
    	
    	

        maxX500 = physics.getSpeedoX() + (int) (100 * Math.cos(Math.toRadians(135))); 
        maxY500 = physics.getSpeedoY() + (int) (-100 * Math.sin(Math.toRadians(135)));
        stopperX500 = maxX500 - (int) (20 * Math.cos(Math.toRadians(135))); 
        stopperY500 = maxY500 - (int) (-20 * Math.sin(Math.toRadians(135))); 
        
        maxX450 = physics.getSpeedoX() + (int) (95 * Math.cos(Math.toRadians(122))); 
        maxY450 = physics.getSpeedoY() + (int) (-95 * Math.sin(Math.toRadians(122)));
        stopperX450 = maxX450 - (int) (10 * Math.cos(Math.toRadians(122))); 
        stopperY450 = maxY450 - (int) (-10 * Math.sin(Math.toRadians(122))); 
        
        maxX400 = physics.getSpeedoX() + (int) (100 * Math.cos(Math.toRadians(108))); 
        maxY400 = physics.getSpeedoY() + (int) (-100 * Math.sin(Math.toRadians(108)));
        stopperX400 = maxX400 - (int) (20 * Math.cos(Math.toRadians(108))); 
        stopperY400 = maxY400 - (int) (-20 * Math.sin(Math.toRadians(108))); 
        
        maxX350 = physics.getSpeedoX() + (int) (95 * Math.cos(Math.toRadians(95))); 
        maxY350 = physics.getSpeedoY() + (int) (-95 * Math.sin(Math.toRadians(95)));
        stopperX350 = maxX350 - (int) (10 * Math.cos(Math.toRadians(95))); 
        stopperY350 = maxY350 - (int) (-10 * Math.sin(Math.toRadians(95))); 
        maxX300 = physics.getSpeedoX() + (int) (100 * Math.cos(Math.toRadians(81))); 
        maxY300 = physics.getSpeedoY() + (int) (-100 * Math.sin(Math.toRadians(81)));
        stopperX300 = maxX300 - (int) (20 * Math.cos(Math.toRadians(81))); 
        stopperY300 = maxY300 - (int) (-20 * Math.sin(Math.toRadians(81)));
        maxX250 = physics.getSpeedoX() + (int) (95 * Math.cos(Math.toRadians(68))); 
        maxY250 = physics.getSpeedoY() + (int) (-95 * Math.sin(Math.toRadians(68)));
        stopperX250 = maxX250 - (int) (10 * Math.cos(Math.toRadians(68))); 
        stopperY250 = maxY250 - (int) (-10 * Math.sin(Math.toRadians(68))); 
        
        maxX200 = physics.getSpeedoX() + (int) (100 * Math.cos(Math.toRadians(54))); 
        maxY200 = physics.getSpeedoY() + (int) (-100 * Math.sin(Math.toRadians(54)));
        stopperX200 = maxX200 - (int) (20 * Math.cos(Math.toRadians(54))); 
        stopperY200 = maxY200 - (int) (-20 * Math.sin(Math.toRadians(54)));
        
        maxX150 = physics.getSpeedoX() + (int) (95 * Math.cos(Math.toRadians(41))); 
        maxY150 = physics.getSpeedoY() + (int) (-95 * Math.sin(Math.toRadians(41)));
        stopperX150 = maxX150 - (int) (10 * Math.cos(Math.toRadians(41))); 
        stopperY150 = maxY150 - (int) (-10 * Math.sin(Math.toRadians(41))); 
        
        maxX100 = physics.getSpeedoX() + (int) (100 * Math.cos(Math.toRadians(27))); 
        maxY100 = physics.getSpeedoY() + (int) (-100 * Math.sin(Math.toRadians(27)));
        stopperX100 = maxX100 - (int) (20 * Math.cos(Math.toRadians(27))); 
        stopperY100 = maxY100 - (int) (-20 * Math.sin(Math.toRadians(27)));
        
        maxX050 = physics.getSpeedoX() + (int) (95 * Math.cos(Math.toRadians(14))); 
        maxY050 = physics.getSpeedoY() + (int) (-95 * Math.sin(Math.toRadians(14)));
        stopperX050 = maxX050 - (int) (10 * Math.cos(Math.toRadians(14))); 
        stopperY050 = maxY050 - (int) (-10 * Math.sin(Math.toRadians(14))); 
        
        maxX000 = physics.getSpeedoX() + (int) (108 * Math.cos(Math.toRadians(0))); 
        maxY000 = physics.getSpeedoY() + (int) (-108 * Math.sin(Math.toRadians(0)));
        stopperX000 = maxX000 - (int) (6 * Math.cos(Math.toRadians(0))); 
        stopperY000 = maxY000 - (int) (-6 * Math.sin(Math.toRadians(0))); 
    	
    	
        
    	
        setFocusable(true);
        
       
        
        
       
        
    }
    
    
    public void startGame(){
    	physicsThread.start();
    	physics.threadIsRunning = true;
    	drawHandlerThread.start();
    	// Erkille: Korjaa kaiken!
    	requestFocusInWindow();
    }
    /*
     * stopGame-metodi vaihtaa pyˆritett‰vi‰ metodeita.
     */
	public void stopGame(){
    	physics.threadIsRunning = false;
    	physicsThread.interrupt();
    	drawHandlerThread.interrupt();
    }
	
	
	public void paint(Graphics g){
		update(g);
		
	}
    /*
     * paint-metodissa piirret‰‰n kaikki.
     */
    public void update(Graphics g) {
    	super.paintComponent(g);
    	
    	
    	Graphics2D g2d = (Graphics2D)g;
//    	setDoubleBuffered(true);
    	RenderingHints hints = new RenderingHints(null);
////    	 Asetellaan hienonnusjutut.
    	hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    	hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    	hints.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
    	hints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
    	hints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
    	g2d.setRenderingHints(hints);
    	
    	// Piirret‰‰n rata.
        
    	Toolkit.getDefaultToolkit().sync();
    	
    	g.drawImage(selectedTrack.getTrackImage(), 0, 0, null);
    	
    	g2d.setColor(new java.awt.Color(0, 0, 0));
    	g2d.setStroke((Stroke) new BasicStroke(3));
    	
    	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
    	if(physics.getSlideListRearLeft().size() > 0){
    		if(physics.getSlideListRearLeft().size() > 1){
    			g2d.drawImage(physics.getSlideImage(), 0, 0, null);
    		}
        	g2d.draw(physics.getSlideListRearLeft().get(physics.getSlideListRearLeft().size()-1));
        	g2d.draw(physics.getSlideListRearRight().get(physics.getSlideListRearRight().size()-1));
    	}
//    	g2d.setColor(new java.awt.Color(255, 255, 255));
//    	g2d.draw(selectedCar.getArea());
//    	
//    	g2d.setColor(new java.awt.Color(0, 255, 255));
//    	g2d.drawLine((int)selectedCar.getX(), (int)selectedCar.getY(), (int)selectedCar.getX(), (int)selectedCar.getY());
    	
    	Toolkit.getDefaultToolkit().sync();
    	g2d.rotate (Math.toRadians(selectedCar.getDegrees()), selectedCar.getX() + selectedCar.getWidth()/2, selectedCar.getY() + selectedCar.getHeight()/2);
    	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
    	g2d.drawImage(selectedCar.getShadow(), (int)(physics.getShadowX()), (int)(physics.getShadowY()), this);
    	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    	g2d.drawImage(selectedCar.getImage(), (int)(selectedCar.getX()), (int)(selectedCar.getY()), this);
    	Toolkit.getDefaultToolkit().sync();
    	g2d.rotate (Math.toRadians(-selectedCar.getDegrees()), selectedCar.getX() + selectedCar.getWidth()/2, selectedCar.getY() + selectedCar.getHeight()/2);
    	
    	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
    	// Piirret‰‰n tulosn‰ytˆt.
    	g2d.drawImage(lapcanvasImage, 613, 148, null);
    	
    	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    	
    	g2d.setColor(Color.WHITE);
    	
    	
    	
    	g2d.setFont(font1);
    	
    	
    	
    	// Muokataan nopeudet "kilometreiksi tunnissa".
    	if(selectedCar.getSpeed() < 0){
    		displaySpeed = -(int)(selectedCar.getSpeed()*14.26f);
    	}
    	else if(selectedCar.getSpeed() >= 0){
    		displaySpeed = (int)(selectedCar.getSpeed()*14.26f);
    		displaySpeedAcc = (double) selectedCar.getSpeed()*14.26f;
    	}
    	// Piirret‰‰n nopeudet.
    	
    	int spdstrLeng1 = (int)g2d.getFontMetrics().getStringBounds("" + displaySpeed, g2d).getWidth(); 
		int spdstrStart1 = 1095 - spdstrLeng1;
		
		g2d.setColor(speedoColor);
    	
    	g2d.drawString("" + displaySpeed, spdstrStart1, 315);
    	if(personalTopSpeed <= displaySpeedAcc){
    		personalTopSpeed = displaySpeedAcc;
    	}
    	
    	
    	
    	
    	g2d.setColor(textColor);
    	g2d.setFont(fontSpeedo2);
    	String personalTopSpeedString = String.format("%.1f", personalTopSpeed);
    	
    	int spdstrLeng2 = (int)g2d.getFontMetrics().getStringBounds("" + personalTopSpeedString, g2d).getWidth(); 
		int spdstrStart2 = 920 - spdstrLeng2; 
    	g2d.drawString("Max: ", 888, 236);
    	g2d.drawString(personalTopSpeedString, spdstrStart2, 249);
    	g2d.setColor(Color.WHITE);
    	g2d.setFont(font2);
    	// Piirret‰‰n t‰m‰nhetkinen kierrosaika.
    	if(physics.isTimerStarted() == false){
    		g2d.drawString("Current Lap: ", 665, 208);
    	}
    	if(physics.isTimerStarted() == true){
    		g2d.drawString("Current Lap: " + physics.timer.elapsedTime(), 665, 208);
    	}
    	if(physics.isLapStarted() == false){
    		g2d.drawString("Lap: ", 738, 230);
    	}
    	if(physics.isLapStarted() == true){
    		if(!physics.endGame){
    			g2d.drawString("Lap: " + physics.getOnGoingLap() + "/10", 738, 230);
    		}
    		else{
    			g2d.drawString("Lap: " + "10/10", 738, 230);
    		}
    	}
    	
    	// Piirret‰‰n edellisen kierroksen kierrosaika.
    	
    	if(physics.getLastLap() == null){
    		g2d.drawString("Last Lap: ", 655, 259);
    	}
    	if(physics.getLastLap() != null){
    		g2d.drawString("Last Lap: " + physics.getLastLap(), 655, 259);
    	}
    	g2d.setColor(textColor);
    	// Piirret‰‰n parhaan kierroksen kierrosaika.
    	if(physics.getBestLap() == null){
    		g2d.drawString("Best Lap: ", 655, 281);
    	}
    	if(physics.getBestLap() != null){
    		g2d.drawString("Best Lap: " + physics.getBestLap(), 655, 281);
    	}
    	// Piirret‰‰n kierrosaikakeskiarvo.
    	if(physics.getOnGoingLap() < 2){
    		g2d.drawString("Average: ", 655, 303);
    	}
    	if(physics.getOnGoingLap() >= 2){
    		g2d.drawString("Average: " + physics.getAverageLapTime(), 655, 303);
    	}
    	
    	
    	
//    	g2d.setColor(speedoColor2);
//    	g2d.fillArc(speedoX- 124, speedoY - 116, 242, 228, -0, 140);
    	g2d.setColor(speedoColor);
    	g2d.drawArc(physics.getSpeedoX() - 110, physics.getSpeedoY() - 110, 220, 220, -5, 145);
    	
    	
//    	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    	g2d.drawLine(maxX000, maxY000, stopperX000, stopperY000);
    	g2d.setColor(Color.WHITE);
    	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
//    	g2d.drawOval(speedoX-4, speedoY-4, 8, 8);
    	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    	g2d.setColor(speedoColor);
    	g2d.fillOval(physics.getSpeedoX() - 3, physics.getSpeedoY() - 3, 7, 7);
    	g2d.setColor(Color.WHITE);
    	
    	g2d.drawLine(maxX500, maxY500, stopperX500, stopperY500);
    	g2d.drawLine(maxX450, maxY450, stopperX450, stopperY450);
    	g2d.drawLine(maxX400, maxY400, stopperX400, stopperY400);
    	g2d.drawLine(maxX350, maxY350, stopperX350, stopperY350);
    	g2d.drawLine(maxX300, maxY300, stopperX300, stopperY300);
    	g2d.drawLine(maxX250, maxY250, stopperX250, stopperY250);
    	g2d.drawLine(maxX200, maxY200, stopperX200, stopperY200);
    	g2d.drawLine(maxX150, maxY150, stopperX150, stopperY150);
    	g2d.drawLine(maxX100, maxY100, stopperX100, stopperY100);
    	g2d.drawLine(maxX050, maxY050, stopperX050, stopperY050);
    	
    	g2d.setFont(fontSpeedo4);
    	g2d.setColor(speedoColor);
    	g2d.drawString("500", stopperX500 + 4 , stopperY500 +12);
    	g2d.drawString("400", stopperX400 - 6, stopperY400 +14);
    	g2d.drawString("300", stopperX300 - 10, stopperY300 +14);
    	g2d.drawString("200", stopperX200 - 14, stopperY200 +14);
    	g2d.drawString("100", stopperX100 - 20, stopperY100 +12);
    	
    	
    	
    	
    	g2d.setFont(fontSpeedo);
    	g2d.setColor(Color.WHITE);
    	g2d.drawString("km/h", physics.getSpeedoX() - 10, physics.getSpeedoY() - 15);
    	g2d.setColor(textColor);
    	g2d.drawLine(863, 170, 863, 300);
    	g2d.drawLine(physics.getSpeedoX(), physics.getSpeedoY(), physics.getSpeedoX() + (int)physics.getxOffset(), physics.getSpeedoY() + (int)physics.getyOffset());
    	g2d.drawLine(physics.getSpeedoX(), physics.getSpeedoY(), physics.getSpeedoX() + (int)physics.getXnegOffset(), physics.getSpeedoY() + (int)physics.getYnegOffset());
    	g2d.setColor(speedoColor);
//    	g2d.fillOval(physics.getSpeedoX() - 3, physics.getSpeedoY()-3, 7, 7);
    	g2d.setColor(textColor);
    	
//    	g2d.setColor(Color.BLACK);
    	
    	g2d.setFont(font1);
    	g2d.setColor(new Color(140,0,0));
    	// Debuggaus-infoa
    	
//    	g2d.drawString(fps + " fps", 10, 30);
//    	g2d.drawString(fpsMax + " fpsMax", 10, 50);
//    	g2d.drawString(fpsMin + " fpsMin", 10, 70);
//    	g2d.drawString(speedMultiplier + "", 300, 300);
//    	g2d.drawString(physics.powValue + "", 300, 330);
//    	g2d.drawString(String.format("%.1f", selectedCar.getSpeed()), 300, 360);
//    	g2d.drawString("Degrees " + selectedCar.getDegrees(), 300, 390);
//    	g2d.drawString("TurnR " + physics.turningPointDegR, 300, 420);
//    	g2d.drawString("TurnL " + physics.turningPointDegL, 300, 450);
//    	
//    	g2d.drawString("SlideR " + physics.slideDegR, 300, 480);
//    	g2d.drawString("SlideL " + physics.slideDegL, 500, 480);
//    	g2d.drawString("Rad deg-slideDeg" + Math.toRadians((selectedCar.getDegrees() - physics.slideDegR)), 300, 510);
//    	g2d.drawString("Cos" + Math.cos(Math.toRadians((selectedCar.getDegrees() - physics.slideDegR)/2)), 300, 540);
//    	g2d.drawString("Rslide " + physics.slideHasStartedR, 300, 570);
//    	g2d.drawString("Lslide " + physics.slideHasStartedL, 300, 600);
//    	g2d.drawString("Slidemultiplier " + physics.slideMultiplier, 300, 630);
//    	g2d.drawString("HandbrakeDeg " + physics.handBrakeDeg, 300, 660);
//    	g2d.drawString("HandbrakeBoolean " + physics.brakeIsPressed, 300, 690);
//    	g2d.setColor(textColor);
//    	 Asetetaan auton suuntakulma ja sijainti sek‰ piirret‰‰n se.
    	
    	
    	// Piirret‰‰n loppuanimaatio
    	if(physics.endGame){
    		float trackBasedExtracter = 0;
    		if(trackNumber == 2){
    			trackBasedExtracter = 0.6f;
    		}
    		g2d.setFont(font1);
    		g2d.setColor(Color.WHITE);
    		if(endGameFader < 0.9f){
    			endGameFader = endGameFader + 0.008f;
    		}
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, endGameFader));
    		g2d.drawImage(pausecanvasImage, 0, 0, null);
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    		String endPhrase = "";
    		// Loppufraasin valinta kierrosaikakeskiarvon mukaan.
    		if(trackNumber != 3){
	    		if(selectedCar != smart){
		    		if(physics.getAverageLapTime() < 6.3 - trackBasedExtracter){
		    			endPhrase = "A godly performance!";
		    		}
		    		if(physics.getAverageLapTime() >= 6.3 - trackBasedExtracter && physics.getAverageLapTime() < 6.8 - trackBasedExtracter){
		    			endPhrase = "Insanely fast!";
		    		}
		    		if(physics.getAverageLapTime() >= 6.8 - trackBasedExtracter && physics.getAverageLapTime() < 7.3 - trackBasedExtracter){
		    			endPhrase = "Properly fast!";
		    		}
		    		if(physics.getAverageLapTime() >= 7.3 - trackBasedExtracter && physics.getAverageLapTime() < 8.8 - trackBasedExtracter){
		    			endPhrase = "Great driving!";
		    		}
		    		if(physics.getAverageLapTime() >= 8.8 - trackBasedExtracter && physics.getAverageLapTime() < 10.3 - trackBasedExtracter){
		    			endPhrase = "Nice run!";
		    		}
		    		if(physics.getAverageLapTime() >= 10.3 - trackBasedExtracter && physics.getAverageLapTime() < 12.3 - trackBasedExtracter){
		    			endPhrase = "Not bad!";
		    		}
		    		if(physics.getAverageLapTime() >= 12.3 - trackBasedExtracter && physics.getAverageLapTime() < 16.3 - trackBasedExtracter){
		    			endPhrase = "Practice makes perfect!";
		    		}
		    		if(physics.getAverageLapTime() >= 16.3 - trackBasedExtracter){
		    			endPhrase = "Come on, mate...";
		    		}
	    		}
	    		if(selectedCar == smart){
	    			if(physics.getAverageLapTime() < 10.1 - trackBasedExtracter){
	    				endPhrase = "A godly performance!";
		    		}
	    			if(physics.getAverageLapTime() >= 10.1 - trackBasedExtracter && physics.getAverageLapTime() < 10.4 - trackBasedExtracter){
	    				endPhrase = "Insanely fast!";
		    		}
	    			if(physics.getAverageLapTime() >= 10.4 - trackBasedExtracter && physics.getAverageLapTime() < 10.8 - trackBasedExtracter){
	    				endPhrase = "Great driving!";
		    		}
	    			if(physics.getAverageLapTime() >= 10.8 - trackBasedExtracter && physics.getAverageLapTime() < 11.6 - trackBasedExtracter){
	    				endPhrase = "Nice run!";
		    		}
	    			if(physics.getAverageLapTime() >= 11.6 - trackBasedExtracter && physics.getAverageLapTime() < 13.6 - trackBasedExtracter){
	    				endPhrase = "Not bad!";
		    		}
	    			if(physics.getAverageLapTime() >= 13.6 - trackBasedExtracter && physics.getAverageLapTime() < 25.3 - trackBasedExtracter){
	    				endPhrase = "Practice makes perfect!";
		    		}
	    			if(physics.getAverageLapTime() >= 25.3 - trackBasedExtracter){
	    				endPhrase = "Come on, mate...";
		    		}
	    		}
    		}
    		g2d.setFont(font4);
    		g2d.setColor(textColor);
    		int stringLen = (int)g2d.getFontMetrics().getStringBounds(endPhrase, g2d).getWidth(); 
			int startText = 1300/2 - stringLen/2;
			g2d.setColor(Color.BLACK);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g2d.drawString(endPhrase, startText+2, 390);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			g2d.setColor(textColor);
    		g2d.drawString(endPhrase, startText, 390);
    		
    	}
    	// Pausevalikon piirto ja tekstin keskitys.
    	if(drawHandler.pause){
    		bgDrawn = false;
    		g2d.setFont(font3);
        	g2d.setColor(Color.BLACK);
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.70f));
    		g2d.drawImage(pausecanvasImage, 0, 0, null);
    		String phrase1 = "Game paused";
    		String phrase2 = "Press C to continue,";
    		String phrase3 = "R to restart";
    		String phrase4 = "or ESC to return to menu";
    		int stringLen = (int)g2d.getFontMetrics().getStringBounds(phrase1, g2d).getWidth(); 
			int startText1 = 1300/2 - stringLen/2;
			stringLen = (int)g2d.getFontMetrics().getStringBounds(phrase2, g2d).getWidth(); 
			int startText2 = 1300/2 - stringLen/2;
			stringLen = (int)g2d.getFontMetrics().getStringBounds(phrase3, g2d).getWidth(); 
			int startText3 = 1300/2 - stringLen/2;
			stringLen = (int)g2d.getFontMetrics().getStringBounds(phrase4, g2d).getWidth(); 
			int startText4 = 1300/2 - stringLen/2;
    		g2d.drawString("Game paused", startText1+2, 390);
    		g2d.drawString("Press C to continue,", startText2+2, 425);
    		g2d.drawString("R to restart", startText3+2, 460);
    		g2d.drawString("or ESC to return to menu", startText4+2, 495);
    		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.99f));
    		g2d.setColor(Color.WHITE);
    		g2d.drawString("Game paused", startText1, 390);
    		g2d.drawString("Press C to continue,", startText2, 425);
    		g2d.drawString("R to restart", startText3, 460);
    		g2d.drawString("or ESC to return to menu", startText4, 495);
    	}
    	setPhysicsCalculated(0);
    	g.dispose();
    	g2d.dispose();
    	
    	
    	

    	}
    public synchronized void setPhysicsCalculated(int i){
    	if(i == 0){
    		physicsCalculated = false;
    	}
    	else if (i == 1){
    		physicsCalculated = true;
    	}
    	
    }


    
    //--------------------------------------------------------------------------------------------------------------------------------
    
    
	  

}