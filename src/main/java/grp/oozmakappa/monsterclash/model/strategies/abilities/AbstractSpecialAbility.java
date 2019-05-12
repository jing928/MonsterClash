package grp.oozmakappa.monsterclash.model.strategies.abilities;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public abstract class AbstractSpecialAbility {
    protected final Piece piece;

    public AbstractSpecialAbility(Piece piece) {
        this.piece = piece;
    }
}
