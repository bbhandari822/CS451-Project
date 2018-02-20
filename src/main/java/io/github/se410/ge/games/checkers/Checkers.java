package io.github.se410.ge.games.checkers;

import io.github.se410.ge.bean.Game;
import io.github.se410.ge.games.checkers.impl.CheckersPlayer;

public interface Checkers extends Game {
    CheckersPlayer getFirstPlayer();

    CheckersPlayer getSecondPlayer();

    CheckersPlayer getStartingPlayer();

    String getLocalizedName();

    @Override
    default String getName(){
        return "Checkers";
    }
}
