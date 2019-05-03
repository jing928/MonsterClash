package grp.oozmakappa.monsterclash.controller.states;

import grp.oozmakappa.monsterclash.controller.PieceListener;
import grp.oozmakappa.monsterclash.model.Team;
import grp.oozmakappa.monsterclash.model.abstracts.Cell;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.utils.Constraints;
import grp.oozmakappa.monsterclash.view.CellLabel;
import grp.oozmakappa.monsterclash.view.PieceButton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * @author Chenglong Ma
 */
public class MoveState implements PieceButtonState {
    private static final Logger LOG = LogManager.getLogger();
    private static MoveState instance;
    private Point initPieceLocation;
    private Team currTeam = Team.OozmaKappa;
    private boolean canMove = true;
    private Thread timeOutThread;

    private MoveState() {
    }

    public static MoveState getInstance(Piece piece) {
        if (instance == null) {
            instance = new MoveState();
        }
        piece.notifyMoving();
        return instance;
    }

    @Deprecated // TODO move to global state
    private void changeTurn() {
        currTeam = currTeam == Team.OozmaKappa
                ? Team.RoarOmegaRoar
                : Team.OozmaKappa;
        canMove = false;
    }

    @Override
    public void todo(PieceListener ctrl) {
        PieceButton button = ctrl.getCurrButton();
        button.addMouseMotionListener(ctrl);
        Piece piece = button.getPiece();
        initPieceLocation = button.getLocation();
        piece.notifyMoving();
        timeOutThread = new Thread(() -> {
            try {
                Thread.sleep(Constraints.TIME_OUT);
                LOG.info("Time out for this turn");
                changeTurn();
                piece.setPosition(piece.getPosition());
                button.setLocation(initPieceLocation);
                JOptionPane.showMessageDialog(button, "Time out for your turn.");
            } catch (InterruptedException ex) {
                LOG.info(ex.getMessage());
            }
        });
        timeOutThread.start();
    }

    @Override
    public void done(PieceListener ctrl) {
        Cell newCell;
        Point newLoc;
        PieceButton button = ctrl.getCurrButton();
        Piece piece = button.getPiece();
        CellLabel cellLabel = ctrl.getClosestCell(button);
        PieceButtonState nextState;
        if (cellLabel != null) {
            changeTurn();
            newCell = cellLabel.getCell();
            newLoc = cellLabel.getLocation();
            timeOutThread.interrupt();
            nextState = ModeExecutionState.getInstance();
            button.removeMouseMotionListener(ctrl);
            LOG.info("Piece has moved.");
        } else {
            // stay put
            newCell = piece.getPosition();
            newLoc = initPieceLocation;
            nextState = this;
            LOG.info("Piece did not move.");
        }
        piece.setPosition(newCell);
        button.setLocation(newLoc);
        ctrl.setState(nextState);
    }
}
