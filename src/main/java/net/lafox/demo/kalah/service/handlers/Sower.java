package net.lafox.demo.kalah.service.handlers;

import net.lafox.demo.kalah.data.Game;
import net.lafox.demo.kalah.exceptions.GameException;
import org.springframework.stereotype.Component;

@Component
public class Sower implements GameHandler {
    @Override
    public Game handle(Game previous) throws GameException {
        Game game = Game.getNewInstance(previous);

        int[] houses = game.getHouses();
        int opponentStore = game.getOpponentStore();

        int seeds = houses[game.getSelectedHouse()];
        houses[game.getSelectedHouse()] = 0;

        int start = game.getSelectedHouse() + 1;
        while (seeds > 0) {
            for (int i = start; i < houses.length && seeds > 0; i++) {
                if (i == opponentStore) continue;
                seeds--;
                houses[i] += 1;
                game.setLastSownHouse(i);
            }
            start = 0;
        }
        return game;
    }
}
