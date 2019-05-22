package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.immutable.History;
import grp.oozmakappa.monsterclash.model.immutable.ImmutableHistory;
import grp.oozmakappa.monsterclash.model.immutable.MutableHistory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Chenglong Ma
 */
public class CommandManager {
    private static final Logger LOG = LogManager.getLogger();
    private final History history;
    private final Deque<ImmutableHistory> universes; // Stores multiple universes caused by time travel or undo
    private static CommandManager commandManager;

    /**
     * private for singleton pattern
     */
    private CommandManager() {
        history = new MutableHistory();
        universes = new ArrayDeque<>();
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
        saveUniverse();
        for (int i = 0; i < number; i++) {
            undoTurn();
        }
    }

    /**
     * Create a copy of the universes with the current history as the `universes` stack should be
     * updated only when time travel or undo happens.
     *
     * @return a copy of universes plus the most recent history version.
     */
    public Deque<ImmutableHistory> viewUniverses() {
        Deque<ImmutableHistory> universesCopy = new ArrayDeque<>(universes);
        universesCopy.push(history.getLatestVersion());
        return universesCopy;
    }

    public void timeTravel(int historyVersionNum, Command destCommand) {
        saveUniverse();
        undoAll();
        LinkedList<Command> targetHistory = history.getVersion(historyVersionNum).getHistory();
        for (Command cmd : targetHistory) {
            storeAndExecute(cmd);
            if (cmd == destCommand) {
                return;
            }
        }
    }

    private void undoAll() {
        for (int i = 0; i < history.size(); i++) {
            history.removeLast().undo();
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

    private void saveUniverse() {
        universes.push(history.getLatestVersion());
    }

}
