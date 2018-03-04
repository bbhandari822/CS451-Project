package io.github.cs451.ge.service.telegram;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.TelegramBotRegistry;
import com.jtelegram.api.events.inline.InlineQueryEvent;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.update.PollingUpdateProvider;
import com.jtelegram.api.user.User;
import io.github.cs451.ge.GameEngine;
import io.github.cs451.ge.bean.service.ServiceProvider;
import io.github.cs451.ge.service.telegram.events.InlineListenerEvent;
import lombok.Getter;

import java.util.UUID;

public class TelegramServiceProvider implements ServiceProvider {
    private final GameEngine instance;
    @Getter
    private TelegramBot bot;

    public TelegramServiceProvider(GameEngine instance) {
        this.instance = instance;
        TelegramBotRegistry registry = TelegramBotRegistry.builder()
                .updateProvider(new PollingUpdateProvider())
                .build();

        this.registerBot(registry);

    }

    private void registerBot(TelegramBotRegistry registry) {
        String apiKey = instance.getConfigurationFile().getTelegramApiKey();

        registry.registerBot(apiKey, this::registerBot);
    }

    private void registerBot(TelegramBot bot, TelegramException exception) {
        if (exception != null) {
            System.out.println("Something bad happened when registering the bot. Shutting down.");
            System.exit(-1);
        }
        this.bot = bot;
        registerEvents();
    }

    private void registerEvents() {
        this.bot.getEventRegistry().registerEvent(InlineQueryEvent.class, new InlineListenerEvent(instance,this));
    }

    @Override
    public UUID getUUID() {
        return UUID.fromString("b75c678c-6b9e-43de-ac36-87a1184d8b08");
    }

    @Override
    public String getName() {
        return "Telegram";
    }

    public TelegramServicePlayer getPlayer(User user) {
        return new TelegramServicePlayer(user, this);
    }

}
