package game;

import java.awt.Color;
import java.awt.Graphics;

public class Key extends Polygon{
	
	/**A constructor for the key which the player needs to collect to win
	 * 
	 * @param shape   Describes the shape of the key using points
	 */
	public Key(Point[] shape) {
		super(shape, new Point(400,200) ,90);
	}
	
	
	
	/**
	 * The method draws a key
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