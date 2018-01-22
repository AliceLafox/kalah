package net.lafox.demo.kalah.service.handlers;

import net.lafox.demo.kalah.data.Game;
import net.lafox.demo.kalah.data.Player;
import net.lafox.demo.kalah.exceptions.GameException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ValidatorTest {
    private GameHandler handler = new Validator();
    private Game game = new Game();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testGameIsOver() {
        game.setWinner(Player.GREEN.name());
        assertException(Validator.GAME_IS_OVER);
        handler.handle(game);
    }

    @Test
    public void testGameIsBroken() {
        game.setHouses(new int[]{200, 100});
        assertException(Validator.GAME_IS_BROKEN);
        handler.handle(game);
    }

    @Test
    public void testGameIsNotStarted() {
        assertException(Validator.GAME_IS_NOT_STARTED);
        handler.handle(null);
    }

    @Test
    public void testSelectedHouseIsEmpty() {
        game.setSelectedHouse(5);
        game.getHouses()[5] = 0;
        game.getHouses()[6] += Game.SEEDS_COUNT;
        assertException(Validator.SELECTED_HOUSE_IS_EMPTY);
        handler.handle(game);
    }

    @Test
    public void testSelectedHouseDoesNotBelongToPlayer() {
        game.setSelectedHouse(12);
        assertException(Validator.SELECTED_HOUSE_DOES_NOT_BELONG_TO_PLAYER);
        handler.handle(game);
    }

    private void assertException(String s) {
        exception.expect(GameException.class);
        exception.expectMessage(s);
    }
}