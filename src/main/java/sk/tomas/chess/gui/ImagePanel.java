package sk.tomas.chess.gui;


import javax.swing.*;
import java.awt.*;

import static sk.tomas.chess.constants.Constants.X;
import static sk.tomas.chess.constants.Constants.Y;
import static sk.tomas.chess.constants.Constants.tile;

/**
 * Created by tomas on 5/12/17.
 */
public class ImagePanel extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawChessBoard(g);
        //drawFigures(g);
    }
/*
    private void drawFigures(Graphics g) {
        for (int i = Constants.startTile; i < Constants.endTile; i++) {
            for (int j = Constants.startTile; j < Constants.endTile; j++) {
                Figure piece = container.getBoard()[i][j].getFigure();
                if (piece != null) {
                    if (piece.getColor().equals(Colors.BLACK)) {
                        piece.draw(g, i, j, images, Colors.BLACK);
                    } else {
                        piece.draw(g, i, j, images, Colors.WHITE);
                    }
                }
            }
        }
    }
*/

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
