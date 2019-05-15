package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RangeChangeCommand implements Command {
    private final Piece piece;
    private final int rangeChange;
    private final int prevRange;

    public RangeChangeCommand(Piece piece, int rangeChange) {
        this.piece = piece;
        this.rangeChange = rangeChange;
        this.prevRange = piece.getOriginalRange();
    }

    @Override
    public void execute() {
        int newRange = prevRange + rangeChange;
        newRange = newRange < 0 ? 0 : newRange;
        piece.setReachableRange(newRange);
        Logger log = LogManager.getLogger();
        log.info("Executed: Range Change Command");
    }

    @Override
    public void undo() {
        piece.setShouldNotify(false);
        piece.setReachableRange(prevRange);
        piece.setShouldNotify(true);
        Logger log = LogManager.getLogger();
        log.info("Undid: Range Change Command");
    }
}
