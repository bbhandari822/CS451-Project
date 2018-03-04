package io.github.cs451.ge.service.telegram.events;

import com.jtelegram.api.events.EventHandler;
import com.jtelegram.api.events.inline.ChosenInlineResultEvent;
import com.jtelegram.api.events.inline.InlineQueryEvent;
import com.jtelegram.api.inline.input.InputTextMessageContent;
import com.jtelegram.api.inline.result.InlineResultArticle;
import com.jtelegram.api.menu.Menu;
import com.jtelegram.api.menu.MenuLoader;
import com.jtelegram.api.requests.inline.AnswerInlineQuery;
import com.jtelegram.api.user.User;
import io.github.cs451.ge.GameEngine;
import io.github.cs451.ge.bean.player.HumanPlayer;
import io.github.cs451.ge.service.telegram.TelegramServicePlayer;
import io.github.cs451.ge.service.telegram.TelegramServiceProvider;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class InlineListenerEvent implements EventHandler<InlineQueryEvent> {
    private final GameEngine instance;
    private final TelegramServiceProvider provider;

    private HumanPlayer registerPlayer(User user) {
        TelegramServicePlayer player = provider.getPlayer(user);

        return instance.getRegistry().registerPlayer(player);
    }

    @Override
    public void onEvent(InlineQueryEvent inlineQueryEvent) {
        System.out.println("Called");
        Menu menu = MenuLoader.createMenu(provider.getBot(), "Loading...");

        AnswerInlineQuery answer = AnswerInlineQuery.builder()
                .queryId(inlineQueryEvent.getQuery().getId())
                .addResult(
                        InlineResultArticle.builder()
                                .title("booper")
                                .id(UUID.randomUUID().toString())
                                .inputMessageContent(
                                        InputTextMessageContent.builder()
                                                .messageText("boop")

                                                .build()
                                )
                                .build()
                )
                .callback(() -> {
                    System.out.println("Selected");
                }).errorHandler(e -> System.out.println(e))
                .build();

        provider.getBot().perform(answer);
    }
}
