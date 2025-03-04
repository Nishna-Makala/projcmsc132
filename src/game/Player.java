package game;

import java.awt.Graphics;

class Player extends Polygon{
	private static final int stepSize = 20;
    private static Point[] shape;
    private static Point startingPosition;
    private Point center;
    boolean lookingLeft;
    
	static {
		shape = new Point[] {new Point(0,0), new Point(30, 0), 
				new Point(50, 30), new Point(0, 30)};
		startingPosition = new Point(145, 420);
	}
	
	public Player() {
		super(shape, startingPosition, 0);
		lookingLeft = false;
		center = findCenter();
	}
	
	void paint (Graphics brush) {
        Point[] transformedShape = super.getPoints(); 
        int[] XVals = splitPoints(transformedShape, true);
        int[] YVals = splitPoints(transformedShape, false);
        brush.fillPolygon(XVals, YVals, XVals.length);
	}
	public void reflect() {
		for (Point p : shape) {
			p.x = 2 * center.x - p.x;
		}
		recenterShape();
	}
	
	public void jump() {
		
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
