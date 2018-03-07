package io.github.cs451.ge.games.checkers;

import io.github.cs451.ge.bean.Game;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

public class Checkers implements Game {
    private final CheckersPlayer firstPlayer = new CheckersPlayer(CheckersColor.RED);
    private final CheckersPlayer secondPlayer = new CheckersPlayer(CheckersColor.WHITE);

    public CheckersPlayer getFirstPlayer() {
        return firstPlayer;
    }

    public CheckersPlayer getSecondPlayer() {
        return secondPlayer;
    }

    public CheckersPlayer getStartingPlayer() {
        // Randomize who gets to be the first player.
        // WCDF 1.14
        Random secureRandom = new SecureRandom();
        int pick = secureRandom.nextInt(2);
        if (pick == 0) return firstPlayer;
        return secondPlayer;
    }

    public String getLocalizedName() {
        return "American";
    }

    @Override
    public UUID getGameUUID() {
        return UUID.fromString("efbf7ed8-1cb8-477a-9b03-6d758e318ccc");
    }

    @Override
    public String getName() {
        return "Checkers";
    }

}
