package sk.tomas.chess.bo;

import sk.tomas.chess.base.ChessBoard;

import java.util.List;

/**
 * Created by tomas on 5/12/17.
 * base figure sinterface
 */
public interface Component {

    List<Move> getAvailableMoves(ChessBoard chessBoard, Position position);

    int calculatePositionValue(Position position, boolean white);

    int getDeletedValue();

}
