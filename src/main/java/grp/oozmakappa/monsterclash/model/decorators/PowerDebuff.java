package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.Command;
import grp.oozmakappa.monsterclash.model.command.CommandManager;
import grp.oozmakappa.monsterclash.model.command.PowerChangeCommand;
import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

public class PowerDebuff extends DebuffDecorator {
    private static final double MAX_LOSS = -10;
    private final double powerLost;

    protected PowerDebuff(CellEffect toDecorated) {
        super(toDecorated);
        powerLost = MAX_LOSS * Math.random();
    }

    @Override
    public void affect(Piece piece) {
        Command powerChangeCmd = new PowerChangeCommand(piece, powerLost);
        CommandManager manager = CommandManager.getInstance();
        manager.storeAndExecute(powerChangeCmd);
        super.affect(piece);
    }
}
