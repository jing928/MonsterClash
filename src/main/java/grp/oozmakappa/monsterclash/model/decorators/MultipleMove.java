package grp.oozmakappa.monsterclash.model.decorators;

import grp.oozmakappa.monsterclash.model.abstracts.Piece;

/**
 * @author Chenglong Ma
 */
public class MultipleMove extends AbstractDecorator implements MoveDecorator {
    private static final int FACTOR = 2;

    public MultipleMove(Piece piece) {
        super(piece);
    }

    @Override
    public void move() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
