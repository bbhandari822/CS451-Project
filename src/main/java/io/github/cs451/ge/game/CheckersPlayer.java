package io.github.cs451.ge.game;

import com.jtelegram.api.user.User;
import lombok.Getter;
import lombok.ToString;

@ToString
public class CheckersPlayer extends Player {
    @Getter
    private final CheckersColor color;

    public CheckersPlayer(User user, CheckersColor color) {
        super(user);
        this.color = color;
    }
}
