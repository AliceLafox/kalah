package net.lafox.demo.kalah.service;

import net.lafox.demo.kalah.data.Game;
import net.lafox.demo.kalah.service.handlers.GameHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameHandler validator;
    @Autowired
    private GameHandler sower;
    @Autowired
    private GameHandler emptyHouseRule;
    @Autowired
    private GameHandler nextPlayerDetector;
    @Autowired
    private GameHandler gameEndDetector;


    @Override
    public Game nextTurn(Game previous) {
        Game game;
        game = validator.handle(previous);
        game = sower.handle(game);
        game = emptyHouseRule.handle(game);
        game = gameEndDetector.handle(game);

        if (game.getWinner() == null) {
            game = nextPlayerDetector.handle(game);
        }

        return game;

    }

    @Override
    public Game newGame() {
        return new Game();
    }
}
