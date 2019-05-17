package grp.oozmakappa.monsterclash.model.strategies.abilities;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.AttackCommand;

/**
 * @author Chenglong Ma
 */
public class StrongerAttack extends AbstractSpecialAbility implements AttackStrategy {
    private static final int FACTOR = 2;

    public StrongerAttack(Piece piece) {
        super(piece);
    }

    @Override
    public double getAttackPower(double defaultValue) {
        return defaultValue * FACTOR;
    }

    @Override
    public int getAttackRange(int defaultValue) {
        return defaultValue;
    }

    @Override
    public void attack(Piece target) {
        AttackCommand.attack(piece, target);
    }
}
