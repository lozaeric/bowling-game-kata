package frame;

public class Roll {
	public static final int MAX_PINS = 10;
	private int pins;
	
	public Roll(int pins) {
		this.pins = pins;
	}
	
	public static boolean isStrike(int pins) {
		return pins == MAX_PINS;
	}
	
	public boolean isStrike() {
		return isStrike(pins);
	}
	
	public int getPins() {
		return pins;
	}
}
