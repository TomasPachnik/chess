package sk.tomas.chess.gui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static sk.tomas.chess.constants.Constants.IMAGE_PATH;

/**
 * Created by tomas on 5/12/17.
 */
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

    public Images() {
        try {
            blackPawn = ImageIO.read(new File(IMAGE_PATH + "Chess_pdt60.png"));
            blackRook = ImageIO.read(new File(IMAGE_PATH + "Chess_rdt60.png"));
            blackKnight = ImageIO.read(new File(IMAGE_PATH + "Chess_ndt60.png"));
            blackBishop = ImageIO.read(new File(IMAGE_PATH + "Chess_bdt60.png"));
            blackQueen = ImageIO.read(new File(IMAGE_PATH + "Chess_qdt60.png"));
            blackKing = ImageIO.read(new File(IMAGE_PATH + "Chess_kdt60.png"));
            whitePawn = ImageIO.read(new File(IMAGE_PATH + "Chess_plt60.png"));
            whiteRook = ImageIO.read(new File(IMAGE_PATH + "Chess_rlt60.png"));
            whiteKnight = ImageIO.read(new File(IMAGE_PATH + "Chess_nlt60.png"));
            whiteBishop = ImageIO.read(new File(IMAGE_PATH + "Chess_blt60.png"));
            whiteQueen = ImageIO.read(new File(IMAGE_PATH + "Chess_qlt60.png"));
            whiteKing = ImageIO.read(new File(IMAGE_PATH + "Chess_klt60.png"));
        } catch (IOException e) {
            //TODO log error
            System.err.println(e);
        }
    }

    public BufferedImage getBlackPawn() {
        return blackPawn;
    }

    public BufferedImage getBlackRook() {
        return blackRook;
    }

    public BufferedImage getBlackKnight() {
        return blackKnight;
    }

    public BufferedImage getBlackBishop() {
        return blackBishop;
    }

    public BufferedImage getBlackQueen() {
        return blackQueen;
    }

    public BufferedImage getBlackKing() {
        return blackKing;
    }

    public BufferedImage getWhitePawn() {
        return whitePawn;
    }

    public BufferedImage getWhiteRook() {
        return whiteRook;
    }

    public BufferedImage getWhiteKnight() {
        return whiteKnight;
    }

    public BufferedImage getWhiteBishop() {
        return whiteBishop;
    }

    public BufferedImage getWhiteQueen() {
        return whiteQueen;
    }

    public BufferedImage getWhiteKing() {
        return whiteKing;
    }
}
