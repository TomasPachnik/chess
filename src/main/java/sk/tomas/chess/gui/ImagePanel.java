package sk.tomas.chess.gui;

import sk.tomas.chess.base.Chess;
import sk.tomas.chess.bo.Figure;
import sk.tomas.chess.bo.Position;
import sk.tomas.chess.util.Colors;
import sk.tomas.chess.util.Utils;
import sk.tomas.servant.annotation.Bean;
import sk.tomas.servant.annotation.Inject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static sk.tomas.chess.constants.Constants.*;

/**
 * Created by tomas on 5/12/17.
 */
@Bean("imagePanel")
public class ImagePanel extends JPanel {

    @Inject
    private Chess chess;
    @Inject
    private Images images;
    private JLabel loading;

    public ImagePanel() {
        super(new BorderLayout());
        createListeners();
    }

    private void createListeners() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Position position = Utils.getPositionFromClick(e.getX(), e.getY());
                chess.click(position);
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

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
        g.setColor(Colors.WHITE);
        g.fillRect(X, Y, tile * 8, tile * 8);
        g.setColor(Colors.BLACK);
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
        g.setColor(Colors.LAST_MOVE);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chess.getBoard()[i + 2][j + 2].isLastMove()) {
                    g.fillRect(X + (tile * (j)), Y + (tile * (i)), tile, tile);
                }
            }
        }
        g.setColor(Colors.SELECTED);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chess.getBoard()[i + 2][j + 2].isSelected()) {
                    g.fillRect(X + (tile * (j)), Y + (tile * (i)), tile, tile);
                }
            }
        }

    }

    public void createLoading() {
        loading = new JLabel(images.getLoading());
        loading.setHorizontalAlignment(JLabel.CENTER);
        loading.setVerticalAlignment(JLabel.CENTER);
        loading.setVerticalTextPosition(JLabel.CENTER);
        loading.setVisible(false);
        add(loading);
    }

    public JLabel getLoading() {
        return loading;
    }
}
