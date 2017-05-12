package sk.tomas.chess.bo.set;

import sk.tomas.chess.bo.ChessBoard;
import sk.tomas.chess.bo.Figure;
import sk.tomas.chess.bo.Move;
import sk.tomas.chess.bo.Position;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tomas on 5/12/17.
 */
public class King extends Figure {

    public King(Color color) {
        super(color);
    }

    @Override
    public List<Move> getAvailableMoves(ChessBoard chessBoard, Position position) {
        List<Move> resultList = new LinkedList<>();

        calc2(chessBoard, position, resultList, -1, -1);
        calc2(chessBoard, position, resultList, -1, 0);
        calc2(chessBoard, position, resultList, -1, 1);
        calc2(chessBoard, position, resultList, 0, -1);
        calc2(chessBoard, position, resultList, 0, 1);
        calc2(chessBoard, position, resultList, 1, -1);
        calc2(chessBoard, position, resultList, 1, 0);
        calc2(chessBoard, position, resultList, 1, 1);

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
