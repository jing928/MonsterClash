package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public class AttackCommand implements Command {
    private final Piece me;
    private final Piece target;
    private final double targetPrevHealth;

    public AttackCommand(Piece me, Piece target) {
        this.me = me;
        this.target = target;
        targetPrevHealth = target.getHealth();
    }

    @Override
    public void execute() {
        me.attack(target);
    }

    @Override
    public void undo() {
        target.setShouldNotify(false);
        target.setHealth(targetPrevHealth);
        target.setShouldNotify(true);
    }
}
