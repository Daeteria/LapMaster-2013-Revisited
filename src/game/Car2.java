package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;


/*
 * Car2-luokka sis�lt�� kaikki autoon liittyv�t ominaisuudet ja ominaisuuksille tarvittavat getterit ja setterit. 
 * Car2-luokan sis�lt�m�t arvot ovat auton kuva, auton varjon kuva, nimi, sijainti x- ja y-koordinaatteina, nopeus, suuntakulma, auton leveys ja pituus, kiihtyvyysarvo, suuri kiihtyvyysarvo,
 * suuren kiihtyvyyden alkamisnopeus, suuren kiihtyvyyden loppumisnopeus, huippunopeusrajoitus, k��nt�s�de, jarruteho ja downforce.
 * 
 */
public class Car2 {

	private float carOnMapX;
	private float carOnMapY;
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
    private BufferedImage bImage;
    
    private double rearLeftCornerX;
    private double rearLeftCornerY;
    private double rearRightCornerX;
    private double rearRightCornerY;
    
    private double frontLeftCornerX;
    private double frontLeftCornerY;
    private double frontRightCornerX;
    private double frontRightCornerY;
    
    private Point rearLeftPoint = new Point();

    private float shadowX;
    private float shadowY;
    
    private AffineTransform carImageTransform;
	private AffineTransform shadowImageTransform;
    
	private GeneralPath slidePathRight;
    private GeneralPath slidePathLeft;
    private Image slideImage;
    private int slideCounter;
    private boolean isSliding;

	private Point rearRightPoint = new Point();
    private Point frontLeftPoint = new Point();
    private Point frontRightPoint = new Point();

    public Car2(String name, String car, String shadow, float acc1, float acc2, float acc3, float acc4, float accStart2, float accStart3, float accStart4, float topSpeed, float turnRadius, float brakePower, float downforce, float grip) {
        ImageIcon ii = new ImageIcon(car);
        ImageIcon ii2 = new ImageIcon(shadow);
        image = ii.getImage();
        image.setAccelerationPriority(1);
        image2 = ii2.getImage();
        image2.setAccelerationPriority(1);
        bImage = convertToBufferedImage(image);
        
        
        x = 700;
        y = 70;
        carOnMapX = 600;
        carOnMapY = 400;
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
    
    public Car2() {
		// TODO Auto-generated constructor stub
	}



	public BufferedImage convertToBufferedImage(Image image)
    {
        BufferedImage newImage = new BufferedImage(
            image.getWidth(null), image.getHeight(null),
            BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
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

	public float getCarOnMapX() {
		return carOnMapX;
	}



	public void setCarOnMapX(float carOnMapX) {
		this.carOnMapX = carOnMapX;
	}



	public float getCarOnMapY() {
		return carOnMapY;
	}



	public void setCarOnMapY(float carOnMapY) {
		this.carOnMapY = carOnMapY;
	}



	public Image getImage() {
        return image;
    }
	
	public Image getShadow( ){
		return image2;
	}
	
	public BufferedImage getBImage() {
		return bImage;
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
		return new Rectangle((int)getX()+1, (int)getY()+1, (int)width-2, (int)height-2);
		}
	
	/* Metodi k��nt�� auton peitt�m�n alueen auton kuvan kulman mukaan ja palauttaa uuden alueen. 
	 * Aluetta k�ytet��n auton paikan m��ritt�miseen, jotta hiekka-alueet ja radan ulkoreunakollisiot toimisivat.
	*/
	public Area getArea(){
		Area carArea = new Area(this.getBounds().getBounds2D());
		AffineTransform atArea = new AffineTransform();
		atArea.rotate(Math.toRadians(this.getDegrees()),this.getX() + this.getWidth()/2, this.getY() + this.getHeight()/2);
		carArea.transform(atArea);
		
		
		
		
		
		rearLeftCornerX = carArea.getBounds().getCenterX() - 0.5*this.getWidth()/2 * Math.cos(Math.toRadians(this.getDegrees())) + 0.65*this.getHeight()/2 * Math.cos(Math.toRadians(this.getDegrees() - 90));
		rearLeftCornerY = carArea.getBounds().getCenterY() - 0.7*this.getWidth()/2 * Math.sin(Math.toRadians(this.getDegrees())) + 0.65*this.getHeight()/2 * Math.sin(Math.toRadians(this.getDegrees() - 90));
		rearRightCornerX = carArea.getBounds().getCenterX() - 0.5*this.getWidth()/2 * Math.cos(Math.toRadians(this.getDegrees())) + 0.65*this.getHeight()/2 * Math.cos(Math.toRadians(this.getDegrees() + 90));
		rearRightCornerY = carArea.getBounds().getCenterY() - 0.7*this.getWidth()/2 * Math.sin(Math.toRadians(this.getDegrees())) + 0.65*this.getHeight()/2 * Math.sin(Math.toRadians(this.getDegrees() + 90));
		
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
	/*
	public AffineTransform getCarTransform(){
		AffineTransform atArea = new AffineTransform();
		atArea.rotate(Math.toRadians(this.getDegrees()),this.getX() + this.getWidth()/2, this.getY() + this.getHeight()/2);
		return atArea;
		
	}*/
	
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



	public AffineTransform getCarImageTransform() {
		return carImageTransform;
	}



	public void setCarImageTransform(AffineTransform carImageTransform) {
		this.carImageTransform = carImageTransform;
	}



	public AffineTransform getShadowImageTransform() {
		return shadowImageTransform;
	}
	
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




	public void setShadowImageTransform(AffineTransform shadowImageTransform) {
		this.shadowImageTransform = shadowImageTransform;
	}
	
	public GeneralPath getSlidePathRight() {
		return slidePathRight;
	}

	public void setSlidePathRight(GeneralPath slidePathRight) {
		this.slidePathRight = slidePathRight;
	}

	public GeneralPath getSlidePathLeft() {
		return slidePathLeft;
	}

	public void setSlidePathLeft(GeneralPath slidePathLeft) {
		this.slidePathLeft = slidePathLeft;
	}

	public Image getSlideImage() {
		return slideImage;
	}

	public void setSlideImage(Image slideImage) {
		this.slideImage = slideImage;
	}

	public boolean isSliding() {
		return isSliding;
	}

	public void setSliding(boolean isSliding) {
		this.isSliding = isSliding;
	}

	public int getSlideCounter() {
		return slideCounter;
	}

	public void incrementSlideCounter() {
		slideCounter += 1;
	}

	public Car2 copyToPaint (Car2 carToCopy){
		Car2 copiedCar = new Car2();
		copiedCar.x = carToCopy.x;
		copiedCar.y = carToCopy.y;
		copiedCar.carOnMapX = carToCopy.carOnMapX;
		copiedCar.carOnMapY = carToCopy.carOnMapY;
		copiedCar.image = carToCopy.image;
		copiedCar.image2 = carToCopy.image2;
		copiedCar.carImageTransform = carToCopy.carImageTransform;
		copiedCar.shadowImageTransform = carToCopy.shadowImageTransform;
		copiedCar.speed = carToCopy.speed;
		copiedCar.slidePathLeft = carToCopy.slidePathLeft;
		copiedCar.slidePathRight = carToCopy.slidePathRight;
		copiedCar.slideImage = carToCopy.slideImage;
		copiedCar.slideCounter = carToCopy.slideCounter;
		copiedCar.isSliding = carToCopy.isSliding;
	    
		return copiedCar;
	}

}