package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

/**
 * @author Chenglong Ma
 */
public class RangeDebuff extends DebuffDecorator {
    private static final int MAX_LOSS = 10;
    private final int loss;

    RangeDebuff(CellEffect toDecorated) {
        super(toDecorated);
        loss = (int) (MAX_LOSS * Math.random()) + 1;
    }

    @Override
    public void affect(Piece piece) {
        LOG.info("Lost range: " + loss);
        int range = piece.getAttackRange();
        piece.setAttackRange(range - loss);
        super.affect(piece);
    }
}
