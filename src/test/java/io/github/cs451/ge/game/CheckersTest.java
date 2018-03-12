package io.github.cs451.ge.game;

import com.jtelegram.api.user.User;
import io.github.cs451.ge.adapter.CheckersUIAction;
import io.github.cs451.ge.adapter.CheckersUIResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
        checkers.resetBoard();
    }

    @Test
    public void testTeamColors() {
        assertEquals(checkers.getPlayer1().getColor(), CheckersColor.RED);
        assertEquals(checkers.getPlayer2().getColor(), CheckersColor.WHITE);
    }

    @Test
    public void testActionTakenWhileComplete() {
        checkers.endGame();
        CheckersUIResponse resp = checkers.handleAction(new CheckersUIAction(checkers.getPlayer1(), new Coordinate(1, 1)));

        assertEquals(resp.getResponseType(), CheckersUIResponse.ResponseType.GAME_OVER);
    }

    @Test
    public void testActionWrongTurnWrongTurn() {
        CheckersPlayer player = null;
        if (checkers.getPlayer1().equals(checkers.getCurrentTurn())) {
            player = checkers.getPlayer2();
        } else {
            player = checkers.getPlayer1();
        }

        CheckersUIResponse resp = checkers.handleAction(new CheckersUIAction(player, new Coordinate(1, 1)));

        assertEquals(resp.getResponseType(), CheckersUIResponse.ResponseType.INVALID_TURN);
    }

    @Test
    public void handleInvalidSelection() {
        CheckersUIResponse resp = checkers.handleAction(new CheckersUIAction(checkers.getCurrentTurn(), new Coordinate(99, 99)));

        assertEquals(resp.getResponseType(), CheckersUIResponse.ResponseType.INVALID_SELECTION);
    }

    @Test
    public void handleSelection() {
        checkers.setCurrentTurn(checkers.getPlayer1());

        CheckersUIResponse resp = checkers.handleAction(new CheckersUIAction(checkers.getCurrentTurn(), new Coordinate(1, 0)));

        assertEquals(resp.getResponseType(), CheckersUIResponse.ResponseType.SUCCESS);
    }

    @Test
    public void handleMoveDeselect() {
        checkers.setCurrentTurn(checkers.getPlayer1());

        checkers.handleAction(new CheckersUIAction(checkers.getCurrentTurn(), new Coordinate(1, 0)));

        CheckersUIResponse resp = checkers.handleAction(new CheckersUIAction(checkers.getCurrentTurn(), new Coordinate(1, 0)));

        assertEquals(resp.getResponseType(), CheckersUIResponse.ResponseType.SUCCESS);
    }

    @Test
    public void handleSuccessfulMove() {
        checkers.setCurrentTurn(checkers.getPlayer1());

        checkers.handleAction(new CheckersUIAction(checkers.getCurrentTurn(), new Coordinate(2, 1)));

        CheckersUIResponse resp = checkers.handleAction(new CheckersUIAction(checkers.getCurrentTurn(), new Coordinate(3, 0)));

        assertEquals(resp.getResponseType(), CheckersUIResponse.ResponseType.SUCCESS);
    }

    @Test
    public void handleDrawRequestAndConfirm() {
        checkers.getCheckersDrawHandler().call(checkers.getPlayer1());
        checkers.getCheckersDrawHandler().confirmDraw(checkers.getPlayer2());

        assertTrue(checkers.isCompleted());
    }

    private User getUser() {
        return mock(User.class);
    }
}
