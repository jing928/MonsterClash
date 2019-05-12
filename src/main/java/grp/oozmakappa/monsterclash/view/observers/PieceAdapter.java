package grp.oozmakappa.monsterclash.view.observers;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * An abstract adapter class for receiving {@link Piece} notifying events.
 * The methods in this class are empty. This class exists as
 * convenience for creating observer objects.
 *
 * @author Chenglong Ma
 */
public abstract class PieceAdapter implements PieceActionObserver, PiecePositionObserver, PiecePropertyObserver {
    @Override
    public void beforeActing(Piece pieceToAct) {
    }

    @Override
    public void afterActing(Piece pieceActed) {
    }

    @Override
    public void beforeMove(Piece pieceToMove) {
    }

    @Override
    public void afterMove(Piece pieceLocated) {
    }

    @Override
    public void healthChanged(double deltaHealth) {
    }

    @Override
    public void powerChanged(double deltaPower) {
    }

    @Override
    public void rangeChanged(int deltaRange) {
    }
}
