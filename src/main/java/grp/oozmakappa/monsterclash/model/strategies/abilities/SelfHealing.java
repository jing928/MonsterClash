package grp.oozmakappa.monsterclash.model.strategies.abilities;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.HealthChangeCommand;

/**
 * @author Chenglong Ma
 */
public class SelfHealing extends AbstractSpecialAbility implements HealStrategy {

    private static final double HEALING_RATE = 0.03;

    public SelfHealing(Piece piece) {
        super(piece);
    }

    @Override
    public int getHealRange(int defaultValue) {
        return 0;
    }

    @Override
    public void heal(Piece target) {
        HealthChangeCommand.setHealth(piece, piece.getHealth() * HEALING_RATE);
        piece.notifyActed();
    }
}
