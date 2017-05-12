package sk.tomas.chess.bo;

import java.util.List;

/**
 * Created by tomas on 5/12/17.
 * base figure sinterface
 */
public interface Component {

    List<Move> getAvailableMoves(ChessBoard chessBoard, Position position);

    int calculatePositionValue(Position position);

    int getDeletedValue();

}
