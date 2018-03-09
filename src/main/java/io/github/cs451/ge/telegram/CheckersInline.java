package io.github.cs451.ge.telegram;


import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.menu.Menu;
import com.jtelegram.api.menu.MenuRow;
import com.jtelegram.api.util.TextBuilder;
import io.github.cs451.ge.game.Checkers;
import io.github.cs451.ge.game.CheckersRow;
import io.github.cs451.ge.game.pieces.Piece;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CheckersInline extends Menu {
    private final String inlineMessageId;
    @Getter
    private final Checkers checkers;

    public CheckersInline(TelegramBot bot, String inlineMessageId, Checkers checkers) {
        super(bot);
        this.inlineMessageId = inlineMessageId;
        this.checkers = checkers;
    }

    @Override
    public List<MenuRow> getRows() {
        return buildRows();
    }

    private List<MenuRow> buildRows() {
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
        List<Box> boxes = new ArrayList<>();
        for (Piece piece : checkersRow.getPieces()) {
            boxes.add(convert(piece));
        }
        return new MenuRow(boxes);
    }

    private Box convert(Piece piece) {
        return new Box(this, piece);
    }

    @Override
    public TextBuilder getMenuMessage() {
        return TextBuilder.create().plain(String.format("Current turn: %s", getCheckers().getCurrentTurn().getUser().getUsernameFallbackName()));

    }
}
