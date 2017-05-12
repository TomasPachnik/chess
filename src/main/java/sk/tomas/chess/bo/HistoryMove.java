package sk.tomas.chess.bo;

/**
 * Created by tomas on 5/12/17.
 */
public class HistoryMove extends Move {

    private Figure fallen;

    public HistoryMove(Position from, Position to, Figure figure) {
        super(from, to);
        this.fallen = figure;
    }

    public Figure getFallen() {
        return fallen;
    }
}
