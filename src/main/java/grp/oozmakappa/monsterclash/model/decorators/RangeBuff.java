package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.command.Command;
import grp.oozmakappa.monsterclash.model.command.CommandManager;
import grp.oozmakappa.monsterclash.model.command.RangeChangeCommand;
import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

public class RangeBuff extends BuffDecorator {
    private static final int MAX_GAIN = 10;
    private final int rangeGained;

    protected RangeBuff(CellEffect toDecorated) {
        super(toDecorated);
        rangeGained = (int) (MAX_GAIN * Math.random());
    }

    @Override
    public void affect(Piece piece) {
        Command rangeChangeCmd = new RangeChangeCommand(piece, rangeGained);
        CommandManager manager = CommandManager.getInstance();
        manager.storeAndExecute(rangeChangeCmd);
        super.affect(piece);
    }
}
