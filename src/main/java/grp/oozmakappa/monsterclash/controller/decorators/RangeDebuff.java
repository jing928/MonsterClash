package grp.oozmakappa.monsterclash.controller.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public class RangeDebuff extends DebuffDecorator {
    private static final int MAX_LOSS = 10;
    private final int loss;

    protected RangeDebuff(EffectDecorator toDecorated) {
        super(toDecorated);
        loss = (int) (MAX_LOSS * Math.random());
    }

    @Override
    public void affect(Piece piece) {
        int range = piece.getAttackRange();
        piece.setAttackRange(range - loss);
        String msg = String.format("You lost %d attack range", loss);
        showMessage(msg);
        super.affect(piece);
    }
}
