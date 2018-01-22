package net.lafox.demo.kalah.service.handlers;

import java.util.Arrays;
import java.util.Objects;

import net.lafox.demo.kalah.data.Game;
import net.lafox.demo.kalah.exceptions.GameException;
import org.springframework.stereotype.Component;

@Component
public class Validator implements GameHandler {

    public static final String SELECTED_HOUSE_DOES_NOT_BELONG_TO_PLAYER = "Selected House Does Not Belong to the Player";
    public static final String SELECTED_HOUSE_IS_EMPTY = "Selected House Is Empty";
    public static final String GAME_IS_BROKEN = "The Game Is Broken";
    public static final String GAME_IS_OVER = "The Game Is Over";
    public static final String GAME_IS_NOT_STARTED = "The Game Is Not Started";

    @Override
    public Game handle(Game previous) throws GameException {
        Game game = Game.getNewInstance(previous);

        gameIsStarted(game);

        gameIsOver(game);

        gameIsBroken(game);

        selectedHouseIsEmpty(game);

        selectedHouseDoesNotBelongToPlayer(game);

        return game;
    }

    private void selectedHouseDoesNotBelongToPlayer(Game game) {
        if (!game.isPlayerHouse(game.getSelectedHouse())) {
            throw new GameException(SELECTED_HOUSE_DOES_NOT_BELONG_TO_PLAYER);
        }
    }

    private void selectedHouseIsEmpty(Game game) {
        if (game.getHouses()[game.getSelectedHouse()] == 0) {
            throw new GameException(SELECTED_HOUSE_IS_EMPTY);
        }
    }

    private void gameIsBroken(Game game) {
        int seedsCount = Arrays.stream(game.getHouses()).sum();
        if (seedsCount != Game.SEEDS_COUNT * (Game.MAX_HOUSES - 2)) {
            throw new GameException(GAME_IS_BROKEN);
        }
    }

    private void gameIsOver(Game game) {
        if (Objects.nonNull(game.getWinner())) {
            throw new GameException(GAME_IS_OVER);
        }
    }

    private void gameIsStarted(Game game) {
        if (Objects.isNull(game)) {
            throw new GameException(GAME_IS_NOT_STARTED);
        }
    }


}
