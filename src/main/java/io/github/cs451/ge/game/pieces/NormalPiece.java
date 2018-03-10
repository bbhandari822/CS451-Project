package io.github.cs451.ge.game.pieces;

import io.github.cs451.ge.game.Checkers;
import io.github.cs451.ge.game.CheckersColor;
import io.github.cs451.ge.game.CheckersPlayer;
import io.github.cs451.ge.game.Coordinate;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@ToString(callSuper = true)
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
    public boolean canOccupy() {
        return false;
    }

    @Override
    public Piece moveTo(Coordinate coordinate) {
        return new NormalPiece(getPlayer(), coordinate);
    }

    @Override
    List<Coordinate.Direction> getAllowedDirections(Checkers checkers) {
        // Top part of game is player1, bottom part is player2
        if (checkers.getPlayer1().equals(getPlayer())) {
            return Arrays.asList(Coordinate.Direction.BOTTOM_LEFT, Coordinate.Direction.BOTTOM_RIGHT);
        } else {
            return Arrays.asList(Coordinate.Direction.TOP_LEFT, Coordinate.Direction.TOP_RIGHT);
        }
    }
}
