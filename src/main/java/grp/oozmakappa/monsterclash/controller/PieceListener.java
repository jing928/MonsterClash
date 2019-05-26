package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.controller.states.ModeSelectionState;
import grp.oozmakappa.monsterclash.controller.states.PieceButtonState;
import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.view.BoardPanel;
import grp.oozmakappa.monsterclash.view.CellLabel;
import grp.oozmakappa.monsterclash.view.PieceButton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

/**
 * @author Chenglong Ma
 */
public class PieceListener extends MouseAdapter {
    private static final Logger LOG = LogManager.getLogger();
    private final BoardPanel boardPanel;
    private Point initMouseLocation;
    private PieceButton currButton;

    private PieceButtonState state;

    public PieceListener(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
        state = ModeSelectionState.getInstance();
    }

    public void enableDrag(Component component) {
        if (!Arrays.asList(component.getMouseMotionListeners()).contains(this)) {
            component.addMouseMotionListener(this);
        }
    }

    public PieceButtonState getState() {
        return state;
    }

    public void setState(PieceButtonState state) {
        this.state = state;
        state.todo(this);
    }

    public void resetState(PieceButtonState prevState) {
        this.state = prevState;
    }

    public PieceButton getButton() {
        return currButton;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isInvalid(e)) {
            return;
        }
        initMouseLocation = e.getPoint();
        state.doing(this);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isInvalid(e)) {
            return;
        }
        state.done(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isInvalid(e)) {
            return;
        }
        // follow the mouse's movement
        Point piecePoint = e.getComponent().getLocation();
        int x = piecePoint.x + e.getX() - initMouseLocation.x;
        int y = piecePoint.y + e.getY() - initMouseLocation.y;
        e.getComponent().setLocation(x, y);
    }

    private boolean isInvalid(MouseEvent e) {
        Component component = e.getComponent();
        if (!component.isEnabled()) {
            return true;
        }
        Constraints constraints = Constraints.getInstance();
        if (!(component instanceof PieceButton)) {
            showMessage(component, "Invalid operation");
            return true;
        }
        currButton = (PieceButton) component;
        Piece piece = currButton.getPiece();
        if (piece.isWin()) {
            return true;
        }
        if (piece.getTeam() != constraints.getCurrentTeam()) {
            showMessage(currButton, "It's not your turn.");
            return true;
        }
        boolean canMove = constraints.canMove();
        if (!canMove) {
            showMessage(component, "Cannot move, Please roll dice first.");
            return true;
        }
        Piece activePiece = constraints.getActivePiece();
        if (activePiece != null && activePiece != piece) {
            showMessage(currButton, "You can only move " + activePiece.getName());
            return true;
        }
        return false;
    }

    private void showMessage(Component component, String msg) {
        JOptionPane.showMessageDialog(component, msg, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Delegates to {@link BoardPanel}
     *
     * @param button
     * @return
     */
    public CellLabel getClosestCell(PieceButton button) {
        return boardPanel.getClosestCell(button);
    }

    public PieceButton getClosestPiece(PieceButton button) {
        return boardPanel.getClosestPiece(button);
    }

    public boolean hasReachablePiece() {
        return boardPanel.hasReachablePiece();
    }

}
