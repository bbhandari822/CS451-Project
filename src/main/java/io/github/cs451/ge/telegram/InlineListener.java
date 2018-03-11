package io.github.cs451.ge.telegram;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.inline.ChosenInlineResultEvent;
import com.jtelegram.api.events.inline.InlineQueryEvent;
import com.jtelegram.api.inline.input.InputTextMessageContent;
import com.jtelegram.api.inline.result.InlineResultArticle;
import com.jtelegram.api.menu.Menu;
import com.jtelegram.api.menu.MenuHandler;
import com.jtelegram.api.menu.viewer.InlineMenuViewer;
import com.jtelegram.api.requests.inline.AnswerInlineQuery;
import io.github.cs451.ge.game.PlayerRegistry;
import io.github.cs451.ge.telegram.lobby.CheckersLobby;

import java.util.UUID;

public class InlineListener {
    public static void onEvent(ChosenInlineResultEvent event) {
        System.out.println("Called1");
        TelegramBot bot = event.getBot();

        CheckersLobby checkers = new CheckersLobby(bot, PlayerRegistry.getPlayer(event.getChosenResult().getFrom()), event.getChosenResult().getInlineMessageId());

        checkers.addViewer(InlineMenuViewer.builder().inlineMessageId(event.getChosenResult().getInlineMessageId()).build());

        MenuHandler.registerMenu(checkers);
    }

    public static void onEvent(InlineQueryEvent event) {
        System.out.println("Called2");
        InlineResultArticle article = InlineResultArticle.builder().id(UUID.randomUUID().toString())
                .id(UUID.randomUUID().toString())
                .title("Checkers")
                .description("Checkers game")
                .thumbUrl("https://i.imgur.com/NoIqczF.jpg")
                .inputMessageContent(InputTextMessageContent.builder().messageText("Placeholder").build())
                .replyMarkup(Menu.KEYBOARD_MARKUP)
                .build();

        AnswerInlineQuery answer = AnswerInlineQuery.builder().queryId(event.getQuery().getId()).addResult(article).build();

        event.getBot().perform(answer);
    }
}
