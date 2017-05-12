package sk.tomas.chess.configuration;

import sk.tomas.chess.base.Chess;
import sk.tomas.chess.base.ChessImpl;
import sk.tomas.chess.gui.Gui;
import sk.tomas.chess.gui.GuiImpl;
import sk.tomas.chess.gui.ImagePanel;
import sk.tomas.chess.gui.MainWindow;
import sk.tomas.servant.annotation.Bean;
import sk.tomas.servant.annotation.Config;

/**
 * Created by Tomas Pachnik on 12-May-17.
 */

@Config
public class Configuration {

    @Bean
    public Chess chess() {
        return new ChessImpl();
    }

    @Bean
    public Gui gui() {
        return new GuiImpl();
    }

    @Bean
    public MainWindow mainWindow() {
        return new MainWindow();
    }

    @Bean
    public ImagePanel imagePanel() {
        return new ImagePanel();
    }

}
