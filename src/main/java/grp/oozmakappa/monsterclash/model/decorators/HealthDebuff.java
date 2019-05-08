package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

/**
 * @author Chenglong Ma
 */
public class HealthDebuff extends DebuffDecorator {
    private static final double MAX_LOSS = 10;
    private final double damage;

    protected HealthDebuff(CellEffect toDecorated) {
        super(toDecorated);
        damage = MAX_LOSS * Math.random();
    }

    @Override
    public void affect(Piece piece) {
        piece.decreaseHealth(damage);
        String msg = String.format("You lost %.2f health", damage);
        showMessage(msg);
        super.affect(piece);
    }
}