package game;

import java.awt.Color;
import java.awt.Graphics;
import game.Player.PlayerState;


/** 
 * Represents a single block object on a screen that the player 
 * uses as a platform to jump
 * 
 * @author Nishna Makala
 * @version 21.0.6 LTS (2025-01-21) - OpenJDK Runtime Environment Temurin-21.0.6+7
 */
public class BreakableBlocks extends Polygon {
	
	private final static Point[] shape; //array of points that make up shape of this block object
	private boolean isPowerUp; //true if BreakableBlock object is a PowerUp, false if not
	
	static {
		shape = new Point[] {new Point(0,0), new Point(0,30), new Point(30,30), new Point(30,0)};
	}
	

	
	
	/** 
	 * A constructor for regular blocks in the game that serve as a
	* mid-air platform for the main character
	* 
	 * @param xCoorBlock   represents the x-coordinate where this block will be placed
	 * @param yCoorBlock   represents the y-coordinate where this block will be placed
	 * @param isPowerUp    true if this block is a power-up, false if not
	 */
	public BreakableBlocks(int xCoorBlock, int yCoorBlock, boolean isPowerUp) {
		super(shape, new Point(xCoorBlock,yCoorBlock) ,0.0);
		this.rotate(180);
		this.isPowerUp = isPowerUp;
	}
	
	
	
	
	/** 
	 * Changes this block to a non-power-block by setting
	 * isPowerUp to "false."
	 */
	public void changeIsPowerUp() {
		isPowerUp = false;
	}

	
	
	/** 
	 * The method draws a filled block representing this BreakableBlock object.
     * The block is drawn in either red (if it is a power-up)
     *  or blue (if it is a regular block).
	 *  
	 * @param brush   An instance of the Graphics class
	 */
	public void paint(Graphics brush) {
		
		if (isPowerUp) {
			brush.setColor(Color.RED); //representing "power up" blocks
		}
		else {
			brush.setColor(Color.BLUE); //representing "regular" blocks
		}

        int[] xPoints = new int[getPoints().length];
        int[] yPoints = new int[getPoints().length];

        for (int i = 0; i < getPoints().length; i++) {
            xPoints[i] = (int) (this.getPoints()[i].getX());
            yPoints[i] = (int) (this.getPoints()[i].getY());
        }
        
        brush.fillPolygon(xPoints, yPoints, getPoints().length);
	}
	
	
	
	
	/** 
	 * If main character is touching a platform, then all points in 
	 * main character should move either up or down until no longer 
	 * colliding with blocks. 
	 * 
	 * @param mainCharacter   An instance of main character class controlled by user
	 */
	public void collides(Player mainCharacter) {
		
		if (isColliding(mainCharacter)) {
			
			/*Checks orientation, i.e. should the mainCharacter bounce down or up
			  when colliding with a block */
			if (Math.abs(mainCharacter.getPoints()[0].getY() - this.getPoints()[0].getY()) <= 30) {
				
				while (isColliding(mainCharacter)) {
					(mainCharacter).landOnPlatform("DOWN", mainCharacter.getPoints()[2].getY()); 
				}	
				
			}
			
			else if (Math.abs(mainCharacter.getPoints()[3].getY() - this.getPoints()[0].getY()) <= 30) {
				
				while (isColliding(mainCharacter)) {
					(mainCharacter).landOnPlatform("UP", mainCharacter.getPoints()[1].getY());
				}	
				
			}
		}
	}


	
	
	/** 
	 * Checks if this block object is currently colliding with Player object.
	 * 
	 * @param mainCharacter An instance of the Player class representing the main character.
	 * @return true if colliding, false if not
	 */
	public boolean isColliding(Polygon mainCharacter) {
		
		for (int i = 0; i < mainCharacter.getPoints().length; i++) {
			
			if (this.contains(mainCharacter.getPoints()[i]) == true){
				return true;
			}
			
		}
		
		return false;
	}
	
	
	
    /**
     * Inner class that handles the power-up behavior for this BreakableBlock object.
     * The power-up is activated when the player comes into contact with the block.
     */
    public class Power implements POWERUPS {
        private boolean isActive;
        
        /** 
         * Checks if the power-up block is at least 10 pixels within the player.
         * If so, the power-up is activated for the player.
         * 
         * @param mainCharacter   An instance of the Player class representing the main character.
         * @return true if powerup was activated, false if not
         */
    	public boolean isActivated(Player mainCharacter) {
    		
    		for (int i = 0; i < 2; i++) {
    			for (int j = 0; j < 4; j++) {
    				
    				if (Math.abs(mainCharacter.getPoints()[i].getY() - getPoints()[j].getY()) <= 10
    						&& Math.abs(mainCharacter.getPoints()[i].getX() - getPoints()[j].getX()) <= 10){
    					isActive = true;
    	    			return true;	
    	    			
    	    		}
    				
    			}
    		}
    			
        	for (int i = 3; i < 4; i++) {
        		for (int j = 0; j < 4; j++) {
        			
        			if (Math.abs(mainCharacter.getPoints()[i].getY() - getPoints()[j].getY()) <= 10
        					&& Math.abs(mainCharacter.getPoints()[i].getX() - getPoints()[j].getX()) <= 10){
        				isActive = true;
        	    		return true;	
        	    		
        	    	}
        		}
    		}
        	
    		return false;
    	}
    	
    	
       /** 
        * Adjusts jumpHeight and jumpSpeed of the player if power-up is active
        * 
        * @param mainCharacter  An instance of the Player class representing the main character.
        */
        public void activatePowerUp(Player mainCharacter) {
        	if (isActive) {
        		changeIsPowerUp();
        		PlayerState.jumpHeight = 180;
        		PlayerState.jumpSpeed = 60;
        	}
        }

        
        
		/** 
		 * Checks if the power-up is currently active.
		 * 
		 * @param mainCharacter  An instance of the Player class representing the main character.
		 * @return true if power-up is currently active, false if not
		 */
		public boolean isPowerUpActive(Player mainCharacter) {
			return isActive;
		}

        
      }

	
	
	
}

