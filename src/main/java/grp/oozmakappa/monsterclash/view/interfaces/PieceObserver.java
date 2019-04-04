package grp.oozmakappa.monsterclash.view.interfaces;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public interface PieceObserver {
    /**
     * Executes before {@link Piece} moving.
     */
    void positionChanging(Piece pieceToMove);

    /**
     * Executes after {@link Piece} moving.
     */
    void positionChanged();
}
