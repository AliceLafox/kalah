package net.lafox.demo.kalah.service.handlers;

import static net.lafox.demo.kalah.service.GameTestUtils.createGame;
import static org.junit.Assert.*;

import net.lafox.demo.kalah.data.Game;
import net.lafox.demo.kalah.data.Player;
import org.junit.Test;

public class SowerTest {
    private GameHandler handler = new Sower();

    @Test
    public void testSeedHouses1() {
        int[] previous = {6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0};
        int[] expected = {0, 7, 7, 7, 7, 7, 1, 6, 6, 6, 6, 6, 6, 0};
        int startHouse = 0;
        int lastSownHouse = 6;

        assertHouses(expectedGame(lastSownHouse, expected), nextGame(startHouse, previous));
    }

    @Test
    public void testSeedHouses2() {
        int[] previous = {6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0};
        int[] expected = {6, 6, 6, 6, 6, 0, 1, 7, 7, 7, 7, 7, 6, 0};
        int startHouse = 5;
        int lastSownHouse = 11;

        assertHouses(expectedGame(lastSownHouse, expected), nextGame(startHouse, previous));
    }

    @Test
    public void testSeedHouses3() {
        int[] previous = {6, 6, 6, 6, 6, 0, 1, 7, 7, 7, 7, 7, 6, 0};
        int[] expected = {7, 7, 7, 7, 7, 0, 1, 7, 7, 7, 7, 7, 0, 1};
        int startHouse = 12;
        int lastSownHouse = 4;

        assertHouses(expectedGame(lastSownHouse, expected), nextGame(startHouse, previous));
    }

    @Test
    public void testSeedHouses4() {
        int[] previous = {0, 7, 7, 7, 7, 7, 1, 6, 6, 6, 6, 6, 6, 0};
        int[] expected = {0, 7, 7, 7, 7, 7, 1, 0, 7, 7, 7, 7, 7, 1};
        int startHouse = 7;
        int lastSownHouse = 13;

        assertHouses(expectedGame(lastSownHouse, expected), nextGame(startHouse, previous));
    }

    @Test
    public void testSeedHouses5() {
        int[] previous = {0, 0, 0, 0, 0, 12, 1, 6, 6, 6, 6, 6, 5, 0};
        int[] expected = {1, 1, 1, 1, 1, 0, 2, 7, 7, 7, 7, 7, 6, 0};
        int selectedHouse = 5;
        int lastSownHouse = 4;

        assertHouses(expectedGame(lastSownHouse, expected), nextGame(selectedHouse, previous));
    }

    private void assertHouses(Game expected, Game actual) {
        assertNotNull(actual);
        assertNotNull(actual.getHouses());
        assertEquals(expected.getLastSownHouse(), actual.getLastSownHouse());
        assertArrayEquals(expected.getHouses(), actual.getHouses());
    }

    private Game nextGame(int startHouse, int[] houses) {
        return handler.handle(createGame(startHouse, houses));
    }

    private Game expectedGame(int lastSownHouse, int[] houses) {
        return createGame(lastSownHouse, houses, Player.GREEN);
    }


}