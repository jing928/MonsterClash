package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PowerChangeCommand implements Command {
    private final Piece piece;
    private final double powerChange;
    private final double prevPower;

    public PowerChangeCommand(Piece piece, double powerChange) {
        this.piece = piece;
        this.powerChange = powerChange;
        this.prevPower = piece.getOriginalAttackPower();
    }

    @Override
    public void execute() {
        double newPower = prevPower + powerChange;
        newPower = newPower < 0 ? 0 : newPower;
        piece.setAttackPower(newPower);
        Logger log = LogManager.getLogger();
        log.info("Executed: Power Change Command");
    }

    @Override
    public void undo() {
        piece.setShouldNotify(false);
        piece.setAttackPower(prevPower);
        piece.setShouldNotify(true);
        Logger log = LogManager.getLogger();
        log.info("Undid: Power Change Command");
    }
}
