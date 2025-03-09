package game;

import java.awt.Color;
import java.awt.Graphics;

public class Key extends Polygon{
	

	private static Point[] shape;
	
	static {
		shape = new Point[] {new Point(0, 0), new Point(0, 10), new Point(-10, 20), new Point(-20, 20), 
				 new Point(-30, 20), new Point(-40, 10), new Point(-40, 0), new Point(-40, -10), 
				 new Point(-30, -20), new Point(-20, -20), new Point(-10, -20), new Point(0, -10), 
				 new Point(0, 0), new Point(-25, 0), new Point(-15, 0), new Point(-15, -50), 
				 new Point(-25, -50), new Point(-25, 0)};
	}
	

	/**A constructor for the key which the player needs to collect to win
	 * 
	 * @param shape   Describes the shape of the key using points
	 */
	public Key() {
		super(shape, new Point(660,20) ,90);
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

