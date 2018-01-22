package net.lafox.demo.kalah.service;

import net.lafox.demo.kalah.data.GameDto;

public interface GameService {
    GameDto nextTurn(GameDto gameDto);

    GameDto newGame(Integer seeds);
}
