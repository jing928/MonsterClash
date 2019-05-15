package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        Logger log = LogManager.getLogger();
        log.info("Executed: Health Change Command");
    }

    @Override
    public void undo() {
        piece.setShouldNotify(false);
        piece.setHealth(prevHealth);
        piece.setShouldNotify(true);
        Logger log = LogManager.getLogger();
        log.info("Undid: Health Change Command");
    }
}
