package io.github.cs451.ge.telegram;


import com.jtelegram.api.events.inline.keyboard.CallbackQueryEvent;
import com.jtelegram.api.menu.MenuButton;
import io.github.cs451.ge.game.Coordinate;
import io.github.cs451.ge.game.pieces.Piece;

public class Box extends MenuButton {
    private final CheckersInline parent;
    private final Coordinate coordinate;
    private final String label;

    public Box(CheckersInline parent, Piece piece) {
        this.parent = parent;
        String lbl = piece.getTelegramDisplay();
        coordinate = piece.getCoordinate();

        if (piece.isSelected()) {
            lbl = String.format("[%s]", lbl);
        }

        this.label = lbl;
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
