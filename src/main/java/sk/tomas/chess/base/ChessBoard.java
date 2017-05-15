package sk.tomas.chess.base;

import java.awt.*;
import java.util.*;
import java.util.List;

import sk.tomas.chess.bo.*;
import sk.tomas.chess.bo.set.*;
import sk.tomas.chess.constants.Constants;
import sk.tomas.chess.gui.Gui;
import sk.tomas.chess.minimax.Minimax;
import sk.tomas.chess.util.Utils;
import sk.tomas.servant.annotation.Inject;

import static sk.tomas.chess.constants.Constants.CHESS;
import static sk.tomas.chess.constants.Constants.endTile;
import static sk.tomas.chess.constants.Constants.startTile;
import static sk.tomas.chess.util.Utils.validatePerform;

/**
 * Created by tomas on 5/12/17.
 */
public class ChessBoard {

    @Inject
    protected History history;
    @Inject
    private Gui gui;
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

    public void perform(Position from, Position to) {
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

    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    public Color getColor(boolean white) {
        if (white) {
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
                if (validatePerform(position, calculate(activePosition))) {
                    perform(activePosition, position);
                    changeActiveColor();
                    changeActivePositions(false);
                    nullActive();

                    if (whiteTurn != whitePlayer) {
                        calculateMove();
                    }
                }

            }
            //history.printHistory();
        }
        showLastMove();
        gui.repaint();
    }

    private void showLastMove() {
        if (history.getLast() != null) {
            hideLastMove();
            getAtPosition(history.getLast().getFrom()).setLastMove(true);
            getAtPosition(history.getLast().getTo()).setLastMove(true);
        }
    }

    public void hideLastMove() {
        for (int i = startTile; i < endTile; i++) {
            for (int j = startTile; j < endTile; j++) {
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
        Position chess = isChess(whiteTurn);
        if (chess != null) {
            gui.showMessage(CHESS + Utils.castPositionToCoordinate(chess));
        }
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

        ClonedChessBoard clonedChessBoard = Utils.cloneChessBoard(this);

        for (Move move : calculate) {
            clonedChessBoard.perform(move.getFrom(), move.getTo());
            Position chess = clonedChessBoard.isChess(whiteTurn);
            if (chess == null) {
                resultList.add(move.getTo());
            }
            clonedChessBoard.revertLastMove();
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

        for (int i = startTile; i < endTile; i++) {
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

        for (int i = startTile; i < endTile; i++) {
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

    public List<Move> getAllMoves(boolean white) {
        List<Move> resultList = new LinkedList<>();
        for (int i = startTile; i < endTile; i++) {
            for (int j = startTile; j < endTile; j++) {
                if (set[i][j].getFigure() != null && set[i][j].getFigure().getColor().equals(getColor(white))) {
                    resultList.addAll(set[i][j].getFigure().getAvailableMoves(this, set[i][j].getPosition()));
                }
            }
        }
        return resultList;
    }

    public int evaluate(boolean white) {
        int result = 0;
        for (int i = Constants.startTile; i < Constants.endTile; i++) {
            for (int j = Constants.startTile; j < Constants.endTile; j++) {
                if (getSet()[i][j].getFigure() != null) {
                    if (getSet()[i][j].getFigure().getColor().equals(getColor(white))) {
                        result += getSet()[i][j].getFigure().calculatePositionValue(getSet()[i][j].getPosition(), white);
                    } else {
                        result -= getSet()[i][j].getFigure().calculatePositionValue(getSet()[i][j].getPosition(), !white);
                    }
                }
            }
        }
        result = result + history.getFallen(white) - history.getFallen(!white);
        return result;
    }

    public void revertLastMove() {
        HistoryMove last = history.getLast();
        if (last != null) {
            getAtPosition(last.getFrom()).setFigure(getAtPosition(last.getTo()).getFigure());
            getAtPosition(last.getTo()).setFigure(last.getFallen());
            history.removeLast();
        }
    }

    @Override
    public String toString() {
        System.out.println("***** printing chessboard pieces *****");
        for (int i = Constants.startTile; i < Constants.endTile; i++) {
            for (int j = Constants.startTile; j < Constants.endTile; j++) {
                if (getSet()[i][j].getFigure() != null) {
                    System.out.print(getSet()[i][j].getFigure() + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println();
        return "";
    }

    public Position isChess(boolean white) {
        Position kingPosition = getKingPosition(white);
        List<Move> allMoves = getAllMoves(!white);
        for (Move move : allMoves) {
            if (move.getTo().equals(kingPosition)) {
                return move.getFrom();
            }
        }
        return null;
    }

    private Position getKingPosition(boolean white) {
        for (int i = Constants.startTile; i < Constants.endTile; i++) {
            for (int j = Constants.startTile; j < Constants.endTile; j++) {
                if (getSet()[i][j].getFigure() != null) {
                    if (getSet()[i][j].getFigure().toString().equals("X")) {
                        if (getSet()[i][j].getFigure().getColor().equals(getColor(white))) {
                            return new Position(i, j);
                        }
                    }
                }
            }
        }
        return null;
    }

}
