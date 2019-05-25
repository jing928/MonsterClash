package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

public class HealthChangeCommand extends AbstractCommand {
    private final Piece piece;
    private final double healthChange;
    private final double prevHealth;

    private HealthChangeCommand(Piece piece, double healthChange) {
        super("Health Changed - " + piece.getName());
        this.piece = piece;
        this.healthChange = healthChange;
        this.prevHealth = piece.getHealth();
    }

    public static void setHealth(Piece piece, double deltaHealth) {
        CommandManager manager = CommandManager.getInstance();
        manager.storeAndExecute(new HealthChangeCommand(piece, deltaHealth));
    }

    @Override
    public void execute() {
        if (healthChange < 0) {
            piece.decreaseHealth(-healthChange);
        } else {
            piece.increaseHealth(healthChange);
        }
        LOG.info("Executed: Health Change Command");
    }

    @Override
    public void undo() {
        piece.setHealth(prevHealth);
        LOG.info("Undid: Health Change Command");
    }
}
