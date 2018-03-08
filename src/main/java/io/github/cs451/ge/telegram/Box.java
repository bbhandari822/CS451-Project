package io.github.cs451.ge.telegram;


import com.jtelegram.api.events.inline.keyboard.CallbackQueryEvent;
import com.jtelegram.api.menu.MenuButton;
import io.github.cs451.ge.game.Coordinate;
import io.github.cs451.ge.game.pieces.Piece;

public class Box extends MenuButton {
    private final Coordinate coordinate;
    private final String label;

    public Box(Piece piece) {
        label = piece.getTelegramDisplay();
        coordinate = piece.getCoordinate();
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public boolean onPress(CallbackQueryEvent callbackQueryEvent) {
        return false;
    }
}
