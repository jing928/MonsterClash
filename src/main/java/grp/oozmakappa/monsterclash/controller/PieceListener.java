package grp.oozmakappa.monsterclash.controller;

import grp.oozmakappa.monsterclash.controller.states.ModeSelectionState;
import grp.oozmakappa.monsterclash.controller.states.PieceButtonState;
import grp.oozmakappa.monsterclash.controller.states.RollingState;
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
import java.awt.event.MouseEvent;

/**
 * @author Chenglong Ma
 */
public class PieceListener extends MouseAdapter implements DiceObserver {
    private static final Logger LOG = LogManager.getLogger();
    private final BoardPanel boardPanel;
    private Point initMouseLocation;
    private Team currTeam = Team.OozmaKappa;
    private boolean canMove = true;
    private PieceButton currButton;
    private PieceButtonState state;

    public PieceListener(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
        state = RollingState.getInstance();
    }

    public void setState(PieceButtonState state) {
        this.state = state;
    }

    public PieceButton getCurrButton() {
        return currButton;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (invalidPiece(e)) {
            return;
        }
        initMouseLocation = e.getPoint();
        state.todo(this);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (invalidPiece(e)) {
            return;
        }
        state.done(this);
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
        if (!canMove || !(e.getComponent() instanceof PieceButton)) {
            return true;
        }
        currButton = (PieceButton) e.getComponent();
        Piece piece = currButton.getPiece();
        if (piece.getTeam() != currTeam) {
            e.consume();
            return true;
        }
        return false;
    }

    @Override
    public void valueChanged(int value) {
        canMove = true;// TODO: state
        setState(ModeSelectionState.getInstance());
    }

    public CellLabel getClosestCell(PieceButton button) {
        return boardPanel.getClosestCell(button);
    }
}
