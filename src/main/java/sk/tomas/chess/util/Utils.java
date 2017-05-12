package sk.tomas.chess.util;

import sk.tomas.chess.bo.Position;
import sk.tomas.chess.constants.Constants;

import static sk.tomas.chess.constants.Constants.endTile;
import static sk.tomas.chess.constants.Constants.offset;
import static sk.tomas.chess.constants.Constants.startTile;

/**
 * Created by tomas on 5/12/17.
 */
public class Utils {

    public static Position getPositionFromClick(int x, int y) {
        int positionY = ((x - Constants.X) / Constants.tile) + offset;
        int positionX = ((y - Constants.Y) / Constants.tile) + offset;
        if (positionX < startTile || positionX >= endTile || positionY < startTile || positionY >= endTile) {
            return null;
        }
        return new Position(positionX, positionY);
    }

}
