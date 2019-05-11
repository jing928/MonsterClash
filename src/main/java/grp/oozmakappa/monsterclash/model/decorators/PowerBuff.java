package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.Command;
import grp.oozmakappa.monsterclash.model.command.CommandManager;
import grp.oozmakappa.monsterclash.model.command.PowerChangeCommand;
import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

/**
 * @author Chenglong Ma
 */
public class PowerBuff extends BuffDecorator {
    private static final double MAX_GAIN = 10;
    private final double powerGained;

    protected PowerBuff(CellEffect toDecorated) {
        super(toDecorated);
        powerGained = MAX_GAIN * Math.random();
    }

    @Override
    public void affect(Piece piece) {
        Command powerChangeCmd = new PowerChangeCommand(piece, powerGained);
        CommandManager manager = CommandManager.getInstance();
        manager.storeAndExecute(powerChangeCmd);
        super.affect(piece);
    }
}
