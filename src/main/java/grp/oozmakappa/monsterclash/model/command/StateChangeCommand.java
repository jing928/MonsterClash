package grp.oozmakappa.monsterclash.model.command;

import grp.oozmakappa.monsterclash.controller.PieceListener;
import grp.oozmakappa.monsterclash.controller.states.PieceButtonState;

public class StateChangeCommand extends AbstractCommand {
    private final PieceListener pieceListener;
    private final PieceButtonState newState;
    private final PieceButtonState prevState;

    private StateChangeCommand(PieceListener pieceListener, PieceButtonState newState) {
        super("State Changed - " + newState.getClass().getSimpleName());
        this.pieceListener = pieceListener;
        this.newState = newState;
        this.prevState = pieceListener.getState();
    }

    public static void setState(PieceListener ctrl, PieceButtonState nextState) {
        CommandManager manager = CommandManager.getInstance();
        manager.storeAndExecute(new StateChangeCommand(ctrl, nextState));
    }

    @Override
    public void execute() {
        pieceListener.setState(newState);

        LOG.info("Executed: State Change Command");
    }

    @Override
    public void undo() {
        pieceListener.undoState(prevState);

        LOG.info("Undid: State Change Command");
    }

}
