package net.lafox.demo.kalah.data;

import lombok.*;

@Builder
@Getter
@Data
public class GameDto {

    private int[] houses;
    private int selectedHouse;
    private Player player;
    private String winner;
    private int seeds;

}
