package io.github.cs451.ge.game.pieces;

import io.github.cs451.ge.game.CheckersColor;
import io.github.cs451.ge.game.CheckersPlayer;
import io.github.cs451.ge.game.Coordinate;
import lombok.RequiredArgsConstructor;

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
}
