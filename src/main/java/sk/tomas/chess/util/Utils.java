package sk.tomas.chess.util;

import sk.tomas.chess.base.ChessBoard;
import sk.tomas.chess.base.ClonedChessBoard;
import sk.tomas.chess.base.ClonedHistory;
import sk.tomas.chess.base.History;
import sk.tomas.chess.bo.Coordinate;
import sk.tomas.chess.bo.Position;
import sk.tomas.chess.bo.Tile;
import sk.tomas.chess.constants.Constants;

import java.util.List;

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

    public static ClonedChessBoard cloneChessBoard(ChessBoard chessBoard) {
        ClonedChessBoard result = new ClonedChessBoard();
        for (int i = startTile; i < endTile; i++) {
            for (int j = startTile; j < endTile; j++) {
                if (chessBoard.getSet()[i][j].getFigure() != null) {
                    result.getSet()[i][j].setFigure(chessBoard.getSet()[i][j].getFigure().clone());
                }
            }
        }

        ClonedHistory history = new ClonedHistory();
        result.setHistory(history);
        history.setChessBoard(result);
        return result;
    }

    public static boolean validatePerform(Position to, List<Position> positions) {
        for (Position p : positions) {
            if ((p.getX() == to.getX()) && (p.getY() == to.getY())) {
                return true;
            }
        }
        return false;
    }

    public static Coordinate castPositionToCoordinate(Position p) {
        String y = "";
        switch (p.getY()) {
            case 2:
                y = "a";
                break;
            case 3:
                y = "b";
                break;
            case 4:
                y = "c";
                break;
            case 5:
                y = "d";
                break;
            case 6:
                y = "e";
                break;
            case 7:
                y = "f";
                break;
            case 8:
                y = "g";
                break;
            case 9:
                y = "h";
                break;
        }

        return new Coordinate((endTile - p.getX()) + "", y);
    }

}
