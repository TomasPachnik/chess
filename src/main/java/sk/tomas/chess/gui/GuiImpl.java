package sk.tomas.chess.gui;

import sk.tomas.servant.annotation.Bean;
import sk.tomas.servant.annotation.Inject;
import sk.tomas.servant.annotation.PostInit;

import javax.swing.*;

/**
 * Created by tomas on 5/12/17.
 * implements Gui interface
 */
@Bean("gui")
public class GuiImpl implements Gui {

    @Inject
    private MainWindow mainWindow;
    @Inject
    private ImagePanel imagePanel;

    @Override
    @PostInit
    public void init() {
        mainWindow.init();
    }

    @Override
    public void repaint() {
        imagePanel.repaint();
    }

    @Override
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void showWaitingIcon() {
        imagePanel.setShowLoading(true);
    }

    @Override
    public void hideWaitingIcon() {
        imagePanel.setShowLoading(false);
    }
}
