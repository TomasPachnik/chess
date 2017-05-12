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

    public Knight(Color color) {
        super(color);
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
    public int calculatePositionValue(Position position) {
        return 0;
    }

    @Override
    public int getDeletedValue() {
        return 0;
    }
}
