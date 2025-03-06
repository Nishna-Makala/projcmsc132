package game;

import java.awt.Color;
import java.awt.Graphics;

public class BreakableBlocks extends Polygon {
	
	/**A constructor for regular blocks in the game that serve as a
	 * mid-air platform for the main character
	 * 
	 * @param shape    An array list of points that make up the block
	 */
	public BreakableBlocks(Point[] shape, int xCoorBlock, int yCoorBlock) {
		super(shape, new Point(xCoorBlock,yCoorBlock) ,0.0);
		this.rotate(180);
	}
	
	
	
	
	
	/** The method draws a filled gray block.
	 *  
	 * @param brush   An instance of the Graphics class
	 */
	
	
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
	
	
	
	/** If main character is touching a platform, then all points in 
	 * main character should move either up or down until no longer 
	 * colliding with blocks. 
	 * 
	 * POSSIBLE PROBLEMS TO ADDRESS PRIOR TO IMPLEMENTATION:
	 * How to determine which direction to move in so that the main
	 * character "bounces" off the blocks? 
	 * 
	 * Should we implement method to check if 
	 * character has jumped and is "falling?" (moving downwards after because not
	 * on platform)
	 * 
	 * @param mainCharacter   An instance of main character class controlled by user
	 */
	public void collides(Polygon mainCharacter) {
		//implementation not yet written
		//if touches x-coordinate, then bounce back left, touches upper y coordinate bounce up
	}
	
	
	

}
