package io.github.cs451.ge.game;

import com.jtelegram.api.user.User;
import io.github.cs451.ge.game.pieces.KingPiece;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Binod Bhandari on 3/11/18.
 */
public class CheckersPlayerTest {

    private Player player1;
    private User user1;

    @Before
    public void prep() {
        Checkers checkers = new Checkers(player1,player1);

        user1 = getUser();
        when(user1.getUsernameFallbackName()).thenReturn("Tester1");
        CheckersPlayer player2 = new CheckersPlayer(player1, checkers.getPlayer1().getColor());
    }


    private User getUser() {
        return mock(User.class);
    }


}