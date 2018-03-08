package io.github.cs451.ge.game;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Checkers implements Game {
    private final static int BOARD_SIZE = 8;
    private final CheckersPlayer player1;
    private final CheckersPlayer player2;

    private final List<CheckersRow> rows = new ArrayList<>(8);

    private void clear() {
        rows.clear();
        for (int i = 0; i < BOARD_SIZE; i++) {
            rows.add(new CheckersRow(BOARD_SIZE, i));
        }
    }

    public void resetBoard() {
        clear();
        rows.get(0).reset(true, player1);
        rows.get(1).reset(false, player1);
        rows.get(2).reset(true, player1);

        rows.get(5).reset(false, player2);
        rows.get(6).reset(true, player2);
        rows.get(7).reset(false, player2);
    }

}
