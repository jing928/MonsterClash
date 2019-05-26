package grp.oozmakappa.monsterclash.controller.states;

import grp.oozmakappa.monsterclash.controller.PieceListener;
import grp.oozmakappa.monsterclash.model.Cell;
import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.MoveCommand;
import grp.oozmakappa.monsterclash.model.command.StateChangeCommand;
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
    private static final Constraints CONSTRAINTS = Constraints.getInstance();
    private static MoveState instance;
    private Point initPieceLocation;
    private Thread timeOutThread;

    private MoveState() {
    }

    public static MoveState getInstance() {
        if (instance == null) {
            instance = new MoveState();
        }
        return instance;
    }

    @Override
    public void todo(PieceListener ctrl) {
        PieceButton button = ctrl.getButton();
        Piece piece = button.getPiece();
        piece.notifyMoving();
        if (timeOutThread == null || !timeOutThread.isAlive()) {
            timeOutThread = new Thread(() -> {
                try {
                    Thread.sleep(CONSTRAINTS.getTimeOut());
                    LOG.info("Time out for this turn");
                    piece.setPosition(piece.getPosition());
                    PieceButtonState.cleanup(ctrl);
                    JOptionPane.showMessageDialog(button, "Time out for your turn.");
                } catch (InterruptedException ex) {
                    LOG.info(ex.getMessage());
                }
            });
            timeOutThread.start();
        }
    }

    @Override
    public void doing(PieceListener ctrl) {
        PieceButton button = ctrl.getButton();
        ctrl.enableDrag(button);
        Piece piece = button.getPiece();
        initPieceLocation = button.getLocation();
        piece.notifyMoving();
    }

    @Override
    public void done(PieceListener ctrl) {
        Cell newCell;
        PieceButton button = ctrl.getButton();
        Piece piece = button.getPiece();
        CellLabel cellLabel = ctrl.getClosestCell(button);
        if (cellLabel != null) {
            newCell = cellLabel.getCell();
            timeOutThread.interrupt();
            MoveCommand.move(piece, newCell);
            StateChangeCommand.setState(ctrl, ActionState.getInstance());
            LOG.info("Piece has moved.");
        } else {
            button.setLocation(initPieceLocation);
            LOG.info("Piece did not move.");
        }
        button.removeMouseMotionListener(ctrl);
    }
}
