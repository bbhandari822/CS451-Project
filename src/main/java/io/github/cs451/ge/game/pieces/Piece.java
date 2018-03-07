package io.github.cs451.ge.game.pieces;

import io.github.cs451.ge.game.Player;

public interface Piece {
    // Lets use this instead of team enum.
    Player getPlayer();
}
