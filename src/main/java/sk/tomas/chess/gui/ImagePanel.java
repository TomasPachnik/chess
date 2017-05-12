package sk.tomas.chess.gui;


import sk.tomas.chess.base.Chess;
import sk.tomas.chess.bo.Figure;
import sk.tomas.chess.constants.Constants;
import sk.tomas.servant.annotation.Inject;

import javax.swing.*;
import java.awt.*;

import static sk.tomas.chess.constants.Constants.*;
import static sk.tomas.chess.constants.Constants.X;
import static sk.tomas.chess.constants.Constants.Y;
import static sk.tomas.chess.constants.Constants.tile;

/**
 * Created by tomas on 5/12/17.
 */
public class ImagePanel extends JPanel {

    @Inject
    private Chess chess;
    @Inject
    private Images images;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawChessBoard(g);
        drawFigures(g);
    }

    private void drawFigures(Graphics g) {
        for (int i = startTile; i < endTile; i++) {
            for (int j = startTile; j < endTile; j++) {
                Figure figure = chess.getBoard()[i][j].getFigure();
                if (figure != null) {
                    g.drawImage(images.getImage(figure), X + (tile * (j - offset)), Y + (tile * (i - offset)), null);
                }
            }
        }
    }


    private void drawChessBoard(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(X, Y, tile * 8, tile * 8);
        g.setColor(Color.BLACK);
        boolean paint = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (paint) {
                    g.fillRect(X + (tile * i), Y + (tile * j), tile, tile);
                }
                paint = !paint;
            }
            paint = !paint;
        }
    }

}
