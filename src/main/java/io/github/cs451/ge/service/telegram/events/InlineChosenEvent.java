package io.github.cs451.ge.service.telegram.events;

import com.jtelegram.api.events.EventHandler;
import com.jtelegram.api.events.inline.ChosenInlineResultEvent;

public class InlineChosenEvent implements EventHandler<ChosenInlineResultEvent> {

    @Override
    public void onEvent(ChosenInlineResultEvent event) {
event.getChosenResult().
    }
}
