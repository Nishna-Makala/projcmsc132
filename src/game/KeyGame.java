package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class KeyGame extends Game implements KeyListener{
	static int counter = 0;
	static Player MainCharacter;
	static boolean leftArrowPressed, rightArrowPressed, gameOver;
	private Gate gate;
	
	
	private ArrayList <BreakableBlocks> breakableBlockRow = new ArrayList<BreakableBlocks>(); //consists of one row of breakable blocks
	private Key key; // the key needed to pass level
	private Point[] breakableBlocksPoints = {new Point(0,0), new Point(0,30), new Point(30,30), new Point(30,0)};
	private Point[] keyPoints = {new Point(0, 0), new Point(0, 10), new Point(-10, 20), new Point(-20, 20), 
		 new Point(-30, 20), new Point(-40, 10), new Point(-40, 0), new Point(-40, -10), 
		 new Point(-30, -20), new Point(-20, -20), new Point(-10, -20), new Point(0, -10), 
		 new Point(0, 0), new Point(-25, 0), new Point(-15, 0), new Point(-15, -50), 
		 new Point(-25, -50), new Point(-25, 0)};
	
	
	
	

  public KeyGame() {
    super("KeyGame!!!", 800, 600);
    this.setFocusable(true);
	this.requestFocus();
	this.addKeyListener(this);
	MainCharacter = new Player();
	
	
	
	for (int i = 1; i <= 4; i++) { //draws blocks in a single row 40 spaces apart
		breakableBlockRow.add(new BreakableBlocks(breakableBlocksPoints, 80+(40*i), 500));
		breakableBlockRow.add(new BreakableBlocks(breakableBlocksPoints, 200+(40*i), 400));
		breakableBlockRow.add(new BreakableBlocks(breakableBlocksPoints, 300+(40*i), 300));
	}

	key = new Key(keyPoints); 
	gate = new Gate();
  }
  
	public void paint(Graphics brush) {
		if (!gameOver) { //draw regular screen (not win screen)
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
	    	gate.paint(brush);
	    	
	    	
	    	
	    	
	    	for (int index = 0; index < breakableBlockRow.size(); index++) {
	    		breakableBlockRow.get(index).paint(brush);
	    	}
	    	
	    	if (!MainCharacter.hasKey) { //draw key if not picked up yet
//	    		System.out.println(MainCharacter.getPoints().toString());
	    		for (Point p : MainCharacter.getPoints()) {
	    			if (key.contains(p)) {
	    				MainCharacter.hasKey = true;
	    				break;
	    			}
	    		}
	        	key.paint(brush);
	    	}
	    	
	    	else { //if player has key, check if they walk through door (i.e. center (position) is inside gate)
	    		//for (Point p : MainCharacter.getPoints()) {
	    			if (gate.gateShape.contains(MainCharacter.position)) {
	    				gameOver = true;
//	    				break;
	    			}
	    		//}
	    	}
		}
		
		else {
            new Object() { //win screen/fulfills anon class req
                {
                	brush.setColor(Color.MAGENTA);
                	brush.fillRect(0, 0, width, height);
//                	PoC of win screen - will make nice later (flashing b/w block text ?)
		}
            };
		}
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