package by.htp.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

	private static Game game;

	@Before
	public void setUp() {
		game = new Game();
		Set<String> gameSet = new HashSet<>();
		gameSet.add("Минск");
		gameSet.add("Москва");
		game.setGameSet(gameSet);
		game.setLastLetterLastWord('А');
	}

	@Test
	public void checkUniquenessTest() {
		assertFalse("This element should be in the set already!", game.checkUniqueness("Минск"));
		assertTrue("This element should not be in the set!", game.checkUniqueness("Уфа"));
	}

	@Test
	public void inputWordCorrectTest() {
		assertFalse(game.inputWordCorrect('М'));
		assertTrue(game.inputWordCorrect('А'));
		game.setGameSet(new HashSet<>());
		assertTrue(game.inputWordCorrect('V'));
	}

	@Test
	public void getFirstCharTest() {
		assertEquals(new Character('Р'), Game.getFirstChar("Рим"));
		assertNotEquals(new Character('F'), Game.getFirstChar("Рим"));
	}

	@Test
	public void getLastCharTest() {
		assertEquals(new Character('М'), Game.getLastChar("Рим"));
		assertEquals(new Character('Н'), Game.getLastChar("Афины"));
		assertEquals(new Character('И'), Game.getLastChar("Алтай"));
		assertEquals(new Character('М'), Game.getLastChar("Римь"));
		assertEquals(new Character('М'), Game.getLastChar("Римъ"));
	}
}
