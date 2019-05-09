package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

/**
 * @author Chenglong Ma
 */
public class HealthBuff extends BuffDecorator {
    private static final double MAX_GAIN = 10;
    private final double healthGained;

    protected HealthBuff(CellEffect toDecorated) {
        super(toDecorated);
        healthGained = MAX_GAIN * Math.random() + 1;
    }

    @Override
    public void affect(Piece piece) {
        piece.increaseHealth(healthGained);
        super.affect(piece);
    }
}
