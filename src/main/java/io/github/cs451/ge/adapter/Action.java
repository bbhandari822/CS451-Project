package io.github.cs451.ge.adapter;

import io.github.cs451.ge.game.Player;

public interface Action<T extends Player> {
    T getPlayer();
}
