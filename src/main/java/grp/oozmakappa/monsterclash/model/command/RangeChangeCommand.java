package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

public class RangeChangeCommand extends AbstractCommand {
    private final Piece piece;
    private final int rangeChange;
    private final int prevRange;

    private RangeChangeCommand(Piece piece, int rangeChange) {
        super("Range Changed - " + piece.getName());
        this.piece = piece;
        this.rangeChange = rangeChange;
        this.prevRange = piece.getOriginalRange();
    }

    public static void setRange(Piece piece, int deltaRange) {
        CommandManager manager = CommandManager.getInstance();
        manager.storeAndExecute(new RangeChangeCommand(piece, deltaRange));
    }

    @Override
    public void execute() {
        int newRange = prevRange + rangeChange;
        newRange = newRange < 0 ? 0 : newRange;
        piece.setReachableRange(newRange);

        LOG.info("Executed: Range Change Command");
    }

    @Override
    public void undo() {
        piece.setReachableRange(prevRange);

        LOG.info("Undid: Range Change Command");
    }
}
