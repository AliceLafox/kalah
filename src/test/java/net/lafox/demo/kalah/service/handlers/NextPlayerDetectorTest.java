package net.lafox.demo.kalah.service.handlers;

import static net.lafox.demo.kalah.service.GameTestUtils.createGame;
import static org.junit.Assert.assertEquals;

import net.lafox.demo.kalah.data.Game;
import net.lafox.demo.kalah.data.Player;
import org.junit.Test;

public class NextPlayerDetectorTest {
    private GameHandler handler = new NextPlayerDetector();

    @Test
    public void testCheckNextPlayer1() {
        int lastSownHouse = Game.STORE_GREEN_INDEX;
        Player expected = Player.GREEN;

        assertEquals(expected, handler.handle(createGame(lastSownHouse, new int[14], Player.GREEN)).getPlayer());
    }

    @Test
    public void testCheckNextPlayer2() {
        int lastSownHouse = Game.STORE_GREEN_INDEX - 1;
        Player expected = Player.RED;

        assertEquals(expected, handler.handle(createGame(lastSownHouse, new int[14], Player.GREEN)).getPlayer());
    }

}