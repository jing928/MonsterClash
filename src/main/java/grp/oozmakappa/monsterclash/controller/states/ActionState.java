package grp.oozmakappa.monsterclash.controller.states;

import grp.oozmakappa.monsterclash.controller.PieceListener;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.StateChangeCommand;
import grp.oozmakappa.monsterclash.view.AbilityDialog;
import grp.oozmakappa.monsterclash.view.PieceButton;

import javax.swing.*;
import java.awt.*;

import static grp.oozmakappa.monsterclash.model.command.TurnChangeCommand.changeTurn;

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
        new AbilityDialog(piece).display();
        if (piece.getCurrAbility() != null) {
            piece.notifyActing();
        }
        if (!ctrl.hasReachablePiece()) {
            PieceButtonState nextState = askContinue(button) ? this : ModeSelectionState.getInstance();
            ctrl.setState(nextState);
            piece.setCurrentAbility(null);
            changeTurn();
        }
    }

    @Override
    public void doing(PieceListener ctrl) {
        PieceButton button = ctrl.getButton();
        Piece piece = button.getPiece();
        if (piece.getCurrAbility() == null) {
            todo(ctrl);
            return;
        }
        piece.notifyActing();
        if (!ctrl.hasReachablePiece()) {
            PieceButtonState nextState = askContinue(button) ? this : ModeSelectionState.getInstance();
            ctrl.setState(nextState);
            return;
        }
        button.addMouseMotionListener(ctrl);
        initPieceLocation = button.getLocation();
    }

    @Override
    public void done(PieceListener ctrl) {
        PieceButton button = ctrl.getButton();
        Piece piece = button.getPiece();
        PieceButtonState nextState = ModeSelectionState.getInstance();
        PieceButton targetButton = ctrl.getClosestPiece(button);
        if (targetButton != null) {
            Piece target = targetButton.getPiece();
            piece.act(target);
            changeTurn();
        }
        piece.setCurrentAbility(null);
        button.removeMouseMotionListener(ctrl);
        button.setLocation(initPieceLocation);
        StateChangeCommand.setState(ctrl, nextState);
        nextState.todo(ctrl);
    }

    private boolean askContinue(Component component) {
        int res = JOptionPane.showConfirmDialog(component, "No reachable target, select again?", "Warning", JOptionPane.YES_NO_OPTION);
        return res == JOptionPane.YES_OPTION;
    }
}
