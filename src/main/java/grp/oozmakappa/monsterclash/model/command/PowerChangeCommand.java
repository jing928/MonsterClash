package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PowerChangeCommand implements Command {
    private final Piece piece;
    private final double powerChange;
    private final double prevPower;

    private PowerChangeCommand(Piece piece, double powerChange) {
        this.piece = piece;
        this.powerChange = powerChange;
        this.prevPower = piece.getOriginalAttackPower();
    }

    public static void setPower(Piece piece, double deltaPower) {
        CommandManager manager = CommandManager.getInstance();
        manager.storeAndExecute(new PowerChangeCommand(piece, deltaPower));
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
        piece.setAttackPower(prevPower);
        Logger log = LogManager.getLogger();
        log.info("Undid: Power Change Command");
    }
}
