package net.lafox.demo.kalah.service.handlers;

import static net.lafox.demo.kalah.service.GameTestUtils.createGame;
import static org.junit.Assert.assertArrayEquals;

import net.lafox.demo.kalah.data.Game;
import net.lafox.demo.kalah.data.Player;
import org.junit.Test;

public class EmptyHouseRuleTest {
    private GameHandler handler = new EmptyHouseRule();

    @Test
    public void testApplyEmptyRule1() {
        int[] previous = {1, 1, 1, 1, 1, 0, 30, 7, 10, 7, 7, 10, 6, 15};
        int[] expected = {1, 1, 1, 1, 0, 0, 41, 7, 0, 7, 7, 10, 6, 15};
        int lastSownHouse = 4;

        assertArrayEquals(expected, handler.handle(createGame(lastSownHouse, previous, Player.GREEN)).getHouses());
    }

    @Test
    public void testApplyEmptyRuleWhenLastSeedLandsIntoStore2() {
        int[] previous = {1, 1, 1, 1, 1, 0, 30, 7, 10, 7, 7, 10, 6, 15};
        int[] expected = previous;
        int lastSownHouse = Game.STORE_RED_INDEX;

        assertArrayEquals(expected, handler.handle(createGame(lastSownHouse, previous, Player.RED)).getHouses());
        assertArrayEquals(expected, handler.handle(createGame(lastSownHouse, previous, Player.GREEN)).getHouses());
    }

    @Test
    public void testApplyEmptyRuleWhenLastSeedLandsIntoStore3() {
        int[] previous = {1, 1, 1, 1, 1, 0, 0, 7, 10, 7, 7, 10, 6, 15};
        int[] expected = previous;
        int lastSownHouse = Game.STORE_GREEN_INDEX;

        assertArrayEquals(expected, handler.handle(createGame(lastSownHouse, previous, Player.RED)).getHouses());
        assertArrayEquals(expected, handler.handle(createGame(lastSownHouse, previous, Player.GREEN)).getHouses());
    }

}