package io.github.cs451.ge.game.pieces;

import io.github.cs451.ge.game.Checkers;
import io.github.cs451.ge.game.CheckersColor;
import io.github.cs451.ge.game.CheckersPlayer;
import io.github.cs451.ge.game.Coordinate;
import io.github.cs451.ge.game.moves.Move;

import java.util.Arrays;
import java.util.List;

public class KingPiece extends Piece {

    public KingPiece(CheckersPlayer player, Coordinate coordinate) {
        super(player, coordinate);
    }

    @Override
    public String getTelegramDisplay() {
        if (getPlayer().getColor() == CheckersColor.RED) {
            return "\uD83C\uDF4E";
        } else {
            return "⬜";
        }
    }

    @Override
    public List<Move> getPossibleMoves(Checkers checkers) {
        return null;
    }

    @Override
    public boolean canOccupy() {
        return false;
    }


}
