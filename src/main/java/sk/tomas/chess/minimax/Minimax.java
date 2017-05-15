package sk.tomas.chess.minimax;

import sk.tomas.chess.base.ChessBoard;
import sk.tomas.chess.base.ClonedChessBoard;
import sk.tomas.chess.bo.Move;
import sk.tomas.chess.util.Utils;
import sk.tomas.servant.annotation.Inject;

import java.util.List;

/**
 * Created by tomas on 5/13/17.
 */
public class Minimax implements Runnable {

    @Inject
    private ChessBoard chessBoard;

    private ClonedChessBoard clonedChessBoard;
    private Move best;

    @Override
    public void run() {
        clonedChessBoard = Utils.cloneChessBoard(chessBoard);
        bestMove(4);
        clonedChessBoard.toString();
        System.out.println(clonedChessBoard.evaluate(true));
        chessBoard.movePerformed(best.getFrom(), best.getTo());
    }

    private void bestMove(int deep) {
        //TODO to false upravit na volitelnu premennu
        boolean white = false;
        clonedChessBoard.setWhiteTurn(white);
        List<Move> moves;
        Move bestMove = null;
        int price, best;

        moves = clonedChessBoard.getAllMoves(white);
        best = Integer.MIN_VALUE;

        for (Move move : moves) {
            clonedChessBoard.perform(move.getFrom(), move.getTo());
            price = -minimax(deep - 1, !white);
            clonedChessBoard.revertLastMove();
            if (price > best) {
                best = price;
                bestMove = move;
            }
        }
        this.best = bestMove;
    }

    private int minimax(int deep, boolean white) {
        List<Move> moves;
        int price, best;

        // if (jsemVMatu(p)) return -MAT;
        // if (remiza(p)) return 0;

        if (deep <= 0) {
            return clonedChessBoard.evaluate(white);
        }

        moves = clonedChessBoard.getAllMoves(white);
        best = Integer.MIN_VALUE;

        for (Move move : moves) {
            clonedChessBoard.perform(move.getFrom(), move.getTo());
            price = -minimax(deep - 1, !white);
            clonedChessBoard.revertLastMove();
            if (price > best) {
                best = price;
            }
        }
        return best;
    }
}
