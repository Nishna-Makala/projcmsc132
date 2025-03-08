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
        int[] xPoints = new int[points.length];
        int[] yPoints = new int[points.length];

        for (int i = 0; i < points.length; i++) {
            xPoints[i] = (int) points[i].getX();
            yPoints[i] = (int) points[i].getY();
        }

        brush.fillPolygon(xPoints, yPoints, points.length);
	}

}
