package io.github.cs451.ge.game.pieces;

import io.github.cs451.ge.game.*;

import java.util.ArrayList;
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
    public List<Move> getPossibleMoves() {
        return new ArrayList<>();
    }
}
