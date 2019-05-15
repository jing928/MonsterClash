package grp.oozmakappa.monsterclash.model.strategies.abilities;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.CommandManager;
import grp.oozmakappa.monsterclash.model.command.HealthChangeCommand;

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
        double cost = -health * COST_RATE;
        piece.setShouldNotify(false);
        CommandManager manager = CommandManager.getInstance();
        manager.storeAndExecute(new HealthChangeCommand(piece, cost));
        piece.setShouldNotify(true);
        manager.storeAndExecute(new HealthChangeCommand(target, health * HEALING_RATE));
        piece.notifyActed();
    }
}
