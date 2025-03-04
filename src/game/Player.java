package game;

import java.awt.Graphics;
import java.awt.Image;

class Player extends Polygon{
	private static final int stepSize = 20;
    private static Point[] shape;
    private static Point startingPosition;
    
	static {
		shape = new Point[] {new Point(0,0), new Point(30, 0), 
				new Point(30, 30), new Point(0, 30)};
		startingPosition = new Point(145, 420);
	}
	
	public Player() {
		super(shape, startingPosition, 0);
	}
	
	void paint (Graphics brush) {
		shape = super.getPoints();
		int[] XVals = splitPoints(shape, true);
		int[] YVals = splitPoints(shape, false);
//		brush.drawPolygon(XVals, YVals, XVals.length);
		brush.fillPolygon(XVals, YVals, XVals.length);
	}
	
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
	
	private static int[] splitPoints(Point[] PointArr, boolean XVal) {
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

}
