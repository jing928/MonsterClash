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
        if (healthChange < 0) {
            piece.decreaseHealth(-healthChange);
        } else {
            piece.increaseHealth(healthChange);
        }
    }

    @Override
    public void undo() {
        piece.setHealth(prevHealth);
    }
}
