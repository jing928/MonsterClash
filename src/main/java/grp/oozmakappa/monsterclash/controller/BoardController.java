package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.interfaces.DiceObserver;
import grp.oozmakappa.monsterclash.view.BoardPanel;
import grp.oozmakappa.monsterclash.view.CellLabel;
import grp.oozmakappa.monsterclash.view.PieceButton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Chenglong Ma
 */
public class BoardController extends MouseAdapter implements DiceObserver {
    private static final Logger LOG = LogManager.getLogger();
    private final BoardPanel boardPanel;
    private Point initMouseLocation, initPieceLocation;
    private Team currTeam = Team.OozmaKappa;
    private boolean canMove = true;

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
        button.addMouseListener(this);
        button.addMouseMotionListener(this);
        boardPanel.addPieceButton(button);
        for (CellLabel cellLabel : boardPanel.getCellLabels()) {
            piece.addObserver(cellLabel);
        }
    }

    /**
     * @param e
     * @pre the clicked component should be {@link PieceButton}
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (invalidPiece(e)) {
            return;
        }
        PieceButton button = (PieceButton) e.getComponent();
        Piece piece = button.getPiece();
        initMouseLocation = e.getPoint();
        initPieceLocation = button.getLocation();
        piece.notifyMoving();
    }

    /**
     * @param e
     * @pre the clicked component should be {@link PieceButton}
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (invalidPiece(e)) {
            return;
        }
        Cell newCell;
        Point newLoc;
        PieceButton button = (PieceButton) e.getComponent();
        Piece piece = button.getPiece();
        CellLabel cellLabel = boardPanel.getClosestCell(button);
        if (cellLabel != null) {
            currTeam = currTeam == Team.OozmaKappa
                    ? Team.RoarOmegaRoar
                    : Team.OozmaKappa;
            canMove = false;
            newCell = cellLabel.getCell();
            newLoc = cellLabel.getLocation();
        } else {
            // stay put
            newCell = piece.getPosition();
            newLoc = initPieceLocation;
        }
        piece.setPosition(newCell);
        button.setLocation(newLoc);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (invalidPiece(e)) {
            return;
        }
        // follow the mouse's movement
        Point piecePoint = e.getComponent().getLocation();
        int x = piecePoint.x + e.getX() - initMouseLocation.x;
        int y = piecePoint.y + e.getY() - initMouseLocation.y;
        e.getComponent().setLocation(x, y);
    }

    private boolean invalidPiece(MouseEvent e) {
        if (!(e.getComponent() instanceof PieceButton)) {
            return true;
        }
        PieceButton button = (PieceButton) e.getComponent();
        Piece piece = button.getPiece();
        if (piece.getTeam() != currTeam || !canMove) {
            e.consume();
            return true;
        }
        return false;
    }

    @Override
    public void valueChanged(int value) {
        canMove = true;
    }
}