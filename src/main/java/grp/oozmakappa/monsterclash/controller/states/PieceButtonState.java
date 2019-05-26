package grp.oozmakappa.monsterclash.controller.states;

import grp.oozmakappa.monsterclash.controller.PieceListener;
import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.command.StateChangeCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static grp.oozmakappa.monsterclash.model.command.TurnChangeCommand.changeTurn;

/**
 * @author Chenglong Ma
 */
public interface PieceButtonState {
    Logger LOG = LogManager.getLogger();

    static void cleanup(PieceListener ctrl) {
        changeTurn();
        PieceButtonState nextState = ModeSelectionState.getInstance();
        StateChangeCommand.setState(ctrl, nextState);
        Constraints.getInstance().setActivePiece(null);
    }

    void todo(PieceListener ctrl);

    void doing(PieceListener ctrl);

    void done(PieceListener ctrl);
}
