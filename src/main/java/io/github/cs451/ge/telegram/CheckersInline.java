package io.github.cs451.ge.telegram;


import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.menu.Menu;
import com.jtelegram.api.menu.MenuButton;
import com.jtelegram.api.menu.MenuRow;
import io.github.cs451.ge.game.Checkers;
import io.github.cs451.ge.game.CheckersRow;
import io.github.cs451.ge.game.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class CheckersInline extends Menu {
    private final Checkers checkers;

    protected CheckersInline(TelegramBot bot, Checkers checkers) {
        super(bot);
        this.checkers = checkers;
    }

    @Override
    public List<MenuRow> getRows() {
        List<MenuRow> rows = new ArrayList<>();

        for (CheckersRow row : checkers.getRows()) {
            rows.add(convert(row));
        }
        return rows;
    }

    @Override
    public void handleException(TelegramException e) {
        System.out.println(e.toString());
    }

    private MenuRow convert(CheckersRow checkersRow) {
        List<MenuButton> boxes = new ArrayList<>();
        for (Piece piece : checkersRow.getPieces()) {
            boxes.add(convert(piece));
        }
        MenuRow menuRow = new MenuRow(boxes);
        return menuRow;
    }

    private Box convert(Piece piece) {
        return new Box(this, piece);
    }
}
