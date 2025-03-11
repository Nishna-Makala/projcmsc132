package game;

import java.awt.Color;
import java.awt.Graphics;

/** 
 * Represents a gate object in the game. Logic for opening gate handled in 
 * KeyGame class (i.e. boolean isOpen depends on player holding Key)
 * @author Carrick Southall
 * @version 21.0.6 LTS (2025-01-21) - OpenJDK Runtime Environment Temurin-21.0.6+7
 */

public class Gate {
	Polygon gateShape;
	boolean isOpen;
	Point[] gatePoints = {new Point(0, 0), new Point(40, 0), new Point(40, 100), new Point(0, 100)};
	
	/**
	 * Constructs the Gate object. 
	     * initializing gateShape from predefined array of Points 
	      */
	public Gate() {
		gateShape = new Polygon(gatePoints, new Point(740, 450), 0);
	}
	
	  /**
	   * Paints the gate on the right side of the screen
	   * 
	   * @param brush   An instance of the Graphics class
	   */
	public void paint(Graphics brush) {
        brush.setColor(Color.GRAY);

        Point[] points = gateShape.getPoints();
        int[] XVals = Player.splitPoints(points, true); //could prob make utilities class for this method but wtv
        int[] YVals = Player.splitPoints(points, false);

        brush.fillPolygon(XVals, YVals, points.length);
	}

}
