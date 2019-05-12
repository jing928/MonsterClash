package grp.oozmakappa.monsterclash.view.observers;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * An abstract adapter class for receiving {@link Piece} notifying events.
 * The methods in this class are empty. This class exists as
 * convenience for creating observer objects.
 *
 * @author Chenglong Ma
 */
public abstract class PieceAdapter implements PiecePositionObserver, PiecePropertyObserver {
    @Override
    public void positionChanging(Piece pieceToMove) {
    }

    @Override
    public void positionChanged(Piece pieceLocated, boolean shouldNotify) {
    }

    @Override
    public void healthChanged(double deltaHealth, boolean shouldNotify) {
    }

    @Override
    public void powerChanged(double deltaPower, boolean shouldNotify) {
    }

    @Override
    public void rangeChanged(int deltaRange, boolean shouldNotify) {
    }
}
