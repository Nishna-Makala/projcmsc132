package game;

import java.awt.Graphics;
import java.awt.Image;

//import javax.swing.ImageIcon;

class Player extends Polygon{
    private static Point[] shape;
    private static Point startingPosition;
    Image image;
	static {
		shape = new Point[] {new Point(0,0), new Point(50, 0), 
				new Point(50, 50), new Point(0, 50)};
		startingPosition = new Point(145, 120);
	}
	
	public Player() {
		super(shape, startingPosition, 0);
//		String imagePath = "C:/Users/Carrick Southall/git/projcmsc132/src/game/mario.png";
//		image = new ImageIcon(imagePath).getImage();
	}
	
	void paint (Graphics brush) {
		shape = super.getPoints();
		int[] XVals = splitPoints(shape, true);
		int[] YVals = splitPoints(shape, false);
		brush.drawPolygon(XVals, YVals, XVals.length);
//		brush.drawImage(image, 300, 300, null);
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
