package io.github.cs451.ge.adapter;

import io.github.cs451.ge.game.CheckersPlayer;
import io.github.cs451.ge.game.Coordinate;
import lombok.Data;
import lombok.Getter;

// This is the action when the user has only selected a piece
@Data
public class CheckersUIAction {
    private final CheckersPlayer player;
    private final Coordinate location;

}
