package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.controller.states.PieceButtonState;
import grp.oozmakappa.monsterclash.controller.states.VerifyingState;
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
public class PieceListener extends MouseAdapter implements DiceObserver {
    private static final Logger LOG = LogManager.getLogger();
    private final BoardPanel boardPanel;
    private Point initMouseLocation;
    //    private PieceButton currButton;
    private PieceButtonState state;

    PieceListener(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
        state = VerifyingState.getInstance(this);
    }

    public void setState(PieceButtonState state) {
        this.state = state;
    }

//    public PieceButton getButton() {
//        return currButton;
//    }

    @Override
    public void mousePressed(MouseEvent e) {
        initMouseLocation = e.getPoint();
        state.todo(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        state.done(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // follow the mouse's movement
        Point piecePoint = e.getComponent().getLocation();
        int x = piecePoint.x + e.getX() - initMouseLocation.x;
        int y = piecePoint.y + e.getY() - initMouseLocation.y;
        e.getComponent().setLocation(x, y);
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

    @Override
    public void valueChanged(int value) {
//        setState(ModeSelectionState.getInstance(this));
    }
}
