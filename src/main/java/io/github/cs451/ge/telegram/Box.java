package io.github.cs451.ge.telegram;


import com.jtelegram.api.events.inline.keyboard.CallbackQueryEvent;
import com.jtelegram.api.menu.MenuButton;

public class Box extends MenuButton {

    @Override
    public String getLabel() {
        return null;
    }

    @Override
    public boolean onPress(CallbackQueryEvent callbackQueryEvent) {
        return false;
    }
}
