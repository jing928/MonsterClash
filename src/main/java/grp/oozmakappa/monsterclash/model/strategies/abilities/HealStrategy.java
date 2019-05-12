package grp.oozmakappa.monsterclash.model.strategies.abilities;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public interface HealStrategy {
    int getHealRange(int defaultValue);

    void heal(Piece target);
}
