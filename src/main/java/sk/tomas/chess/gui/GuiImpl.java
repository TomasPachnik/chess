package sk.tomas.chess.gui;

import sk.tomas.chess.base.Chess;
import sk.tomas.servant.annotation.Inject;

/**
 * Created by tomas on 5/12/17.
 * implements Gui interface
 */
public class GuiImpl implements Gui {

    @Inject
    private MainWindow mainWindow;
    @Inject
    private Chess chess;

    @Override
    public void init() {
        mainWindow.init();
    }

    @Override
    public void repaint() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showWaitingIcon() {

    }

    @Override
    public void hideWaitingIcon() {

    }
}
