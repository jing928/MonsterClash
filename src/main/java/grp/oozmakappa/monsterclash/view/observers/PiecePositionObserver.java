package grp.oozmakappa.monsterclash.view.observers;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public interface PiecePositionObserver {
    /**
     * Executes before {@link Piece} moving.
     *
     * @param pieceToMove
     * @Requires distance > 0
     */
    void beforeMove(Piece pieceToMove);

    /**
     * Executes after {@link Piece} moving.
     *
     * @param pieceLocated
     */
    void afterMove(Piece pieceLocated, boolean isUndoing);

}
