package io.github.cs451.ge.game;

import com.jtelegram.api.user.User;
import lombok.Getter;
import lombok.ToString;

@ToString
public class CheckersPlayer {
    @Getter
    private final Player player;
    @Getter
    private final CheckersColor color;

    public CheckersPlayer(Player player, CheckersColor color) {
        this.player = player;
        this.color = color;
    }

    public User getUser() {
        return player.getUser();
    }
}
