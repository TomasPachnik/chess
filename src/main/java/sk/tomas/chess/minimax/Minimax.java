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

    private final int BIG_NUMBER = 10000;
    @Inject
    private ChessBoard chessBoard;
    private ClonedChessBoard clonedChessBoard;
    private Move best;

    @Override
    public void run() {
        clonedChessBoard = Utils.cloneChessBoard(chessBoard);
        bestMove(6);
        chessBoard.movePerformed(best.getFrom(), best.getTo());
    }

    private void bestMove(int deep) {
        //TODO to false upravit na volitelnu premennu
        boolean white = false;
        clonedChessBoard.setWhiteTurn(white);
        List<Move> moves;
        Move bestMove = null;
        int price, alpha;

        moves = clonedChessBoard.getAllMoves(white);
        alpha = Integer.MIN_VALUE;

        for (Move move : moves) {
            clonedChessBoard.perform(move.getFrom(), move.getTo());
            price = -alphaBeta(deep - 1, !white, Integer.MIN_VALUE, blizKMatu(-alpha));
            price = dalOdMatu(price);
            clonedChessBoard.revertLastMove();
            if (price > alpha) {
                alpha = price;
                bestMove = move;
            }
        }
        this.best = bestMove;
    }

    private int alphaBeta(int deep, boolean white, int alpha, int beta) {
        List<Move> moves;
        int price;

        if (deep <= 0) {
            return clonedChessBoard.evaluate(white);
        }

        moves = clonedChessBoard.getAllMoves(white);

        for (Move move : moves) {
            clonedChessBoard.perform(move.getFrom(), move.getTo());
            price = -alphaBeta(deep - 1, !white, blizKMatu(-beta), blizKMatu(-alpha));
            price = dalOdMatu(price);
            clonedChessBoard.revertLastMove();
            if (price > alpha) {
                alpha = price;
                if (price >= beta) {
                    return beta;
                }
            }
        }
        return alpha;
    }

    private int blizKMatu(int price) {
        if (price > BIG_NUMBER) return price + 10;
        if (price < -BIG_NUMBER) return price - 10;
        return price;
    }

    private int dalOdMatu(int price) {
        if (price > BIG_NUMBER) return price - 10;
        if (price < -BIG_NUMBER) return price + 10;
        return price;
    }
}
