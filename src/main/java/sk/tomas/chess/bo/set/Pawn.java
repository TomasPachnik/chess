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

    private int[][] blackPositions = new int[][]{
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100},
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100},
            {-100, -100, 0, 0, 0, 0, 0, 0, 0, 0, -100, -100},
            {-100, -100, 0, 0, -1, -8, -8, 0, -1, 0, 0, -100, -100},
            {-100, -100, 1, 1, 1, 4, 4, 1, 1, 1, -100, -100},
            {-100, -100, 1, 1, 6, 8, 8, 6, 1, 1, -100, -100},
            {-100, -100, 4, 4, 6, 10, 10, 6, 4, 4, -100, -100},
            {-100, -100, 10, 10, 10, 15, 15, 10, 10, 10, -100, -100},
            {-100, -100, 20, 20, 20, 20, 20, 20, 20, 20, -100},
            {-100, -100, 20, 20, 20, 20, 20, 20, 20, 20, -100, -100},
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100},
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100}};

    private int[][] whitePositions = new int[][]{
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100},
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100},
            {-100, -100, 20, 20, 20, 20, 20, 20, 20, 20, -100, -100},
            {-100, -100, 20, 20, 20, 20, 20, 20, 20, 20, -100},
            {-100, -100, 10, 10, 10, 15, 15, 10, 10, 10, -100, -100},
            {-100, -100, 4, 4, 6, 10, 10, 6, 4, 4, -100, -100},
            {-100, -100, 1, 1, 6, 8, 8, 6, 1, 1, -100, -100},
            {-100, -100, 1, 1, 1, 4, 4, 1, 1, 1, -100, -100},
            {-100, -100, 0, 0, -1, -8, -8, 0, -1, 0, 0, -100, -100},
            {-100, -100, 0, 0, 0, 0, 0, 0, 0, 0, -100, -100},
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100},
            {-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100}};

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public Figure clone() {
        return new Pawn(getColor());
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
    public int calculatePositionValue(Position position, boolean white) {
        if (white) {
            return whitePositions[position.getX()][position.getY()];
        } else {
            return blackPositions[position.getX()][position.getY()];
        }
    }

    @Override
    public int getDeletedValue() {
        return -50;
    }
}
