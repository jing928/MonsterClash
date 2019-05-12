package grp.oozmakappa.monsterclash.model.strategies.abilities;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public class FartherMove extends AbstractSpecialAbility implements MoveStrategy {
    private static final int FACTOR = 2;

    public FartherMove(Piece piece) {
        super(piece);
    }

    @Override
    public void move() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
