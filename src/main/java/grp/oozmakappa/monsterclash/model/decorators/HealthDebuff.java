package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.HealthChangeCommand;
import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

/**
 * @author Chenglong Ma
 */
class HealthDebuff extends DebuffDecorator {
    private static final double MAX_LOSS = 10;
    private final double damage;

    HealthDebuff(CellEffect toDecorated) {
        super(toDecorated);
        damage = MAX_LOSS * Math.random() + 1;
    }

    @Override
    public boolean affect(Piece piece) {
        LOG.info("Get hurt: " + damage);
        HealthChangeCommand.setHealth(piece, -damage);
        return super.affect(piece);
    }
}
