package game;

import java.awt.Color;
import java.awt.Graphics;

public class Gate {
	Polygon gateShape;
	boolean isOpen;
	Point[] gatePoints = {new Point(0, 0), new Point(40, 0), new Point(40, 100), new Point(0, 100)};
	
	public Gate() {
		gateShape = new Polygon(gatePoints, new Point(740, 450), 0);
	}
	
	public void paint(Graphics brush) {
        brush.setColor(Color.GRAY);

        Point[] points = gateShape.getPoints();
        int[] XVals = Player.splitPoints(points, true); //could prob make utilities class for this method but wtv
        int[] YVals = Player.splitPoints(points, false);

        brush.fillPolygon(XVals, YVals, points.length);
	}

}
