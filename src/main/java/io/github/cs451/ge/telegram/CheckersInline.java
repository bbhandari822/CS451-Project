package io.github.cs451.ge.telegram;


import com.jtelegram.api.TelegramBot;
import com.jtelegram.api.ex.TelegramException;
import com.jtelegram.api.menu.Menu;
import com.jtelegram.api.menu.MenuRow;
import com.jtelegram.api.util.TextBuilder;
import io.github.cs451.ge.game.Checkers;
import io.github.cs451.ge.game.CheckersPlayer;
import io.github.cs451.ge.game.CheckersRow;
import io.github.cs451.ge.game.pieces.Piece;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckersInline extends Menu {
    @Getter
    private final Checkers checkers;

    public CheckersInline(TelegramBot bot, Checkers checkers) {
        super(bot);
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
        // Add the draw button
        if (!checkers.isCompleted()) {
            rows.add(new MenuRow(Collections.singletonList(new DrawButton(this))));
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
        if (getCheckers().getCheckersDrawHandler().isActive()) {
            if (getCheckers().getCheckersDrawHandler().isConfirmed()) {
                return getHeader().plain("Game ended in draw!");
            } else {
                CheckersPlayer player = getCheckers().getCheckersDrawHandler().getDrawRequester();
                return getHeader().plain(String.format("%s - %s requested the game to end in a draw. Do you accept?", player.getUser().getUsernameFallbackName(), player.getColor().getColor()));
            }
        }
        if (!getCheckers().isCompleted()) {
            return getCurrentTurnMessage();
        } else {
            return getHeader().plain(String.format("Winner: %s", getCheckers().getWinner().getUser().getUsernameFallbackName()));
        }
    }

    private TextBuilder getHeader() {
        return TextBuilder.create().bold("✏️Telegram Checkers ✏️").newLine();
    }

    private TextBuilder getCurrentTurnMessage() {
        Checkers checkers = getCheckers();
        CheckersPlayer player1 = checkers.getPlayer1();
        CheckersPlayer player2 = checkers.getPlayer2();

       TextBuilder builder = getHeader().newLine();
        builder = applyPlayer(player1, builder);
        builder = applyPlayer(player2, builder);

        return builder;
    }

    public TextBuilder applyPlayer(CheckersPlayer player, TextBuilder builder) {
        Checkers checkers = getCheckers();
        CheckersPlayer currentPlayer = checkers.getCurrentTurn();

        builder.plain(String.format("%s %s %s: %d", currentPlayer.equals(player) ? "\uD83D\uDC49\uD83C\uDFFB" : "", player.getUser().getUsernameFallbackName(), player.getColor().getColor(), checkers.getPiecesLeft(player))).newLine();
        return builder;
    }
}
