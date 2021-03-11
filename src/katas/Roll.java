package katas;

public class Roll {
	public static final int MAX_PINS = 10;
	private int pins;
	
	public Roll(int pins) {
		this.pins = pins;
	}
	
	public boolean isStrike() {
		return pins == MAX_PINS;
	}
	
	public int getPins() {
		return pins;
	}
}
