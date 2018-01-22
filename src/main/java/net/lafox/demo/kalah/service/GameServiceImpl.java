package net.lafox.demo.kalah.service;

import net.lafox.demo.kalah.data.Game;
import net.lafox.demo.kalah.data.GameDto;
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
    public GameDto nextTurn(GameDto gameDto) {
        Game game = new Game(gameDto);

        game = validator.handle(game);
        game = sower.handle(game);
        game = emptyHouseRule.handle(game);
        game = gameEndDetector.handle(game);

        if (game.getWinner() == null) {
            game = nextPlayerDetector.handle(game);
        }

        return game.asDto();
    }

    @Override
    public GameDto newGame(Integer seeds) {
        if (seeds == null || seeds < Game.MIN_SEEDS_COUNT || seeds > Game.SEEDS_COUNT) {
            return new Game().asDto();
        } else return new Game(seeds).asDto();

    }
}
