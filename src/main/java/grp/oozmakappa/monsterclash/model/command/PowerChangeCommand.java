package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

public class PowerChangeCommand implements Command {
    private final Piece piece;
    private final double powerChange;
    private final double prevPower;

    public PowerChangeCommand(Piece piece, double powerChange) {
        this.piece = piece;
        this.powerChange = powerChange;
        this.prevPower = piece.getOriginalAttackPower();
    }

    @Override
    public void execute() {
        double newPower = prevPower + powerChange;
        piece.setAttackPower(newPower);
    }

    @Override
    public void undo() {
        piece.setUndoing(true);
        piece.setAttackPower(prevPower);
        piece.setUndoing(false);
    }
}