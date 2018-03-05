package io.github.cs451.ge.bean.player;


public interface AIPlayer extends Player {
    @Override
    default String getName() {
        return "Robot!";
    }
}
