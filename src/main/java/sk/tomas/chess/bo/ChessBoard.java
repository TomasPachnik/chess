package sk.tomas.chess.bo;

import sk.tomas.chess.bo.set.*;
import sk.tomas.chess.gui.Gui;
import sk.tomas.chess.util.Utils;
import sk.tomas.servant.annotation.Inject;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by tomas on 5/12/17.
 */
public class ChessBoard {

    @Inject
    private Gui gui;

    private Tile[][] set;
    private Position activePosition; //oznacene policko (moze byt len take na ktorom je figurka farby, ktora je na tahu)
    private java.util.List<Position> calculatedPositions; //policka, na ktore sa moze presunut figurka, ktora je oznacena (activePosition)

    public ChessBoard() {
        setUpTiles();
    }

    private void setUpTiles() {
        set = new Tile[12][12];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                set[i][j] = new Tile(new Position(i, j));
            }
        }
    }

    public void setUp() {
        //TODO zabit vypoctove vlakno, ak bezi
        setUpTiles();
        setUpPieces();
    }

    public void click(Position position) {
        if (activePosition == null) {//oznacenie
            calculatedPositions = calculate(position);
            if (calculatedPositions.size() > 0) {
                changeActivePositions(true);
            } else {
                nullActive();
            }
        } else {
            if (position.equals(activePosition)) {//zrusenie oznacenia
                changeActivePositions(false);
                nullActive();
            } else {//vykonanie tahu
                //perform(activePosition, position);
                //changeActiveColor();
                changeActivePositions(false);
                nullActive();
                /*
                if (activeColor.getColor().equals(Color.BLACK)) {
                    Move move = evaluator.calculateNextMove3();
                    if (move != null) {
                        perform(move.getFrom().getPosition(), move.getTo().getPosition());
                        showtLastMove();
                        changeActivePositions(false);
                        changeActiveColor();
                        nullActive();
                    }
                }
                */
            }
            //history.printHistory();
        }

    }

    private void nullActive() {
        activePosition = null;
        calculatedPositions = null;
    }

    private void changeActivePositions(boolean state) {
        if (calculatedPositions != null) {
            for (Position p : calculatedPositions) {
                getAtPosition(p).setSelected(state);
            }
        }
    }

    private List<Position> calculate(Position from) {
        if (getAtPosition(from).getFigure() == null) {
            return new LinkedList<>();
        }
        List<Move> calculate = getAtPosition(from).getFigure().getAvailableMoves(this, from);
        List<Position> resultList = new LinkedList<>();
        for (Move move : calculate) {
            resultList.add(move.getTo());
        }
        activePosition = from;
        calculatedPositions = resultList;
        return resultList;
    }

    private void setUpPieces() {
        set[2][2].setFigure(new Rook(Color.BLACK));
        set[2][3].setFigure(new Knight(Color.BLACK));
        set[2][4].setFigure(new Bishop(Color.BLACK));
        set[2][5].setFigure(new Queen(Color.BLACK));
        set[2][6].setFigure(new King(Color.BLACK));
        set[2][7].setFigure(new Bishop(Color.BLACK));
        set[2][8].setFigure(new Knight(Color.BLACK));
        set[2][9].setFigure(new Rook(Color.BLACK));

        for (int i = 0; i < 12; i++) {
            set[3][i].setFigure(new Pawn(Color.BLACK));
        }

        set[9][2].setFigure(new Rook(Color.WHITE));
        set[9][3].setFigure(new Knight(Color.WHITE));
        set[9][4].setFigure(new Bishop(Color.WHITE));
        set[9][5].setFigure(new Queen(Color.WHITE));
        set[9][6].setFigure(new King(Color.WHITE));
        set[9][7].setFigure(new Bishop(Color.WHITE));
        set[9][8].setFigure(new Knight(Color.WHITE));
        set[9][9].setFigure(new Rook(Color.WHITE));

        for (int i = 0; i < 12; i++) {
            set[8][i].setFigure(new Pawn(Color.WHITE));
        }
        gui.repaint();
    }

    private Tile getAtPosition(Position p) {
        return set[p.getX()][p.getY()];
    }

    public Tile[][] getSet() {
        return set;
    }
}
