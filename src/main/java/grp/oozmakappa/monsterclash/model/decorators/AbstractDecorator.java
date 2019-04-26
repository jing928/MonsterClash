package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public abstract class AbstractDecorator {
    protected final Piece piece;

    public AbstractDecorator(Piece piece) {
        this.piece = piece;
    }
}
