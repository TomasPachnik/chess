package sk.tomas.chess.base;

import sk.tomas.chess.bo.Move;
import sk.tomas.chess.bo.Position;
import sk.tomas.chess.bo.Tile;
import sk.tomas.chess.bo.set.*;
import sk.tomas.chess.gui.Gui;
import sk.tomas.chess.minimax.Minimax;
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
    @Inject
    private History history;
    @Inject
    private Minimax minimax;

    private Tile[][] set;
    private boolean whiteTurn; //na rade je biely hrac
    private boolean whitePlayer; //ludsky hrac je biely
    private Position activePosition; //oznacene policko (moze byt len take na ktorom je figurka farby, ktora je na tahu)
    private List<Position> calculatedPositions; //policka, na ktore sa moze presunut figurka, ktora je oznacena (activePosition)
    private Thread thread; // druhe vlakno urcene na vypocet

    public ChessBoard() {
        whiteTurn = true;
        setWhitePlayer(true);
        nullActive();
        setUpTiles();
    }

    private void setWhitePlayer(boolean white) {
        whitePlayer = white;
    }

    private void setUpTiles() {
        set = new Tile[12][12];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                set[i][j] = new Tile(new Position(i, j));
            }
        }
    }

    private void perform(Position from, Position to) {
        history.add(from, to, getAtPosition(to).getFigure());
        getAtPosition(to).setFigure(getAtPosition(from).getFigure());
        getAtPosition(from).setFigure(null);
    }

    public void setUp() {
        //TODO zabit vypoctove vlakno, ak bezi
        setUpTiles();
        setUpPieces();
    }

    private Color getActiveColor() {
        if (whiteTurn) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    public void click(Position position) {
        if (activePosition == null && getAtPosition(position).getFigure() != null && !getAtPosition(position).getFigure().getColor().equals(getActiveColor())) {
            return;
        }
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
                perform(activePosition, position);
                changeActiveColor();
                changeActivePositions(false);
                nullActive();

                if (whiteTurn != whitePlayer) {
                    calculateMove();
                }

            }
            //history.printHistory();
        }
        showLastMove();
        gui.repaint();
    }

    private void showLastMove() {
        if (history.getLast() != null) {
            clearLastMove();
            getAtPosition(history.getLast().getFrom()).setLastMove(true);
            getAtPosition(history.getLast().getTo()).setLastMove(true);
        }
    }

    public void clearLastMove() {
        for (int i = 2; i < 10; i++) {
            for (int j = 2; j < 10; j++) {
                set[i][j].setLastMove(false);
            }
        }
    }

    public void movePerformed(Position from, Position to) {
        perform(from, to);
        changeActiveColor();
        changeActivePositions(false);
        nullActive();
        showLastMove();
        gui.repaint();
        gui.hideWaitingIcon();
    }

    private void changeActiveColor() {
        whiteTurn = !whiteTurn;
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

        for (int i = 2; i < 10; i++) {
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

        for (int i = 2; i < 10; i++) {
            set[8][i].setFigure(new Pawn(Color.WHITE));
        }
        gui.repaint();
    }

    public Tile getAtPosition(Position p) {
        return set[p.getX()][p.getY()];
    }

    public Tile[][] getSet() {
        return set;
    }

    private void calculateMove() {
        thread = new Thread(minimax);
        gui.showWaitingIcon();
        thread.start();
    }

}
