package io.github.cs451.ge.service.telegram.events;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.EventHandler;
import com.jtelegram.api.events.message.TextMessageEvent;
import com.jtelegram.api.message.impl.TextMessage;
import com.jtelegram.api.requests.message.send.SendText;
import io.github.cs451.ge.GameEngine;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GameListenerEvent implements EventHandler<TextMessageEvent> {
    private final GameEngine instance;

    @Override
    public void onEvent(TextMessageEvent event) {
        TelegramBot bot = event.getBot();
        TextMessage message = event.getMessage();
        bot.perform(SendText
                .builder()
                .chatId(message.getChat().getChatId())
                .text("Hello fellow humans, I am bot. Now I am become Death, the destroyer of worlds.")
                .build());

    }
}
