package io.github.cs451.ge.game.pieces;

import io.github.cs451.ge.game.Checkers;
import io.github.cs451.ge.game.CheckersMoveCollection;
import io.github.cs451.ge.game.Coordinate;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = true)
public class EmptyPiece extends Piece {

    public EmptyPiece(Coordinate coordinate) {
        super(null, coordinate);
    }

    @Override
    public String getTelegramDisplay() {
        return isUsed() ? "❎" : ".";
    }

    @Override
    public CheckersMoveCollection getPossibleMoves(Checkers checkers) {
        return new CheckersMoveCollection();

    }

    @Override
    public boolean canOccupy() {
        return true;
    }

    @Override
    public Piece moveTo(Coordinate coordinate) {
        return new EmptyPiece(coordinate);
    }

    @Override
    List<Coordinate.Direction> getAllowedDirections(Checkers checkers) {
        return new ArrayList<>();
    }

}
