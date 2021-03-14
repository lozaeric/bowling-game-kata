package test;

import game.Game;
import junit.framework.TestCase;

public class GameTest extends TestCase {
	public void testGame() throws Exception {
		final Game g = new Game();
		int rolls[] = new int[] {1, 4, 4, 5, 6, 4, 5, 5, 10, 0, 1, 7, 3, 6, 4, 10, 2, 8, 6};
		
		for (int r: rolls) {
			g.roll(r);
		}
		assertEquals(133, g.score());
	}
	
	public void testPerfectGame() throws Exception {
		final Game g = new Game();
		for (int i=0; i<12; i++) {
			g.roll(10);
		}
		assertEquals(300, g.score());
	}
	
	public void testAnotherGame() throws Exception {
		final Game g = new Game();
		for (int i=0; i<21; i++) {
			g.roll(5);
		}
		assertEquals(150, g.score());
	}
	
	public void testAlmostPerfectGame() throws Exception {
		final Game g = new Game();
		for (int i=0; i<10; i++) {
			g.roll(9);
			g.roll(0);
		}
		assertEquals(90, g.score());
	}
	
	public void testEmptyGame() throws Exception {
		final Game g = new Game();
		assertEquals(0, g.score());
	}
}
