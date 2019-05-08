package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;
import grp.oozmakappa.monsterclash.model.interfaces.CellEffect;

/**
 * @author Chenglong Ma
 */
public class PowerBuff extends BuffDecorator {
    private static final double MAX_GAIN = 10;
    private final double powerGained;

    protected PowerBuff(CellEffect toDecorated) {
        super(toDecorated);
        powerGained = MAX_GAIN * Math.random();
    }

    @Override
    public void affect(Piece piece) {
        double power = piece.getAttackPower();
        power += powerGained;
        piece.setAttackPower(power);
        String msg = String.format("You gained %.2f attack power", powerGained);
        showMessage(msg);
        super.affect(piece);
    }
}