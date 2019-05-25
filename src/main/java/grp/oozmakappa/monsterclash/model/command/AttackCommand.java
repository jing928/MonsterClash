package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public class AttackCommand extends AbstractCommand {
    private final Piece me;
    private final Piece target;
    private final double targetPrevHealth;

    private AttackCommand(Piece me, Piece target) {
        super("Attack - " + me.getName());
        this.me = me;
        this.target = target;
        targetPrevHealth = target.getHealth();
    }

    public static void attack(Piece source, Piece target) {
        CommandManager manager = CommandManager.getInstance();
        manager.storeAndExecute(new AttackCommand(source, target));
    }

    @Override
    public void execute() {
        me.attack(target);
        LOG.info("Executed: Attack Command");
    }

    @Override
    public void undo() {
        target.setShouldNotify(false);
        target.setHealth(targetPrevHealth);
        target.setShouldNotify(true);
        LOG.info("Undid: Attack Command");
    }
}
