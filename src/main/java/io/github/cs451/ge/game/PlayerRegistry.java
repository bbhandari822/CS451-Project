package io.github.cs451.ge.game;

import com.jtelegram.api.user.User;

import java.util.HashMap;
import java.util.Map;

public class PlayerRegistry {
    private static final Map<User, CheckersPlayer> map = new HashMap<>();

    public static CheckersPlayer getPlayer(User user) {
        return map.computeIfAbsent(user, CheckersPlayer::new);
    }
}
