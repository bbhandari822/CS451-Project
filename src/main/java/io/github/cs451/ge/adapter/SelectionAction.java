package io.github.cs451.ge.adapter;

import io.github.cs451.ge.game.Coordinate;
import lombok.Data;

// This is the action when the user has only selected a piece
@Data
public class SelectionAction implements Action {
    private final Coordinate location;
}
