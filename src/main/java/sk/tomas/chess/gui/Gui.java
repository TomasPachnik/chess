package sk.tomas.chess.gui;

/**
 * Created by tomas on 5/12/17.
 * main gui interface communicating with application
 */
public interface Gui {

    void repaint();

    void showMessage(String message);

    void showWaitingIcon();

    void hideWaitingIcon();

}
