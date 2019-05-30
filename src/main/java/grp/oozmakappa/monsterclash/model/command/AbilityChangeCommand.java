package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.Ability;
import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public class AbilityChangeCommand extends AbstractCommand {
    private final Piece piece;
    private final Ability prevAbility, nextAbility;

    private AbilityChangeCommand(Piece piece, Ability nextAbility) {
        super(String.format("Ability Changed to %s - %s", nextAbility, piece.getName()));
        this.piece = piece;
        prevAbility = piece.getCurrAbility();
        this.nextAbility = nextAbility;
    }

    public static void setAbility(Piece piece, Ability nextAbility) {
        CommandManager manager = CommandManager.getInstance();
        manager.storeAndExecute(new AbilityChangeCommand(piece, nextAbility));
    }

    @Override
    public void execute() {
        piece.setCurrentAbility(nextAbility);
        LOG.info("Executed: Ability Command");
    }

    @Override
    public void undo() {
        piece.resetCurrentAbility(prevAbility);
        LOG.info("Undid: Ability Command");
    }

    @Override
    public void redo() {
        piece.resetCurrentAbility(prevAbility);
    }
}
