package io.github.cs451.ge.telegram;


import com.jtelegram.api.events.inline.keyboard.CallbackQueryEvent;
import com.jtelegram.api.menu.MenuButton;
import com.jtelegram.api.user.User;
import io.github.cs451.ge.game.CheckersPlayer;
import io.github.cs451.ge.game.Player;
import io.github.cs451.ge.game.PlayerRegistry;

public class DrawButton extends MenuButton {
    private final CheckersInline parent;

    public DrawButton(CheckersInline parent) {
        this.parent = parent;
    }

    @Override
    public String getLabel() {
        return "Draw";
    }

    @Override
    public boolean onPress(CallbackQueryEvent callbackQueryEvent) {
        User user = callbackQueryEvent.getQuery().getFrom();
        Player player = PlayerRegistry.getPlayer(user);
        CheckersPlayer checkersPlayer = parent.getCheckers().getPlayer(player);

        parent.getCheckers().getCheckersDrawHandler().call(checkersPlayer);
        return true;
    }
}
