package game;

import java.awt.Color;
import java.awt.Graphics;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/

import java.awt.event.*;
import java.util.ArrayList;

import game.BreakableBlocks.Power;

class KeyGame extends Game implements KeyListener{
	static int counter = 0;
	static Player MainCharacter;
	private ArrayList <BreakableBlocks> breakableBlockRow = new ArrayList<BreakableBlocks>(); //consists of one row of breakable blocks
	private Key key; // the key needed to pass level
	static boolean leftArrowPressed, rightArrowPressed, gameOver;
	private Gate gate;
	private Power testPower;

	


	
	
	
  public KeyGame() {
    super("KeyGame!!!", 800, 600);
    this.setFocusable(true);
	this.requestFocus();
	this.addKeyListener(this);
	MainCharacter = new Player();
	
	
	//Adding blocks
	for (int i = 0; i <= 20; i++) {
		breakableBlockRow.add(new BreakableBlocks((40*i), 500, false));
	}
	for (int i = 1; i <= 4; i++) { //draws blocks in a single row 40 spaces apart
		breakableBlockRow.add(new BreakableBlocks(350+(40*i), 400, false));
		breakableBlockRow.add(new BreakableBlocks(100+(40*i), 300, false));
		breakableBlockRow.add(new BreakableBlocks(300+(40*i), 200, false));
		breakableBlockRow.add(new BreakableBlocks(500+(40*i), 80, false));
	}
	
	for (int i = 1; i <= 3; i++) { //draws blocks in a single row 40 spaces apart
		breakableBlockRow.add(new BreakableBlocks(500+(40*i), 300, false));
	}
	breakableBlockRow.add(new BreakableBlocks(500+(40*4), 300, true));
	testPower = breakableBlockRow.get(breakableBlockRow.size() - 1).new Power();
	


	
	
	key = new Key(); 
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
	    	if(testPower.isPowerUpActive(MainCharacter)) {
	    		brush.setColor(Color.RED);
	    	}
	    	else {
	    		brush.setColor(Color.pink);
	    	}
	    	MainCharacter.move(leftArrowPressed, rightArrowPressed);
	    	MainCharacter.playerState.updateJump(); //update state (is player jumping)
	    	MainCharacter.paint(brush);
	    	gate.paint(brush);
	    	
	    	testPower.isActivated(MainCharacter);
	    	testPower.activatePowerUp(MainCharacter);
	    	
	    	
	    	
	    	
	    	
	    	breakableBlockRow.forEach(block -> { //Lambda expression requirement
	    		block.paint(brush);
	    		block.collides(MainCharacter);
	    		MainCharacter.gravity(block);
	    	});
	    	
	    	
	    	
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
			if (!MainCharacter.getIsJumping()) { //add back .playerState.
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