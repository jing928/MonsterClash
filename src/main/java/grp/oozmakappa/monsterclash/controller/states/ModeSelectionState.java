package grp.oozmakappa.monsterclash.controller.states;

import grp.oozmakappa.monsterclash.controller.PieceListener;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.strategies.modes.DefaultMode;
import grp.oozmakappa.monsterclash.view.ModeDialog;
import grp.oozmakappa.monsterclash.view.PieceButton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Chenglong Ma
 */
public class ModeSelectionState implements PieceButtonState {

    private static final Logger LOG = LogManager.getLogger();
    private static ModeSelectionState instance;

    private ModeSelectionState() {
        // for singleton pattern
    }

    public static ModeSelectionState getInstance() {
        if (instance == null) {
            instance = new ModeSelectionState();
        }
        return instance;
    }


    @Override
    public void todo(PieceListener ctrl) {
        PieceButton button = ctrl.getButton();
        button.removeMouseMotionListener(ctrl);
        Piece piece = button.getPiece();
        new ModeDialog(piece).display();
        PieceButtonState nextState = piece.getCurrMode() == DefaultMode.getInstance()
                ? this
                : MoveState.getInstance(piece);
        ctrl.setState(nextState);
    }

    @Override
    public void done(PieceListener ctrl) {
        // unreachable method
    }
}
