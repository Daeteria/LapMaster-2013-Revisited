package game;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

import javax.swing.ImageIcon;


/*
 * Car2-luokka sis‰lt‰‰ kaikki autoon liittyv‰t ominaisuudet ja ominaisuuksille tarvittavat getterit ja setterit. 
 * Car2-luokan sis‰lt‰m‰t arvot ovat auton kuva, auton varjon kuva, nimi, sijainti x- ja y-koordinaatteina, nopeus, suuntakulma, auton leveys ja pituus, kiihtyvyysarvo, suuri kiihtyvyysarvo,
 * suuren kiihtyvyyden alkamisnopeus, suuren kiihtyvyyden loppumisnopeus, huippunopeusrajoitus, k‰‰ntˆs‰de, jarruteho ja downforce.
 * 
 */
public class Car2 {

    private float x;
    private float y;
    private float speed;
    private float degrees;
    private double width;
    private double height;
    private float acc1;
    private float acc2;
    private float acc3;
    private float acc4;
    private float accStart2;
    private float accStart3;
    private float accStart4;
    private float topSpeed;
    private float turnRadius;
    private float brakePower;
    private float downforce;
    private float grip;
    private float standardGrip;
   


	private String name;
	private Image image;
    private Image image2;
    
    private double rearLeftCornerX;
    private double rearLeftCornerY;
    private double rearRightCornerX;
    private double rearRightCornerY;
    
    private double frontLeftCornerX;
    private double frontLeftCornerY;
    private double frontRightCornerX;
    private double frontRightCornerY;
    
    private Point rearLeftPoint = new Point();
    public Point getRearLeftPoint() {
		return rearLeftPoint;
	}



	public void setRearLeftPoint(Point rearLeftPoint) {
		this.rearLeftPoint = rearLeftPoint;
	}



	public Point getRearRightPoint() {
		return rearRightPoint;
	}



	public void setRearRightPoint(Point rearRightPoint) {
		this.rearRightPoint = rearRightPoint;
	}



	private Point rearRightPoint = new Point();
    private Point frontLeftPoint = new Point();
    private Point frontRightPoint = new Point();

    public Car2(String name, String car, String shadow, float acc1, float acc2, float acc3, float acc4, float accStart2, float accStart3, float accStart4, float topSpeed, float turnRadius, float brakePower, float downforce, float grip) {
        ImageIcon ii = new ImageIcon(car);
        ImageIcon ii2 = new ImageIcon(shadow);
        image = ii.getImage();
        image2 = ii2.getImage();
        
        
        x = 720;
        y = 80;
        width = image.getWidth(null);
        height = image.getHeight(null);
        this.acc1 = acc1;
        this.acc2 = acc2;
        this.acc3 = acc3;
        this.acc4 = acc4;
        this.accStart2 = accStart2;
        this.accStart3 = accStart3;
        this.accStart4 = accStart4;
        this.topSpeed = topSpeed;
        this.turnRadius = turnRadius;
        this.brakePower = brakePower;
        this.downforce = downforce;
        this.name = name;
        this.grip = grip;
        standardGrip = grip;
    }
    

    
    public String getName() {
		return name;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

    public float getDegrees() {
		return degrees;
	}

	public void setDegrees(float degrees) {
		this.degrees = degrees;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getY() {
        return y;
    }

	public Image getImage() {
        return image;
    }
	
	public Image getShadow( ){
		return image2;
	}

	public float getAcc1() {
		return acc1;
	}
	
	public float getAcc2() {
		return acc2;
	}
	
	public float getAcc3() {
		return acc3;
	}
	
	public float getAcc4() {
		return acc4;
	}
	public float getAccStart2() {
		return accStart2;
	}
	public float getAccStart3() {
		return accStart3;
	}
	public float getAccStart4() {
		return accStart4;
	}
	public float getTurnRadius() {
		return turnRadius;
	}

	public float getTopSpeed() {
		return topSpeed;
	}
	
	public float getBrakePower() {
		return brakePower;
	}
	public float getDownforce(){
		return downforce;
	}
	public float getGrip(){
		return grip;
	}
	
	
	public Rectangle getBounds() {
		return new Rectangle((int)x+1, (int)y+1, (int)width-2, (int)height-2);
		}
	
	/* Metodi k‰‰nt‰‰ auton peitt‰m‰n alueen auton kuvan kulman mukaan ja palauttaa uuden alueen. 
	 * Aluetta k‰ytet‰‰n auton paikan m‰‰ritt‰miseen, jotta hiekka-alueet ja radan ulkoreunakollisiot toimisivat.
	*/
	public Area getArea(){
		Area carArea = new Area(this.getBounds().getBounds2D());
		AffineTransform atArea = new AffineTransform();
		atArea.rotate(Math.toRadians(this.getDegrees()),this.getX() + this.getWidth()/2, this.getY() + this.getHeight()/2);
		carArea.transform(atArea);
		
		
		
		
		
		rearLeftCornerX = carArea.getBounds().getCenterX() - 0.8*this.getWidth()/2 * Math.cos(Math.toRadians(this.getDegrees())) + 0.65*this.getHeight()/2 * Math.cos(Math.toRadians(this.getDegrees() - 90));
		rearLeftCornerY = carArea.getBounds().getCenterY() - 0.8*this.getWidth()/2 * Math.sin(Math.toRadians(this.getDegrees())) + 0.65*this.getHeight()/2 * Math.sin(Math.toRadians(this.getDegrees() - 90));
		rearRightCornerX = carArea.getBounds().getCenterX() - 0.8*this.getWidth()/2 * Math.cos(Math.toRadians(this.getDegrees())) + 0.65*this.getHeight()/2 * Math.cos(Math.toRadians(this.getDegrees() + 90));
		rearRightCornerY = carArea.getBounds().getCenterY() - 0.8*this.getWidth()/2 * Math.sin(Math.toRadians(this.getDegrees())) + 0.65*this.getHeight()/2 * Math.sin(Math.toRadians(this.getDegrees() + 90));
		
		frontLeftCornerX = carArea.getBounds().getCenterX() + 0.8*this.getWidth()/2 * Math.cos(Math.toRadians(this.getDegrees())) + 0.65*this.getHeight()/2 * Math.cos(Math.toRadians(this.getDegrees() - 90));
		frontLeftCornerY = carArea.getBounds().getCenterY() + 0.8*this.getWidth()/2 * Math.sin(Math.toRadians(this.getDegrees())) + 0.65*this.getHeight()/2 * Math.sin(Math.toRadians(this.getDegrees() - 90));
		frontRightCornerX = carArea.getBounds().getCenterX() + 0.8*this.getWidth()/2 * Math.cos(Math.toRadians(this.getDegrees())) + 0.65*this.getHeight()/2 * Math.cos(Math.toRadians(this.getDegrees() + 90));
		frontRightCornerY = carArea.getBounds().getCenterY() + 0.8*this.getWidth()/2 * Math.sin(Math.toRadians(this.getDegrees())) + 0.65*this.getHeight()/2 * Math.sin(Math.toRadians(this.getDegrees() + 90));
		
		rearLeftPoint.setLocation(rearLeftCornerX, rearLeftCornerY);
		rearRightPoint.setLocation(rearRightCornerX, rearRightCornerY);
		frontLeftPoint.setLocation(frontLeftCornerX, frontLeftCornerY);
		frontRightPoint.setLocation(frontRightCornerX, frontRightCornerY);
		
		return carArea;
	}
	
	public AffineTransform getCarTransform(){
		AffineTransform atArea = new AffineTransform();
		atArea.rotate(Math.toRadians(this.getDegrees()),this.getX() + this.getWidth()/2, this.getY() + this.getHeight()/2);
		return atArea;
		
	}
	
	public void calculateCarCorners(){
		
	}


	public double getRearLeftCornerX() {
		return rearLeftCornerX;
	}



	public void setRearLeftCornerX(double rearLeftCornerX) {
		this.rearLeftCornerX = rearLeftCornerX;
	}



	public double getRearLeftCornerY() {
		return rearLeftCornerY;
	}



	public void setRearLeftCornerY(double rearLeftCornerY) {
		this.rearLeftCornerY = rearLeftCornerY;
	}



	public double getRearRightCornerX() {
		return rearRightCornerX;
	}



	public void setRearRightCornerX(double rearRightCornerX) {
		this.rearRightCornerX = rearRightCornerX;
	}



	public double getRearRightCornerY() {
		return rearRightCornerY;
	}



	public void setRearRightCornerY(double rearRightCornerY) {
		this.rearRightCornerY = rearRightCornerY;
	}



	public Point getFrontLeftPoint() {
		return frontLeftPoint;
	}



	public void setFrontLeftPoint(Point frontLeftPoint) {
		this.frontLeftPoint = frontLeftPoint;
	}



	public Point getFrontRightPoint() {
		return frontRightPoint;
	}



	public void setFrontRightPoint(Point frontRightPoint) {
		this.frontRightPoint = frontRightPoint;
	}
	public float getStandardGrip() {
		return standardGrip;
}
	 
	public void setGrip(float grip) {
			this.grip = grip;
	}

}