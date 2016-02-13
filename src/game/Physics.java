package game;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
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
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Physics implements Runnable{
	private Car2 selectedCar;
    private Car2 f1;
    
    public StopWatch timer = new StopWatch();
    
	double powValue;
    
    private boolean slideHasStarted = false;
    boolean slideHasStartedR = false;
    boolean slideHasStartedL = false;
    float slideDegR;
    float slideDegL;
    private boolean hasBeenOnSand;
    
    private ArrayList<Area> walls = new ArrayList<Area>();
    
    private Area wall1 = new Area(new Rectangle(0, 0, 1300, 1));
    private Area wall2 = new Area(new Rectangle(0, 0, 1, 799));
    private Area wall3 = new Area(new Rectangle(0, 800, 1300, 1));
    private Area wall4 = new Area(new Rectangle(1295, 0, 1, 800));
    boolean brakeIsPressed = false;
    private boolean brakeIsLifted = false;
    private boolean turningRightIsPressed = false;
    private boolean turningLeftIsPressed = false;
    float handBrakeDeg;
    private float lostFrictionDeg = 12f;
    float turningPointDegR;
    float turningPointDegL;
    float slideMultiplier;
    private float handBrakeStartSpeed; 
    private boolean onSand;
    private boolean hittingWallForwards;
    private boolean hittingWallBackwards;
    private boolean hasHitaWallForwards;
    private boolean hasHitaWallBackwards;
    private float shadowX;
    private float shadowY;
    private boolean timerStarted;
    private boolean lapStarted;
    public boolean isLapStarted() {
		return lapStarted;
	}

	public void setLapStarted(boolean lapStarted) {
		this.lapStarted = lapStarted;
	}

	private boolean sector1;
    private boolean sector2;
    private boolean sector3;
    private String lastLap;
    private String bestLap;
    public String getBestLap() {
		return bestLap;
	}

	public void setBestLap(String bestLap) {
		this.bestLap = bestLap;
	}

	private double lastLapdouble;
    public double bestLapdouble;
    public double getBestLapdouble() {
		return bestLapdouble;
	}

	public double averageLapTime;
    public double getAverageLapTime() {
		return averageLapTime;
	}

	public void setAverageLapTime(double averageLapTime) {
		this.averageLapTime = averageLapTime;
	}

	public double averageLapTimeTotal;
   
    private int onGoingLap;
    public int getOnGoingLap() {
		return onGoingLap;
	}

	public void setOnGoingLap(int onGoingLap) {
		this.onGoingLap = onGoingLap;
	}

	private boolean isRunning = true;
    public boolean isRunning() {
		return isRunning;
	}

	private Track selectedTrack;
    private int trackNumber;
    public Game game;
    public GameSession session;
    boolean threadIsRunning;
    
    boolean endGame;
    
    private float previousSpeed = 0f;
    private float speedMultiplier;
    
    
    private double xOffset;
    private double yOffset;
    private double xnegOffset;
    private double ynegOffset;
    private double xCounterOffset;
    private double yCounterOffset;
    private int speedoX = 985;
    private int speedoY = 275;
    
    private int counter1 = 0;
    private int slideCounter = 0;
    private int lastPointToDraw = 0;
    
    private Image slideImage;
    
    private boolean needForStop = false;
    private boolean handBrakeIsPressed = false;
    private ArrayList<GeneralPath> slideListRearRight = new ArrayList<GeneralPath>();
    private ArrayList<GeneralPath> slideListRearLeft = new ArrayList<GeneralPath>();
    private Shape slideShape;
    private GeneralPath slidesPath = 
	        new GeneralPath(GeneralPath.WIND_EVEN_ODD, 10);
    
    private int tiresOnSand;
    private boolean imageHasBeenCreated;
    private float steeringAngleMultiplierRight = 0;
    private float steeringAngleMultiplierLeft = 0;
	
    private int slideExceptionCounter = 0;
    
	public Physics(Car2 selectedCar, Track selectedTrack, GameSession session){
		this.selectedCar = selectedCar;
		this.selectedTrack = selectedTrack;
		this.session = session;
		
		
    	
    	 walls.add(wall1);
         walls.add(wall2);
         walls.add(wall3);
         walls.add(wall4);
	}

	public void resume(){
    	if(session.inputManager.isPressed("ESC")){
    		averageLapTime = 0;
    		session.game.endGame();
    	}
    	if(session.inputManager.isPressed("C")){
    		if(timer.lastLap() != 0){
    			timer.resumeWatch();
    		}
    		isRunning = true;
    		session.drawHandler.setPause(false);
    	}
    	if(session.inputManager.isPressed("R")){
    		session.game.restartGame();
    	}
    }
	public void pause(){
    	if(session.inputManager.isPressed("S")){
    		
    		if(isRunning){
    			timer.pauseWatch();
    			isRunning = false;
    			session.drawHandler.setPause(true);
    			
			}
    		}
    	}
    
    /*
     * run-metodi on pelin thread, joka pyörittää peliä 60 ruutua/sekunti -nopeudella. Se kutsuu kaikkia pelin pyörimiseen tarvittavia metodeja.
     */
    public void run()
    {
       
       

       
       while (threadIsRunning){
    	  // Kun peli on keskeytetty, pelkästään resume-metodin toiminnot ovat käytössä.
    	 
    	  if(!isRunning){
    		  resume();
    	  }
    	  // Pelin normaali tila.
    	  if(isRunning){
	          
	             
	          
	          // Auton normaali liike ja keskeytyksen mahdollisuus.
	          if(!endGame){
	        	  move();
	        	  
	        	  pause();
	          }
	          
	          // Pelin loppuanimaatio.
	          if(endGame){
	        	  endGameMove();
	          }
	          // Auton varjon sijainti lasketaan joka tapauksessa.
	          shadowCalculator();
	          
	          // Testiradassa ei ole ulkoseiniä tai hiekkaa, joten tarkistusta ei silloin vaadita.
	          if(session.trackNumber != 3){
	        	  checkCollisions();
	          }
	          // Sektoreiden tarkistukset suoretaan joka tapauksessa.
	          speedoMeter();
	          checkStartLine();
	          checkSector1();
	          checkSector2();
	          checkSector3();
	          checkBestLap();
    	  }
    	  
    	  session.setPhysicsCalculated(1);
    	  
	          try {
	  			Thread.sleep(16);
	  		} catch (InterruptedException e) {
	  			break;
	  		}
          }
       
       }
       
    
	
	public void speedoMeter(){
    	setxOffset((100 * Math.cos(Math.toRadians(135*(Math.abs(selectedCar.getSpeed()*14.2626)/500))))); 
    	setyOffset((-100 * Math.sin(Math.toRadians(135*(Math.abs(selectedCar.getSpeed()*14.2626)/500)))));
    	setXnegOffset((-20 * Math.cos(Math.toRadians(135*(Math.abs(selectedCar.getSpeed()*14.2626)/500))))); 
    	setYnegOffset((20 * Math.sin(Math.toRadians(135*(Math.abs(selectedCar.getSpeed()*14.2626)/500))))); 
    	setxCounterOffset((10 * Math.cos(Math.toRadians(90 + 135*(Math.abs(selectedCar.getSpeed()*14.2626))))));
    	setyCounterOffset((-10 * Math.sin(Math.toRadians(90 + 135*(Math.abs(selectedCar.getSpeed()*14.2626))))));
    	
//    	(int)Math.cos(Math.toRadians(90 * selectedCar.getSpeed() / 30));
    }
    
    
    // Auton liikkuminen
    
    public void move() {
    	// Perusliikkuminen nuolta ylös painettaessa
    	float downForceGrip = Math.abs((selectedCar.getSpeed() * selectedCar.getDownforce()) / 80f);
    	
    	selectedCar.setGrip(selectedCar.getStandardGrip() + downForceGrip*7.5f);
//    	System.out.println(selectedCar.getGrip());
    	if(session.inputManager.isPressed("Control")){
    		
    		if(brakeIsPressed == false){
    			handBrakeStartSpeed = selectedCar.getSpeed();
    			// Jos auto on luistossa oikealle, asetetaan käsijarrun liukumiskulma luistokulman mukaan
    			if(slideHasStartedR){
    				handBrakeDeg = slideDegR;
    			}
    			// Jos auto on luistossa vasemmalle, asetetaan käsijarrun liukumiskulma luistokulman mukaan
    			else if(slideHasStartedL){
    				handBrakeDeg = slideDegL;
    			}
    			// Jos auto ei ole luistossa, asetetaan käsijarrun liukumiskulma auton kulman mukaan
    			else{
    				handBrakeDeg = selectedCar.getDegrees();
    			}
    			
    			brakeIsPressed = true;
    			brakeIsLifted = false;
    		}
    		// Asettaa autolle X- ja Y-koordinaatit, jos käsijarrua on painettu
    		
//    		
    	}
        if(!session.inputManager.isPressed("Control")){
			brakeIsPressed = false;
			handBrakeDeg = selectedCar.getDegrees();
		}
    	if(session.inputManager.isPressed("Up") && !session.inputManager.isPressed("Control")){
    		// Liikkuminen käännyttäessä
//    		if(session.inputManager.isPressed("Left") ||session.inputManager.isPressed("Right")){
//    			selectedCar.setSpeed(selectedCar.getSpeed() + selectedCar.getAcc1()*0.77f);
//    			if(selectedCar.getSpeed() >= selectedCar.getAccStart2()){
//	    			selectedCar.setSpeed(selectedCar.getSpeed() + selectedCar.getAcc2()*0.77f);
//	    		}
//    			if(selectedCar.getSpeed() >= selectedCar.getAccStart3()){
//	    			selectedCar.setSpeed(selectedCar.getSpeed() + selectedCar.getAcc3()*0.77f);
//	    		}
//    			if(selectedCar.getSpeed() >= selectedCar.getAccStart4()){
//	    			selectedCar.setSpeed(selectedCar.getSpeed() + selectedCar.getAcc4()*0.77f);
//	    		}
//    		}
    		// Liikkuminen suoraan
//    		else{
    			float accelerationMultiPlier = 0.62f;
	    		selectedCar.setSpeed(selectedCar.getSpeed() + selectedCar.getAcc1()*accelerationMultiPlier);
	    		// Kovempi kiihtyvyys rajanopeuden jälkeen
	    		if(selectedCar.getSpeed() >= selectedCar.getAccStart2()){
	    			selectedCar.setSpeed(selectedCar.getSpeed() + selectedCar.getAcc2()*1.1f);
	    		}
	    		if(selectedCar.getSpeed() >= selectedCar.getAccStart3()){
	    			selectedCar.setSpeed(selectedCar.getSpeed() + selectedCar.getAcc3()*0.6f);
	    		}
	    		if(selectedCar.getSpeed() >= selectedCar.getAccStart4()){
	    			selectedCar.setSpeed(selectedCar.getSpeed() + selectedCar.getAcc4()*0.3f);
	    		}
//    		}
    		
    		
    		
    	}
    	// Perusliikkuminen nuolta alas painettaessa
    	if(session.inputManager.isPressed("Down") && !session.inputManager.isPressed("Control")){
    		// Jarru
    		if(selectedCar.getSpeed() > 0){
    			if(selectedCar.getSpeed() < 2f){
    				selectedCar.setSpeed(selectedCar.getSpeed() - (selectedCar.getSpeed()*(1+downForceGrip*8f) / selectedCar.getBrakePower()*3));
    			}
    			else{
    			selectedCar.setSpeed(selectedCar.getSpeed() - (selectedCar.getSpeed()*(1+downForceGrip*8f) / selectedCar.getBrakePower()));
    			}
    		}
    		// Pakki
    		if(selectedCar.getSpeed() <= 0){
    			selectedCar.setSpeed(selectedCar.getSpeed() - selectedCar.getAcc1()*0.7f);
    			if(session.inputManager.isPressed("Left") ||session.inputManager.isPressed("Right")){
    				selectedCar.setSpeed(selectedCar.getSpeed()*0.99f);
    			}
    		}
    	}
    	// Kääntyminen vasemmalle
    	if(session.inputManager.isPressed("Left") && selectedCar.getSpeed() != 0 && !session.inputManager.isPressed("Control")){
    		if(steeringAngleMultiplierLeft < 1f){
    			steeringAngleMultiplierLeft = steeringAngleMultiplierLeft + 0.2f;
    		}
    		else{
    			steeringAngleMultiplierLeft = 1;
    		}
    		// Kun nopeus on nollan ja kolmen välissä: Kääntyvyys riippuvainen nopeudesta
    		if (selectedCar.getSpeed() < 3f && selectedCar.getSpeed() >= 0f) {
    			selectedCar.setDegrees(selectedCar.getDegrees() - steeringAngleMultiplierLeft*(1+downForceGrip)*selectedCar.getTurnRadius()*selectedCar.getSpeed()*1.83f);
            }
    		
    		// Kun nopeus on kolmen ja kymmenen välissä: Kääntyvyys vakio
    		if(selectedCar.getSpeed() >= 3f){
    			selectedCar.setDegrees(selectedCar.getDegrees() - steeringAngleMultiplierLeft*selectedCar.getTurnRadius()*5.7f*(1+downForceGrip));
            }
    		// Kun nopeus on negatiivinen: Kääntyvyys riippuvainen nopeudesta
    		if (selectedCar.getSpeed() < 0f) {
    			selectedCar.setDegrees((selectedCar.getDegrees() - steeringAngleMultiplierLeft*selectedCar.getTurnRadius() * selectedCar.getSpeed()*1.9f));
            }
    		// Kun nopeus on yli kymmenenen: Kääntyvyys riippuvainen nopeudesta
//    		if(selectedCar.getSpeed() >= 10f){
//    			selectedCar.setDegrees(selectedCar.getDegrees() - (1+downForceGrip)*selectedCar.getTurnRadius()*36f/(selectedCar.getSpeed()*0.85f));
//    		}
	
    	}
//    	System.out.println("SangleMultRight: " + steeringAngleMultiplierRight);
    	// Kääntyminen oikealle
    	if(session.inputManager.isPressed("Right") && selectedCar.getSpeed() != 0 && !session.inputManager.isPressed("Control")){
    		if(steeringAngleMultiplierRight < 1f){
    			steeringAngleMultiplierRight = steeringAngleMultiplierRight + 0.2f;
    			
    		}
    		else{
    			steeringAngleMultiplierRight = 1;
    		}
    		// Kun nopeus on nollan ja kolmen välissä: Kääntyvyys riippuvainen nopeudesta
    		if (selectedCar.getSpeed() < 3f && selectedCar.getSpeed() >= 0) {
    			selectedCar.setDegrees(selectedCar.getDegrees() + steeringAngleMultiplierRight*selectedCar.getTurnRadius()*selectedCar.getSpeed()*1.83f*(1+downForceGrip));
            } 
    		
    		// Kun nopeus on kolmen ja kymmenen välissä: Kääntyvyys vakio
    		if(selectedCar.getSpeed() >= 3f){
    			selectedCar.setDegrees(selectedCar.getDegrees() + steeringAngleMultiplierRight*selectedCar.getTurnRadius()*5.7f*(1+downForceGrip));
            }
    		
    		// Kun nopeus on negatiivinen: Kääntyvyys riippuvainen nopeudesta
    		if (selectedCar.getSpeed() < 0f) {
    			selectedCar.setDegrees((selectedCar.getDegrees() + steeringAngleMultiplierRight*selectedCar.getTurnRadius() * selectedCar.getSpeed()*1.9f));
            } 
    		// Kun nopeus on yli kymmenenen: Kääntyvyys riippuvainen nopeudesta
//    		if(selectedCar.getSpeed() >= 10f){
//    			selectedCar.setDegrees(selectedCar.getDegrees() + selectedCar.getTurnRadius()*36f/(selectedCar.getSpeed()*0.85f*(1+downForceGrip)));
//    		}
    		
    		
    	}
    	if(!session.inputManager.isPressed("Right")){
			if(steeringAngleMultiplierRight > 0){
				steeringAngleMultiplierRight = steeringAngleMultiplierRight - 0.2f;
			}
			else{
				steeringAngleMultiplierRight = 0;
			}
		}
    	if(!session.inputManager.isPressed("Left")){
			if(steeringAngleMultiplierLeft > 0){
				steeringAngleMultiplierLeft = steeringAngleMultiplierLeft - 0.2f;
			}
			else{
				steeringAngleMultiplierLeft = 0;
			}
		}
    	
    	
    	//-------------------   	
    	
    	
    	// Käsijarrun vaikutus nopeuteen ja auton keulan osoittamaan suuntaan
    	
    	if(session.inputManager.isPressed("Control")){
    		handBrakeIsPressed = true;
    		// Jarrutusteho, jos nopeus on yli 3 (Pienempi arvo on tehokkaampi)
    		if(selectedCar.getSpeed() > 3.0f){
    		selectedCar.setSpeed(selectedCar.getSpeed() - (selectedCar.getSpeed() / (selectedCar.getBrakePower()*1.1f)));
    		}
    		// Jarrutusteho, jos nopeus on alle 3 (Pienempi arvo on tehokkaampi)
    		if(selectedCar.getSpeed() <= 3.0f){
    			selectedCar.setSpeed(selectedCar.getSpeed() - selectedCar.getSpeed() / (selectedCar.getBrakePower()*0.55f));
    		}
    		
	        	if(session.inputManager.isPressed("Right")){
	        		selectedCar.setDegrees(selectedCar.getDegrees() + selectedCar.getSpeed()*selectedCar.getTurnRadius()*((handBrakeStartSpeed - selectedCar.getSpeed())/2.23f));
	        	}
//	        	if(slideHasStartedR){
//	        		selectedCar.setDegrees(selectedCar.getDegrees() + selectedCar.getSpeed()*selectedCar.getTurnRadius()*((handBrakeStartSpeed - selectedCar.getSpeed())/2.23f));
//	        		if(inputManager.isPressed("Right")){
//		        		selectedCar.setDegrees(selectedCar.getDegrees() + selectedCar.getSpeed()*selectedCar.getTurnRadius()*((handBrakeStartSpeed - selectedCar.getSpeed())/4.23f));
//		        	}
//	        	}
	        	if(session.inputManager.isPressed("Left")){
	        		selectedCar.setDegrees(selectedCar.getDegrees() - selectedCar.getSpeed()*selectedCar.getTurnRadius()*((handBrakeStartSpeed - selectedCar.getSpeed())/2.23f));
	        	}
//	        	if(slideHasStartedL){
//	        		selectedCar.setDegrees(selectedCar.getDegrees() - selectedCar.getSpeed()*selectedCar.getTurnRadius()*((handBrakeStartSpeed - selectedCar.getSpeed())/2.23f));
//	        		if(inputManager.isPressed("Left")){
//		        		selectedCar.setDegrees(selectedCar.getDegrees() - selectedCar.getSpeed()*selectedCar.getTurnRadius()*((handBrakeStartSpeed - selectedCar.getSpeed())/4.23f));
//		        	}
//	        	}
	        	
    		}
	
    	if(!session.inputManager.isPressed("Control")){
    		handBrakeIsPressed = false;
    	}
    	
    	
    	
    	
    	//-------------------
    	
    	
    	
    	// Auton kääntymisen rajoittaminen
    	
    	// Jos kulma yli 180 astetta
    	if(selectedCar.getDegrees() >= 180){
    		selectedCar.setDegrees(selectedCar.getDegrees() - 360);
    		
    		
    	}
    	if(turningPointDegR >= 180){
    		turningPointDegR = turningPointDegR - 360;
    		
    	}
    	if(turningPointDegR <= -180){
    		turningPointDegR = turningPointDegR + 360;
    		
    	}
    	
    	// Jos kulma alle -180 astetta
    	if(selectedCar.getDegrees() <= -180){
    		selectedCar.setDegrees(selectedCar.getDegrees() + 360);
    		
    	}
    	if(turningPointDegL >= 180){
    		turningPointDegL = turningPointDegL - 360;
    		
    	}
    	if(turningPointDegL <= -180){
    		turningPointDegL = turningPointDegL + 360;
    		
    	}
    	
    	
    	
    	//-------------------
    	
    	Area dummy = selectedCar.getArea();
    	
    	// Liike eri olosuhteissa
    	
    	// Tiellä
    	if(!onSand && !hittingWallForwards && !hittingWallBackwards){
    		if(hasBeenOnSand){
    			hasBeenOnSand = false;
    		}
    		// Rajoittaa huippunopeuden eteenpäin
	    	if (Math.abs (selectedCar.getSpeed()) > selectedCar.getTopSpeed()) {
	    		selectedCar.setSpeed(selectedCar.getTopSpeed());
	        }
	    	// Rajoittaa huippunopeuden taaksepäin
	        if (selectedCar.getSpeed() < -4.5f) {
	        	selectedCar.setSpeed(-4.5f);
	        }
	        
    	}
    	if(Math.abs(selectedCar.getSpeed()) > 0.5f){
    		if(tiresOnSand == 1){
        		selectedCar.setSpeed(selectedCar.getSpeed()*0.97f);
        	}
    		else if(tiresOnSand == 2){
        		selectedCar.setSpeed(selectedCar.getSpeed()*0.92f);
        	}
    		else if(tiresOnSand == 3){
        		selectedCar.setSpeed(selectedCar.getSpeed()*0.90f);
        	}
    		else if(tiresOnSand == 4){
        		selectedCar.setSpeed(selectedCar.getSpeed()*0.86f);
        	}
    	}
    	
    	
    	
    	// Seinässä
    	// Etuperin törmäys
    	if(hittingWallForwards){
    		if(selectedCar.getSpeed() <= 1.68f && selectedCar.getSpeed() >= 0){
    			selectedCar.setSpeed(-1f);
    		}
    		else{
    			selectedCar.setSpeed(selectedCar.getSpeed()*-0.6f);
    		}
    			
  
    	}
    	// Takaperin törmäys
    	if(hittingWallBackwards){
    		if(selectedCar.getSpeed() >= -1 && selectedCar.getSpeed() <= 0){
    			selectedCar.setSpeed(1f);
    		}
    		else{
    			selectedCar.setSpeed(selectedCar.getSpeed()*-1f);
    		}
    				
    	}
    	
    	
    	
    	//-------------------
        
        
        
    	
    	// Luippari
        
    	// Nopeuden alittaessa 4.5, luipparinaloituskulma asetetaan auton senhetkisen suunnan mukaan
        if(selectedCar.getSpeed() < 1.0f){
    		turningPointDegL = selectedCar.getDegrees();
    		turningPointDegR = selectedCar.getDegrees();
    	}
        
        // Luipparinlisäyskulma
        // Mitä pienempi arvo, sitä isompi luiston kulma
        if(selectedCar != f1){
	        slideMultiplier = (lostFrictionDeg*selectedCar.getGrip()/(selectedCar.getSpeed() * selectedCar.getTurnRadius()*2.4f));
	        if(slideMultiplier <= 0.5f){
	        	slideMultiplier = 0.5f;
	        }
        }
        else if(selectedCar == f1){
        	slideMultiplier = (lostFrictionDeg*selectedCar.getGrip()/(selectedCar.getSpeed() * selectedCar.getTurnRadius()*2.4f));
	        if(slideMultiplier <= 0.5f){
	        	slideMultiplier = 0.5f;
	        }
        }
        
        
        // Jos nopeus on erisuuri kuin 0, eikä käsijarrua ole painettu
    	if(selectedCar.getSpeed() != 0){
    		// Jos painetaan nuolta oikealle ja nopeus on suurempi kuin 4.5
    		if(session.inputManager.isPressed("Right") && selectedCar.getSpeed() >= 1.0f){
    			turningPointDegL = selectedCar.getDegrees();
    			// Tallentaa kääntymisenaloituskulman
        		if(turningRightIsPressed == false){
        			turningPointDegR = selectedCar.getDegrees();
        			turningRightIsPressed = true;
        		}
    		}
    		// Jos painetaan nuolta vasemmalle ja nopeus on suurempi kuin 4.5
    		else if(session.inputManager.isPressed("Left") && selectedCar.getSpeed() >= 1.0f){
    			turningPointDegR = selectedCar.getDegrees();
    			// Tallentaa kääntymisenaloituskulman
        		if(turningLeftIsPressed == false){
        			turningPointDegL = selectedCar.getDegrees();
        			turningLeftIsPressed = true;
        		}
    		}
    		
    		// Oikealle käännyttäessä
    		
    		
    		
}
    		
    		if(slideHasStarted == false){
    			if(!session.inputManager.isPressed("Control")){
    			selectedCar.setX(selectedCar.getX() + selectedCar.getSpeed()*(float)Math.cos(Math.toRadians(selectedCar.getDegrees())));
            	selectedCar.setY(selectedCar.getY() + selectedCar.getSpeed()*(float)Math.sin(Math.toRadians(selectedCar.getDegrees())));
    			}
    			if(session.inputManager.isPressed("Control")){
                	selectedCar.setX(selectedCar.getX() + selectedCar.getSpeed()*(float)Math.cos(Math.toRadians(handBrakeDeg)));
                	selectedCar.setY(selectedCar.getY() + selectedCar.getSpeed()*(float)Math.sin(Math.toRadians(handBrakeDeg)));
    			}
            	slideDegR = selectedCar.getDegrees();
            	slideDegL = selectedCar.getDegrees();
    			if(selectedCar.getSpeed() >= 1.0f ){
    				if(selectedCar.getDegrees() <= 0 && turningPointDegR > 0){
    	    			if(360 + selectedCar.getDegrees() - turningPointDegR >= lostFrictionDeg){
    	    				slideHasStartedR = true;
    	    				slideHasStarted = true;
    	    				slideDegR = turningPointDegR;
    	    				
    	    				
    	    			}
    				}
    				else if(selectedCar.getDegrees() > turningPointDegR){
    					if(selectedCar.getDegrees() - turningPointDegR >= lostFrictionDeg){
    						slideHasStartedR = true;
    						slideHasStarted = true;
    	    				slideDegR = turningPointDegR;
    					}
    				}
    				
    				else if(selectedCar.getDegrees() >= 0 && turningPointDegL < 0){
    	    			if(360 - selectedCar.getDegrees() + turningPointDegL >= lostFrictionDeg){
    	    				slideHasStartedL = true;
    	    				slideHasStarted = true;
    	    				slideDegL = turningPointDegL;
    	    			}
    				}
    				else if(selectedCar.getDegrees() < turningPointDegL){
    					if(turningPointDegL - selectedCar.getDegrees() >= lostFrictionDeg){
    						slideHasStartedL = true;
    						slideHasStarted = true;
    	    				slideDegL = turningPointDegL;
    	    				
    					}
    				}
    				
    				
    			}
    			
    			
    		
            		
            	
    		}
    		if(slideHasStartedR == true){
            	slideHasStartedL = false;
            	turningPointDegL = selectedCar.getDegrees();
            }
    		else if(slideHasStartedL == true){
            	slideHasStartedR = false;
            	turningPointDegR = selectedCar.getDegrees();
            }
    		if(slideHasStartedR == true){
	    		if(selectedCar.getDegrees() <= 0 && slideDegR >= 0){
	    			if(360 + selectedCar.getDegrees() - slideDegR <= 10){
	            		slideHasStartedR = false;
	            		slideHasStarted = false;
	            		
	            	}
	    		}
	    		else if(slideDegR <= selectedCar.getDegrees()){
	    			if(Math.abs(slideDegR - selectedCar.getDegrees()) <= 10){
	    				slideHasStartedR = false;
	    				slideHasStarted = false;
	    			}
	        		
	        	}
	    		if(selectedCar.getSpeed() < 1.0f){
	    			slideHasStartedR = false;
	    			slideHasStarted = false;
	    		}
	    		if(selectedCar.getSpeed() >= 1.0f){
	    			
        		
        		// Asettaa autolle X- ja Y-koordinaatit nopeuden ja kääntymisenaloituskulman mukaan
	    			if(slideDegR > selectedCar.getDegrees() && selectedCar.getDegrees() < -90){
	    				float prevSpeed = selectedCar.getSpeed();
	    				selectedCar.setSpeed((float)(selectedCar.getSpeed()*Math.abs(Math.cos(Math.toRadians((360 + selectedCar.getDegrees() - slideDegR)/4)))));	
	    				if(prevSpeed - selectedCar.getSpeed() >= 3){
	    					System.out.println("Rikkihän se meni (oikea1)");
	    					System.out.println("Auton kulma :" + selectedCar.getDegrees());
	    					System.out.println("Luiston kulma vasen :" + slideDegL);
	    					System.out.println("Luiston kulma oikea :" + slideDegR);
	    					System.out.println("Nopeuskerroin: " + Math.cos(Math.toRadians((360 + selectedCar.getDegrees() - slideDegR)/4)));
	    				}
	    			}
	    			else{
	    				float prevSpeed = selectedCar.getSpeed();
	    				selectedCar.setSpeed((float)(selectedCar.getSpeed()*Math.abs(Math.cos(Math.toRadians((selectedCar.getDegrees() - slideDegR)/4)))));
	    				if(prevSpeed - selectedCar.getSpeed() >= 3){
	    					System.out.println("Rikkihän se meni (oikea2)");
	    					System.out.println("Auton kulma :" + selectedCar.getDegrees());
	    					System.out.println("Luiston kulma vasen :" + slideDegL);
	    					System.out.println("Luiston kulma oikea :" + slideDegR);
	    					System.out.println("Nopeuskerroin: " + Math.cos(Math.toRadians((selectedCar.getDegrees() - slideDegR)/4)));
	    				}
	    			}
	    	
	            	// Lisää kääntymisenaloituskulmaan kääntösäteen ja luipparinlisäyskulman tulon

		            if(!session.inputManager.isPressed("Control")){
	            		selectedCar.setX(selectedCar.getX() + selectedCar.getSpeed()*(float)Math.cos(Math.toRadians(slideDegR)));
	                	selectedCar.setY(selectedCar.getY() + selectedCar.getSpeed()*(float)Math.sin(Math.toRadians(slideDegR)));
	        			}
	        		if(session.inputManager.isPressed("Control")){
	        			selectedCar.setX(selectedCar.getX() + selectedCar.getSpeed()*(float)Math.cos(Math.toRadians(handBrakeDeg)));
	        	    	selectedCar.setY(selectedCar.getY() + selectedCar.getSpeed()*(float)Math.sin(Math.toRadians(handBrakeDeg)));
	        		}
	            	
	        		if(slideDegR >= 180){
	            		slideDegR = slideDegR - 360;
	            	}
	        			slideDegR += selectedCar.getTurnRadius()*slideMultiplier*(0.4f+selectedCar.getGrip());
	        		
	        		
	    			if(session.inputManager.isPressed("Up") && !session.inputManager.isPressed("Control")){
	            	selectedCar.setDegrees(selectedCar.getDegrees() + (selectedCar.getTurnRadius()*slideMultiplier*(0.99f*(2 + (float)Math.sqrt(selectedCar.getSpeed()))*(selectedCar.getAcc1() + selectedCar.getAcc2() + selectedCar.getAcc3() + selectedCar.getAcc4()))));
	    			}
	    			
	            	turningPointDegR = selectedCar.getDegrees();
	            	turningRightIsPressed = false;
	            	}
            	
        	}
    		
    		//-----------------------------------------------------------------------------------------
    		// Vasen
    		
    		
    		else if(slideHasStartedL == true){
	    		if(selectedCar.getDegrees() >= 0 && slideDegL <= 0){
	    			if(360 + selectedCar.getDegrees() - slideDegL <= 10){
	            		slideHasStartedL = false;
	            		slideHasStarted = false;
	            		
	            	}
	    		}
	    		else if(slideDegL >= selectedCar.getDegrees()){
	    			if(Math.abs(slideDegL - selectedCar.getDegrees()) <= 10){
	    				slideHasStartedL = false;
	    				slideHasStarted = false;
	    			}
	        		
	        	}
	    		if(selectedCar.getSpeed() < 1.0f){
	    			slideHasStartedL = false;
	    			slideHasStarted = false;
	    		}
	    		if(selectedCar.getSpeed() >= 1.0f){
	    			
	        		
	        		// Asettaa autolle X- ja Y-koordinaatit nopeuden ja kääntymisenaloituskulman mukaan
	    			if(slideDegL < selectedCar.getDegrees() && selectedCar.getDegrees() > 90){
//	    				slideExceptionCounter++;
//	    				System.out.println("Vasen poikkeus" + slideExceptionCounter);
	    				float prevSpeed = selectedCar.getSpeed();
	    				selectedCar.setSpeed((float)(selectedCar.getSpeed()*Math.abs(Math.cos(Math.toRadians((360 - selectedCar.getDegrees() + slideDegL)/4)))));
	    				if(prevSpeed - selectedCar.getSpeed() >= 3){
	    					System.out.println("Rikkihän se meni (vasen1)");
	    					System.out.println("Auton kulma :" + selectedCar.getDegrees());
	    					System.out.println("Luiston kulma vasen :" + slideDegL);
	    					System.out.println("Luiston kulma oikea :" + slideDegR);
	    					System.out.println("Nopeuskerroin: " + Math.cos(Math.toRadians((360 - selectedCar.getDegrees() + slideDegL)/4)));
	    				}
	    				
	    			}
	    			else{
	    				float prevSpeed = selectedCar.getSpeed();
	    			selectedCar.setSpeed((float)(selectedCar.getSpeed()*Math.abs(Math.cos(Math.toRadians((selectedCar.getDegrees() - slideDegL)/4)))));
	    				if(prevSpeed - selectedCar.getSpeed() >= 3){
	    					System.out.println("Rikkihän se meni (vasen2)");
	    					System.out.println("Auton kulma :" + selectedCar.getDegrees());
	    					System.out.println("Luiston kulma vasen :" + slideDegL);
	    					System.out.println("Luiston kulma oikea :" + slideDegR);
	    					System.out.println("Nopeuskerroin: " + Math.cos(Math.toRadians((360 - selectedCar.getDegrees() + slideDegL)/4)));
	    				}
	    			}
	    			if(!session.inputManager.isPressed("Control")){
	    				selectedCar.setX(selectedCar.getX() + selectedCar.getSpeed()*(float)Math.cos(Math.toRadians(slideDegL)));
	                	selectedCar.setY(selectedCar.getY() + selectedCar.getSpeed()*(float)Math.sin(Math.toRadians(slideDegL)));
	    			}
	        		
	    			if(session.inputManager.isPressed("Control")){
	            		selectedCar.setX(selectedCar.getX() + selectedCar.getSpeed()*(float)Math.cos(Math.toRadians(handBrakeDeg)));
	            		selectedCar.setY(selectedCar.getY() + selectedCar.getSpeed()*(float)Math.sin(Math.toRadians(handBrakeDeg)));
	            	}
	            	// Lisää kääntymisenaloituskulmaan kääntösäteen ja luipparinlisäyskulman tulon
	            	
		            if(slideDegL <= -180){
		            	slideDegL = slideDegL + 360;
		            }
		            
		            	slideDegL -= selectedCar.getTurnRadius()*slideMultiplier*(0.4+selectedCar.getGrip());
			   
		            //  - 0.4f*(float)Math.sqrt(selectedCar.getSpeed())*selectedCar.getDownforce()
	            	
		        	if(session.inputManager.isPressed("Up") && !session.inputManager.isPressed("Control")){
		                   selectedCar.setDegrees(selectedCar.getDegrees() - (selectedCar.getTurnRadius()*slideMultiplier*(0.99f*(2 + (float)Math.sqrt(selectedCar.getSpeed()))*(selectedCar.getAcc1() + selectedCar.getAcc2() + selectedCar.getAcc3() + selectedCar.getAcc4()))));
		            }
	            	turningPointDegL = selectedCar.getDegrees();
	            	turningLeftIsPressed = false;
	    			
	    		}
        	}
    		
    		

        	
        	
        	
        	// Asettaa autolle X- ja Y-koordinaatit, jos se ei ole luistossa
        	
        		
        	
        
    	
    	
    	
    	//-------------------
    	
    	
    	
    	// Käsijarru
    	
    	
    	// Käsijarruhuijaksen estokeino
//    	if(!inputManager.isPressed("Control")){
//    		brakeIsPressed = false;
//    		if(brakeIsLifted == false){
//    			selectedCar.setSpeed(selectedCar.getSpeed()*0.75f);
//    			brakeIsLifted = true;
//    		}
//    	}
    	
    	
    	
    	
    	if(!session.inputManager.isPressed("Right")){
    		turningRightIsPressed = false;
//    		turningPointDegR = selectedCar.getDegrees();
    	}
    	if(!session.inputManager.isPressed("Left")){
    		turningLeftIsPressed = false;
//    		turningPointDegL = selectedCar.getDegrees();
    	}
        // "Ilmanvastus".
//    	powValue = 1 + (selectedCar.getSpeed()*0.5f);
//        speedMultiplier = (float)Math.pow(0.999, powValue);
        
        
        powValue = 0.993 - (float)Math.pow(Math.abs(Math.abs(selectedCar.getSpeed())), 0.1)*0.00001;
        if(selectedCar.getSpeed() < 0){
        	powValue = powValue - (float)Math.pow(Math.abs(Math.abs(selectedCar.getSpeed())), 0.1)*0.005;
        }
        speedMultiplier = (float)(1 * powValue);
        
        
        
        selectedCar.setSpeed(selectedCar.getSpeed()*speedMultiplier);
        if(previousSpeed - selectedCar.getSpeed() > 5){
//        	System.out.println("Degrees: " + selectedCar.getDegrees());
//        	System.out.println("TurningPointDegR: " + turningPointDegR);
//        	System.out.println("TurningPointDegL: " + turningPointDegL);
//        	System.out.println("SlideDegR: " + slideDegR);
//        	System.out.println("SlideDegL: " + slideDegL);
//        	System.out.println("HandBrakeDeg: " + handBrakeDeg);
//        	System.out.println("Slide: " + slideHasStarted);
//        	System.out.println("SlideL: " + slideHasStartedL);
//        	System.out.println("SlideR: " + slideHasStartedR);
//        	System.out.println("Cos inputvalue: " + (360 + selectedCar.getDegrees() - slideDegR));
        }
        previousSpeed = selectedCar.getSpeed();
        
    	// Nopeuden pyöristys nollaan.
    	if(selectedCar.getSpeed() <= 0.05f && selectedCar.getSpeed() >= -0.05f){
        	selectedCar.setSpeed(0f);
        }
    	

		if(((slideHasStartedL || slideHasStartedR) && (session.inputManager.isPressed("Up") || selectedCar.getSpeed() > 4.5f)  || handBrakeIsPressed) && selectedCar.getSpeed() != 0){
			
				if(counter1 == 0){
					GeneralPath polylineRearRight = 
					        new GeneralPath(GeneralPath.WIND_EVEN_ODD, 10);
					GeneralPath polylineRearLeft = 
					        new GeneralPath(GeneralPath.WIND_EVEN_ODD, 10);
					slideListRearRight.add(polylineRearRight);
					slideListRearLeft.add(polylineRearLeft);
					slideListRearRight.get(slideCounter).moveTo((float)selectedCar.getRearRightCornerX(), (float)selectedCar.getRearRightCornerY());
					slideListRearLeft.get(slideCounter).moveTo((float)selectedCar.getRearLeftCornerX(), (float)selectedCar.getRearLeftCornerY());
					
					
				}
				if(imageHasBeenCreated){
					slideImage = makeImage(slideShape);
					imageHasBeenCreated = false;
				}
				
				if(counter1 > 0){
					slideListRearRight.get(slideCounter).lineTo((float)selectedCar.getRearRightCornerX(), (float)selectedCar.getRearRightCornerY());
					slideListRearLeft.get(slideCounter).lineTo((float)selectedCar.getRearLeftCornerX(), (float)selectedCar.getRearLeftCornerY());
					
				}
				counter1++;
				needForStop = true;
				
		}
		else{
			if(needForStop){
				counter1 = 0;
				slidesPath.append(slideListRearRight.get(slideCounter), false);
				slidesPath.append(slideListRearLeft.get(slideCounter), false);
				AffineTransform at = null;
				slideShape = slidesPath.createTransformedShape(at);
				imageHasBeenCreated = true;
				
				slideCounter++;
//				System.out.println(slideCounter);
				needForStop = false;
			}
		}
//		if((!slideHasStartedL && !slideHasStartedR && !handBrakeIsPressed) && needForStop){
//			counter1 = 0;
//			slidesPath.append(slideListRearRight.get(slideCounter), false);
//			slidesPath.append(slideListRearLeft.get(slideCounter), false);
//			AffineTransform at = null;
//			slideShape = slidesPath.createTransformedShape(at);
//			imageHasBeenCreated = true;
//			
//			slideCounter++;
////			System.out.println(slideCounter);
//			needForStop = false;
//		}
        
    }
    /*
     * checkCollisions-metodi tarkistaa, leikkaako auton alue toisen alueen (hiekka tai seinä).
     */
    public void checkCollisions(){
    	for(int i = 0; i < walls.size(); i++){
	    	Area check = (Area)selectedCar.getArea().clone();
	    	Area otherArea = walls.get(i);
	    	check.intersect(otherArea);
	    	if(!check.isEmpty()){
	    		if(selectedCar.getSpeed() > 0 && hasHitaWallBackwards == false){
	    			hittingWallForwards = true;
	    			hasHitaWallForwards = true;
		    		break;
	    		}
	    		if(selectedCar.getSpeed() < 0 && hasHitaWallForwards == false){
	    			hittingWallBackwards = true;
	    			hasHitaWallBackwards = true;
	    			break;
	    		}
	    		
	    	}
	    	
	    	if(check.isEmpty()){
	    		if(selectedCar.getSpeed() < -1f){
	    			hasHitaWallForwards = false;
	    		}
	    		hittingWallForwards = false;
	    		hasHitaWallBackwards = false;
	    		hittingWallBackwards = false;
	    		
	    		
	    		
	    		
	    	}
	    	
	    }
    		tiresOnSand = 0;
    		Point rearRight = selectedCar.getRearRightPoint();
	    	Point rearLeft = selectedCar.getRearLeftPoint();
	    	Point frontRight = selectedCar.getFrontRightPoint();
	    	Point frontLeft = selectedCar.getFrontLeftPoint();
	    	for(int i = 0; i < selectedTrack.getSand().size(); i++){
//		    	Area check = (Area)selectedCar.getArea().clone();
		    	
		    	Area otherArea = selectedTrack.getSand().get(i);
		    	
		    	if(otherArea.contains(frontLeft)){
		    		tiresOnSand++;
		    	}
		    	if(otherArea.contains(frontRight)){
		    		tiresOnSand++;
		    	}
		    	if(otherArea.contains(rearLeft)){
		    		tiresOnSand++;
		    	}
		    	if(otherArea.contains(rearRight)){
		    		tiresOnSand++;
		    	}
		    	
//		    	if(!check.isEmpty()){
//		    		onSand = true;
//		    		break;
//		    	}
//		    	else{
//		    		onSand = false;
//		    	}
	    	}
    	
    }
    /*
     * checkStartLine-metodi tarkistaa onko kierros aloitettu/lopetettu oikeaoppisesti kaikki sektorit läpikäyden.
     */
    public void checkStartLine(){
    	Area check = (Area)selectedCar.getArea().clone();
    	Area otherArea = selectedTrack.getStartLine();
    	check.intersect(otherArea);
    	// Kierroksen lopetus.
    	if(!check.isEmpty() && lapStarted == true && sector1 == true && sector2 == true && sector3 == true){
    		lastLap = "" + timer.lastLap();
    		lastLapdouble = timer.lastLap();
    		// Kierrosaikalaskuja.
    		averageLapTimeTotal = averageLapTimeTotal + lastLapdouble;
    		averageLapTime = averageLapTimeTotal/onGoingLap;
    		averageLapTime = Math.round(averageLapTime * 1000.0);
    		averageLapTime = averageLapTime / 1000.0;
    		// Tarkistusarvoja.
    		lapStarted = false;
    		sector1 = false;
    		sector2 = false;
    		sector3 = false;
    		// Loppuanimaation aloitusehto.
    		if(onGoingLap == 10){
    			endGame = true;
    		}
    		
    	}
    	// Kierroksen aloitus.
    	if(!check.isEmpty()){
    		if(lapStarted == false){
    			timer.startWatch(System.currentTimeMillis());
    			onGoingLap += 1;
    		}
    		setTimerStarted(true);
    		lapStarted = true;
    		if(endGame){
    			setTimerStarted(false);
    		}
    		
    		
    	}
    	
    	
    }
    /*
     * checkSector1-3 -metodit tarkistavat että kierros tulee oikeaoppisesti täyteen.
     */
    public void checkSector1(){
    	Area check = (Area)selectedCar.getArea().clone();
    	Area otherArea = selectedTrack.getSector1Line();
    	check.intersect(otherArea);
    	if(!check.isEmpty() && lapStarted == true && sector1 == false){
    		sector1 = true;
    	}
    }
    public void checkSector2(){
    	Area check = (Area)selectedCar.getArea().clone();
    	Area otherArea = selectedTrack.getSector2Line();
    	check.intersect(otherArea);
    	if(!check.isEmpty() && lapStarted == true && sector1 == true && sector2 == false){
    		sector2 = true;
    	}
    }
    public void checkSector3(){
    	Area check = (Area)selectedCar.getArea().clone();
    	Area otherArea = selectedTrack.getSector3Line();
    	check.intersect(otherArea);
    	if(!check.isEmpty() && lapStarted == true && sector1 == true && sector2 == true && sector3 == false){
    		sector3 = true;
    	}
    }
    
    /*
     * checkBestLap-metodi tarkistaa, oliko edellinen kierros paras.
     */
    public void checkBestLap(){
    	if (bestLap == null && lastLap != null){
    		bestLap = lastLap;
    		bestLapdouble = lastLapdouble;
    	}
    	if (lastLapdouble < bestLapdouble){
    		bestLap = lastLap;
    		bestLapdouble = lastLapdouble;
    	}
    }
    
    public String getLastLap() {
		return lastLap;
	}

	public void setLastLap(String lastLap) {
		this.lastLap = lastLap;
	}

	// shadowCalculator-metodi laskee varjolle oikean sijainnin suhteessa autoon. "Valon lähde" on ruudun koko alalaita.
    public void shadowCalculator(){
    	if(selectedCar.getDegrees() <= 90 && selectedCar.getDegrees() >= -90){
    		setShadowY(selectedCar.getY() - 4*(float)(Math.sin(Math.toRadians(90 + selectedCar.getDegrees()))));
    	}
    	else{
    		setShadowY(selectedCar.getY() - 4*(float)(Math.sin(Math.toRadians(90 + selectedCar.getDegrees()))));
    	}
    	if(selectedCar.getDegrees() <= 0){
    		setShadowX(selectedCar.getX() +  4*(float)(Math.cos(Math.toRadians(90 + selectedCar.getDegrees()))));
    	}
    	else{
    		setShadowX(selectedCar.getX() +  4*(float)(Math.cos(Math.toRadians(90 + selectedCar.getDegrees()))));
    	}
    }
    
    // endGameMove-metodi liikuttaa autoa loppuanimaatiossa. Sisältää checkCollisions-metodin.
    public void endGameMove(){
    	if(!onSand && !hittingWallForwards && !hittingWallBackwards){
    		// Rajoittaa huippunopeuden eteenpäin
	    	if (Math.abs (selectedCar.getSpeed()) > selectedCar.getTopSpeed()) {
	    		selectedCar.setSpeed(selectedCar.getTopSpeed());
	        }
	    	// Rajoittaa huippunopeuden taaksepäin
	        if (selectedCar.getSpeed() < -3.5f) {
	        	selectedCar.setSpeed(-3.5f);
	        }
	        
    	}
    	if(Math.abs(selectedCar.getSpeed()) > 0.5f){
    		if(tiresOnSand == 1){
        		selectedCar.setSpeed(selectedCar.getSpeed()*0.94f);
        	}
        	if(tiresOnSand == 2){
        		selectedCar.setSpeed(selectedCar.getSpeed()*0.92f);
        	}
        	if(tiresOnSand == 3){
        		selectedCar.setSpeed(selectedCar.getSpeed()*0.90f);
        	}
        	if(tiresOnSand == 4){
        		selectedCar.setSpeed(selectedCar.getSpeed()*0.86f);
        	}
    	}
    	
    	
    	
    	// Seinässä
    	// Etuperin törmäys
    	if(hittingWallForwards){
    		if(selectedCar.getSpeed() <= 1.68f && selectedCar.getSpeed() >= 0){
    			selectedCar.setSpeed(-1f);
    		}
    		else{
    			selectedCar.setSpeed(selectedCar.getSpeed()*-0.6f);
    		}
    			
  
    	}
    	// Takaperin törmäys
    	if(hittingWallBackwards){
    		if(selectedCar.getSpeed() >= -1 && selectedCar.getSpeed() <= 0){
    			selectedCar.setSpeed(1f);
    		}
    		else{
    			selectedCar.setSpeed(selectedCar.getSpeed()*-1f);
    		}
    				
    	}
    	selectedCar.setSpeed(selectedCar.getSpeed()*0.955f);
    	selectedCar.setX(selectedCar.getX() + selectedCar.getSpeed()*(float)Math.cos(Math.toRadians(selectedCar.getDegrees())));
    	selectedCar.setY(selectedCar.getY() + selectedCar.getSpeed()*(float)Math.sin(Math.toRadians(selectedCar.getDegrees())));
    	// Jos auton nopeus on riittävän lähellä nollaa, kutsutaan endGame-metodia.
    	if(selectedCar.getSpeed() <= 0.001f && selectedCar.getSpeed() >= -0.001f){
    		session.game.endGame();
    	}
    	
    }

	public double getxOffset() {
		return xOffset;
	}

	public void setxOffset(double xOffset) {
		this.xOffset = xOffset;
	}

	public double getyOffset() {
		return yOffset;
	}

	public void setyOffset(double yOffset) {
		this.yOffset = yOffset;
	}

	public double getXnegOffset() {
		return xnegOffset;
	}

	public void setXnegOffset(double xnegOffset) {
		this.xnegOffset = xnegOffset;
	}

	public double getYnegOffset() {
		return ynegOffset;
	}

	public void setYnegOffset(double ynegOffset) {
		this.ynegOffset = ynegOffset;
	}

	public double getxCounterOffset() {
		return xCounterOffset;
	}

	public void setxCounterOffset(double xCounterOffset) {
		this.xCounterOffset = xCounterOffset;
	}

	public double getyCounterOffset() {
		return yCounterOffset;
	}

	public void setyCounterOffset(double yCounterOffset) {
		this.yCounterOffset = yCounterOffset;
	}

	public int getSpeedoX() {
		return speedoX;
	}

	public void setSpeedoX(int speedoX) {
		this.speedoX = speedoX;
	}

	public int getSpeedoY() {
		return speedoY;
	}

	public void setSpeedoY(int speedoY) {
		this.speedoY = speedoY;
	}

	public boolean isTimerStarted() {
		return timerStarted;
	}

	public void setTimerStarted(boolean timerStarted) {
		this.timerStarted = timerStarted;
	}

	public float getShadowY() {
		return shadowY;
	}

	public void setShadowY(float shadowY) {
		this.shadowY = shadowY;
	}

	public float getShadowX() {
		return shadowX;
	}

	public void setShadowX(float shadowX) {
		this.shadowX = shadowX;
	}

	public int getLastPointToDraw() {
		return lastPointToDraw;
	}

	public void setLastPointToDraw(int lastPointToDraw) {
		this.lastPointToDraw = lastPointToDraw;
	}


	public ArrayList<GeneralPath> getSlideListRearRight() {
		return slideListRearRight;
	}

	public void setSlideListRearRight(ArrayList<GeneralPath> slideListRearRight) {
		this.slideListRearRight = slideListRearRight;
	}

	public ArrayList<GeneralPath> getSlideListRearLeft() {
		return slideListRearLeft;
	}

	public void setSlideListRearLeft(ArrayList<GeneralPath> slideListRearLeft) {
		this.slideListRearLeft = slideListRearLeft;
	}

	public GeneralPath getSlidesPath() {
		return slidesPath;
	}

	public void setSlidesPath(GeneralPath slidesPath) {
		this.slidesPath = slidesPath;
	}

	public Shape getSlideShape() {
		return slideShape;
	}

	public void setSlideShape(Shape slideShape) {
		this.slideShape = slideShape;
	}

	public Image makeImage(Shape s) {
	    Rectangle r = new Rectangle(1300, 825);
	    BufferedImage image = new BufferedImage(r.width, r.height, BufferedImage.TRANSLUCENT);
	    Graphics2D gr = image.createGraphics();
	    // move the shape in the region of the image
//	    gr.translate(-r.x, -r.y);
	    RenderingHints hints = new RenderingHints(null);
	    hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
	    hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    hints.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	    hints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	    hints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
//	    gr.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
	    gr.setColor(new java.awt.Color(0, 0, 0));
	    gr.setRenderingHints(hints);
	    gr.setStroke((Stroke) new BasicStroke(3));
	    gr.draw(s);
	    gr.dispose();
	    return image;
	}

	public Image getSlideImage() {
		return slideImage;
	}

	public void setSlideImage(Image slideImage) {
		this.slideImage = slideImage;
	}

}
