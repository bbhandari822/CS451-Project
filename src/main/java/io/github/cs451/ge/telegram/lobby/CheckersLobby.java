package io.github.cs451.ge.telegram.lobby;


import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.menu.Menu;
import com.jtelegram.api.menu.MenuHandler;
import com.jtelegram.api.menu.MenuRow;
import com.jtelegram.api.util.TextBuilder;
import io.github.cs451.ge.game.Checkers;
import io.github.cs451.ge.game.CheckersPlayer;
import io.github.cs451.ge.game.Player;
import io.github.cs451.ge.telegram.CheckersInline;

import java.util.Collections;
import java.util.List;

public class CheckersLobby extends Menu {
    private final Player lobbyOwner;
    private final String inlineMessageId;
    private Player opponent;

    public CheckersLobby(TelegramBot bot, Player lobbyOwner, String inlineMessageId) {
        super(bot);
        this.lobbyOwner = lobbyOwner;
        this.inlineMessageId = inlineMessageId;
    }

    public boolean join(Player player) {
        if (player.equals(lobbyOwner)) return false;
        opponent = player;
        return true;
    }

    public void createGame() {
        Checkers game = new Checkers(lobbyOwner, opponent);
        game.resetBoard();
        CheckersInline inline = new CheckersInline(getBot(), game);
        MenuHandler.registerMenu(inline);
        MenuHandler.unregisterMenu(this);

        this.migrateTo(inline);
    }

    @Override
    public List<MenuRow> getRows() {
        return Collections.singletonList(new MenuRow(Collections.singletonList(new JoinButton(this))));
    }

    @Override
    public TextBuilder getMenuMessage() {
        return TextBuilder.create().plain(String.format("Welcome to the Checkers Lobby. %s is waiting for someone to join.", lobbyOwner.getUser().getUsernameFallbackName()));
    }

    @Override
    public void handleException(TelegramException e) {

    }
}
