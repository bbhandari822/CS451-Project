package io.github.cs451.ge.adapter;

import io.github.cs451.ge.game.Coordinate;
import lombok.Data;

// This is the action when the user finally decides to move a piece
@Data
public class MoveAction implements Action {
    private final Coordinate from;
    private final Coordinate to;
}
