package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public class StrongerAttack extends AbstractDecorator implements AttackDecorator {
    private static final int FACTOR = 2;

    public StrongerAttack(Piece piece) {
        super(piece);
    }

    @Override
    public void attack(Piece target) {
        double damage = piece.getAttackPower();
        double strengthenDamage = damage * FACTOR;
        piece.setAttackPower(strengthenDamage);
        piece.attack(target);
    }
}
