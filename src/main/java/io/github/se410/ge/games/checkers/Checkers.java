package io.github.se410.ge.games.checkers;

import io.github.se410.ge.bean.Game;

public interface Checkers extends Game {
    CheckersTeamColor getFirstTeamColor();

    CheckersTeamColor getSecondTeamColor();

    String getLocalizedName();
}
