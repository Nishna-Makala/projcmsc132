package game;

import java.awt.Graphics;

/** 
 * Represents a player character in the game, capable of moving left/right,
 * jumping, and landing on different surfaces (arrays of breakableblocks)
 * The player is drawn as a four-point polygon
 * @author Carrick Southall
 * @version 21.0.6 LTS (2025-01-21) - OpenJDK Runtime Environment Temurin-21.0.6+7
 */

class Player extends Polygon{
	private static final int stepSize = 10;
    private static Point[] shape;
    private static Point startingPosition;
    private Point center;
    boolean lookingLeft, hasKey, executingLandOnPlatform, isJumping;
    PlayerState playerState;
    private double yInit = 470;
    
	static {
		shape = new Point[] {new Point(0,0), new Point(30, 0), 
				new Point(50, 30), new Point(0, 30)};
		startingPosition = new Point(145, 470);
	}
	
    /**
     * Constructs a Player object and initializes its position, state, and shape.
     */
	public Player() {
		super(shape, startingPosition, 0);
		lookingLeft = false;
		center = findCenter();
		playerState = new PlayerState();
	}
	
    /**
     * Paints the player object on the screen using the provided Graphics object.
     * 
     * @param brush   An instance of the Graphics class
     */
	void paint (Graphics brush) {
        Point[] transformedShape = super.getPoints(); 
        int[] XVals = splitPoints(transformedShape, true);
        int[] YVals = splitPoints(transformedShape, false);
        brush.fillPolygon(XVals, YVals, XVals.length);
	}
	
    /**
     * Reflects the player across its y-axis by flipping 
     * the x-coordinates of its points based on superclass position.
     */
	public void reflect() {
		for (Point p : shape) {
			p.x = 2 * center.x - p.x;
		}
		recenterShape();
	}
	
    /**
     * Moves the player left or right based on the input.
     * 
     * @param leftArrowHeld indicates if the left arrow key is being held.
     * @param rightArrowHeld indicates if the right arrow key is being held.
     */
	public void move(boolean leftArrowHeld, boolean rightArrowHeld) {
		if ((leftArrowHeld && rightArrowHeld) || (!leftArrowHeld && !rightArrowHeld)) {
			return;
		}
		if (leftArrowHeld) {
			if (super.position.getX() <= 24) {
				return;
			}
			super.position.setX(super.position.getX() - stepSize);
		}
		else {
			if (super.position.getX() >= 750) {
				return;
			}
			super.position.setX(super.position.getX() + stepSize);
		}
	}

	private double findArea() {
//    	copied from Polygon class
	    double sum = 0;
	    for (int i = 0, j = 1; i < shape.length; i++, j=(j+1)%shape.length) {
	      sum += shape[i].x*shape[j].y-shape[j].x*shape[i].y;
	    }
	    return Math.abs(sum/2);
	  }
	
	private Point findCenter() {
//    	copied from Polygon class
	    Point sum = new Point(0,0);
	    for (int i = 0, j = 1; i < shape.length; i++, j=(j+1)%shape.length) {
	      sum.x += (shape[i].x + shape[j].x)
	               * (shape[i].x * shape[j].y - shape[j].x * shape[i].y);
	      sum.y += (shape[i].y + shape[j].y)
	               * (shape[i].x * shape[j].y - shape[j].x * shape[i].y);
	    }
	    double area = findArea();
	    return new Point(Math.abs(sum.x/(6*area)),Math.abs(sum.y/(6*area)));
	  }
	
	
    private void recenterShape() {
//    	copied from Polygon class
        Point origin = new Point(shape[0].x, shape[0].y);
        for (Point p : shape) {
            if (p.x < origin.x) origin.x = p.x;
            if (p.y < origin.y) origin.y = p.y;
        }

        for (Point p : shape) {
            p.x -= origin.x;
            p.y -= origin.y;
        }
        center = findCenter();
    }
	
    /**
     * Splits the points array into its X/Y components depending on the boolean 
     * passed in (true will return array of X components)
     * 
     * @param PointArr the array of Points to split.
     * @param XVal if true, return X components, otherwise return Y components.
     * @return an array of integers containing either X or Y components of a
     * point array
     */
	public static int[] splitPoints(Point[] PointArr, boolean XVal) {
		int[] NewArr = new int[PointArr.length];
		if (XVal) {
			for (int i = 0; i < PointArr.length; i++) {
				NewArr[i] = (int) PointArr[i].getX();
			}
			return NewArr;
		}
		for (int i = 0; i < PointArr.length; i++) {
			NewArr[i] = (int) PointArr[i].getY();
		}
		return NewArr;
	}
	
    /**
     * Getter method for player position (center of Player polygon)
     * 
     * @return the position of the player.
     */
	Point getPosition() {
		return super.position;
	}
	
    /**
     * Sets the position of player polygon to the provided x and y coordinates.
     * 
     * @param x the x-coordinate.
     * @param y the y-coordinate.
     */
	private void setPosition(double x, double y) {
		super.position.setX(x);
		super.position.setY(y);
	}
	
    /** 
     * Tracks the player's jumping state (is/isn't) and provides
     *  methods for starting jump/updating position
     */
	public class PlayerState {
		double yMax;
		static int jumpHeight = 120;
		static int jumpSpeed = 30;
		
        /**
         * Starts a jump for the player if position is valid
         * 
         * @param yPosition y component of player polygon position.
         */
		public void startJump(double yPosition) {
			if (!isJumping && yPosition > yMax) { //currently allowing double jump because of this logic) {
				yInit = yPosition;
				yMax = Math.max(yPosition - jumpHeight, 30); //either full height or slightly below top of window, whichever is more
				isJumping = true;
			}
		}
		
        /**
         * Constructs new PlayerState obj
         */
		public PlayerState() {
			super();
		}
		
        /**
         * Updates the player's jump state/position
         */
		public void updateJump() {

			if (!executingLandOnPlatform) { 
				if (isJumping) {
					double yPosition = getPosition().getY();
					if (KeyGame.counter % 1 == 0) {
						if (yPosition > yMax ) { 
							setPosition(getPosition().getX(), yPosition -= jumpSpeed);
						}
						else {
							isJumping = false;						}
					}
				}
				else {
					double yPosition = getPosition().getY();
					if (KeyGame.counter % 3 == 0) {
						if (yPosition < yInit) { //mod 3 for debugging purposes/easier to watch movement
							setPosition(getPosition().getX(), yPosition + jumpSpeed);
						}
					}
				}
			}
		}
	}
	
	
	
	


	/**
	 * Checks if the points on Breakable Block object and Player
	 * are at least 30 pixels within of each other
	 * 
	 * @param block
	 * @return true if collides, false if not
	 */
	private boolean isColliding(BreakableBlocks block) {
		for (int i = 0; i < block.getPoints().length; i++) {
			for (int j = 0; j < block.getPoints().length; j++) {
				if (Math.abs(block.getPoints()[i].getY() - this.getPoints()[j].getY()) <= 30){
					return true;
			}
		}
	}
		
	return false;
	
	}
	
	/** Continuously moves player down to simulate gravity
	 * 
	 * @param block
	 */
	public void gravity(BreakableBlocks block) {
		if(!isJumping && !executingLandOnPlatform && !(isColliding(block))) {
			if (KeyGame.counter % 3 == 0) {
					setPosition(getPosition().getX(), updateYInit() + 0.7);
			}
		}
	}
	
	/** getter method for isJumping
	 * 
	 * @return  isJumping
	 */
	public boolean getIsJumping() {
		return isJumping;
	}
	
	
	/** updates yInit variable and returns it
	 * 
	 * @return yInit variable
	 */
	public double updateYInit() {
		yInit = getPosition().getY();
		return yInit;
	}
	
	/**
	 * Created in Player class in order for BreakableBlock class collides
	 * method to access mainCharacter object.

	 * @param direction   direction is specified in BreakableBlocks class,
	 *  where this method is called. This can be "up" or "down"
	 *  @return executingLandOnPlatform true if player landing on platform
	 */
	public boolean landOnPlatform(String direction, double yPosition){
		
		executingLandOnPlatform = false; 
		
		switch(direction) {
		
		default:
			return false;
		
		case "DOWN":
			executingLandOnPlatform = true; 
			isJumping = false;
			setPosition(getPosition().getX(), this.getPoints()[0].getY()+22);
			updateYInit();
			break;
		
		case "UP":
			executingLandOnPlatform = true; 
			isJumping = false;
			setPosition(getPosition().getX(), this.getPoints()[1].getY() - 2);
			updateYInit();
			break;
		
		}
		
		executingLandOnPlatform = false; 
		return true;
		
	}

		

		
}
	


