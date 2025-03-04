package game;

import java.awt.Color;
import java.awt.Graphics;

public class BreakableBlock extends Polygon {
	
	private static Point[] shape;
	
	static {
		Point[] shape = {new Point(0,0), new Point(-4,0), new Point(0,4), new Point(-4,4)}; 
	}
	
	public BreakableBlock() {
		super(shape, new Point(6,0), 0.0);
		// TODO Auto-generated constructor stub
	}

	
	/*Question: How are points doubles and this needs to be array of ints?
	 * 
	 */
    public void paint(Graphics brush) {
    	brush.setColor(Color.red); 
        int[] xPoints = new int[this.getPoints().length];
        int[] yPoints = new int[this.getPoints().length];

        for (int i = 0; i < this.getPoints().length; i++) {
            xPoints[i] = (int) this.getPoints()[i].getX(); 
            yPoints[i] = (int) this.getPoints()[i].getY(); 
        }
        
        brush.fillPolygon(xPoints, yPoints, xPoints.length);
    }
}
