package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public class SelfHealing extends AbstractDecorator implements HealDecorator {

    public SelfHealing(Piece piece) {
        super(piece);
    }

    @Override
    public void heal(Piece target, double health) {
        piece.increaseHealth(health);
    }
}
