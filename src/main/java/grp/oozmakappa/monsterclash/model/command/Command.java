package grp.oozmakappa.monsterclash.model.command;

/**
 * @author Chenglong Ma
 */
public interface Command {
    void execute();

    void undo();
}
