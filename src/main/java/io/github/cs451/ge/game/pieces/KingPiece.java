package io.github.cs451.ge.game.pieces;

import io.github.cs451.ge.game.Checkers;
import io.github.cs451.ge.game.CheckersColor;
import io.github.cs451.ge.game.CheckersPlayer;
import io.github.cs451.ge.game.Coordinate;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@ToString(callSuper = true)
public class KingPiece extends Piece {

    public KingPiece(CheckersPlayer player, Coordinate coordinate) {
        super(player, coordinate);
    }

    @Override
    public String getTelegramDisplay() {
        if (getPlayer().getColor() == CheckersColor.RED) {
            //🍎
            return "\uD83C\uDF4E";
        } else {
            return "⬜";
        }
    }

    public boolean canOccupy() {
        return false;
    }

    @Override
    public Piece moveTo(Coordinate coordinate) {
        return new KingPiece(getPlayer(), coordinate);
    }

    @Override
    List<Coordinate.Direction> getAllowedDirections(Checkers checkers) {
        return Arrays.asList(Coordinate.Direction.values());
    }

}
