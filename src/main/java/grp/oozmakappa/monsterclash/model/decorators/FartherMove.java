package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public class FartherMove extends AbstractDecorator implements MoveDecorator {
    private static final int FACTOR = 2;

    public FartherMove(Piece piece) {
        super(piece);
    }

    @Override
    public void move() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
