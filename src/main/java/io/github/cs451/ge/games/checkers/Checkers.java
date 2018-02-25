package io.github.cs451.ge.games.checkers;

import io.github.cs451.ge.bean.Game;
import io.github.cs451.ge.games.checkers.impl.CheckersPlayer;

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
