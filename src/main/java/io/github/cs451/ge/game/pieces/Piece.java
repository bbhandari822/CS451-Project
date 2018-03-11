package io.github.cs451.ge.game.pieces;

import io.github.cs451.ge.game.Checkers;
import io.github.cs451.ge.game.CheckersMoveCollection;
import io.github.cs451.ge.game.CheckersPlayer;
import io.github.cs451.ge.game.Coordinate;
import io.github.cs451.ge.game.moves.AttackMove;
import io.github.cs451.ge.game.moves.Move;
import io.github.cs451.ge.game.moves.NormalMove;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@ToString
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

    public CheckersMoveCollection getPossibleMoves(Checkers checkers) {
        List<Move> moves = new ArrayList<>();
        for (Coordinate.Direction direction : getAllowedDirections(checkers)) {
            Coordinate newCoordinate = getCoordinate().apply(direction);
            Piece newPiece = checkers.getPiece(newCoordinate);

            // we're out of bounds
            if (newPiece == null) continue;

            if (newPiece.canOccupy()) {
                moves.add(new NormalMove(this, newPiece));
                continue;
            }

            if (canAttack(checkers, newPiece, direction)) {
                moves.add(new AttackMove(this, newPiece, checkers.getPiece(newPiece.getCoordinate().apply(direction))));
            }
        }
        return new CheckersMoveCollection( moves);
    }

    public abstract boolean canOccupy();

    public boolean canAttack(Checkers checkers, Piece otherPiece, Coordinate.Direction direction) {
        // This shouldn't happen.
        if (getPlayer() == null) return false;

        // Make sure its a different player than the current piece.
        if (getPlayer().equals(otherPiece.getPlayer())) return false;

        Coordinate jumpCoordinate = otherPiece.coordinate.apply(direction);
        Piece jumpPiece = checkers.getPiece(jumpCoordinate);

        // Out of bounds
        if (jumpPiece == null) return false;

        // It's an empty block
        return jumpPiece.canOccupy();

    }

    public abstract Piece moveTo(Coordinate coordinate);

    abstract List<Coordinate.Direction> getAllowedDirections(Checkers checkers);
}
