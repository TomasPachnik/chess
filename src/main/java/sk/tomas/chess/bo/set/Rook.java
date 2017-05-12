package sk.tomas.chess.bo.set;

import sk.tomas.chess.bo.ChessBoard;
import sk.tomas.chess.bo.Figure;
import sk.tomas.chess.bo.Move;
import sk.tomas.chess.bo.Position;

import java.awt.*;
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
        return null;
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
