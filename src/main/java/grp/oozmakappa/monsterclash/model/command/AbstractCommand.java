package grp.oozmakappa.monsterclash.model.command;

/**
 * @author Chenglong Ma
 */
public abstract class AbstractCommand implements Command {
    private final String name;

    protected AbstractCommand(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
