package io.github.cs451.ge.game.pieces;

import io.github.cs451.ge.game.Player;

public class EmptyPiece implements Piece {

    @Override
    public Player getPlayer() {
        // This is going to be null because it isn't owned by anyone.
        return null;
    }
}
