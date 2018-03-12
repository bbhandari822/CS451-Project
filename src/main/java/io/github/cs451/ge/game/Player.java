package io.github.cs451.ge.game;

import com.jtelegram.api.user.User;
import lombok.Getter;

public class Player {
    @Getter
    private final User user;

    public Player(User user) {
        this.user = user;
    }
}
