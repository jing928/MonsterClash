package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.immutable.History;
import grp.oozmakappa.monsterclash.model.immutable.MutableHistory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Chenglong Ma
 */
public class CommandManager {
    private static final Logger LOG = LogManager.getLogger();
    private final History history;
    private static CommandManager commandManager;

    /**
     * private for singleton pattern
     */
    private CommandManager() {
        history = new MutableHistory();
    }

    public static synchronized CommandManager getInstance() {
        if (commandManager == null) {
            commandManager = new CommandManager();
        }
        return commandManager;
    }

    public void storeAndExecute(Command cmd) {
        this.history.add(cmd);
        cmd.execute();
    }

    public void undoTurns(int number) {
        for (int i = 0; i < number; i++) {
            undoTurn();
        }
    }

    private void undoTurn() {
        if (history.size() == 0) {
            LOG.info("No historical commands");
            return;
        }
        int turnChangeCounter = 0;
        // A list of commands to undo
        Queue cmdList = new LinkedList<Command>();
        boolean turnStartFound = false;
        while (!turnStartFound) {
            if (history.size() == 0 || turnChangeCounter == 3) {
                turnStartFound = true;
            } else if (history.peekLast() instanceof TurnChangeCommand) {
                if (++turnChangeCounter < 3) {
                    Command lastCmd = history.removeLast();
                    cmdList.add(lastCmd);
                }
            } else {
                Command lastCmd = history.removeLast();
                cmdList.add(lastCmd);
            }
        }
        
        for (Object object : cmdList) {
            Command cmd = (Command) object;
            cmd.undo();
        }
    }

}
