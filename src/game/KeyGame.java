package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.ArrayList;
import game.BreakableBlocks.Power;

/*
CLASS: KeyGame
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/


/** 
 * The KeyGame class manages the interactions between all game elements.
 * The user controls a main character (an instance of the Player class)
 * using the arrow keys and space bar to collect a key and open the gate
 * to complete the level. The game includes a power-up block. When
 * collected by the player, this power-up allows the player to jump higher,
 * making it easier to reach the key.
 * 
 * @author Carrick Southall, Nishna Makala
 * @version 21.0.6 LTS (2025-01-21) - OpenJDK Runtime Environment Temurin-21.0.6+7
 */
class KeyGame extends Game implements KeyListener{
	
	static int counter = 0; //tracks the game's state
	static boolean leftArrowPressed, rightArrowPressed, gameOver; //tracks user input
	
	//Game elements: Breakable blocks, key, gate, and power-up
	static Player MainCharacter; 
	private ArrayList <BreakableBlocks> breakableBlockRow = new ArrayList<BreakableBlocks>();
	private Key key; 
	private Gate gate;
	private Power testPower;

	

/**
 * Constructs the KeyGame object. Sets-up game window, adds the main character, 
     * initializing and places breakable blocks, key, and gate, and power-up. 
     * The constructor also sets up the key listener for handling player input.
      */
  public KeyGame() {
    super("KeyGame!!!", 800, 600);
    this.setFocusable(true);
	this.requestFocus();
	this.addKeyListener(this);
	MainCharacter = new Player();
	setBlocks();
	key = new Key(); 
	gate = new Gate();
	
  }

  
  /** 
   * Initializes and places all block objects on the screen, including those
   * with power-ups. All block objects are added to the ArrayList 
   * breakableBlockRow.
   * 
   */
  private void setBlocks() {
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
  }
  
  /**
   * Paints the game screen and game elements. Updates the state of all elements.
   * 
   * @param brush   An instance of the Graphics class
   */
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
			  /**
			   * Anonymous class used to render "you win" screen when player's 
			   * center collides with Gate ivar shape.
			   */
            new Object() { //win screen/fulfills anon class req
                {
                	brush.setColor(Color.BLACK);
                	brush.fillRect(0, 0, width, height);
                	AttributedString attributedString = new AttributedString("Game Over!\n You Win!!");
                    attributedString.addAttribute(TextAttribute.FONT, new Font("Serif", Font.BOLD, 40));
                    attributedString.addAttribute(TextAttribute.FOREGROUND, Color.WHITE, 0, 21); 
                    AttributedCharacterIterator iterator = attributedString.getIterator();
                    brush.drawString(iterator, 50, 100);
//                	PoC of win screen - will make nice later (flashing b/w block text ?)
		}
            };
		}

  }
	
	
	
	/**
	 * Handles key press events for player control. If the left arrow key 
	 * is pressed, the character will move left and face left. If the right 
	 * arrow key is pressed, the character will move right and face right.
	 * If the spacebar is pressed, the character will jump.
	 * 
	 * @param e Contains information about the key that was pressed.
	 */
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
	
	
	
	/**
	 * This method processes  when the user releases the left or right arrow keys.
	 * 
	 * @param e Contains information about the key that was pressed.
	 */
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftArrowPressed = false;
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightArrowPressed = false;
		}
	}
	
	
	
	/**
	 * This method is required by the KeyListener interface 
	 * but is not used in this implementation.
	 * 
	 * @param e Contains information about the key that was pressed.
	 */
	public void keyTyped(KeyEvent e) {
		return;
	}
  
	
	/**
	 * The main method to initialize and start the game by creating new instance
	 * of KeyGame class.
	 * 
	 * @param args
	 */
	public static void main (String[] args) {
		KeyGame a = new KeyGame();
		a.repaint();
  }
	
}