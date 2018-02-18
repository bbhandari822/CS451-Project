package io.github.se410.ge.engine;

import io.github.se410.ge.bean.Game;

public class GameInstance {
    private final Game activeGame;

    public GameInstance(Game activeGame) {
        this.activeGame = activeGame;
    }
}
