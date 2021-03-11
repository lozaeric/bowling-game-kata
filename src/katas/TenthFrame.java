package katas;

public class TenthFrame extends Frame {
	@Override
	public boolean isCompleted() {
		return getNumberOfRolls() == getMaxRolls();
	}
	
	@Override
	public void validateRoll() {
		if (getNumberOfRolls() == getMaxRolls())
			throw new IllegalStateException("invalid roll because of limit");
	}	

	@Override
	public int getMaxRolls() {
		return 3;
	}
}
