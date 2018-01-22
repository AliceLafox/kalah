package net.lafox.demo.kalah.service;

import net.lafox.demo.kalah.data.Game;

public interface GameService {
    Game nextTurn(Game game);

    Game newGame();
}
