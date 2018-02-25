package io.github.cs451.ge.engine;

import io.github.cs451.ge.bean.Game;

public class GameInstance {
    private final Game activeGame;

    public GameInstance(Game activeGame) {
        this.activeGame = activeGame;
    }
}
