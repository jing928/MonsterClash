package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.Dice;
import grp.oozmakappa.monsterclash.model.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TurnChangeCommand implements Command {
    private final Constraints constraints;
    private final Team prevTeam;

    private TurnChangeCommand(Constraints constraints) {
        this.constraints = constraints;
        this.prevTeam = constraints.getCurrentTeam();
    }

    public static void changeTurn() {
        CommandManager manager = CommandManager.getInstance();
        manager.storeAndExecute(new TurnChangeCommand(Constraints.getInstance()));
    }

    @Override
    public void execute() {
        constraints.changeTurn();
        Logger log = LogManager.getLogger();
        log.info("Executed: Turn Change Command");
        log.info("Current turn: " + constraints.getCurrentTeam());
    }

    @Override
    public void undo() {
        constraints.setCurrentTeam(prevTeam);
        constraints.setCanMove(false);
        Dice.getInstance().setCanRoll(true);
        Logger log = LogManager.getLogger();
        log.info("Undid: Turn Change Command");
        log.info("Current turn: " + constraints.getCurrentTeam());
    }

}
