package katas;

import java.util.ArrayDeque;
import java.util.Deque;

public class Game {
	private final Deque<Frame> frames;
	
	public Game() {
		this.frames = new ArrayDeque<>();
	}
	
	public void roll(int pins) {
		getCurrentFrame().addRoll(pins);
	}
	
	private Frame getCurrentFrame() {
		if (frames.size() == 0) {
			frames.add(new Frame());
		} else if (frames.getLast().isCompleted()) {
			final Frame newFrame = frames.size() < 9? new Frame():new TenthFrame();
			frames.getLast().setNext(newFrame);
			frames.add(newFrame);
		}
		
		return frames.getLast();
	}
	
	public int score() {
		return frames.stream().mapToInt(Frame::getScore).sum();
	}
}
