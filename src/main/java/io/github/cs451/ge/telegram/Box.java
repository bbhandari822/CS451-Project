package io.github.cs451.ge.telegram;


import com.jtelegram.api.events.inline.keyboard.CallbackQueryEvent;
import com.jtelegram.api.menu.MenuButton;
import com.jtelegram.api.requests.inline.AnswerCallbackQuery;
import com.jtelegram.api.user.User;
import io.github.cs451.ge.adapter.CheckersUIAction;
import io.github.cs451.ge.adapter.CheckersUIResponse;
import io.github.cs451.ge.game.CheckersPlayer;
import io.github.cs451.ge.game.Coordinate;
import io.github.cs451.ge.game.Player;
import io.github.cs451.ge.game.PlayerRegistry;
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
        try {

            User user = callbackQueryEvent.getQuery().getFrom();
            Player player = PlayerRegistry.getPlayer(user);
            CheckersPlayer checkersPlayer = parent.getCheckers().getPlayer(player);

            CheckersUIAction action = new CheckersUIAction(checkersPlayer, coordinate);
            CheckersUIResponse response = parent.getCheckers().handleAction(action);
            System.out.println(response);
            if (response == null) {
                return false;
            }

            AnswerCallbackQuery answer = AnswerCallbackQuery.builder().showAlert(true).text(response.getResponseType().getMessage()).queryId(callbackQueryEvent.getQuery().getId()).build();

            if (!response.isSuccess()) {
                callbackQueryEvent.getBot().perform(answer);
            }

            return response.isSuccess();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
