package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.PowerChangeCommand;
import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

/**
 * @author Chenglong Ma
 */
class PowerBuff extends BuffDecorator {
    private static final double MAX_GAIN = 10;
    private final double powerGained;

    PowerBuff(CellEffect toDecorated) {
        super(toDecorated);
        powerGained = MAX_GAIN * Math.random() + 1;
    }

    @Override
    public void affect(Piece piece) {
        LOG.info("Gain power: " + powerGained);
        PowerChangeCommand.setPower(piece, powerGained);
        super.affect(piece);
    }
}
