package grp.oozmakappa.monsterclash.controller.states;

import grp.oozmakappa.monsterclash.controller.PieceListener;
import grp.oozmakappa.monsterclash.model.Cell;
import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.MoveCommand;
import grp.oozmakappa.monsterclash.model.command.StateChangeCommand;
import grp.oozmakappa.monsterclash.model.command.TurnChangeCommand;
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
    }

    @Override
    public void doing(PieceListener ctrl) {
        PieceButton button = ctrl.getButton();
        button.addMouseMotionListener(ctrl);
        Piece piece = button.getPiece();
        initPieceLocation = button.getLocation();
        piece.notifyMoving();
        timeOutThread = new Thread(() -> {
            try {
                Thread.sleep(Constraints.getInstance().getTimeOut());
                LOG.info("Time out for this turn");
                button.setLocation(initPieceLocation);
                TurnChangeCommand.changeTurn();
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
        PieceButton button = ctrl.getButton();
        Piece piece = button.getPiece();
        CellLabel cellLabel = ctrl.getClosestCell(button);
        PieceButtonState nextState;
        if (cellLabel != null) {
            newCell = cellLabel.getCell();
            timeOutThread.interrupt();
            nextState = ActionState.getInstance();
            MoveCommand.move(piece, newCell);
            LOG.info("Piece has moved.");
        } else {
            // stay put
            nextState = this;
            button.setLocation(initPieceLocation);
            LOG.info("Piece did not move.");
        }
        button.removeMouseMotionListener(ctrl);
        StateChangeCommand.setState(ctrl, nextState);
    }
}
