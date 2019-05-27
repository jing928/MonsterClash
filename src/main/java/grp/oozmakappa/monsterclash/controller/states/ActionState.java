package grp.oozmakappa.monsterclash.controller.states;

import grp.oozmakappa.monsterclash.controller.PieceListener;
import grp.oozmakappa.monsterclash.model.Ability;
import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.AbilityChangeCommand;
import grp.oozmakappa.monsterclash.view.AbilityDialog;
import grp.oozmakappa.monsterclash.view.PieceButton;

import javax.swing.*;
import java.awt.*;

import static grp.oozmakappa.monsterclash.model.rules.AbstractRuleFactory.RULE_A;
import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 * @author Chenglong Ma
 */
public class ActionState implements PieceButtonState {
    private static final Constraints CONSTRAINTS = Constraints.getInstance();
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
        notify(button);
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
        notify(button);
        ctrl.enableDrag(button);
        if (!ctrl.hasReachablePiece()) {
            if (askContinue(button)) {
                reset(ctrl);
                return;
            }
            PieceButtonState.cleanup(ctrl);
        }
    }

    @Override
    public void done(PieceListener ctrl) {
        PieceButton button = ctrl.getButton();
        Piece piece = button.getPiece();
        PieceButton targetButton = ctrl.getClosestPiece(button);
        if (targetButton != null) {
            Piece target = targetButton.getPiece();
            piece.act(target);
            PieceButtonState.cleanup(ctrl);
        }
        piece.notifyActed();
        reset(ctrl);
    }

    private void notify(PieceButton button) {
        Piece piece = button.getPiece();
        Ability currAbility = piece.getCurrAbility();
        if (currAbility == Ability.SPECIAL_HEALING && CONSTRAINTS.getCurrentRule().equals(RULE_A)) {
            piece.addActionObserver(button);
        } else {
            piece.removeActionObserver(button);
        }
        piece.notifyActing();
    }

    private void reset(PieceListener ctrl) {
        PieceButton button = ctrl.getButton();
        Piece piece = button.getPiece();
        AbilityChangeCommand.setAbility(piece, null);
        button.removeMouseMotionListener(ctrl);
        button.setLocation(initPieceLocation);
    }

    private boolean askContinue(Component component) {
        String msg = "No reachable target, select again?";
        int res = JOptionPane.showConfirmDialog(component, msg, "Warning", YES_NO_OPTION);
        return res == JOptionPane.YES_OPTION;
    }
}
