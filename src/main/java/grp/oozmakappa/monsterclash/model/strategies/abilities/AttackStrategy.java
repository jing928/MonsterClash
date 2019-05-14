package grp.oozmakappa.monsterclash.model.strategies.abilities;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public interface AttackStrategy {
    double getAttackPower(double defaultValue);

    int getAttackRange(int defaultValue);

    void attack(Piece target);
}
