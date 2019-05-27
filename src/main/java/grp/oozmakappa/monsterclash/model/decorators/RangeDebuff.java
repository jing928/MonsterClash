package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.RangeChangeCommand;
import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

/**
 * @author Chenglong Ma
 */
public class RangeDebuff extends DebuffDecorator {
    private static final int MAX_LOSS = -2;
    private final int loss;

    RangeDebuff(CellEffect toDecorated) {
        super(toDecorated);
        loss = (int) (MAX_LOSS * Math.random()) - 1;
    }

    @Override
    public boolean affect(Piece piece) {
        LOG.info("Lost range: " + -loss);
        RangeChangeCommand.setRange(piece, loss);
        return super.affect(piece);
    }
}
