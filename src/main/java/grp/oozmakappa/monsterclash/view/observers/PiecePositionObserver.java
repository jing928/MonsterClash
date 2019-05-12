package grp.oozmakappa.monsterclash.view.observers;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public interface PiecePositionObserver {
    /**
     * Executes before {@link Piece} moving.
     */
    void positionChanging(Piece pieceToMove);

    /**
     * Executes after {@link Piece} moving.
     *
     * @param pieceLocated
     */
    void positionChanged(Piece pieceLocated, boolean shouldNotify);
}
