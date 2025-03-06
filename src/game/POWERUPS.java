package game;

public enum POWERUPS {
	SPEED(1.25), JUMP_BOOST(1.75);
	
	private double modifier;
	
	private POWERUPS(double modifier) {
		this.modifier = modifier;
	}
	
	public double getModifier() {
		return this.modifier;
	}
}
