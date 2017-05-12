package sk.tomas.chess.base;

import sk.tomas.chess.bo.Tile;

/**
 * Created by tomas on 5/12/17.
 * main application interface communicating with gui
 */
public interface Chess {

    Tile[][] getBoard();

    void performMove();

    void revertMove();

    void calculateMove();

    void getBestMove();

    void newGame();

    void saveGame();

    void loadGame();
}
