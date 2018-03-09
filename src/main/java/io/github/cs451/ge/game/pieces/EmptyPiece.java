package io.github.cs451.ge.game.pieces;

import io.github.cs451.ge.game.CheckersPlayer;
import io.github.cs451.ge.game.Coordinate;
import io.github.cs451.ge.game.Move;

import java.util.ArrayList;
import java.util.List;

public class EmptyPiece extends Piece {

    public EmptyPiece( Coordinate coordinate) {
        super(null, coordinate);
    }

    @Override
    public String getTelegramDisplay() {
        return ".";
    }

    @Override
    public List<Move> getPossibleMoves() {
        return new ArrayList<>();
    }
}
