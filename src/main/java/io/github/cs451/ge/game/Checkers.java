package io.github.cs451.ge.game;

import io.github.cs451.ge.adapter.CheckersUIAction;
import io.github.cs451.ge.adapter.CheckersUIResponse;
import io.github.cs451.ge.game.moves.Move;
import io.github.cs451.ge.game.pieces.Piece;
import lombok.Getter;
import lombok.ToString;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;

@Getter
@ToString
public class Checkers implements Game {
    private final static int BOARD_SIZE = 8;
    private final CheckersPlayer player1;
    private final CheckersPlayer player2;
    private final List<CheckersRow> rows = new ArrayList<>(BOARD_SIZE);
    private final SecureRandom random = new SecureRandom();

    private Coordinate selectedPiece;

    private CheckersPlayer currentTurn;

    public Checkers(CheckersPlayer player1, CheckersPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;

        player1.setColor(CheckersColor.RED);
        player2.setColor(CheckersColor.WHITE);
    }

    private void clear() {
        rows.clear();
        for (int i = 0; i < BOARD_SIZE; i++) {
            rows.add(new CheckersRow(BOARD_SIZE, i));
        }
    }

    public void resetBoard() {
        clear();
        resetTurn();
        rows.get(0).reset(true, player1);
        rows.get(1).reset(false, player1);
        rows.get(2).reset(true, player1);

        rows.get(5).reset(false, player2);
        rows.get(6).reset(true, player2);
        rows.get(7).reset(false, player2);
    }

    public Piece getPiece(Coordinate coordinate) {
        try {
            return rows.get(coordinate.getRow()).getPiece(coordinate);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void setPiece(Piece piece) {
        Coordinate coordinate = piece.getCoordinate();
        rows.get(coordinate.getRow()).setPiece(piece);
    }

    public CheckersUIResponse handleAction(CheckersUIAction action) {
        // Only process action for the current player.
        if (!currentTurn.equals(action.getPlayer())) {
            return new CheckersUIResponse(false, CheckersUIResponse.ResponseType.INVALID_TURN);
        }

        CheckersUIResponse result = handleSelection(action);

        if (result != null) return result;

        return handleMove(action);
    }

    private CheckersUIResponse handleMove(CheckersUIAction action) {
        System.out.println("Calling handle move.");
        // There is no selected piece.
        if (selectedPiece == null) return null;

        Set<Move> moves = getAllMoves(getPiece(selectedPiece));

        boolean hasAttackMove = false;

        Coordinate from = selectedPiece;
        Coordinate to = action.getLocation();

        Move selectedMove = null;
        System.out.println(moves);
        for (Move move : moves) {
            System.out.println(move);
            if (move.mustBeTaken()) hasAttackMove = true;

            if (!move.getFrom().getCoordinate().equals(from) || !move.getTo().getCoordinate().equals(to)) {
                continue;
            }
            selectedMove = move;
            break;
        }

        if (selectedMove == null) {
            return new CheckersUIResponse(false, CheckersUIResponse.ResponseType.INVALID_MOVE);
        }

        // If there is a move that has to be taken.
        if (hasAttackMove && !selectedMove.mustBeTaken()) {
            return new CheckersUIResponse(false, CheckersUIResponse.ResponseType.HAVE_TO_ATTACK);

        }
        selectedMove.apply(this);
        processTurn();
        removeSelection();
        return new CheckersUIResponse(true, CheckersUIResponse.ResponseType.SUCCESS);
    }


    private CheckersUIResponse handleSelection(CheckersUIAction action) {
        System.out.println("Calling handle selection.");

        if (selectedPiece != null) return null;

        Coordinate selected = action.getLocation();
        Piece piece = getPiece(selected);
        if (!piece.getPlayer().equals(action.getPlayer()))
            return new CheckersUIResponse(false, CheckersUIResponse.ResponseType.INVALID_SELECTION);

        piece.setSelected(true);
        selectedPiece = piece.getCoordinate();
        return new CheckersUIResponse(true, CheckersUIResponse.ResponseType.SUCCESS);
    }

    private void removeSelection() {
        loopOverPieces(piece -> piece.setSelected(false));
    }

    private void loopOverPieces(Consumer<Piece> consumer) {
        for (CheckersRow row : rows) {
            for (Piece piece : row.getPieces()) {
                consumer.accept(piece);
            }
        }
    }

    private Set<Move> getAllMoves(Piece piece) {

        Set<Move> moves = new TreeSet<>(piece.getPossibleMoves(this));
        return moves;
    }

    private void resetTurn() {
        if (random.nextDouble() < 0.5) {
            currentTurn = player1;
        } else {
            currentTurn = player2;
        }
    }

    private void processTurn() {
        // Essentially change the current turn of the game.
        if (currentTurn.equals(player1)) {
            currentTurn = player2;
        } else {
            currentTurn = player1;
        }
    }

    public boolean isAtBorder(Coordinate coordinate) {
        if (coordinate.getRow() == 0 || coordinate.getRow() == BOARD_SIZE - 1) return true;
        return false;
    }
}
