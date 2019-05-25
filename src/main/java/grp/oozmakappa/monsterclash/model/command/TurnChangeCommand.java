package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.Constraints;
import grp.oozmakappa.monsterclash.model.Dice;
import grp.oozmakappa.monsterclash.model.Team;

public class TurnChangeCommand extends AbstractCommand {
    private final Constraints constraints;
    private final Team prevTeam;

    private TurnChangeCommand(Constraints constraints) {
        super("Turn Changed from " + constraints.getCurrentTeam());
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

        LOG.info("Executed: Turn Change Command");
        LOG.info("Current turn: " + constraints.getCurrentTeam());
    }

    @Override
    public void undo() {
        constraints.setCurrentTeam(prevTeam);
        constraints.setCanMove(false);
        Dice.getInstance().setCanRoll(true);
        constraints.setActivePiece(null);

        LOG.info("Undid: Turn Change Command");
        LOG.info("Current turn: " + constraints.getCurrentTeam());
    }

}
