package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;

class KeyGame extends Game implements KeyListener{
	static int counter = 0;
	static Player MainCharacter;
	static boolean leftArrowPressed, rightArrowPressed;

  public KeyGame() {
    super("KeyGame!!!", 800, 600);
    this.setFocusable(true);
	this.requestFocus();
	this.addKeyListener(this);
	MainCharacter = new Player();
  }
  
	public void paint(Graphics brush) {
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
    	
    	// sample code for printing message for debugging
    	// counter is incremented and this message printed
    	// each time the canvas is repainted
    	counter++;
    	brush.setColor(Color.white);
    	brush.drawString("Counter is " + counter,10,10);
    	brush.setColor(Color.pink);
    	MainCharacter.move(leftArrowPressed, rightArrowPressed);
    	MainCharacter.playerState.updateJump(); //update state (is player jumping)
    	MainCharacter.paint(brush);
  }
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftArrowPressed = true;
            if (MainCharacter != null && !MainCharacter.lookingLeft) {
                MainCharacter.reflect(); 
                MainCharacter.lookingLeft = true;
            }
        } 
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightArrowPressed = true;
            if (MainCharacter != null && MainCharacter.lookingLeft) {
                MainCharacter.reflect(); 
                MainCharacter.lookingLeft = false;
            }
        }
		else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (!MainCharacter.playerState.isJumping) {
				MainCharacter.playerState.startJump(MainCharacter.getPosition().getY());
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftArrowPressed = false;
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightArrowPressed = false;
		}
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
  
	public static void main (String[] args) {
		KeyGame a = new KeyGame();
		a.repaint();
  }
	
}