package io.github.cs451.ge.game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CheckersColor {
    //🔴
    RED("\uD83D\uDD34"),
    WHITE("⚪️");
    private final String color;
}
