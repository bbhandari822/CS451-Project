package io.github.cs451.ge.game;

import lombok.Data;

@Data
public class Coordinate {
    // Row - This is used to find the CheckersRow
    private final int x;
    // Column - This is whats used in CheckersRow
    private final int y;
}
