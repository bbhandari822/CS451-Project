package io.github.cs451.ge.telegram;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.TelegramBotRegistry;
import com.jtelegram.api.events.inline.ChosenInlineResultEvent;
import com.jtelegram.api.events.inline.InlineQueryEvent;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.update.PollingUpdateProvider;
import io.github.cs451.ge.configuration.ConfigurationLoader;

import java.util.function.BiConsumer;

public class TelegramRegister {
    public TelegramRegister() {
        String key = ConfigurationLoader.loadConfiguration().getTelegram().getApiKey();
        TelegramBotRegistry registry = TelegramBotRegistry.builder().updateProvider(new PollingUpdateProvider()).build();

        registry.registerBot(key, (bot, e) -> {
            bot.getEventRegistry().registerEvent(ChosenInlineResultEvent.class, InlineListener::onEvent);
            bot.getEventRegistry().registerEvent(InlineQueryEvent.class, InlineListener::onEvent);
        });

    }
}
