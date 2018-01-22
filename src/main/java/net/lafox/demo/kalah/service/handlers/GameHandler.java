package net.lafox.demo.kalah.service.handlers;

import net.lafox.demo.kalah.data.Game;
import net.lafox.demo.kalah.exceptions.GameException;

public interface GameHandler {
    Game handle(Game game) throws GameException;
}
