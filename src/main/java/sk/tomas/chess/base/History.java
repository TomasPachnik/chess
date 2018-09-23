package sk.tomas.chess.base;

import sk.tomas.chess.bo.Figure;
import sk.tomas.chess.bo.HistoryMove;
import sk.tomas.chess.bo.Move;
import sk.tomas.chess.bo.Position;
import sk.tomas.servant.annotation.Bean;
import sk.tomas.servant.annotation.Inject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tomas on 5/12/17.
 */
@Bean
public class History {

    @Inject
    protected ChessBoard chessBoard;

    private List<HistoryMove> moves;

    public History() {
        this.moves = new LinkedList<>();
    }

    public void add(Position from, Position to, Figure figure) {
        moves.add(new HistoryMove(from, to, figure));
    }

    public HistoryMove getLast() {
        if (!moves.isEmpty()) {
            return moves.get(moves.size() - 1);
        }
        return null;
    }

    public void removeLast() {
        if (!moves.isEmpty()) {
            moves.remove(moves.get(moves.size() - 1));
        }
    }

    public int getFallen(boolean white) {
        int result = 0;
        for (HistoryMove move : moves) {
            if (move.getFallen() != null && move.getFallen().getColor().equals(chessBoard.getColor(white))) {
                result += move.getFallen().getDeletedValue();
            }
        }
        return result;
    }
}
