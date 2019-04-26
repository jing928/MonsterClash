package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public class FatherAttack extends AbstractDecorator implements AttackDecorator {
    private static final int FACTOR = 2;

    public FatherAttack(Piece piece) {
        super(piece);
    }

    @Override
    public void attack(Piece target) {
        int range = piece.getAttackRange();
        int strengthenRange = range * FACTOR;
        piece.setAttackRange(strengthenRange);
        piece.attack(target);
    }
}
