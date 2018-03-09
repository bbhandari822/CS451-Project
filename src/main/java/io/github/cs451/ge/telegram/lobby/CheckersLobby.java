package io.github.cs451.ge.telegram.lobby;


import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.menu.Menu;
import com.jtelegram.api.menu.MenuHandler;
import com.jtelegram.api.menu.MenuRow;
import io.github.cs451.ge.game.Checkers;
import io.github.cs451.ge.game.CheckersPlayer;
import io.github.cs451.ge.telegram.CheckersInline;

import java.util.Collections;
import java.util.List;

public class CheckersLobby extends Menu {
    private final CheckersPlayer lobbyOwner;
    private final String inlineMessageId;
    private CheckersPlayer opponent;

    public CheckersLobby(TelegramBot bot, CheckersPlayer lobbyOwner, String inlineMessageId) {
        super(bot);
        this.lobbyOwner = lobbyOwner;
        this.inlineMessageId = inlineMessageId;
    }

    public void join(CheckersPlayer player) {
        opponent = player;
    }

    public CheckersInline createGame() {
        Checkers game = new Checkers(lobbyOwner, opponent);
        game.resetBoard();
        CheckersInline inline = new CheckersInline(getBot(), inlineMessageId, game);
        MenuHandler.registerMenu(inline);
        MenuHandler.unregisterMenu(this);

        this.migrateTo(inline);
        return inline;
    }

    @Override
    public List<MenuRow> getRows() {
        return Collections.singletonList(new MenuRow(Collections.singletonList(new JoinButton(this))));
    }

    @Override
    public void handleException(TelegramException e) {

    }
}
