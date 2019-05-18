package grp.oozmakappa.monsterclash.controller.states;

import grp.oozmakappa.monsterclash.controller.PieceListener;
import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.StateChangeCommand;
import grp.oozmakappa.monsterclash.view.AbilityDialog;
import grp.oozmakappa.monsterclash.view.PieceButton;

import javax.swing.*;
import java.awt.*;

import static grp.oozmakappa.monsterclash.model.command.TurnChangeCommand.changeTurn;
import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 * @author Chenglong Ma
 */
public class ActionState implements PieceButtonState {
    private static ActionState instance;
    private Point initPieceLocation;


    private ActionState() {
        // for singleton pattern
    }

    public static ActionState getInstance() {
        if (instance == null) {
            instance = new ActionState();
        }
        return instance;
    }

    @Override
    public void todo(PieceListener ctrl) {
        PieceButton button = ctrl.getButton();
        Piece piece = button.getPiece();
        if (piece.isWin()) {
            return;
        }
        new AbilityDialog(piece).display();
    }

    @Override
    public void doing(PieceListener ctrl) {
        PieceButton button = ctrl.getButton();
        initPieceLocation = button.getLocation();
        Piece piece = button.getPiece();
        if (piece.getCurrAbility() == null) {
            new AbilityDialog(piece).display();
            return;
        }
        piece.notifyActing();
        if (!ctrl.hasReachablePiece()) {
            if (askContinue(button)) {
                piece.setCurrentAbility(null);
                return;
            }
            cleanup(ctrl);
            return;
        }
        button.addMouseMotionListener(ctrl);
    }

    @Override
    public void done(PieceListener ctrl) {
        PieceButton button = ctrl.getButton();
        Piece piece = button.getPiece();
        PieceButton targetButton = ctrl.getClosestPiece(button);
        if (targetButton != null) {
            Piece target = targetButton.getPiece();
            piece.act(target);
        }
        cleanup(ctrl);
    }

    private void cleanup(PieceListener ctrl) {
        changeTurn();
        PieceButton button = ctrl.getButton();
        Piece piece = button.getPiece();
        piece.setCurrentAbility(null);
        button.removeMouseMotionListener(ctrl);
        button.setLocation(initPieceLocation);
        PieceButtonState nextState = ModeSelectionState.getInstance();
        StateChangeCommand.setState(ctrl, nextState);
        Constraints.getInstance().setActivePiece(null);
    }

    private boolean askContinue(Component component) {
        String msg = "No reachable target, select again?";
        int res = JOptionPane.showConfirmDialog(component, msg, "Warning", YES_NO_OPTION);
        return res == JOptionPane.YES_OPTION;
    }
}
