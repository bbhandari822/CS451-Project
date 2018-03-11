package io.github.cs451.ge.game;

import com.jtelegram.api.user.User;

import java.util.HashMap;
import java.util.Map;

public class PlayerRegistry {
    private static final Map<User, Player> map = new HashMap<>();

    public static Player getPlayer(User user) {
        return map.computeIfAbsent(user, Player::new);
    }
}
