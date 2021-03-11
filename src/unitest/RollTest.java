package unitest;

import junit.framework.TestCase;
import katas.Roll;

public class RollTest extends TestCase {
	public void testRoll() throws Exception {
		for (int p = 0; p < Roll.MAX_PINS; p++) {
			final Roll r = new Roll(p);
			assertEquals(p, r.getPins());
			assertFalse(r.isStrike());
		}
	}
	
	public void testIsStrike() throws Exception {
		final Roll r = new Roll(Roll.MAX_PINS);
		assertTrue(r.isStrike());
	}
}
