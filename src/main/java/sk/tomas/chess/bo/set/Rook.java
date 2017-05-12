package sk.tomas.chess.bo.set;

import sk.tomas.chess.bo.ChessBoard;
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
public class Rook extends Figure {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public List<Move> getAvailableMoves(ChessBoard chessBoard, Position position) {
        List<Move> resultList = new LinkedList<>();
        for (int i = position.getX() + 1; i < Constants.endTile; i++) {
            if (calc(chessBoard, position, resultList, i, position.getY())) {
                break;
            }
        }

        for (int i = position.getX() - 1; i >= Constants.startTile; i--) {
            if (calc(chessBoard, position, resultList, i, position.getY())) {
                break;
            }
        }

        for (int j = position.getY() + 1; j < Constants.endTile; j++) {
            if (calc(chessBoard, position, resultList, position.getX(), j)) {
                break;
            }
        }

        for (int j = position.getY() - 1; j >= Constants.startTile; j--) {
            if (calc(chessBoard, position, resultList, position.getX(), j)) {
                break;
            }
        }
        return resultList;
    }

    @Override
    public int calculatePositionValue(Position position) {
        return 0;
    }

    @Override
    public int getDeletedValue() {
        return 0;
    }
}
