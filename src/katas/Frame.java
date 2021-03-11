package katas;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public class Frame {
	private List<Roll> rolls;
	private Optional<Frame> next;
	
	public Frame() {
		rolls = new ArrayList<>();
		next = Optional.empty();
	}
	
	public boolean isCompleted() {
		return rolls.size() == getMaxRolls() || rolls.get(0).isStrike();
	}
	
	public boolean isStrike() {
		return getFirstRoll().map(Roll::isStrike).orElse(false);
	}
	
	public boolean isSpare() {
		return getFirstRoll().map(Roll::getPins).orElse(0)+ getSecondRoll().map(Roll::getPins).orElse(0) == Roll.MAX_PINS;
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
	
	public int getNumberOfRolls() {
		return rolls.size();
	}
	
	public int getMaxRolls() {
		return 2;
	}
	
	public Optional<Roll> getFirstRoll() {
		return getRoll(0);
	}
	
	public Optional<Roll> getSecondRoll() {
		return getRoll(1).isPresent()? getRoll(1):getNext().flatMap(Frame::getFirstRoll);
	}
	
	protected Optional<Roll> getRoll(int i) {
		return rolls.size() > i ?
				Optional.of(rolls.get(i)):
				Optional.empty();
	}
	
	public void validateRoll() {
		if (rolls.size() == getMaxRolls())
			throw new IllegalStateException("invalid roll because of limit");
		if (rolls.size() == 1 && rolls.get(0).isStrike())
			throw new IllegalStateException("invalid roll because of strike");
	}
	
	public void addRoll(int pins) {
		validateRoll();
		rolls.add(new Roll(pins));
	}
	
	public void setNext(Frame next) {
		this.next = Optional.of(next);
	}
	
	public Optional<Frame> getNext() {
		return next;
	}
}
