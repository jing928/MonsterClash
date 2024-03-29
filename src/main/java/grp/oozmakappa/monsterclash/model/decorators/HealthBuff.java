package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.HealthChangeCommand;
import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

/**
 * @author Chenglong Ma
 */
class HealthBuff extends BuffDecorator {
    private static final double MAX_GAIN = 10;
    private final double healthGained;

    HealthBuff(CellEffect toDecorated) {
        super(toDecorated);
        healthGained = MAX_GAIN * Math.random() + 1;
    }

    @Override
    public boolean affect(Piece piece) {
        LOG.info("Gain health: " + healthGained);
        HealthChangeCommand.setHealth(piece, healthGained);
        return super.affect(piece);
    }
}
