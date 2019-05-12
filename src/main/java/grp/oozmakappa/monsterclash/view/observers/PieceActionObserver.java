package grp.oozmakappa.monsterclash.view.observers;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public interface PieceActionObserver {
    void beforeActing(Piece pieceToAct);

    void afterActing(Piece pieceActed);
}
