package test;

import org.junit.jupiter.api.Assertions;

import frame.Frame;
import frame.LastFrame;
import frame.RegularFrame;
import junit.framework.TestCase;

public class FrameTest extends TestCase {
	public void testFrame() throws Exception {
		final Frame f = new RegularFrame();
		
		f.addRoll(5);
		assertFalse(f.isCompleted());
		
		f.addRoll(4);
		assertEquals(5, f.getFirstRoll().get().getPins());
		assertEquals(4, f.getSecondRoll().get().getPins());
		assertEquals(9, f.getScore());
		assertTrue(f.isCompleted());
	}
	
	public void testEmptyFrame() throws Exception {
		final Frame f = new RegularFrame();
		
		assertTrue(f.getFirstRoll().isEmpty());
		assertTrue(f.getSecondRoll().isEmpty());
		
		f.addRoll(3);

		assertTrue(f.getFirstRoll().isPresent());
		assertTrue(f.getSecondRoll().isEmpty());
	}
	
	public void testStrike() throws Exception {
		final Frame f = new RegularFrame();
		final Frame f2 = new RegularFrame();
		
		f.addRoll(10);
		f.setNext(f2);
		f2.addRoll(5);
		f2.addRoll(2);

		assertTrue(f.isCompleted());
		assertEquals(17, f.getScore());
	}	
	
	public void testNextWhenNoStrikeAndNoSpare() throws Exception {
		final Frame f = new RegularFrame();
		final Frame f2 = new RegularFrame();
		
		f.addRoll(1);
		f.setNext(f2);
		f2.addRoll(5);

		assertFalse(f.isCompleted());
		assertEquals(1, f.getScore());
	}
	
	public void testSpare() throws Exception {
		final Frame f = new RegularFrame();
		final Frame f2 = new RegularFrame();
		
		f.addRoll(3);
		f.addRoll(7);
		f.setNext(f2);
		f2.addRoll(5);
		f2.addRoll(2);

		assertTrue(f.isCompleted());
		assertEquals(15, f.getScore());
	}	
		
	public void testInvalidRoll() throws Exception {
		final Frame f = new RegularFrame();
		
		f.addRoll(1);
		f.addRoll(1);
		Assertions.assertThrows(IllegalStateException.class, () -> f.addRoll(1));
	}
	
	public void testInvalidRollBecauseOfStrike() throws Exception {
		final Frame f = new RegularFrame();
		
		f.addRoll(10);
		Assertions.assertThrows(IllegalStateException.class, () -> f.addRoll(1));
	}
	
	public void testGet(){
		final Frame f = Frame.get(0);
		assertTrue(f instanceof RegularFrame);
		
		final Frame f2 = Frame.get(1);
		assertTrue(f2 instanceof RegularFrame);
		assertEquals(2, f2.getMaxRolls());
		
		final Frame f3 = Frame.get(Frame.MAX_NUMBER_OF_FRAMES - 1);
		assertTrue(f3 instanceof LastFrame);
		assertEquals(3, f3.getMaxRolls());
	}
}
