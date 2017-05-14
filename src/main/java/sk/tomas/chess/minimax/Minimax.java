package sk.tomas.chess.minimax;

import sk.tomas.chess.base.ChessBoard;
import sk.tomas.chess.bo.Move;
import sk.tomas.chess.bo.Position;
import sk.tomas.chess.util.Utils;
import sk.tomas.servant.annotation.Inject;

import java.util.List;

/**
 * Created by tomas on 5/13/17.
 */
public class Minimax implements Runnable {

    @Inject
    private ChessBoard chessBoard;

    private Move best;

    @Override
    public void run() {
        bestMove(2);
        System.out.println(best);
        chessBoard.movePerformed(best.getFrom(), best.getTo());
    }

    private void bestMove(int deep) {
        //TODO to false upravit
        boolean white = false;
        List<Move> moves;
        Move bestMove = null;
        int price, best;

        moves = chessBoard.getAllMoves(white);
        best = Integer.MIN_VALUE;

        for (Move move : moves) {
            chessBoard.perform(move.getFrom(), move.getTo());
            price = -minimax(deep - 1, !white);
            chessBoard.revertLastMove();
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

        if (deep <= 0) return chessBoard.evaluate(white);

        moves = chessBoard.getAllMoves(white);
        best = Integer.MIN_VALUE;

        for (Move move : moves) {
            chessBoard.perform(move.getFrom(), move.getTo());
            price = -minimax(deep - 1, !white);
            chessBoard.revertLastMove();
            if (price > best) {
                best = price;
            }
        }

  /* Pokud je výsledná hodnota velmi velká nebo velmi malá, znamená,
     že dávám nebo dostávám mat. V tom případě je třeba cenu upravit,
     aby se např. z "bílý dává 3. půltahem mat" stalo "černý dostává
     4. půltahem mat", neboť se návratem do volající funkce posunu
     o jeden půltah. Toto je nezbytné, jinak by program nepreferoval
     např. mat 1. tahem před matem 2. tahem.

        if (nejlepsi > MNOHO) nejlepsi--;
        if (nejlepsi < -MNOHO) nejlepsi++;
*/
        return best;

    }
}
