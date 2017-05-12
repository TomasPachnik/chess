package sk.tomas.chess.bo;

import sk.tomas.chess.bo.set.*;

import java.awt.*;

/**
 * Created by tomas on 5/12/17.
 */
public class ChessBoard {

    private Tile[][] set;

    public ChessBoard() {
        setUpTiles();
        setUpPieces();
    }

    private void setUpTiles() {
        set = new Tile[12][12];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                set[i][j] = new Tile(new Position(i, j));
            }
        }
    }

    private void setUpPieces() {
        set[2][2].setFigure(new Rook(Color.BLACK));
        set[2][3].setFigure(new Knight(Color.BLACK));
        set[2][4].setFigure(new Bishop(Color.BLACK));
        set[2][5].setFigure(new Queen(Color.BLACK));
        set[2][6].setFigure(new King(Color.BLACK));
        set[2][7].setFigure(new Bishop(Color.BLACK));
        set[2][8].setFigure(new Knight(Color.BLACK));
        set[2][9].setFigure(new Rook(Color.BLACK));

        for (int i = 0; i < 12; i++) {
            set[3][i].setFigure(new Pawn(Color.BLACK));
        }

        set[9][2].setFigure(new Rook(Color.WHITE));
        set[9][3].setFigure(new Knight(Color.WHITE));
        set[9][4].setFigure(new Bishop(Color.WHITE));
        set[9][5].setFigure(new Queen(Color.WHITE));
        set[9][6].setFigure(new King(Color.WHITE));
        set[9][7].setFigure(new Bishop(Color.WHITE));
        set[9][8].setFigure(new Knight(Color.WHITE));
        set[9][9].setFigure(new Rook(Color.WHITE));

        for (int i = 0; i < 12; i++) {
            set[8][i].setFigure(new Pawn(Color.WHITE));
        }
    }

}
