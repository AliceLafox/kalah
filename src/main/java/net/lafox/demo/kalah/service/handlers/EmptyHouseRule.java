package net.lafox.demo.kalah.service.handlers;

import net.lafox.demo.kalah.data.Game;
import net.lafox.demo.kalah.exceptions.GameException;
import org.springframework.stereotype.Component;

@Component
public class EmptyHouseRule implements GameHandler {
    @Override
    public Game handle(Game previous) throws GameException {
        Game game = Game.getNewInstance(previous);
        if (lastSeedLandedIntoPlayerEmptyHouse(game)) {
            captureOppositeHouse(game);

        }
        return game;
    }

    private boolean lastSeedLandedIntoPlayerEmptyHouse(Game game) {
        return lastSownHouseBelongsToPlayer(game) &&
                game.getLastSownHouse() != game.getPlayerStore() &&
                lastSownHouseWasEmpty(game);
    }

    private boolean lastSownHouseWasEmpty(Game game) {
        return game.getHouses()[game.getLastSownHouse()] == 1;
    }

    private boolean lastSownHouseBelongsToPlayer(Game game) {
        return game.isPlayerHouse(game.getLastSownHouse()) || game.isPlayerStore(game.getLastSownHouse());
    }

    private void captureOppositeHouse(Game game) {
        game.getHouses()[game.getPlayerStore()] +=
                game.getHouses()[game.getLastSownHouse()]
                        + game.getHouses()[game.getOpponentHouse(game.getLastSownHouse())];
        emptyLastSownHouse(game);
        emptyOppositeHouse(game);
    }

    private void emptyLastSownHouse(Game game) {
        game.getHouses()[game.getLastSownHouse()] = 0;
    }

    private void emptyOppositeHouse(Game game) {
        game.getHouses()[game.getOpponentHouse(game.getLastSownHouse())] = 0;
    }

}
