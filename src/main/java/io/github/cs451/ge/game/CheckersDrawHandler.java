package io.github.cs451.ge.game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckersDrawHandler {
    private final Checkers checkers;
    @Getter
    private CheckersPlayer drawRequester;
    @Getter
    private boolean confirmed;

    public void call(CheckersPlayer player) {
        if (checkers.isCompleted()) return;
        if (!isActive()) {
            drawRequester = player;
        } else {
            confirmDraw(player);
        }
    }

    public boolean isActive() {
        return drawRequester != null;
    }

    void confirmDraw(CheckersPlayer checkersPlayer) {
        if (checkersPlayer.equals(drawRequester)) return;

        confirmed = true;
        checkers.endGame();

    }

    public void reset() {
        confirmed = false;
        drawRequester = null;
    }
}
