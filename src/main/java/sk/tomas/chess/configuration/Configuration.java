package sk.tomas.chess.configuration;

import sk.tomas.chess.base.Chess;
import sk.tomas.chess.base.ChessImpl;
import sk.tomas.chess.base.ChessBoard;
import sk.tomas.chess.base.History;
import sk.tomas.chess.gui.*;
import sk.tomas.chess.minimax.Minimax;
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

    @Bean
    public Images images() {
        return new Images();
    }

    @Bean
    public ChessBoard chessBoard() {
        return new ChessBoard();
    }

    @Bean
    public History history() {
        return new History();
    }

    @Bean
    public Minimax minimax() {
        return new Minimax();
    }

}
