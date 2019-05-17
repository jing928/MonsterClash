package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Chenglong Ma
 */
public class AttackCommand implements Command {
    private final Piece me;
    private final Piece target;
    private final double targetPrevHealth;

    private AttackCommand(Piece me, Piece target) {
        this.me = me;
        this.target = target;
        targetPrevHealth = target.getHealth();
    }

    public static void attact(Piece source, Piece target) {
        CommandManager manager = CommandManager.getInstance();
        manager.storeAndExecute(new AttackCommand(source, target));
    }

    @Override
    public void execute() {
        me.attack(target);
        Logger log = LogManager.getLogger();
        log.info("Executed: Attack Command");
    }

    @Override
    public void undo() {
        target.setShouldNotify(false);
        target.setHealth(targetPrevHealth);
        target.setShouldNotify(true);
        Logger log = LogManager.getLogger();
        log.info("Undid: Attack Command");
    }
}
