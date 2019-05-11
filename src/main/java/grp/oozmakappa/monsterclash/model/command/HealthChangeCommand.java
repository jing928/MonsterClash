package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

public class HealthChangeCommand implements Command {
    private final Piece piece;
    private final double healthChange;
    private final double prevHealth;

    public HealthChangeCommand(Piece piece, double healthChange) {
        this.piece = piece;
        this.healthChange = healthChange;
        this.prevHealth = piece.getHealth();
    }

    @Override
    public void execute() {
        double newHealth = prevHealth + healthChange;
        piece.setHealth(newHealth);
    }

    @Override
    public void undo() {
        piece.setUndoing(true);
        piece.setHealth(prevHealth);
        piece.setUndoing(false);
    }
}
