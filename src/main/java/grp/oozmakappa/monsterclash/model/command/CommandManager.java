package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.immutable.History;
import grp.oozmakappa.monsterclash.model.immutable.ImmutableHistory;
import grp.oozmakappa.monsterclash.model.immutable.MutableHistory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Chenglong Ma
 */
public class CommandManager {
    private static final Logger LOG = LogManager.getLogger();
    private static CommandManager commandManager;
    private final History history;
    private final Deque<ImmutableHistory> universes; // Stores multiple universes caused by time travel or undo

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

    public void storeAndRedo(Command cmd) {
        this.history.add(cmd);
        cmd.redo();
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
    public Deque<ImmutableHistory> getUniverses() {
        Deque<ImmutableHistory> universesCopy = new ArrayDeque<>(universes);
        universesCopy.addLast(history.getLatestVersion());
        return universesCopy;
    }

    public void timeTravel(int historyVersionNum, Command destCommand) {
        Constraints constraints = Constraints.getInstance();
        boolean enabled = constraints.isEnableObstacle();
        constraints.enableObstacle(false);
        saveUniverse();
        undoAll();
        LinkedList<Command> targetHistory = history.getVersion(historyVersionNum).getHistory();
        for (Command cmd : targetHistory) {
            storeAndRedo(cmd);
            if (cmd == destCommand) {
                return;
            }
        }
        constraints.enableObstacle(enabled);
    }

    private void undoAll() {
        int size = history.size();
        for (int i = 0; i < size; i++) {
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
        LinkedList<Command> cmdList = new LinkedList<>();
        boolean turnStartFound = false;
        while (!turnStartFound) {
            // the first `TurnChangeCommand` and `DiceCommand` cannot be undone.
            if (history.size() == 2 || turnChangeCounter == 3) {
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
        universes.addLast(history.getLatestVersion());
    }

}
