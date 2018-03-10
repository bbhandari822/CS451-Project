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

    public enum ResponseType {
        SUCCESS,
        INVALID_TURN,
        INVALID_SELECTION,
        INVALID_MOVE,
        HAVE_TO_ATTACK;
    }
}
