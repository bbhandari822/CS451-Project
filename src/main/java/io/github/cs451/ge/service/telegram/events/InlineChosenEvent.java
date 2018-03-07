package io.github.cs451.ge.service.telegram.events;

import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.events.EventHandler;
import com.jtelegram.api.events.inline.ChosenInlineResultEvent;
import com.jtelegram.api.menu.*;
import com.jtelegram.api.menu.viewer.InlineMenuViewer;
import com.jtelegram.api.requests.inline.AnswerCallbackQuery;
import com.jtelegram.api.requests.message.edit.EditTextMessage;
import com.jtelegram.api.user.User;
import io.github.cs451.ge.bean.Lobby;
import io.github.cs451.ge.bean.player.Player;
import io.github.cs451.ge.games.checkers.Checkers;
import io.github.cs451.ge.service.telegram.TelegramHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InlineChosenEvent implements EventHandler<ChosenInlineResultEvent> {
    private final TelegramHandler handler;

    @Override
    public void onEvent(ChosenInlineResultEvent event) {
        final TelegramBot bot = event.getBot();
        Lobby<Checkers> checkersLobby = new Lobby<>(new Checkers(), 2);

        User user = event.getChosenResult().getFrom();
        Player player = handler.getPlayer(user);

        checkersLobby.joinPlayer(player);

        String inlineMessageId = event.getChosenResult().getInlineMessageId();
        SimpleMenu menu = SimpleMenu.builder().bot(event.getBot()).build();

        // Edit lobby message
        bot.perform(EditTextMessage.builder().inlineMessageId(inlineMessageId).text(checkersLobby.getLobbyText()).replyMarkup(Menu.KEYBOARD_MARKUP).build());

        menu.addRow(MenuRow
                .from(
                        SimpleMenuButton.builder()
                                .label("Quit")
                                .onPress((simpleMenuButton, callbackQueryEvent) -> {
                                    Player clicker = handler.getPlayer(callbackQueryEvent.getQuery().getFrom());

                                    if (!checkersLobby.leavePlayer(clicker)) {
                                        bot.perform(AnswerCallbackQuery.builder().
                                                showAlert(true).text("You are not part of that lobby.").
                                                queryId(callbackQueryEvent.getQuery().getId()).
                                                build());
                                        return false;
                                    }
                                    bot.perform(EditTextMessage.builder().inlineMessageId(callbackQueryEvent.getQuery().getInlineMessageId()).text(checkersLobby.getLobbyText()).build());
                                    return true;
                                })
                                .build(),

                        SimpleMenuButton.builder()
                                .label("Join")
                                .onPress(((simpleMenuButton, callbackQueryEvent) -> {
                                    Player clicker = handler.getPlayer(callbackQueryEvent.getQuery().getFrom());

                                    if (!checkersLobby.joinPlayer(clicker)) {
                                        bot.perform(AnswerCallbackQuery.builder().
                                                showAlert(true).text("You can not join this lobby.").
                                                queryId(callbackQueryEvent.getQuery().getId()).
                                                build());
                                        return false;
                                    }

                                    event.getBot().perform(EditTextMessage.builder()
                                            .inlineMessageId(callbackQueryEvent.getQuery().getInlineMessageId())
                                            .text(checkersLobby.getLobbyText())
                                            .build());
                                    return true;
                                })).build()
                ));

        menu.addViewer(InlineMenuViewer.builder().inlineMessageId(inlineMessageId).build());
        MenuHandler.registerMenu(menu);

    }
}
