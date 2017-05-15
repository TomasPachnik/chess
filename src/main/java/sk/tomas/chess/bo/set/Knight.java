package sk.tomas.chess.bo.set;

import sk.tomas.chess.base.ChessBoard;
import sk.tomas.chess.bo.Figure;
import sk.tomas.chess.bo.Move;
import sk.tomas.chess.bo.Position;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tomas on 5/12/17.
 */
public class Knight extends Figure {

    private int[][] blackPositions = new int[][]{
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100},
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100},
            {-100, -100, -10, -4, -3, -2, -2, -3, -4, -10, -100, -100},
            {-100, -100, -5, -5, -3, 0, 0, -3, -5, -5, -100, -100},
            {-100, -100, -3, 0, 3, 5, 5, 3, 0, -3, -100, -100},
            {-100, -100, -2, 5, 5, 7, 7, 5, 5, -2, -100, -100},
            {-100, -100, 0, 5, 6, 8, 8, 6, 5, 0, -100, -100},
            {-100, -100, 5, 6, 7, 8, 8, 7, 6, 5, -100, -100},
            {-100, -100, 0, 5, 6, 8, 8, 6, 5, 0, -100, -100},
            {-100, -100, -10, -5, -2, 0, 0, -2, -5, -10, -100, -100},
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100},
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100}};

    private int[][] whitePositions = new int[][]{
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100},
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100},
            {-100, -100, -10, -5, -2, 0, 0, -2, -5, -10, -100, -100},
            {-100, -100, 0, 5, 6, 8, 8, 6, 5, 0, -100},
            {-100, -100, 5, 6, 7, 8, 8, 7, 6, 5, -100, -100},
            {-100, -100, 0, 5, 6, 8, 8, 6, 5, 0, -100, -100},
            {-100, -100, -2, 5, 5, 7, 7, 5, 5, -2, -100, -100},
            {-100, -100, -3, 0, 3, 5, 5, 3, 0, -3, -100, -100},
            {-100, -100, -5, -5, -3, 0, 0, -3, -5, -5, -100, -100},
            {-100, -100, -10, -4, -3, -2, -2, -3, -4, -10, -100, -100},
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100},
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100}};

    public Knight(Color color) {
        super(color);
    }

    @Override
    public Figure clone() {
        return new Knight(getColor());
    }

    @Override
    public List<Move> getAvailableMoves(ChessBoard chessBoard, Position position) {
        List<Move> resultList = new LinkedList<>();

        calc2(chessBoard, position, resultList, -1, -2);
        calc2(chessBoard, position, resultList, -2, -1);
        calc2(chessBoard, position, resultList, -2, 1);
        calc2(chessBoard, position, resultList, -1, 2);
        calc2(chessBoard, position, resultList, 1, 2);
        calc2(chessBoard, position, resultList, 2, 1);
        calc2(chessBoard, position, resultList, 2, -1);
        calc2(chessBoard, position, resultList, 1, -2);

        return resultList;
    }

    @Override
    public int calculatePositionValue(Position position, boolean white) {
        if (white) {
            return whitePositions[position.getX()][position.getY()];
        } else {
            return blackPositions[position.getX()][position.getY()];
        }
    }

    @Override
    public int getDeletedValue() {
        return -150;
    }
}
