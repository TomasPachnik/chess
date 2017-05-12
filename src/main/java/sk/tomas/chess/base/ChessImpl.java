package sk.tomas.chess.base;

import sk.tomas.chess.bo.ChessBoard;
import sk.tomas.chess.bo.Position;
import sk.tomas.chess.bo.Tile;
import sk.tomas.servant.annotation.Inject;

/**
 * Created by tomas on 5/12/17.
 * implementation of Chess interface
 */
public class ChessImpl implements Chess {

    @Inject
    private ChessBoard chessBoard;

    @Override
    public Tile[][] getBoard() {
        return chessBoard.getSet();
    }

    @Override
    public void click(Position position) {
        chessBoard.click(position);
    }

    @Override
    public void revertMove() {

    }

    @Override
    public void calculateMove() {

    }

    @Override
    public void getBestMove() {

    }

    @Override
    public void newGame() {
        chessBoard.setUp();
    }

    @Override
    public void saveGame() {

    }

    @Override
    public void loadGame() {

    }
}
