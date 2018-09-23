package sk.tomas.chess;

import org.junit.Assert;
import org.junit.Test;
import sk.tomas.chess.base.ChessBoard;
import sk.tomas.chess.bo.Position;
import sk.tomas.servant.core.Servant;
import sk.tomas.servant.exception.ServantException;

/**
 * Created by Tomas Pachnik on 15-May-17.
 */

public class EvaluateTest {

    @Test
    public void evaluateTest() throws ServantException {
        Servant.addConfiguration(Main.class);
        ChessBoard chessBoard = (ChessBoard) Servant.getByName("chessBoard");
        chessBoard.setUp();
        Assert.assertTrue(chessBoard.evaluate(true) == 0);
        chessBoard.perform(new Position(9, 3), new Position(7, 4));
        Assert.assertTrue(chessBoard.evaluate(true) == 7);
        chessBoard.perform(new Position(7, 4), new Position(5, 5));
        Assert.assertTrue(chessBoard.evaluate(true) == 12);
        chessBoard.perform(new Position(5, 5), new Position(3, 4));
        Assert.assertTrue(chessBoard.evaluate(true) == 59);
    }

}
