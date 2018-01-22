package net.lafox.demo.kalah.service.handlers;

import net.lafox.demo.kalah.data.Game;
import net.lafox.demo.kalah.data.Player;
import net.lafox.demo.kalah.exceptions.GameException;
import org.springframework.stereotype.Component;

@Component
public class NextPlayerDetector implements GameHandler {
    @Override
    public Game handle(Game previous) throws GameException {
        Game game = Game.getNewInstance(previous);

        if (lastSeedNotLandedIntoPlayerStore(game)) {
            game.setPlayer(game.getPlayer() == Player.RED ? Player.GREEN : Player.RED);
        }

        return game;
    }

    private boolean lastSeedNotLandedIntoPlayerStore(Game game) {
        return game.getLastSownHouse() != game.getPlayerStore();
    }
}
