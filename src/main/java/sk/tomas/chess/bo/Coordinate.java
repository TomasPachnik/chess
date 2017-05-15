package sk.tomas.chess.bo;

/**
 * Created by Tomas Pachnik on 15-May-17.
 */
public class Coordinate {

    private String x;
    private String y;

    public Coordinate(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    @Override
    public String toString() {
        return y + x;
    }

}
