package grp.oozmakappa.monsterclash.model.strategies.abilities;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public class OtherHealing extends AbstractSpecialAbility implements HealStrategy {
    private static final double COST_RATE = 0.03;
    private static final double HEALING_RATE = 0.02;

    public OtherHealing(Piece piece) {
        super(piece);
    }

    @Override
    public int getHealRange(int defaultValue) {
        return defaultValue;
    }

    @Override
    public void heal(Piece target) {
        double health = piece.getHealth();
        piece.setHealth(health * (1 - COST_RATE));
        target.increaseHealth(health * HEALING_RATE);
        piece.notifyActed();
    }
}
