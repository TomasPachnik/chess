package sk.tomas.chess.configuration;

/**
 * Created by tomas on 5/12/17.
 * main application interface communicating with gui
 */
public interface Chess {

    void performMove();

    void revertMove();

    void calculateMove();

    void getBestMove();

    void newGame();

    void saveGame();

    void loadGame();
}
