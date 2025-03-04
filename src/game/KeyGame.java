package game;

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
	static Player MainCharacter;

  public KeyGame() {
    super("KeyGame!!!", 800, 600);
    this.setFocusable(true);
	this.requestFocus();
  }
  
	public void paint(Graphics brush) {
		Player MainCharacter = new Player();
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
    	
    	// sample code for printing message for debugging
    	// counter is incremented and this message printed
    	// each time the canvas is repainted
    	counter++;
    	brush.setColor(Color.white);
    	brush.drawString("Counter is " + counter,10,10);
    	brush.setColor(Color.pink);
    	MainCharacter.paint(brush);
  }
  
	public static void main (String[] args) {
		KeyGame a = new KeyGame();
		a.repaint();
		Player MainCharacter = new Player();
  }
	
}