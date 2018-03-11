package io.github.cs451.ge.game;

import com.jtelegram.api.user.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CheckersTest {
    private Player player1;
    private Player player2;
    private User user1;
    private User user2;
    private Checkers checkers;

    @Before
    public void prep() {
        user1 = getUser();
        user2 = getUser();
        when(user1.getUsernameFallbackName()).thenReturn("Tester1");
        when(user2.getUsernameFallbackName()).thenReturn("Tester2");

        player1 = new Player(user1);
        player2 = new Player(user2);

        checkers = new Checkers(player1, player2);
    }

    @Test
    public void testTeamColors() {
        assertEquals(checkers.getPlayer1().getColor(), CheckersColor.RED);
        assertEquals(checkers.getPlayer2().getColor(), CheckersColor.WHITE);
    }

    private User getUser() {
        return mock(User.class);
    }
}
