package grp.oozmakappa.monsterclash.controller.states;

import grp.oozmakappa.monsterclash.controller.PieceListener;

/**
 * @author Chenglong Ma
 */
public abstract class PieceButtonState {
    final PieceListener ctrl;

    PieceButtonState(PieceListener ctrl) {
        this.ctrl = ctrl;
    }

    public abstract void todo();

    public abstract void done();
}
