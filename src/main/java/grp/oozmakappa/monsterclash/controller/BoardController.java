package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.model.Dice;
import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.interfaces.DiceObserver;
import grp.oozmakappa.monsterclash.view.BoardPanel;
import grp.oozmakappa.monsterclash.view.CellLabel;
import grp.oozmakappa.monsterclash.view.PieceButton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 * @author Chenglong Ma
 */
public class BoardController extends MouseAdapter implements DiceObserver {
    private static final Logger LOG = LogManager.getLogger();
    private final BoardPanel boardPanel;
    private Point initMouseLocation, initPieceLocation;
    private Team currTeam = Team.OozmaKappa;
    private boolean canMove = true;
    private Thread timeOutThread;

    public BoardController(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }


    /**
     * Adds new {@link PieceButton} into {@link BoardPanel}
     *
     * @param piece
     */
    public void addPiece(Piece piece) {
        // Board Panel is the creator of piece button.
        PieceButton button = new PieceButton(piece);
        PieceListener listener = new PieceListener(boardPanel);
        // TODO: to remove
        Dice.getInstance().addObserver(listener);
        button.addMouseListener(listener);
        // TODO
//        button.addMouseMotionListener(listener);
        boardPanel.addPieceButton(button);
        for (CellLabel cellLabel : boardPanel.getCellLabels()) {
            piece.addObserver(cellLabel);
        }
    }

    @Override
    public void valueChanged(int value) {
        // TODO: change state
    }
}
