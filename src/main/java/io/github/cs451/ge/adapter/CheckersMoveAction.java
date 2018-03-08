package io.github.cs451.ge.adapter;

import io.github.cs451.ge.game.CheckersPlayer;
import io.github.cs451.ge.game.Coordinate;
import lombok.Data;

// This is the action when the user finally decides to move a piece
@Data
public class CheckersMoveAction implements Action<CheckersPlayer> {
    private final CheckersPlayer checkersPlayer;
    private final Coordinate from;
    private final Coordinate to;

    @Override
    public CheckersPlayer getPlayer() {
        return null;
    }
}
