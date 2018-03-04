package io.github.cs451.ge.service.telegram;

import com.jtelegram.api.user.User;
import io.github.cs451.ge.bean.service.ServicePlayer;
import io.github.cs451.ge.bean.service.ServiceProvider;

public class TelegramServicePlayer implements ServicePlayer {
    private final User user;
    private final ServiceProvider serviceProvider;

    public TelegramServicePlayer(User user, ServiceProvider serviceProvider) {
        this.user = user;
        this.serviceProvider = serviceProvider;
    }

    @Override
    public String getUniqueId() {
        return String.valueOf(user.getId());
    }

    @Override
    public String getName() {
        return user.getUsernameFallbackName();
    }

    @Override
    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }
}
