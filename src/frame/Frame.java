package frame;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public abstract class Frame {
	public static int MAX_NUMBER_OF_FRAMES = 10;
	private List<Roll> rolls;
	private Optional<Frame> next;
	
	Frame() {
		rolls = new ArrayList<>();
		next = Optional.empty();
	}
	
	public static Frame get(int numberOfFrames) {
		if (numberOfFrames < 0 || numberOfFrames >= MAX_NUMBER_OF_FRAMES)
			throw new IllegalStateException("invalid number of frames");
		if (numberOfFrames < MAX_NUMBER_OF_FRAMES - 1)
			return new RegularFrame();
		else
			return new LastFrame();
	}
	
	public boolean isStrike() {
		return getFirstRoll().map(Roll::isStrike).orElse(false);
	}
	
	public boolean isSpare() {
		final int pins = getFirstRoll().map(Roll::getPins).orElse(0) + getSecondRoll().map(Roll::getPins).orElse(0);
		return Roll.isStrike(pins);
	}
	
	public int getScore() {
		int score = rolls.stream().mapToInt(Roll::getPins).sum();
		if (next.isPresent()) {
			if (isStrike()) {
				score += next.get().getFirstRoll().map(Roll::getPins).orElse(0);
				score += next.get().getSecondRoll().map(Roll::getPins).orElse(0);
			} else if (isSpare()) {
				score += next.get().getFirstRoll().map(Roll::getPins).orElse(0);
			}
		}
		return score;
	}
	
	public void addRoll(int pins) {
		validateRoll();
		rolls.add(new Roll(pins));
	}
	
	public int getNumberOfRolls() {
		return rolls.size();
	}
	
	public Optional<Roll> getFirstRoll() {
		return getRoll(0);
	}
	
	public Optional<Roll> getSecondRoll() {
		return getRoll(1).or(() -> getNext().flatMap(Frame::getFirstRoll));
	}
	
	protected Optional<Roll> getRoll(int i) {
		return rolls.size() > i ?
				Optional.of(rolls.get(i)):
				Optional.empty();
	}
	
	public void setNext(Frame next) {
		this.next = Optional.of(next);
	}
	
	public Optional<Frame> getNext() {
		return next;
	}
	
	public boolean isCompleted() {
		return getNumberOfRolls() == getMaxRolls();
	}
	
	public void validateRoll() {
		if (getNumberOfRolls() == getMaxRolls())
			throw new IllegalStateException("invalid roll because of limit");
	}	
	
	public abstract int getMaxRolls();
}
