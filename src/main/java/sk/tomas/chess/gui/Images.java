package sk.tomas.chess.gui;

import sk.tomas.chess.bo.Figure;
import sk.tomas.servant.annotation.Bean;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static sk.tomas.chess.constants.Constants.IMAGES;
import static sk.tomas.chess.constants.Constants.IMAGE_PATH;

/**
 * Created by tomas on 5/12/17.
 */
@Bean
public class Images {

    private BufferedImage blackPawn;
    private BufferedImage blackRook;
    private BufferedImage blackKnight;
    private BufferedImage blackBishop;
    private BufferedImage blackQueen;
    private BufferedImage blackKing;
    private BufferedImage whitePawn;
    private BufferedImage whiteRook;
    private BufferedImage whiteKnight;
    private BufferedImage whiteBishop;
    private BufferedImage whiteQueen;
    private BufferedImage whiteKing;
    private Image loading;

    public Images() {
        try {
            blackPawn = ImageIO.read(getClass().getResourceAsStream(IMAGE_PATH + "Chess_pdt60.png"));
            blackRook = ImageIO.read(getClass().getResourceAsStream(IMAGE_PATH + "Chess_rdt60.png"));
            blackKnight = ImageIO.read(getClass().getResourceAsStream(IMAGE_PATH + "Chess_ndt60.png"));
            blackBishop = ImageIO.read(getClass().getResourceAsStream(IMAGE_PATH + "Chess_bdt60.png"));
            blackQueen = ImageIO.read(getClass().getResourceAsStream(IMAGE_PATH + "Chess_qdt60.png"));
            blackKing = ImageIO.read(getClass().getResourceAsStream(IMAGE_PATH + "Chess_kdt60.png"));
            whitePawn = ImageIO.read(getClass().getResourceAsStream(IMAGE_PATH + "Chess_plt60.png"));
            whiteRook = ImageIO.read(getClass().getResourceAsStream(IMAGE_PATH + "Chess_rlt60.png"));
            whiteKnight = ImageIO.read(getClass().getResourceAsStream(IMAGE_PATH + "Chess_nlt60.png"));
            whiteBishop = ImageIO.read(getClass().getResourceAsStream(IMAGE_PATH + "Chess_blt60.png"));
            whiteQueen = ImageIO.read(getClass().getResourceAsStream(IMAGE_PATH + "Chess_qlt60.png"));
            whiteKing = ImageIO.read(getClass().getResourceAsStream(IMAGE_PATH + "Chess_klt60.png"));

            URL url = this.getClass().getClassLoader().getResource(IMAGES + "loading.gif");
            loading = Toolkit.getDefaultToolkit().createImage(url);
        } catch (IOException e) {
            //TODO log error
            System.err.println(e);
        }
    }

    public BufferedImage getImage(Figure figure) {
        if (figure == null) {
            return null;
        }

        if (figure.getColor().equals(Color.BLACK)) {
            switch (figure.getClass().getSimpleName()) {
                case "Rook":
                    return blackRook;
                case "Knight":
                    return blackKnight;
                case "Bishop":
                    return blackBishop;
                case "Queen":
                    return blackQueen;
                case "King":
                    return blackKing;
                default:
                    return blackPawn;
            }
        } else {
            switch (figure.getClass().getSimpleName()) {
                case "Rook":
                    return whiteRook;
                case "Knight":
                    return whiteKnight;
                case "Bishop":
                    return whiteBishop;
                case "Queen":
                    return whiteQueen;
                case "King":
                    return whiteKing;
                default:
                    return whitePawn;
            }
        }
    }

    public Image getLoading() {
        return loading;
    }
}
