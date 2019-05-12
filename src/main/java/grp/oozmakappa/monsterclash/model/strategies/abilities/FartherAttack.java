package grp.oozmakappa.monsterclash.model.strategies.abilities;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public class FartherAttack extends AbstractSpecialAbility implements AttackStrategy {
    private static final int FACTOR = 2;

    public FartherAttack(Piece piece) {
        super(piece);
    }

    @Override
    public double getAttackPower(double defaultValue) {
        return defaultValue;
    }

    @Override
    public int getAttackRange(int defaultValue) {
        return defaultValue * FACTOR;
    }

    @Override
    public void attack(Piece target) {
        piece.attack(target);
    }
}
