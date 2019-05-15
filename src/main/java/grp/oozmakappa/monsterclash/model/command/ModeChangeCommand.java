package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.strategies.modes.Mode;

public class ModeChangeCommand implements Command {
    private final Piece piece;
    private final Mode newMode;
    private final Mode prevMode;

    public ModeChangeCommand(Piece piece, Mode newMode) {
        this.piece = piece;
        this.newMode = newMode;
        this.prevMode = piece.getCurrMode();
    }

    @Override
    public void execute() {
        piece.setMode(newMode);
    }

    @Override
    public void undo() {
        piece.setMode(prevMode);
    }

}
