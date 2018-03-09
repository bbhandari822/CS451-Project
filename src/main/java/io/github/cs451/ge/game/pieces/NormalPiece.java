package io.github.cs451.ge.game.pieces;

import io.github.cs451.ge.game.CheckersColor;
import io.github.cs451.ge.game.CheckersPlayer;
import io.github.cs451.ge.game.Coordinate;
import io.github.cs451.ge.game.Move;

import java.util.ArrayList;
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
    public List<Move> getPossibleMoves() {
        return new ArrayList<>();
    }
}
