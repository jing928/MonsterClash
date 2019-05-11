package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.Command;
import grp.oozmakappa.monsterclash.model.command.CommandManager;
import grp.oozmakappa.monsterclash.model.command.HealthChangeCommand;
import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

/**
 * @author Chenglong Ma
 */
public class HealthDebuff extends DebuffDecorator {
    private static final double MAX_LOSS = -10;
    private final double damage;

    protected HealthDebuff(CellEffect toDecorated) {
        super(toDecorated);
        damage = MAX_LOSS * Math.random();
    }

    @Override
    public void affect(Piece piece) {
        Command healthChangeCmd = new HealthChangeCommand(piece, damage);
        CommandManager manager = CommandManager.getInstance();
        manager.storeAndExecute(healthChangeCmd);
        super.affect(piece);
    }
}
