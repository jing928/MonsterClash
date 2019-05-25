package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.Dice;

/**
 * @author Chenglong Ma
 */
public class DiceCommand extends AbstractCommand {
    private static final Dice DICE = Dice.getInstance();
    private static final Constraints CONSTRAINTS = Constraints.getInstance();
    private final boolean canMove, canRoll;
    private final int prevValue;

    private DiceCommand() {
        super("Roll Dice");
        canMove = CONSTRAINTS.canMove();
        canRoll = DICE.canRoll();
        prevValue = DICE.getValue();
    }

    public static void roll() {
        CommandManager manager = CommandManager.getInstance();
        manager.storeAndExecute(new DiceCommand());
    }

    @Override
    public void execute() {
        DICE.roll();
        LOG.info("Executed: Dice Command");
    }

    @Override
    public void undo() {
        CONSTRAINTS.setCanMove(canMove);
        DICE.setCanRoll(canRoll);
        DICE.setValue(prevValue);
        LOG.info("Undid: Dice Command");
    }
}
