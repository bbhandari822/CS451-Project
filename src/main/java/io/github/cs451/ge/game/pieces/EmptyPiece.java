package io.github.cs451.ge.game.pieces;

import io.github.cs451.ge.game.CheckersPlayer;
import io.github.cs451.ge.game.Coordinate;

public class EmptyPiece extends Piece {

    public EmptyPiece( Coordinate coordinate) {
        super(null, coordinate);
    }

    @Override
    public String getTelegramDisplay() {
        return ".";
    }
}
