package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.PowerChangeCommand;
import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

public class PowerDebuff extends DebuffDecorator {
    private static final double MAX_LOSS = -10;
    private final double powerLost;

    PowerDebuff(CellEffect toDecorated) {
        super(toDecorated);
        powerLost = MAX_LOSS * Math.random() - 1;
    }

    @Override
    public boolean affect(Piece piece) {
        LOG.info("Lost power: " + -powerLost);
        PowerChangeCommand.setPower(piece, -powerLost);
        return super.affect(piece);
    }
}
