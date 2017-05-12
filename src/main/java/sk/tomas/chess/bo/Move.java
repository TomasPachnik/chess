package sk.tomas.chess.bo;

/**
 * Created by tomas on 5/12/17.
 */
public class Move {

    private Position from;
    private Position to;

    public Move(Position from, Position to) {
        this.from = new Position(from.getX(), from.getY());
        this.to = new Position(to.getX(), to.getY());
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }
}
