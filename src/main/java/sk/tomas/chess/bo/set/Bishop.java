package sk.tomas.chess.bo.set;

import sk.tomas.chess.base.ChessBoard;
import sk.tomas.chess.bo.Figure;
import sk.tomas.chess.bo.Move;
import sk.tomas.chess.bo.Position;
import sk.tomas.chess.constants.Constants;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tomas on 5/12/17.
 */
public class Bishop extends Figure {

    private int[][] blackPositions = new int[][]{
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100},
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100},
            {-100, -100, -5, -4, -3, -2, -2, -3, -4, -5, -100, -100},
            {-100, -100, -5, 6, -2, 5, 5, -2, 6, -5, -100, -100},
            {-100, -100, 0, 0, 1, 5, 5, 1, 0, 0, -100, -100},
            {-100, -100, 0, 2, 5, 5, 5, 5, 5, 0, -100, -100},
            {-100, -100, 0, 5, 6, 8, 8, 6, 5, 0, -100, -100},
            {-100, -100, 0, 0, 0, 0, 0, 0, 0, 0, -100, -100},
            {-100, -100, 0, 0, 0, 0, 0, 0, 0, 0, -100, -100},
            {-100, -100, -5, 0, 0, 0, 0, 0, 0, -5 - 100, -100},
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100},
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100}};

    private int[][] whitePositions = new int[][]{
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100},
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100},
            {-100, -100, -5, 0, 0, 0, 0, 0, 0, -5 - 100, -100},
            {-100, -100, 0, 0, 0, 0, 0, 0, 0, 0, -100, -100},
            {-100, -100, 0, 0, 0, 0, 0, 0, 0, 0, -100, -100},
            {-100, -100, 0, 5, 6, 8, 8, 6, 5, 0, -100, -100},
            {-100, -100, 0, 2, 5, 5, 5, 5, 5, 0, -100, -100},
            {-100, -100, 0, 0, 1, 5, 5, 1, 0, 0, -100, -100},
            {-100, -100, -5, 6, -2, 5, 5, -2, 6, -5, -100, -100},
            {-100, -100, -5, -4, -3, -2, -2, -3, -4, -5, -100, -100},
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100},
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100}};

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public Figure clone() {
        return new Bishop(getColor());
    }

    @Override
    public List<Move> getAvailableMoves(ChessBoard chessBoard, Position position) {
        List<Move> resultList = new LinkedList<>();
        for (int i = 1; i < 8; i++) {
            if ((position.getX() - i >= Constants.startTile) && (position.getY() - i >= Constants.startTile)) {
                if (calc(chessBoard, position, resultList, position.getX() - i, position.getY() - i)) {
                    break;
                }
            } else {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            if ((position.getY() + i >= Constants.startTile) && (position.getY() - i >= Constants.startTile)) {
                if (calc(chessBoard, position, resultList, position.getX() + i, position.getY() - i)) {
                    break;
                }
            } else {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            if ((position.getX() - i >= Constants.startTile) && (position.getY() + i >= Constants.startTile)) {
                if (calc(chessBoard, position, resultList, position.getX() - i, position.getY() + i)) {
                    break;
                }
            } else {
                break;
            }
        }

        for (int i = 1; i < 8; i++) {
            if ((position.getX() + i >= Constants.startTile) && (position.getY() + i >= Constants.startTile)) {
                if (calc(chessBoard, position, resultList, position.getX() + i, position.getY() + i)) {
                    break;
                }
            } else {
                break;
            }
        }

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
