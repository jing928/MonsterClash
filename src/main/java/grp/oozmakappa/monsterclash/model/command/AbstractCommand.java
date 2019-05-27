package grp.oozmakappa.monsterclash.model.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Chenglong Ma
 */
public abstract class AbstractCommand implements Command {
    protected static final Logger LOG = LogManager.getLogger();
    private final String name;

    protected AbstractCommand(String name) {
        this.name = name;
    }

    @Override
    public void redo() {
        execute();
    }

    @Override
    public String toString() {
        return name;
    }
}
