package io.github.cs451.ge.telegram;


import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.menu.Menu;
import com.jtelegram.api.menu.MenuRow;

import java.util.List;

public class CheckersInline extends Menu {
    protected CheckersInline(TelegramBot bot) {
        super(bot);
    }

    @Override
    public List<MenuRow> getRows() {
        return null;
    }
}
