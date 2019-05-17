package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.strategies.modes.Mode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModeChangeCommand implements Command {
    private final Piece piece;
    private final Mode newMode;

    private ModeChangeCommand(Piece piece, Mode newMode) {
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
        Logger log = LogManager.getLogger();
        log.info("Executed: Mode Change Command");
    }

    @Override
    public void undo() {
        piece.setMode();
        Logger log = LogManager.getLogger();
        log.info("Undid: Mode Change Command");
    }

}
