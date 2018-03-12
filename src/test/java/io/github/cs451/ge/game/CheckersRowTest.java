package io.github.cs451.ge.game;

import com.jtelegram.api.user.User;
import io.github.cs451.ge.game.pieces.EmptyPiece;
import io.github.cs451.ge.game.pieces.KingPiece;
import io.github.cs451.ge.game.pieces.Piece;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Binod Bhandari on 3/11/18.
 */
public class CheckersRowTest {

    CheckersRow checkersRow = new CheckersRow(8, 8);
    private Player player1;
    private User user1;
    private CheckersPlayer player2;

    private User getUser() {
        return mock(User.class);
    }

    @Before
    public void prep() {
        Checkers checkers = new Checkers(player1, player1);

        user1 = getUser();
        when(user1.getUsernameFallbackName()).thenReturn("Tester1");
        player2 = new CheckersPlayer(player1, checkers.getPlayer1().getColor());
    }


    //    Coordinate coordinate = piece.getCoordinate();
//    pieces.set(coordinate.getColumn(), piece);
    @Test
    public void setPiece() throws AssertionError {

        CheckersRow checkersRow = new CheckersRow(7, 7);
        KingPiece kingPiece = new KingPiece(player2, new Coordinate(7, 7));
        checkersRow.setPiece(kingPiece.moveTo(new Coordinate(7, 5)));
    }

    @Test
    public void reset() {

    }

    @Test
    public void getPieces() {
        checkersRow.setPiece(new KingPiece(player2, new Coordinate(7, 7)));

    }

    @Test
    public void getPiece() throws NullPointerException {
        Coordinate coordinate = new Coordinate(0, 0);
        Piece piece = new EmptyPiece(coordinate);
        checkersRow.getPieces();
        assertEquals(piece.isSelected(), false);
    }
}
