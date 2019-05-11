package grp.oozmakappa.monsterclash.model.interfaces;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Chenglong Ma
 */
public interface CellEffect {
    Logger LOG = LogManager.getLogger();

    void affect(Piece piece);
}
