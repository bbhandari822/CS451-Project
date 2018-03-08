package io.github.cs451.ge.adapter;

import io.github.cs451.ge.game.Coordinate;

// This is the action when the user has only selected a piece
public class SelectionAction implements Action {
    private final Coordinate location;

    public SelectionAction(Coordinate location) {
        this.location = location;
    }
}
