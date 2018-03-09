package io.github.cs451.ge.telegram;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.inline.ChosenInlineResultEvent;
import com.jtelegram.api.events.inline.InlineQueryEvent;
import com.jtelegram.api.inline.input.InputTextMessageContent;
import com.jtelegram.api.inline.result.InlineResultArticle;
import com.jtelegram.api.menu.*;
import com.jtelegram.api.menu.viewer.InlineMenuViewer;
import com.jtelegram.api.requests.inline.AnswerInlineQuery;
import io.github.cs451.ge.game.Checkers;
import io.github.cs451.ge.game.CheckersColor;
import io.github.cs451.ge.game.CheckersPlayer;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class InlineListener {
    public static void onEvent(ChosenInlineResultEvent event) {
        TelegramBot telegramBot = event.getBot();
        System.out.println("Called1");
        Checkers checkers = new Checkers(new CheckersPlayer(null, CheckersColor.RED), new CheckersPlayer(null, CheckersColor.WHITE));
        checkers.resetBoard();

        CheckersInline checkersInline = new CheckersInline(event.getBot(), checkers);
        checkersInline.addViewer(InlineMenuViewer.builder().inlineMessageId(event.getChosenResult().getInlineMessageId()).build());

        MenuHandler.registerMenu(checkersInline);

        System.out.println("Called3");

    }

    public static void onEvent(InlineQueryEvent event) {
        System.out.println("Called2");
        InlineResultArticle article = InlineResultArticle.builder().id(UUID.randomUUID().toString())
                .id(UUID.randomUUID().toString())
                .title("Checkers")
                .description("Checkers game")
                .thumbUrl("https://i.imgur.com/NoIqczF.jpg")
                .inputMessageContent(InputTextMessageContent.builder().messageText("Hi").build())
                .replyMarkup(Menu.KEYBOARD_MARKUP)
                .build();

        AnswerInlineQuery answer = AnswerInlineQuery.builder().queryId(event.getQuery().getId()).addResult(article).build();

        event.getBot().perform(answer);
    }
}
