package grp.oozmakappa.monsterclash.controller.states;

import grp.oozmakappa.monsterclash.controller.PieceListener;

/**
 * @author Chenglong Ma
 */
public class ModeExecutionState implements PieceButtonState {
    private static ModeExecutionState instance;

    private ModeExecutionState() {
        // for singleton pattern
    }

    public static ModeExecutionState getInstance() {
        if (instance == null) {
            instance = new ModeExecutionState();
        }
        return instance;
    }


    @Override
    public void todo(PieceListener ctrl) {
        // TODO implement strategy pattern
    }

    @Override
    public void done(PieceListener ctrl) {
        ctrl.setState(RollingState.getInstance());
    }
}
