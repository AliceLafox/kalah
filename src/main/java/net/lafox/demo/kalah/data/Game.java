package net.lafox.demo.kalah.data;

import static net.lafox.demo.kalah.data.Player.GREEN;
import static net.lafox.demo.kalah.data.Player.RED;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.ObjectUtils;

@Getter
@Setter
@ToString
public class Game implements Cloneable {
    public static final int MAX_HOUSES = 14;
    public static final int STORE_RED_INDEX = MAX_HOUSES - 1;
    public static final int STORE_GREEN_INDEX = MAX_HOUSES / 2 - 1;
    public static final int SEEDS_COUNT = 6;

    private Player player = GREEN;


    private int[] houses = new int[MAX_HOUSES];
    private int selectedHouse = 0;

    @JsonIgnore
    private int lastSownHouse = 0;

    private String winner;

    public Game() {
        fillTheHouses(SEEDS_COUNT);
    }

    public static Game getNewInstance(Game game) {
        return ObjectUtils.clone(game);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @JsonIgnore
    public int getOpponentStore() {
        return selectedHouse <= STORE_GREEN_INDEX ? STORE_RED_INDEX : STORE_GREEN_INDEX;
    }

    @JsonIgnore
    public int getOpponentHouse(int house) {
        return houses.length - 2 - house;
    }

    @JsonIgnore
    public int getPlayerStore() {
        return player == GREEN ? STORE_GREEN_INDEX : STORE_RED_INDEX;
    }

    @JsonIgnore
    public Player getOpponentPlayer() {
        return player == GREEN ? RED : GREEN;
    }

    @JsonIgnore
    public boolean isPlayerHouse(int index) {
        return (player == GREEN && index >= 0 && index < STORE_GREEN_INDEX) ||
                (player == RED && index > STORE_GREEN_INDEX && index < STORE_RED_INDEX);
    }

    @JsonIgnore
    public boolean isPlayerStore(int index) {
        return (player == GREEN && index == STORE_GREEN_INDEX) ||
                (player == RED && index == STORE_RED_INDEX);
    }

    private void fillTheHouses(int seedsCount) {
        Arrays.fill(houses, seedsCount);
        houses[STORE_RED_INDEX] = 0;
        houses[STORE_GREEN_INDEX] = 0;
    }

}
