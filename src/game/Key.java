package game;

import java.awt.Color;
import java.awt.Graphics;

/** 
 * Represents the key that the player needs to collect in order to 
 * open the gate and win the game. 
 * 
 * @author Nishna Makala
 * @version 21.0.6 LTS (2025-01-21) - OpenJDK Runtime Environment Temurin-21.0.6+7
 */
public class Key extends Polygon{
	

	private static Point[] shape; //array of points that compose shape of this key object
	
	static {
		
		shape = new Point[] {new Point(0, 0), new Point(0, 10), new Point(-10, 20), new Point(-20, 20), 
				 new Point(-30, 20), new Point(-40, 10), new Point(-40, 0), new Point(-40, -10), 
				 new Point(-30, -20), new Point(-20, -20), new Point(-10, -20), new Point(0, -10), 
				 new Point(0, 0), new Point(-25, 0), new Point(-15, 0), new Point(-15, -50), 
				 new Point(-25, -50), new Point(-25, 0)};
	}
	

	
	/**
	 * Constructs new key object. This key is 
	 * positioned at coordinates (660, 20) and has an initial rotation of 90 degrees.
	 */
	public Key() {
		super(shape, new Point(660,20), 90);
	}
	
	
	
	/**
	 * The method draws a yellow key on screen using Graphics object
	 * 
	 * @param brush    An instance of the Graphics class
	 */
	public void paint(Graphics brush) {
        brush.setColor(Color.yellow); // Set the fill color

        // Convert Point array to int arrays
        int[] xPoints = new int[getPoints().length];
        int[] yPoints = new int[getPoints().length];

        for (int i = 0; i < getPoints().length; i++) {
            xPoints[i] = (int) (this.getPoints()[i].getX());
            yPoints[i] = (int) (this.getPoints()[i].getY());
        }

        // Draw filled polygon
        brush.fillPolygon(xPoints, yPoints, getPoints().length);
	}
	

}

