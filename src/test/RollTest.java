package test;

import frame.Roll;
import junit.framework.TestCase;

public class RollTest extends TestCase {
	public void testRoll() throws Exception {
		for (int p = 0; p < Roll.MAX_PINS; p++) {
			final Roll r = new Roll(p);
			assertEquals(p, r.getPins());
			assertFalse(r.isStrike());
			assertFalse(Roll.isStrike(p));
		}
	}
	
	public void testIsStrike() throws Exception {
		final Roll r = new Roll(Roll.MAX_PINS);
		assertTrue(r.isStrike());
	}
	
	public void testStaticIsStrike() throws Exception {
		assertTrue(Roll.isStrike(Roll.MAX_PINS));
	}
}
