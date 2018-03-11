package io.github.cs451.ge.adapter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class CheckersUIResponse {
    private final boolean success;
    private final ResponseType responseType;

    @RequiredArgsConstructor
    @Getter
    public enum ResponseType {

        SUCCESS("Successful!"),
        INVALID_TURN("It isn't your turn!"),
        INVALID_SELECTION("You can't select that."),
        INVALID_MOVE("That's not a valid move."),
        HAVE_TO_ATTACK("You have to attack."),
        GAME_OVER("Game is over."),
        MUST_COMPLETE_JUMPS("You must complete your attack jumps.");
        private final String message;

    }
}
