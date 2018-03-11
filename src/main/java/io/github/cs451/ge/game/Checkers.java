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
    private boolean mustTakeMoves;
    private CheckersPlayer winner;


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
            return null;
        }
    }

    public void setPiece(Piece piece) {
        Coordinate coordinate = piece.getCoordinate();
        rows.get(coordinate.getRow()).setPiece(piece);
    }

    public CheckersUIResponse handleAction(CheckersUIAction action) {
        if (winner != null) return new CheckersUIResponse(false, CheckersUIResponse.ResponseType.GAME_OVER);
        // Only process action for the current player.
        if (!currentTurn.equals(action.getPlayer())) {
            return new CheckersUIResponse(false, CheckersUIResponse.ResponseType.INVALID_TURN);
        }

        CheckersUIResponse result = handleSelection(action);

        if (result != null) return result;

        return handleMove(action);
    }

    private CheckersUIResponse handleSelection(CheckersUIAction action) {
        System.out.println("Calling handle selection.");


        if (selectedPiece != null) return null;

        Coordinate selected = action.getLocation();
        Piece piece = getPiece(selected);
        if (piece.getPlayer() == null || !piece.getPlayer().equals(action.getPlayer()))
            return new CheckersUIResponse(false, CheckersUIResponse.ResponseType.INVALID_SELECTION);


        CheckersMoveCollection moves = getAllMoves(action.getPlayer());
        // If there is an attack move available
        if (getAllMoves(action.getPlayer()).hasAnAttackMove() && !getAllMoves(piece).hasAnAttackMove())
            return new CheckersUIResponse(false, CheckersUIResponse.ResponseType.HAVE_TO_ATTACK);


        applySelection(piece);
        return new CheckersUIResponse(true, CheckersUIResponse.ResponseType.SUCCESS);
    }

    private CheckersUIResponse handleMove(CheckersUIAction action) {
        System.out.println("Calling handle move.");
        CheckersPlayer player = action.getPlayer();
        // There is no selected piece.
        if (selectedPiece == null) return null;

        // Player wants to disselect
        if (selectedPiece.equals(action.getLocation())) {
            if (mustTakeMoves) {
                return new CheckersUIResponse(false, CheckersUIResponse.ResponseType.MUST_COMPLETE_JUMPS);
            }
            removeSelection();
            return new CheckersUIResponse(true, CheckersUIResponse.ResponseType.SUCCESS);
        }

        CheckersMoveCollection moves = getAllMoves(player);

        boolean hasAttackMove = moves.hasAnAttackMove();

        Coordinate from = selectedPiece;
        Coordinate to = action.getLocation();

        Move selectedMove = null;
        moves.forEach(m -> System.out.printf("%s - %s%n", m.getClass().getName(), m.toString()));
       // Searching for the move
        for (Move move : moves) {
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
        return handleEndTurn(action, selectedMove);

    }

    private CheckersUIResponse handleEndTurn(CheckersUIAction action, Move selectedMove) {
        removeSelection();

        Piece newPiece = getPiece(action.getLocation());
        CheckersMoveCollection moves = getAllMoves(newPiece);
        if (selectedMove.mustBeTaken() && moves.hasAnAttackMove()) {
            applySelection(newPiece);
            mustTakeMoves = true;
        } else {
            processTurn();
            mustTakeMoves = false;
        }
        checkEndGame();
        return new CheckersUIResponse(true, CheckersUIResponse.ResponseType.SUCCESS);
    }

    /**
     * Removes the "Selected" status from a piece.
     */
    private void removeSelection() {
        selectedPiece = null;
        loopOverPieces(piece -> piece.setSelected(false));
    }

    /**
     * Adds the "Selected" status to a piece.
     *
     * @param piece
     */
    private void applySelection(Piece piece) {
        selectedPiece = piece.getCoordinate();
        piece.setSelected(true);
    }

    private void loopOverPieces(Consumer<Piece> consumer) {
        for (CheckersRow row : rows) {
            for (Piece piece : row.getPieces()) {
                consumer.accept(piece);
            }
        }
    }

    private void checkEndGame() {
        CheckersMoveCollection p1Moves = getAllMoves(player1);
        if (p1Moves.isEmpty()) {
            winner = player2;
            return;
        }
        CheckersMoveCollection p2Moves = getAllMoves(player2);

        if (p2Moves.isEmpty()) {
            winner = player1;
            return;
        }
    }

    private CheckersMoveCollection getAllMoves(CheckersPlayer player) {
        CheckersMoveCollection moveCollection = new CheckersMoveCollection();
        loopOverPieces(piece -> {
            if (piece.getPlayer() == null || !piece.getPlayer().equals(player)) {
                return;
            }
            moveCollection.addAll(piece.getPossibleMoves(this));
        });

        return moveCollection;
    }

    private CheckersMoveCollection getAllMoves(Piece piece) {
        return piece.getPossibleMoves(this);
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
        return coordinate.getRow() == 0 || coordinate.getRow() == BOARD_SIZE - 1;
    }
}
