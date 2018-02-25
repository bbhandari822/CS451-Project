package io.github.cs451.ge.service.telegram;

import com.jtelegram.api.user.User;
import io.github.cs451.ge.bean.service.ServicePlayer;

public class TelegramServicePlayer implements ServicePlayer {
    private final User user;

    public TelegramServicePlayer(User user) {
        this.user = user;
    }

    @Override
    public String getUniqueId() {
        return String.valueOf(user.getId());
    }

    @Override
    public String getName() {
        return user.getUsernameFallbackName();
    }
}
