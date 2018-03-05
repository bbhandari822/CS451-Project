package io.github.cs451.ge.service.telegram.events;

import com.jtelegram.api.events.EventHandler;
import com.jtelegram.api.events.inline.InlineQueryEvent;
import com.jtelegram.api.inline.input.InputTextMessageContent;
import com.jtelegram.api.inline.result.InlineResultArticle;
import com.jtelegram.api.menu.Menu;
import com.jtelegram.api.requests.inline.AnswerInlineQuery;
import com.jtelegram.api.user.User;
import com.jtelegram.api.util.TextBuilder;
import io.github.cs451.ge.GameEngine;
import io.github.cs451.ge.bean.player.HumanPlayer;
import io.github.cs451.ge.bean.player.Player;
import io.github.cs451.ge.service.telegram.TelegramHandler;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class InlineListenerEvent implements EventHandler<InlineQueryEvent> {
    private final GameEngine instance;
    private final TelegramHandler provider;

//    private HumanPlayer registerPlayer(User user) {
//        Player player = provider.getPlayer(user);
//
//    }

    @Override
    public void onEvent(InlineQueryEvent inlineQueryEvent) {

        // TODO Stop hardcoding this
        AnswerInlineQuery answer = AnswerInlineQuery.builder()
                .queryId(inlineQueryEvent.getQuery().getId())
                .addResult(
                        InlineResultArticle.builder()
                                .title("Checkers")
                                .thumbUrl("https://i.imgur.com/NoIqczF.jpg")
                                .description("American Checkers")
                                .id(UUID.randomUUID().toString())
                                .inputMessageContent(
                                        InputTextMessageContent.builder()
                                                .messageText(TextBuilder.create().bold("Checkers").newLine().plain("Lobby"))
                                                .build()
                                )
                                .replyMarkup(Menu.KEYBOARD_MARKUP)
                                .id(UUID.randomUUID().toString())
                                .build()
                )
                .build();

        provider.getBot().perform(answer);
    }
}
