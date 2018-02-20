package io.github.se410.ge.games.checkers.impl;

import io.github.se410.ge.games.checkers.Checkers;
import io.github.se410.ge.games.checkers.CheckersColor;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

public class AmericanCheckers implements Checkers {
    private final CheckersPlayer firstPlayer = new CheckersPlayer(CheckersColor.RED);
    private final CheckersPlayer secondPlayer = new CheckersPlayer(CheckersColor.WHITE);

    @Override
    public CheckersPlayer getFirstPlayer() {
        return firstPlayer;
    }

    @Override
    public CheckersPlayer getSecondPlayer() {
        return secondPlayer;
    }

    @Override
    public CheckersPlayer getStartingPlayer() {
        // Randomize who gets to be the first player.
        // WCDF 1.14
        Random secureRandom = new SecureRandom();
        int pick = secureRandom.nextInt(2);
        if (pick == 0) return firstPlayer;
        return secondPlayer;
    }

    @Override
    public String getLocalizedName() {
        return "American";
    }

    @Override
    public UUID getGameUUID() {
        return UUID.fromString("efbf7ed8-1cb8-477a-9b03-6d758e318ccc");
    }

}
