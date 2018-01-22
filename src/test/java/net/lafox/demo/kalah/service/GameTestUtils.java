package net.lafox.demo.kalah.service;

import net.lafox.demo.kalah.data.Game;
import net.lafox.demo.kalah.data.Player;

public class GameTestUtils {

    public static Game createGame(int selectedHouse, int[] houses) {
        Game game = new Game();
        game.setHouses(houses);
        game.setSelectedHouse(selectedHouse);
        return game;
    }

    public static Game createGame(int lastSownHouse, int[] houses, Player player) {
        Game game = new Game();
        game.setHouses(houses);
        game.setLastSownHouse(lastSownHouse);
        game.setPlayer(player);
        return game;
    }

    public static Game createGame(int lastSownHouse, int[] houses, int selectedHouse) {
        Game game = new Game();
        game.setHouses(houses);
        game.setLastSownHouse(lastSownHouse);
        game.setSelectedHouse(selectedHouse);
        return game;
    }
}
