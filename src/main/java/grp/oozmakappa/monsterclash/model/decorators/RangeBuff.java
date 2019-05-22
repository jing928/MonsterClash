package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.RangeChangeCommand;
import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

public class RangeBuff extends BuffDecorator {
    private static final int MAX_GAIN = 10;
    private final int rangeGained;

    protected RangeBuff(CellEffect toDecorated) {
        super(toDecorated);
        rangeGained = (int) (MAX_GAIN * Math.random()) + 1;
    }

    @Override
    public boolean affect(Piece piece) {
        LOG.info("Gain range: " + rangeGained);
        RangeChangeCommand.setRange(piece, rangeGained);
        return super.affect(piece);
    }
}
