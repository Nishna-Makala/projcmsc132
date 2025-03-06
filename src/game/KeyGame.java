package game;
import java.awt.Graphics;
<<<<<<< HEAD


=======
>>>>>>> Nishna
/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;
<<<<<<< HEAD

class KeyGame extends Game {
	static int counter = 0;
=======
import java.util.ArrayList;

class KeyGame extends Game {
	static int counter = 0;
	private ArrayList <BreakableBlocks> breakableBlockRow = new ArrayList<BreakableBlocks>(); //consists of one row of breakable blocks
	private Key key; // the key needed to pass level
	private Point[] breakableBlocksPoints = {new Point(0,0), new Point(0,30), new Point(30,30), new Point(30,0)};
	private Point[] keyPoints = {new Point(0, 0), new Point(0, 10), new Point(-10, 20), new Point(-20, 20), 
		 new Point(-30, 20), new Point(-40, 10), new Point(-40, 0), new Point(-40, -10), 
		 new Point(-30, -20), new Point(-20, -20), new Point(-10, -20), new Point(0, -10), 
		 new Point(0, 0), new Point(-25, 0), new Point(-15, 0), new Point(-15, -50), 
		 new Point(-25, -50), new Point(-25, 0)};
>>>>>>> Nishna

  public KeyGame() {
    super("KeyGame!!!", 800, 600);
    this.setFocusable(true);
	this.requestFocus();
<<<<<<< HEAD
	BreakableBlock testBlock =  new BreakableBlock();
=======
	
	for (int i = 1; i <= 4; i++) { //draws blocks in a single row 40 spaces apart
		breakableBlockRow.add(new BreakableBlocks(breakableBlocksPoints, 80+(40*i), 500));
		breakableBlockRow.add(new BreakableBlocks(breakableBlocksPoints, 200+(40*i), 400));
		breakableBlockRow.add(new BreakableBlocks(breakableBlocksPoints, 300+(40*i), 300));
	}

	key = new Key(keyPoints); 
	
>>>>>>> Nishna
  }
  
	public void paint(Graphics brush) {
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
<<<<<<< HEAD
    	testBlock.paint(brush);
=======
    	
    	for (int index = 0; index < breakableBlockRow.size(); index++) {
    		breakableBlockRow.get(index).paint(brush);
    	}
    	
    	key.paint(brush);

>>>>>>> Nishna
    	
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