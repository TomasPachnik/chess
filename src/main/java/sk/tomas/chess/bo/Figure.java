package sk.tomas.chess.bo;

import java.awt.*;

/**
 * Created by tomas on 5/12/17.
 */
public abstract class Figure {

    private Color color;

    public Figure(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
