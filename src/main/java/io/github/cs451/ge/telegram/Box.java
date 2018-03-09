package io.github.cs451.ge.telegram;


import com.jtelegram.api.events.inline.keyboard.CallbackQueryEvent;
import com.jtelegram.api.menu.MenuButton;
import com.jtelegram.api.menu.MenuHandler;
import io.github.cs451.ge.game.Coordinate;
import io.github.cs451.ge.game.pieces.KingPiece;
import io.github.cs451.ge.game.pieces.Piece;

import java.util.concurrent.ThreadLocalRandom;

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
        System.out.println("Clicked a button");
        if (ThreadLocalRandom.current().nextDouble() < 0.5)
            parent.getCheckers().getPiece(coordinate).setSelected(true);
        else
            parent.getCheckers().setPiece(new KingPiece(parent.getCheckers().getPiece(coordinate).getPlayer(), coordinate));

        CheckersInline newBoard = parent.generateNewBoard();
        MenuHandler.registerMenu(newBoard);

        parent.migrateTo(newBoard);
        return false;
    }
}
