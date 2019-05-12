package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

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
    }

    @Override
    public void undo() {
        piece.setShouldNotify(false);
        piece.setReachableRange(prevRange);
        piece.setShouldNotify(true);
    }
}
