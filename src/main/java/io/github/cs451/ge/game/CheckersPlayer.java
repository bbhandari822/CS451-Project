package io.github.cs451.ge.game;

import com.jtelegram.api.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class CheckersPlayer extends Player {
    @Getter
    @Setter
    private CheckersColor color;

    public CheckersPlayer(User user) {
        super(user);
    }
}
