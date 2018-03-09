package io.github.cs451.ge.game.pieces;

import io.github.cs451.ge.game.CheckersPlayer;
import io.github.cs451.ge.game.Coordinate;
import io.github.cs451.ge.game.Move;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
public abstract class Piece {
    // Lets use this instead of team enum.
    @Getter
    private final CheckersPlayer player;
    @Getter
    private final Coordinate coordinate;
    @Getter
    @Setter
    private boolean selected = false;


    public abstract String getTelegramDisplay();

    public abstract List<Move> getPossibleMoves();
}
