package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HealthChangeCommand implements Command {
    private final Piece piece;
    private final double healthChange;
    private final double prevHealth;

    private HealthChangeCommand(Piece piece, double healthChange) {
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
        Logger log = LogManager.getLogger();
        log.info("Executed: Health Change Command");
    }

    @Override
    public void undo() {
        piece.setHealth(prevHealth);
        Logger log = LogManager.getLogger();
        log.info("Undid: Health Change Command");
    }
}
