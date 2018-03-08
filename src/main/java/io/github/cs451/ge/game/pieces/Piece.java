package io.github.cs451.ge.game.pieces;

import io.github.cs451.ge.game.CheckersPlayer;
import io.github.cs451.ge.game.Coordinate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Piece {
    // Lets use this instead of team enum.
    @Getter
    private final CheckersPlayer player;
    @Getter
    private final Coordinate coordinate;


    public abstract String getTelegramDisplay();
}
