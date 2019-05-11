package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.Command;
import grp.oozmakappa.monsterclash.model.command.CommandManager;
import grp.oozmakappa.monsterclash.model.command.HealthChangeCommand;
import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

/**
 * @author Chenglong Ma
 */
public class HealthBuff extends BuffDecorator {
    private static final double MAX_GAIN = 10;
    private final double healthGained;

    protected HealthBuff(CellEffect toDecorated) {
        super(toDecorated);
        healthGained = MAX_GAIN * Math.random();
    }

    @Override
    public void affect(Piece piece) {
        Command healthChangeCmd = new HealthChangeCommand(piece, healthGained);
        CommandManager manager = CommandManager.getInstance();
        manager.storeAndExecute(healthChangeCmd);
        super.affect(piece);
    }
}
