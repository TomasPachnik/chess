package sk.tomas.chess.base;

import sk.tomas.chess.bo.Figure;
import sk.tomas.chess.bo.HistoryMove;
import sk.tomas.chess.bo.Move;
import sk.tomas.chess.bo.Position;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tomas on 5/12/17.
 */
public class History {

    private List<HistoryMove> moves;

    public History() {
        this.moves = new LinkedList<>();
    }

    public void add(Position from, Position to, Figure figure) {
        moves.add(new HistoryMove(from, to, figure));
    }

    public Move getLast() {
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

}
