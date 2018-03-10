package io.github.cs451.ge.telegram.lobby;


import com.jtelegram.api.events.inline.keyboard.CallbackQueryEvent;
import com.jtelegram.api.menu.MenuButton;
import com.jtelegram.api.user.User;
import io.github.cs451.ge.game.CheckersPlayer;
import io.github.cs451.ge.game.PlayerRegistry;

public class JoinButton extends MenuButton {
    private final CheckersLobby lobby;

    public JoinButton(CheckersLobby lobby) {
        this.lobby = lobby;
    }

    @Override
    public String getLabel() {
        return "Join";
    }

    @Override
    public boolean onPress(CallbackQueryEvent callbackQueryEvent) {
        User user = callbackQueryEvent.getQuery().getFrom();
        CheckersPlayer player = PlayerRegistry.getPlayer(user);

        if (lobby.join(player)) {
            lobby.createGame();
        }
        return false;
    }
}
