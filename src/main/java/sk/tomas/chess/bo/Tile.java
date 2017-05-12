package sk.tomas.chess.bo;

/**
 * Created by tomas on 5/12/17.
 */
public class Tile {

    private Position position;
    private Figure figure;
    private boolean selected;
    private boolean lastMove;

    public Tile(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isLastMove() {
        return lastMove;
    }

    public void setLastMove(boolean lastMove) {
        this.lastMove = lastMove;
    }
}
