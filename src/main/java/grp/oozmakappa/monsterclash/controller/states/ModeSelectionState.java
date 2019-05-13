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
public class ModeSelectionState extends PieceButtonState {

    private static final Logger LOG = LogManager.getLogger();
    private static ModeSelectionState instance;

    private ModeSelectionState(PieceListener ctrl) {
        super(ctrl);
        // for singleton pattern
    }

    public static synchronized ModeSelectionState getInstance(PieceListener ctrl) {
        if (instance == null) {
            instance = new ModeSelectionState(ctrl);
        }
        return instance;
    }


    @Override
    public void todo() {
        PieceButton button = ctrl.getButton();
        button.removeMouseMotionListener(ctrl);
        Piece piece = button.getPiece();
        new ModeDialog(piece).display();
        PieceButtonState nextState = piece.getCurrMode() == DefaultMode.getInstance()
                ? this
                : MoveState.getInstance(piece, ctrl);
        ctrl.setState(nextState);
    }

    @Override
    public void done() {
        // unreachable method
    }
}
