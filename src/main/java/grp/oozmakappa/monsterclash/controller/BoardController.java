package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.model.Board;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.view.BoardPanel;
import grp.oozmakappa.monsterclash.view.interfaces.PieceObserver;

import java.awt.*;

/**
 * @author Chenglong Ma
 */
public class BoardController implements PieceObserver {
    private final BoardPanel boardPanel;
    private final Board board;

    public BoardController(BoardPanel boardPanel, Board board) {
        this.boardPanel = boardPanel;
        this.board = board;
    }


    @Override
    public void positionChanging(Piece pieceToMove) {

    }

    @Override
    public void positionChanged(Piece pieceMoved, Point newPosition) {

    }

    @Override
    public void positionVerified(Piece pieceVerified, Point nextPosition) {

    }
}
