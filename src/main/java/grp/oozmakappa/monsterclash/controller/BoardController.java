package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.view.BoardPanel;
import grp.oozmakappa.monsterclash.view.CellLabel;
import grp.oozmakappa.monsterclash.view.PieceButton;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Chenglong Ma
 */
public class BoardController extends MouseAdapter {
    private final BoardPanel boardPanel;
    private Point initMouseLocation, initPieceLocation;

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
        if (!(e.getComponent() instanceof PieceButton)) {
            return;
        }
        PieceButton button = (PieceButton) e.getComponent();
        initMouseLocation = e.getPoint();
        initPieceLocation = button.getLocation();
        button.getPiece().notifyMoving();
    }

    /**
     * @param e
     * @pre the clicked component should be {@link PieceButton}
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (!(e.getComponent() instanceof PieceButton)) {
            return;
        }
        PieceButton button = (PieceButton) e.getComponent();
        Piece piece = button.getPiece();
        Cell newCell;
        Point newLoc;
        CellLabel cellLabel = boardPanel.getClosestCell(button);
        if (cellLabel != null) {
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
        // follow the mouse's movement
        Point piecePoint = e.getComponent().getLocation();
        int x = piecePoint.x + e.getX() - initMouseLocation.x;
        int y = piecePoint.y + e.getY() - initMouseLocation.y;
        e.getComponent().setLocation(x, y);
    }
}
