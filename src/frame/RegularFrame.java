package frame;

public class RegularFrame extends Frame {
	@Override
	public boolean isCompleted() {
		return super.isCompleted() || getFirstRoll().map(Roll::isStrike).orElse(false);
	}
	
	@Override
	public void validateRoll() {
		super.validateRoll();
		if (getNumberOfRolls() == 1 && getFirstRoll().map(Roll::isStrike).orElse(false))
			throw new IllegalStateException("invalid roll because of strike");
	}	

	@Override
	public int getMaxRolls() {
		return 2;
	}
}
