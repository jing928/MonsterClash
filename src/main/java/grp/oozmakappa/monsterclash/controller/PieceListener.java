package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.view.PieceButton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Chenglong Ma
 */
public class PieceListener extends MouseAdapter {
    private static final Logger LOG = LogManager.getLogger();
    private final Piece piece;
    private final PieceButton pieceButton;
    private Point initPoint;

    public PieceListener(PieceButton pieceButton, Piece piece) {
        this.pieceButton = pieceButton;
        this.piece = piece;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        initPoint = e.getPoint();
        piece.notifyMoving();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        piece.notifyMoved(pieceButton.getLocation());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point piecePoint = pieceButton.getLocation();
        int x = piecePoint.x + e.getX() - initPoint.x;
        int y = piecePoint.y + e.getY() - initPoint.y;
        pieceButton.setLocation(x, y);
    }
}
