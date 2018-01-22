package net.lafox.demo.kalah.service.handlers;

import static net.lafox.demo.kalah.data.Game.STORE_GREEN_INDEX;
import static net.lafox.demo.kalah.data.Game.STORE_RED_INDEX;

import net.lafox.demo.kalah.data.Game;
import net.lafox.demo.kalah.data.Player;
import net.lafox.demo.kalah.exceptions.GameException;
import org.springframework.stereotype.Component;

@Component
public class GameEndDetector implements GameHandler {
    @Override
    public Game handle(Game previous) throws GameException {
        Game game = Game.getNewInstance(previous);

        if (playerHousesAreEmpty(game)) {
            putOpponentSeedsToStore(game);
            game.setWinner(getTheWinner(game));
        }

        return game;
    }

    private void putOpponentSeedsToStore(Game game) {
        int start = startHouse(game.getOpponentPlayer());
        int end = endHouse(game.getOpponentPlayer());

        for (int i = start; i <= end; i++) {
            game.getHouses()[game.getOpponentStore()] += game.getHouses()[i];
            game.getHouses()[i] = 0;
        }
    }

    private int startHouse(Player player) {
        return player == Player.GREEN ? 0 : STORE_GREEN_INDEX + 1;
    }

    private int endHouse(Player player) {
        return player == Player.GREEN ? STORE_GREEN_INDEX - 1 : STORE_RED_INDEX - 1;
    }

    private boolean playerHousesAreEmpty(Game game) {
        int start = startHouse(game.getPlayer());
        int end = endHouse(game.getPlayer());

        for (int i = start; i <= end; i++) {
            if (game.getHouses()[i] > 0) return false;
        }

        return true;
    }

    private String getTheWinner(Game game) {
        if (game.getHouses()[STORE_GREEN_INDEX] == game.getHouses()[STORE_RED_INDEX]) return "A DRAW";

        if (game.getHouses()[STORE_GREEN_INDEX] > game.getHouses()[STORE_RED_INDEX])
            return Player.GREEN.name();
        return Player.RED.name();
    }

}
