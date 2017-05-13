package sk.tomas.chess.minimax;

import sk.tomas.chess.base.ChessBoard;
import sk.tomas.chess.bo.Move;
import sk.tomas.chess.bo.Position;
import sk.tomas.servant.annotation.Inject;

/**
 * Created by tomas on 5/13/17.
 */
public class Minimax implements Runnable {

    @Inject
    private ChessBoard chessBoard;

    private Move best;

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        chessBoard.movePerformed(new Position(3, 4), new Position(5, 4));
    }

}
