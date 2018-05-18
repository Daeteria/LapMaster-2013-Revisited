package game;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/*
 * Track-luokka sis�lt�� kaikki rataan liittyv�t tiedot. Se sis�lt�� ulko- ja sis�hiekka-alueet, maaliviivan kohdan, sektoreiden kohdat ja radan kuvan.
 */
public class Track{
	private ArrayList<Area> sand;
	private GeneralPath inside;
    private GeneralPath outside;
    private Area startLine;
    private Area sector1Line;
    private Area sector2Line;
    private Area sector3Line;
    private ImageIcon trackIcon;
    private Image trackImage;
    private String imagePath;
    private int sectorLineWidth = 164;
    private int startLineWidth = 90;
    private int startLineX;
    private int startLineY;
    private int startPositionX;
    private int startPositionY;
    private int trackWidth;
    private int trackHeight;
    
    
    /*
     * Rata luodaan menussa asetetun trackNumberin perusteella.
     */
	public Track(int trackNumber){
		
		if(trackNumber == 1){
			
			imagePath = "Images\\Rata1.png";
			trackIcon = new ImageIcon(imagePath);
		    trackImage = trackIcon.getImage();
			sand = new ArrayList<Area>();
			inside = new GeneralPath();
		    outside = new GeneralPath();
			startPositionX = 650;
			startPositionY = 80;
		    startLine = new Area(new Rectangle(910, 30, 1, 120));
		    sector1Line = new Area(new Rectangle(1010, 570, 1, 200));
		    sector2Line = new Area(new Rectangle(10, 550, 240, 1));
		    sector3Line = new Area(new Rectangle(5, 180, 240, 1));
			
			/*
			 * Hiekka-alueiden muodot luodaan GeneralPathin metodeita hy�dynt�en.
			 */
			inside.moveTo(270, 149);
	        inside.lineTo(1020, 149);
	        inside.curveTo(1020, 149, 1167, 160, 1130, 305);
	        inside.curveTo(1123, 315, 1106, 308, 1108, 350);
	        inside.lineTo(1112, 518);
	        inside.curveTo(1112, 518, 1100, 620, 1060, 610);
	        inside.curveTo(1060, 610, 1040, 630, 860, 520);
	        inside.curveTo(860, 520, 675, 420, 425, 540);
	        inside.curveTo(425, 540, 330, 592, 250, 583);
	        inside.curveTo(250, 583, 160, 580, 230, 503);
	        inside.curveTo(230, 503, 250, 483, 350, 446);
	        inside.curveTo(350, 446, 425, 415, 412, 330);
	        inside.curveTo(412, 330, 410, 280, 380, 255);
	        inside.curveTo(380, 255, 380, 240, 270, 235);
	        inside.curveTo(240, 240, 125, 160, 270, 149);

	        Area inSide = new Area(inside);

	        sand.add(inSide);

	        outside.moveTo(0, 0);
	        outside.lineTo(1300, 0);
	        outside.lineTo(1300, 400);
	        outside.lineTo(1233, 400);
	        outside.curveTo(1233, 405, 1226, 320, 1256, 320);
	        outside.curveTo(1240, 280, 1295, 78, 1100, 38);
	        outside.lineTo(135, 38);
	        outside.curveTo(135, 38, -25, 22, 20, 240);
	        outside.curveTo(22, 240, 0, 277, 155, 300);
	        outside.curveTo(200, 300, 250, 345, 150, 386);
	        outside.curveTo(150, 386, 31, 420, 40, 480);
	        outside.curveTo(45, 620, 15, 746, 300, 717);
	        outside.curveTo(430, 717, 552, 528, 900, 685);
	        outside.curveTo(1060, 725, 1160, 780, 1220, 600);
	        outside.curveTo(1220, 600, 1250, 510, 1232, 400);
	        outside.lineTo(1300, 400);
	        outside.lineTo(1300, 800);
	        outside.lineTo(0, 800);
	        outside.lineTo(0, 0);

	        Area outSide = new Area(outside);

	        sand.add(outSide);
		}
		if(trackNumber == 2){
			imagePath = "Images\\Rata2.png";
			trackIcon = new ImageIcon(imagePath);
			trackImage = trackIcon.getImage();
			sand = new ArrayList<Area>();
			inside = new GeneralPath();
		    outside = new GeneralPath();
			startPositionX = 650;
			startPositionY = 80;
		    startLine = new Area(new Rectangle(910, 30, 1, 120));
		    sector1Line = new Area(new Rectangle(1010, 570, 1, 200));
		    sector2Line = new Area(new Rectangle(10, 550, 240, 1));
		    sector3Line = new Area(new Rectangle(5, 300, 240, 1));
		    
		    inside.moveTo(257, 232);
		    inside.curveTo(257, 232, 307, 188, 607, 155);
		    inside.curveTo(607, 155, 900, 120, 1080, 170);
		    inside.curveTo(1080, 170, 1150, 220, 1155, 390);
		    inside.curveTo(1155, 390, 1140, 680, 1000, 600);
		    inside.curveTo(1000, 600, 940, 590, 600, 330);
		    inside.curveTo(600, 330, 500, 280, 400, 398);
		    inside.curveTo(400, 398, 357, 450, 257, 600);
		    inside.curveTo(257, 600, 234, 643, 210, 600);
		    inside.curveTo(210, 600, 110, 400, 257, 232);
	      
		    
		    outside.moveTo(0, 0);
	        outside.lineTo(1300, 0);
	        outside.lineTo(1300, 400);
	        outside.lineTo(1276, 405);
	        outside.curveTo(1276, 405, 1298, 98, 1130, 47);
	        outside.curveTo(1130, 47, 675, 10, 200, 54);
	        outside.curveTo(200, 54, 20, 72, 20, 380);
	        outside.curveTo(20, 380, 8, 645, 120, 722);
	        outside.curveTo(120, 722, 240, 845, 332, 722);
	        outside.curveTo(332, 722, 382, 672, 462, 522);
	        outside.curveTo(462, 522, 542, 422, 652, 522);
	        outside.curveTo(652, 522, 742, 592, 842, 702);
	        outside.curveTo(842, 702, 1002, 802, 1120, 742);
	        outside.curveTo(1120, 742, 1308, 665, 1276, 405);
	        outside.lineTo(1300, 400);
	        outside.lineTo(1300, 800);
	        outside.lineTo(0, 800);
	        outside.lineTo(0, 0);
		    Area inSide = new Area(inside);
		    Area outSide = new Area(outside);

	        sand.add(inSide);
	        sand.add(outSide);
		}
		
		if(trackNumber == 3){
			imagePath = "Images\\New track 2017_1.png";
			trackIcon = new ImageIcon(imagePath);
			trackImage = trackIcon.getImage();
			startLineX = 2856;
			startLineY = 620;
			startPositionX = 2700;
			startPositionY = 740;
			startLine = new Area(new Rectangle(startLineX, startLineY, 1, 300));
		    sector1Line = new Area(new Rectangle(6425, 1020, 6800, 1));
		    sector2Line = new Area(new Rectangle(4800, 2440, 1, 2750));
		    sector3Line = new Area(new Rectangle(265, 1950, 660, 1));
		}
		trackWidth = trackImage.getWidth(null);
	    trackHeight = trackImage.getWidth(null);
	}



	public int getTrackWidth() {
		return trackWidth;
	}



	public int getTrackHeight() {
		return trackHeight;
	}



	public int getStartPositionX() {
		return startPositionX;
	}



	public int getStartPositionY() {
		return startPositionY;
	}



	public ArrayList<Area> getSand() {
		return sand;
	}
	
	public void setSandPosition(int x, int y) {
	}

	public Area getStartLine() {
		return startLine;
	}



	public Area getSector1Line() {
		return sector1Line;
	}



	public Area getSector2Line() {
		return sector2Line;
	}



	public Area getSector3Line() {
		return sector3Line;
	}

	

	public String getImagePath() {
		return imagePath;
	}
	
	
	
	public Image getTrackImage() {
		return trackImage;
	}
	
	
	
}