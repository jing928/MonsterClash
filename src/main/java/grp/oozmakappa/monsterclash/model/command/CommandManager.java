package grp.oozmakappa.monsterclash.model.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

/**
 * @author Chenglong Ma
 */
public class CommandManager {
    private static final Logger LOG = LogManager.getLogger();
    private static final int UNDO_MOVES = 2;
    private final LinkedList<Command> history;

    /**
     * private for singleton pattern
     */
    private CommandManager() {
        history = new LinkedList<>();
    }

    public static CommandManager getInstance() {
        return LazyHolder.COMMAND_MANAGER;
    }

    public void storeAndExecute(Command cmd) {
        this.history.add(cmd);
        cmd.execute();
        LOG.info("Execute Command");
    }

    public void undoLast() {
        for (int i = 0; i < UNDO_MOVES; i++) {
            if (history.size() == 0) {
                LOG.info("No historical commands");
                return;
            }
            this.history.removeLast().undo();
            LOG.info("Undo last command " + (i + 1));
        }
    }

    /**
     * for singleton pattern
     */
    private static class LazyHolder {
        static final CommandManager COMMAND_MANAGER = new CommandManager();
    }
}
