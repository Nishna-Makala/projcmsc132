package game;
import java.awt.Graphics;


/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;

class KeyGame extends Game {
	static int counter = 0;
	private BreakableBlocks testBlock, testBlock2;
	private Key key;
	private Point[] breakableBlocksPoints = {new Point(0,0), new Point(0,30), new Point(30,30), new Point(30,0)};
	private Point[] keyPoints = {new Point(20, 50), new Point(17, 60), new Point(10, 67), new Point(0, 70), new Point(-10, 67), 
			new Point(-17, 60), new Point(-20, 50), new Point(-17, 40), new Point(-10, 32), 
			new Point(0, 30), new Point(10, 32), new Point(17, 40), new Point(20, 50), 
			new Point(-5, 50), new Point(5, 50), new Point(5, 0), new Point(-5, 0), new Point(-5, 50)};

  public KeyGame() {
    super("KeyGame!!!", 800, 600);
    this.setFocusable(true);
	this.requestFocus();
	testBlock = new BreakableBlocks(breakableBlocksPoints, 50, 500);
	testBlock2 = new BreakableBlocks(breakableBlocksPoints, 90, 500);
	key = new Key(keyPoints);
	
  }
  
	public void paint(Graphics brush) {
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
    	
    	testBlock.paint(brush);
    	testBlock2.paint(brush);
    	key.paint(brush);

    	
    	// sample code for printing message for debugging
    	// counter is incremented and this message printed
    	// each time the canvas is repainted
    	counter++;
    	brush.setColor(Color.white);
    	brush.drawString("Counter is " + counter,10,10);
  }
  
	public static void main (String[] args) {
		KeyGame a = new KeyGame();
		a.repaint();
  }
	
}