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

    /**
     * Resets the state of {@link PieceListener} and whole game.
     *
     * @param ctrl
     */
    static void cleanup(PieceListener ctrl) {
        PieceButtonState nextState = ModeSelectionState.getInstance();
        StateChangeCommand.setState(ctrl, nextState);
        changeTurn();
        Constraints.getInstance().setActivePiece(null);
    }

    /**
     * Executes before clicked.
     *
     * @param ctrl
     */
    void todo(PieceListener ctrl);

    /**
     * Executes when clicked
     *
     * @param ctrl
     */
    void doing(PieceListener ctrl);

    /**
     * Executes when releasing mouse.
     *
     * @param ctrl
     */
    void done(PieceListener ctrl);
}
