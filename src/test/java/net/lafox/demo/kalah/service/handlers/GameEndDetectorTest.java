package net.lafox.demo.kalah.service.handlers;

import static net.lafox.demo.kalah.service.GameTestUtils.createGame;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import net.lafox.demo.kalah.data.Game;
import net.lafox.demo.kalah.data.Player;
import org.junit.Test;

public class GameEndDetectorTest {
    private GameHandler handler = new GameEndDetector();

    @Test
    public void test() {
        Game game = createGame(4, new int[]{0, 0, 0, 0, 0, 0, 29, 2, 2, 1, 1, 0, 0, 37}, Player.GREEN);
        Game expected = createGame(4, new int[]{0, 0, 0, 0, 0, 0, 29, 0, 0, 0, 0, 0, 0, 43}, Player.GREEN);
        expected.setWinner(Player.RED.name());

        Game actual = handler.handle(game);
        assertArrayEquals(expected.getHouses(), actual.getHouses());
        assertEquals(expected.getWinner(), actual.getWinner());

    }

    @Test
    public void testADrawAsWinner() {
        Game game = createGame(4, new int[]{0, 0, 0, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0, 30}, Player.GREEN);
        Game expected = Game.getNewInstance(game);
        expected.setWinner("A DRAW");

        Game actual = handler.handle(game);
        assertEquals(expected.getWinner(), actual.getWinner());

    }
}