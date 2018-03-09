package io.github.cs451.ge.game.pieces;

import io.github.cs451.ge.game.Checkers;
import io.github.cs451.ge.game.Coordinate;
import io.github.cs451.ge.game.moves.Move;

import java.util.ArrayList;
import java.util.List;

public class EmptyPiece extends Piece {

    public EmptyPiece(Coordinate coordinate) {
        super(null, coordinate);
    }

    @Override
    public String getTelegramDisplay() {
        return ".";
    }

    @Override
    public List<Move> getPossibleMoves(Checkers checkers) {
        return new ArrayList<>();

    }

    @Override
    public boolean canOccupy() {
        return true;
    }

    @Override
    public Piece moveTo(Coordinate coordinate) {
        return new EmptyPiece(coordinate);
    }

}
