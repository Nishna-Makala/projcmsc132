package game;

import java.awt.Color;
import java.awt.Graphics;

public class Key extends Polygon{
	
	public Key(Point[] shape) {
		super(shape, new Point(100,20) ,90);
	}
	
	
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
