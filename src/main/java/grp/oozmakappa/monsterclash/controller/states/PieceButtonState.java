package grp.oozmakappa.monsterclash.controller.states;

import grp.oozmakappa.monsterclash.controller.PieceListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Chenglong Ma
 */
public interface PieceButtonState {
    Logger LOG = LogManager.getLogger();

    void todo(PieceListener ctrl);

    void doing(PieceListener ctrl);

    void done(PieceListener ctrl);
}
