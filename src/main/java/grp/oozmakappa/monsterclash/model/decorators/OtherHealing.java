package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public class OtherHealing extends AbstractDecorator implements HealDecorator {
    public OtherHealing(Piece piece) {
        super(piece);
    }

    @Override
    public void heal(Piece target, double health) {
        target.increaseHealth(health);
    }
}
