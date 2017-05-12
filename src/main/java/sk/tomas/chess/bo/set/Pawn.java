package sk.tomas.chess.bo.set;

import sk.tomas.chess.base.ChessBoard;
import sk.tomas.chess.bo.Figure;
import sk.tomas.chess.bo.Move;
import sk.tomas.chess.bo.Position;
import sk.tomas.chess.constants.Constants;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tomas on 5/12/17.
 */
public class Pawn extends Figure {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public List<Move> getAvailableMoves(ChessBoard chessBoard, Position position) {
        List<Move> resultList = new LinkedList<>();
        if (chessBoard.getAtPosition(position).getFigure().getColor().equals(Color.BLACK)) {
            if (position.getX() < Constants.endTile) {
                //posun o jedno dopredu
                if (chessBoard.getSet()[position.getX() + 1][position.getY()].getFigure() == null) {
                    resultList.add(new Move(position, chessBoard.getSet()[position.getX() + 1][position.getY()].getPosition()));
                }
                //posun o dva dopredu z prvej pozicie
                if (position.getX() == 3 && chessBoard.getSet()[position.getX() + 1][position.getY()].getFigure() == null && chessBoard.getSet()[position.getX() + 2][position.getY()].getFigure() == null) {
                    resultList.add(new Move(position, chessBoard.getSet()[position.getX() + 2][position.getY()].getPosition()));
                }
                //posun o jedno po diagonale na policko obsadene nepriatelskych hracom
                if (chessBoard.getSet()[position.getX() + 1][position.getY() - 1].getFigure() != null && chessBoard.getSet()[position.getX() + 1][position.getY() - 1].getFigure().getColor().equals(Color.WHITE)) {
                    resultList.add(new Move(position, chessBoard.getSet()[position.getX() + 1][position.getY() - 1].getPosition()));
                }
                //posun o jedno po diagonale na policko obsadene nepriatelskych hracom
                if (chessBoard.getSet()[position.getX() + 1][position.getY() + 1].getFigure() != null && chessBoard.getSet()[position.getX() + 1][position.getY() + 1].getFigure().getColor().equals(Color.WHITE)) {
                    resultList.add(new Move(position, chessBoard.getSet()[position.getX() + 1][position.getY() + 1].getPosition()));
                }
            }
        } else {
            if (position.getX() > Constants.startTile) {
                //posun o jedno dopredu
                if (chessBoard.getSet()[position.getX() - 1][position.getY()].getFigure() == null) {
                    resultList.add(new Move(position, chessBoard.getSet()[position.getX() - 1][position.getY()].getPosition()));
                }
                //posun o dva dopredu z prvej pozicie
                if (position.getX() == 8 && chessBoard.getSet()[position.getX() - 1][position.getY()].getFigure() == null && chessBoard.getSet()[position.getX() - 2][position.getY()].getFigure() == null) {
                    resultList.add(new Move(position, chessBoard.getSet()[position.getX() - 2][position.getY()].getPosition()));
                }
                //posun o jedno po diagonale na policko obsadene nepriatelskych hracom
                if (chessBoard.getSet()[position.getX() - 1][position.getY() + 1].getFigure() != null && chessBoard.getSet()[position.getX() - 1][position.getY() + 1].getFigure().getColor().equals(Color.BLACK)) {
                    resultList.add(new Move(position, chessBoard.getSet()[position.getX() - 1][position.getY() + 1].getPosition()));
                }
                //posun o jedno po diagonale na policko obsadene nepriatelskych hracom
                if (chessBoard.getSet()[position.getX() - 1][position.getY() - 1].getFigure() != null && chessBoard.getSet()[position.getX() - 1][position.getY() - 1].getFigure().getColor().equals(Color.BLACK)) {
                    resultList.add(new Move(position, chessBoard.getSet()[position.getX() - 1][position.getY() - 1].getPosition()));
                }
            }
        }
        return resultList;
    }

    @Override
    public int calculatePositionValue(Position position) {
        return 0;
    }

    @Override
    public int getDeletedValue() {
        return 0;
    }
}
