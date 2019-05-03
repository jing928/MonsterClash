package grp.oozmakappa.monsterclash.controller.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public class HealthBuff extends BuffDecorator {
    private static final double MAX_GAIN = 10;
    private final double healthGained;

    protected HealthBuff(EffectDecorator toDecorated) {
        super(toDecorated);
        healthGained = MAX_GAIN * Math.random();
    }

    @Override
    public void affect(Piece piece) {
        piece.increaseHealth(healthGained);
        String msg = String.format("You gained %.2f health", healthGained);
        showMessage(msg);
        super.affect(piece);
    }
}
