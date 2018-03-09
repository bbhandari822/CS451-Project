package io.github.cs451.ge.game.pieces;

import io.github.cs451.ge.game.Checkers;
import io.github.cs451.ge.game.CheckersColor;
import io.github.cs451.ge.game.CheckersPlayer;
import io.github.cs451.ge.game.Coordinate;
import io.github.cs451.ge.game.moves.AttackMove;
import io.github.cs451.ge.game.moves.Move;
import io.github.cs451.ge.game.moves.NormalMove;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NormalPiece extends Piece {

    public NormalPiece(CheckersPlayer player, Coordinate coordinate) {
        super(player, coordinate);
    }

    @Override
    public String getTelegramDisplay() {
        if (getPlayer().getColor() == CheckersColor.RED) {
            return "\uD83D\uDD34";
        } else {
            return "⚪️";
        }
    }

    @Override
    public List<Move> getPossibleMoves(Checkers checkers) {
        List<Move> moves = new ArrayList<>();
        for (Coordinate.Direction direction : getAllowedDirections(checkers)) {
            Coordinate newCoordinate = getCoordinate().apply(direction);
            Piece newPiece = checkers.getPiece(newCoordinate);

            // we're out of bounds
            if (newPiece == null) continue;

            if (newPiece.getPlayer() == null) {
                moves.add(new NormalMove(this, newPiece));
                continue;
            }

            if (canAttack(checkers, newPiece, direction)) {
                moves.add(new AttackMove(this, newPiece, checkers.getPiece(newPiece.getCoordinate().apply(direction))));
            }
        }
        return moves;
    }

    @Override
    public boolean canOccupy() {
        return false;
    }

    private List<Coordinate.Direction> getAllowedDirections(Checkers checkers) {
        if (checkers.getPlayer1().equals(getPlayer())) {
            return Arrays.asList(Coordinate.Direction.BOTTOM_LEFT, Coordinate.Direction.BOTTOM_RIGHT);
        } else {
            return Arrays.asList(Coordinate.Direction.TOP_LEFT, Coordinate.Direction.TOP_RIGHT);
        }
    }
}
