package sk.tomas.chess.bo;

import sk.tomas.chess.constants.Constants;

import java.awt.*;
import java.util.List;

/**
 * Created by tomas on 5/12/17.
 */
public abstract class Figure implements Component {

    private Color color;

    public Figure(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    protected boolean calc(ChessBoard chb, Position position, List<Move> resultList, int i, int j) {
        if ((i < Constants.endTile) && (j < Constants.endTile)) {
            if (chb.getSet()[i][j].getFigure() == null) {
                resultList.add(new Move(position, chb.getSet()[i][j].getPosition()));
            } else {
                Color fromColor = chb.getSet()[position.getX()][position.getY()].getFigure().getColor();
                Color toColor = chb.getSet()[i][j].getFigure().getColor();
                if (!fromColor.equals(toColor)) {
                    resultList.add(new Move(position, chb.getSet()[i][j].getPosition()));
                    return true;
                } else {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    protected void calc2(ChessBoard chb, Position position, List<Move> resultList, int i, int j) {
        if ((position.getX() + i < Constants.endTile) && (position.getX() + i >= Constants.startTile) && (position.getY() + j < Constants.endTile) && (position.getY() + j >= Constants.startTile)) {
            if (((chb.getSet()[position.getX() + i][position.getY() + j].getFigure() == null)) || !((chb.getSet()[position.getX() + i][position.getY() + j].getFigure().getColor().equals(chb.getAtPosition(position).getFigure().getColor())))) {
                resultList.add(new Move(position, new Position(position.getX() + i, position.getY() + j)));
            }
        }
    }

}
