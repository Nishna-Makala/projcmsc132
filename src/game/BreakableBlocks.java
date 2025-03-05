package game;

import java.awt.Color;
import java.awt.Graphics;

public class BreakableBlocks extends Polygon {
	
	
	public BreakableBlocks(Point[] shape) {
		super(shape, new Point(50,50) ,0.0);
	}
	
	public void paint(Graphics brush) {
        brush.setColor(Color.lightGray); // Set the fill color

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
