package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.Command;
import grp.oozmakappa.monsterclash.model.command.CommandManager;
import grp.oozmakappa.monsterclash.model.command.RangeChangeCommand;
import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

/**
 * @author Chenglong Ma
 */
public class RangeDebuff extends DebuffDecorator {
    private static final int MAX_LOSS = -10;
    private final int loss;

    protected RangeDebuff(CellEffect toDecorated) {
        super(toDecorated);
        loss = (int) (MAX_LOSS * Math.random());
    }

    @Override
    public void affect(Piece piece) {
        Command rangeChangeCmd = new RangeChangeCommand(piece, loss);
        CommandManager manager = CommandManager.getInstance();
        manager.storeAndExecute(rangeChangeCmd);
        super.affect(piece);
    }
}
