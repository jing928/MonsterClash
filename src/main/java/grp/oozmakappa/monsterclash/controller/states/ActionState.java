package grp.oozmakappa.monsterclash.controller.states;

import grp.oozmakappa.monsterclash.controller.PieceListener;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.view.AbilityDialog;
import grp.oozmakappa.monsterclash.view.PieceButton;

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
        // TODO implement strategy pattern
    }

    @Override
    public void done(PieceListener ctrl) {
        PieceButton button = ctrl.getButton();
        Piece piece = button.getPiece();
        PieceButton targetButton = ctrl.getClosestPiece(button);
        PieceButtonState nextState;
        if (targetButton != null) {
            Piece target = targetButton.getPiece();
            piece.act(target);
            nextState = RollingState.getInstance();
            piece.setCurrentAbility(null);
        } else {
            nextState = this;
        }
        button.removeMouseMotionListener(ctrl);
        button.setLocation(initPieceLocation);
        ctrl.setState(nextState);
    }
}
