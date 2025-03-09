package game;

import java.awt.Color;
import java.awt.Graphics;

import game.Player.PlayerState;

public class BreakableBlocks extends Polygon {
	
	/** Represents a single block on a screen.
	 * 
	 * @author Nishna Makala
	 */
	
	
	private final static Point[] shape;
	private boolean isPowerUp; //true if BreakableBlock object is a PowerUp, false if not
	
	static {
		shape = new Point[] {new Point(0,0), new Point(0,30), new Point(30,30), new Point(30,0)};
	}
	

	
	
	/**A constructor for regular blocks in the game that serve as a
	 * mid-air platform for the main character
	 * 
	 * @param shape    An array list of points that make up the block
	 */
	public BreakableBlocks(int xCoorBlock, int yCoorBlock, boolean isPowerUp) {
		super(shape, new Point(xCoorBlock,yCoorBlock) ,0.0);
		this.rotate(180);
		this.isPowerUp = isPowerUp;
	}
	
	
	/** Changes BreakableBlock object to a not-power-block by setting
	 * is PowerUp to "false."
	 * 
	 */
	public void changeIsPowerUp() {
		isPowerUp = false;
	}

	
	
	/** The method draws a filled gray block representing BreakableBlock object.
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
	
	
	
	
	/** If main character is touching a platform, then all points in 
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


	
	/** Checks if block object is colliding with Player object
	 * 
	 * @param mainCharacter
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
	
	
    public class Power implements POWERUPS {
        private boolean isActive;
        
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
    	
    	
       
        public void activatePowerUp(Player mainCharacter) {
        	if (isActive) {
        		changeIsPowerUp();
        		PlayerState.jumpHeight = 180;
        		PlayerState.jumpSpeed = 60;
        	}
        }

		
		public boolean isPowerUpActive(Player mainCharacter) {
			return isActive;
		}

        
      }

	
	
	
}

