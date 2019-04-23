package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public class AttackCommand implements Command {
    private final Piece me;
    private final Piece target;
    // TODO: add other required states.
    private final double myState;
    private final double targetState;

    public AttackCommand(Piece me, Piece target) {
        this.me = me;
        myState = me.getHealth();
        this.target = target;
        targetState = target.getHealth();
    }

    @Override
    public void execute() {
        me.attack(target);
    }

    @Override
    public void undo() {
        me.setHealth(myState);
        target.setHealth(targetState);
    }
}
