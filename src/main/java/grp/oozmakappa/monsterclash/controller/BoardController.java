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
    private Point initMouseLocation, initPieceLocation;
    private BoardPanel boardPanel;

    public BoardController() {
    }

    public void setBoardPanel(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }


    public void addPiece(Piece piece) {
        PieceButton button = new PieceButton(piece);
        button.addMouseListener(this);
        button.addMouseMotionListener(this);
        boardPanel.addPieceButton(button);
        for (CellLabel cellLabel : boardPanel.getCellLabels()) {
            piece.addObserver(cellLabel);
        }
    }

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

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!(e.getComponent() instanceof PieceButton)) {
            return;
        }
        PieceButton button = (PieceButton) e.getComponent();
        CellLabel cellLabel = boardPanel.getClosestCell(button);
        Piece piece = button.getPiece();
        if (cellLabel != null) {
            Cell newCell = cellLabel.getCell();
            piece.setPosition(newCell);
            button.setLocation(cellLabel.getLocation());
        } else {
            piece.setPosition(piece.getPosition());
            button.setLocation(initPieceLocation);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point piecePoint = e.getComponent().getLocation();
        int x = piecePoint.x + e.getX() - initMouseLocation.x;
        int y = piecePoint.y + e.getY() - initMouseLocation.y;
        e.getComponent().setLocation(x, y);
    }
}
