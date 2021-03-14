package game;

import java.util.ArrayDeque;
import java.util.Deque;

import frame.Frame;

public class Game {
	private final Deque<Frame> frames;
	
	public Game() {
		this.frames = new ArrayDeque<>();
	}
	
	public void roll(int pins) {
		getCurrentFrame().addRoll(pins);
	}
	
	private Frame getCurrentFrame() {
		if (frames.isEmpty()) {
			frames.add(Frame.get(frames.size()));
		} else if (frames.getLast().isCompleted()) {
			final Frame newFrame = Frame.get(frames.size());
			frames.getLast().setNext(newFrame);
			frames.add(newFrame);
		}
		
		return frames.getLast();
	}
	
	public int score() {
		return frames.stream().mapToInt(Frame::getScore).sum();
	}
}
