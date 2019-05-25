package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.strategies.modes.Mode;

public class ModeChangeCommand extends AbstractCommand {
    private final Piece piece;
    private final Mode newMode;

    private ModeChangeCommand(Piece piece, Mode newMode) {
        super("Mode Changed - " + piece.getName());
        this.piece = piece;
        this.newMode = newMode;
    }

    public static void setMode(Piece piece, Mode mode) {
        CommandManager manager = CommandManager.getInstance();
        manager.storeAndExecute(new ModeChangeCommand(piece, mode));
    }

    @Override
    public void execute() {
        piece.setMode(newMode);
        LOG.info("Executed: Mode Change Command");
    }

    @Override
    public void undo() {
        piece.setMode();
        LOG.info("Undid: Mode Change Command");
    }

}
