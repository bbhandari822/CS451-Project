package io.github.cs451.ge.service.telegram;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.TelegramBotRegistry;
import com.jtelegram.api.events.inline.ChosenInlineResultEvent;
import com.jtelegram.api.events.inline.InlineQueryEvent;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.update.PollingUpdateProvider;
import com.jtelegram.api.user.User;
import io.github.cs451.ge.GameEngine;
import io.github.cs451.ge.bean.player.HumanPlayer;
import io.github.cs451.ge.bean.player.Player;
import io.github.cs451.ge.service.telegram.events.InlineChosenEvent;
import io.github.cs451.ge.service.telegram.events.InlineListenerEvent;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;

public class TelegramHandler {
    private final Map<User, Player> users = new HashMap<>();
    private final GameEngine instance;
    @Getter
    private TelegramBot bot;

    public TelegramHandler(GameEngine instance) {
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
        this.bot.getEventRegistry().registerEvent(InlineQueryEvent.class, new InlineListenerEvent(instance, this));
        this.bot.getEventRegistry().registerEvent(ChosenInlineResultEvent.class, new InlineChosenEvent(this));
    }

    public Player getPlayer(User user) {
       return users.compute(user, (user1, player) -> {
            if (player != null) {
                return player;
            }
            HumanPlayer p = new HumanPlayer(UUID.randomUUID());
            p.addIntegration(user1);

            return p;
        });
    }

}
