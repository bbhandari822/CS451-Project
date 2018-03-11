package io.github.cs451.ge.telegram;


import com.jtelegram.api.events.inline.keyboard.CallbackQueryEvent;
import com.jtelegram.api.menu.MenuButton;
import com.jtelegram.api.requests.inline.AnswerCallbackQuery;
import com.jtelegram.api.user.User;
import io.github.cs451.ge.adapter.CheckersUIAction;
import io.github.cs451.ge.adapter.CheckersUIResponse;
import io.github.cs451.ge.game.CheckersPlayer;
import io.github.cs451.ge.game.Coordinate;
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

            System.out.println("Called press");
            User user = callbackQueryEvent.getQuery().getFrom();
            CheckersPlayer player = PlayerRegistry.getPlayer(user);

            CheckersUIAction action = new CheckersUIAction(player, coordinate);
            CheckersUIResponse response = parent.getCheckers().handleAction(action);
            System.out.println(response);
            if (response == null) {
                System.out.println("boop");
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
