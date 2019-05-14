package grp.oozmakappa.monsterclash.controller.states;

import grp.oozmakappa.monsterclash.controller.PieceListener;
import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.view.AbilityDialog;
import grp.oozmakappa.monsterclash.view.PieceButton;

import javax.swing.*;
import java.awt.*;

/**
 * @author Chenglong Ma
 */
public class ActionState implements PieceButtonState {
    private static ActionState instance;
    private Point initPieceLocation;


    private ActionState() {
        // for singleton pattern
    }

    public static ActionState getInstance(Piece piece) {
        if (instance == null) {
            instance = new ActionState();
        }
        new AbilityDialog(piece).display();
        if (piece.getCurrAbility() != null) {
            piece.notifyActing();
        }
        return instance;
    }


    @Override
    public void todo(PieceListener ctrl) {
        PieceButton button = ctrl.getButton();
        Piece piece = button.getPiece();
        if (piece.getCurrAbility() == null) {
            new AbilityDialog(piece).display();
            return;
        }
        piece.notifyActing();
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
            Constraints.getInstance().changeTurn();
        } else if (askContinue(button)) {
            nextState = this;
        } else {
            Constraints.getInstance().changeTurn();
        }
        piece.setCurrentAbility(null);
        button.removeMouseMotionListener(ctrl);
        button.setLocation(initPieceLocation);
        ctrl.setState(nextState);
    }

    private boolean askContinue(Component component) {
        int res = JOptionPane.showConfirmDialog(component, "No reachable target, select again?", "Warning", JOptionPane.YES_NO_OPTION);
        return res == JOptionPane.YES_OPTION;
    }
}
